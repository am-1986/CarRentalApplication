package com.upgrad.hirewheels.controller;

import com.upgrad.hirewheels.dto.VehicleDTO;
import com.upgrad.hirewheels.entities.Vehicle;
import com.upgrad.hirewheels.services.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/hirewheels/v1")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping(value = "/vehicles", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VehicleDTO>> getVehicle() {
        List<Vehicle> vehicleList = vehicleService.getAllVehicles();
        List<VehicleDTO> vehicleDTOList = new ArrayList<>();
        for(Vehicle vehicle : vehicleList) {
            vehicleDTOList.add(modelMapper.map(vehicle, VehicleDTO.class));
        }

        return new ResponseEntity<>(vehicleDTOList, HttpStatus.OK);
    }
}
