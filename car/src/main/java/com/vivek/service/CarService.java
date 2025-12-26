package com.vivek.service;

import com.vivek.dto.CarRequestDTO;
import com.vivek.dto.CarResponseDTO;
import com.vivek.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface CarService {
    CarResponseDTO save(CarRequestDTO dto);
    CarResponseDTO update(String vinNumber,CarRequestDTO dto);
    CarResponseDTO findByVINumber(String vinNumber);
    Page<CarResponseDTO> findAllCar(Pageable pageable);
    List<CarResponseDTO> findByCompanyName(String companyName);
    List<CarResponseDTO> findAllByModel(String model);
    List<CarResponseDTO> findByMileageAbove20(float mileage);
    List<CarResponseDTO> findByColor(String color);
    void delete(String vinNumber);
}
