package ec.edu.ups.icc.pruebatecnica.vehicles.mapper;

import org.springframework.stereotype.Component;

import ec.edu.ups.icc.pruebatecnica.vehicles.dto.VehicleResponseDto;
import ec.edu.ups.icc.pruebatecnica.vehicles.entity.Vehicle;

@Component
public class VehicleMapper {

    public VehicleResponseDto toDto(Vehicle vehicle) {
        
        VehicleResponseDto dto = new VehicleResponseDto();
        dto.setId(vehicle.getId());
        dto.setBrand(vehicle.getBrand());
        dto.setModel(vehicle.getModel());
        dto.setPrice(vehicle.getPrice());
        dto.setStock(vehicle.getStock());
        return dto;

    }
}