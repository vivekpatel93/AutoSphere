package com.vivek.service.impl;

import com.vivek.dto.CarRequestDTO;
import com.vivek.dto.CarResponseDTO;
import com.vivek.dto.OwnerDTO;
import com.vivek.dto.PurchaseResponseDTO;
import com.vivek.entity.Car;
import com.vivek.entity.CarUser;
import com.vivek.entity.Purchase;
import com.vivek.exception.ResourceNotFoundException;
import com.vivek.repository.CarRepository;
import com.vivek.repository.CarUserRepository;
import com.vivek.repository.PurchaseRepository;
import com.vivek.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class CarServiceImplementation implements CarService {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CarUserRepository carUserRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;
    // convert Entity to Response DTO
    private CarResponseDTO mapToResponseDTO(Car car){
        CarResponseDTO dto=new CarResponseDTO();

        dto.setVinNumber(car.getVinNumber());
        dto.setCarType(car.getCarType());
        dto.setCompanyName(car.getCompanyName());
        dto.setModel(car.getModel());
        dto.setColor(car.getColor());
        dto.setMileage(car.getMileage());
        dto.setSeatCapacity(car.getSeatCapacity());
        dto.setPrice(car.getPrice());
        if (car.getOwner() != null) {
            OwnerDTO ownerDTO = new OwnerDTO();
            ownerDTO.setUserId(car.getOwner().getUserId());
            dto.setOwner(ownerDTO);
        }
        return dto;
    }

    // convert RequestDTO to Entity
    private Car mapToEntity(CarRequestDTO dto){
        Car car=new Car();
        car.setVinNumber(dto.getVinNumber());
        car.setCarType(dto.getCarType());
        car.setCompanyName(dto.getCompanyName());
        car.setModel(dto.getModel());
        car.setColor(dto.getColor());
        car.setMileage(dto.getMileage());
        car.setSeatCapacity(dto.getSeatCapacity());
        car.setPrice(dto.getPrice());
        return car;
    }

    private Page<CarResponseDTO> mapToResponseDTO(Page<Car> cars) {
        return cars.map(this::mapToResponseDTO);
    }

    @Override
    public CarResponseDTO save(CarRequestDTO dto){
        Car car=mapToEntity(dto);
        return mapToResponseDTO(carRepository.save(car));
    }

    @Override
    public CarResponseDTO update(String vinNumber,CarRequestDTO dto){

        Car c=carRepository.findById(vinNumber)
                .orElseThrow(()->new ResourceNotFoundException("Car with given vINumber not found!, Sorry unable to update."));
        c.setCompanyName(dto.getCompanyName());
        c.setCarType(dto.getCarType());
        c.setModel(dto.getModel());
        c.setColor(dto.getColor());
        c.setPrice(dto.getPrice());
        c.setSeatCapacity(dto.getSeatCapacity());
        c.setMileage(dto.getMileage());

        // --- update relation (IMPORTANT) ---
        if (dto.getOwner() != null && dto.getOwner().getUserId() != null) {

            CarUser user = carUserRepository.findById(dto.getOwner().getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));

            c.setOwner(user);
        }
        Car car=carRepository.save(c);
        return mapToResponseDTO(car);
    }
    @Override
    public CarResponseDTO findByVINumber(String vinNumber){
        return mapToResponseDTO(carRepository.findById(vinNumber).orElseThrow(() -> new ResourceNotFoundException("Car not found")));
    }

    @Override
    public Page<CarResponseDTO> findAllCar(Pageable pageable){
        return mapToResponseDTO(carRepository.findAll(pageable));
    }

    @Override
    public List<CarResponseDTO> findByCompanyName(String companyName){
        List<CarResponseDTO> carDTO = carRepository.findByCompanyName(companyName)
                .stream()
                .map(this::mapToResponseDTO)
                .toList();

        if (carDTO.isEmpty()) {
            throw new ResourceNotFoundException(
                    "Car not found with given companyName: " + companyName);
        }

        return carDTO;
    }

    @Override
    public List<CarResponseDTO> findAllByModel(String model){

        return carRepository.findByModelName(model)
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    @Override
    public List<CarResponseDTO> findByMileageAbove20(float mileage){

        return carRepository.findByMileage(mileage)
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    @Override
    public List<CarResponseDTO> findByColor(String color){
        return carRepository.findByColor(color)
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    @Override
    public void delete(String vinNumber){
        Car car=carRepository.findById(vinNumber).orElseThrow(()-> new ResourceNotFoundException("Deletion can't possible."));
        carRepository.delete(car);
    }
    @Override
    public PurchaseResponseDTO purchaseCar(String vin){
        String email= SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        CarUser user=carUserRepository.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException("User not found."));

        Car car=carRepository.findById(vin)
                .orElseThrow(()->new ResourceNotFoundException("Car not found with this id."));

        if(car.getOwner() != null){
            throw new RuntimeException("Car already sold.");
        }
        // If car not sold then assign to new owner
        car.setOwner(user);
        //save
        carRepository.save(car);
        // save purchase record
        Purchase purchase = new Purchase();
        purchase.setBuyer(user);
        purchase.setCar(car);
        purchase.setAmount(car.getPrice());
        purchase.setPurchaseDate(LocalDateTime.now());

        purchaseRepository.save(purchase);

        return new PurchaseResponseDTO(
                "Purchase successful",user.getName(),car.getModel(),car.getVinNumber(),car.getPrice(),purchase.getPurchaseDate()
        );
    }
}
