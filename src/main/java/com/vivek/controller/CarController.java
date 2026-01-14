package com.vivek.controller;

import com.vivek.dto.CarRequestDTO;
import com.vivek.dto.CarResponseDTO;
import com.vivek.dto.PurchaseResponseDTO;
import com.vivek.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {
    @Autowired
    private CarService carService;

    @PostMapping("/car/save")
    public ResponseEntity<CarResponseDTO> registerCar(@RequestBody CarRequestDTO dto){
        return ResponseEntity.ok(carService.save(dto));
    }

    @PutMapping("/car/update")
    public ResponseEntity<CarResponseDTO> updateCar(@RequestParam String vinNumber, @RequestBody CarRequestDTO dto){
        return ResponseEntity.ok(carService.update(vinNumber, dto));
    }

    @GetMapping("/car/findById")
    public ResponseEntity<CarResponseDTO> findById(@RequestParam String vinNumber){
        return ResponseEntity.ok(carService.findByVINumber(vinNumber));
    }
    @GetMapping("/car/findAll")
    public ResponseEntity<Page<CarResponseDTO>> findAllCar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue ="4") int size){
        return ResponseEntity.ok(carService.findAllCar(PageRequest.of(page,size)));
    }

    @GetMapping("/car/findCompany")
    public ResponseEntity<List<CarResponseDTO>> findCompany(@RequestParam String companyName){
        return ResponseEntity.ok(carService.findByCompanyName(companyName));
    }

    @GetMapping("/car/findAllByModel")
    public ResponseEntity<List<CarResponseDTO>>  findAllByModel(@RequestParam String model){
        return ResponseEntity.ok(carService.findAllByModel(model));
    }

    @GetMapping("/car/findByMilageabove20")
    public ResponseEntity<List<CarResponseDTO>> findByMilageabove20(@RequestParam float milage){
        return ResponseEntity.ok(carService.findByMileageAbove20(milage));
    }

    @GetMapping("/car/findByColor")
    public ResponseEntity<List<CarResponseDTO>> findByColor(@RequestParam String color){
        return ResponseEntity.ok(carService.findByColor(color));
    }

    @DeleteMapping("/car/delete")
    public ResponseEntity<Void> delete(@RequestParam String vinNumber){
        carService.delete(vinNumber);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/car/purchase")
    public PurchaseResponseDTO purchase(@RequestParam String vin){
        return carService.purchaseCar(vin);
    }
}
