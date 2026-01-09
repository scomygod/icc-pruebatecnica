package ec.edu.ups.icc.pruebatecnica.vehicles.service;

import java.util.List;

import ec.edu.ups.icc.pruebatecnica.vehicles.dto.OperationResponseDto;
import ec.edu.ups.icc.pruebatecnica.vehicles.dto.VehicleResponseDto;
import ec.edu.ups.icc.pruebatecnica.vehicles.dto.VehicleStockRequestDto;

public interface VehicleService {

    List<VehicleResponseDto> getAllActive();

    List<VehicleResponseDto> getLowStockExpensive();

    OperationResponseDto deleteByModel(String model);

    VehicleResponseDto updateStock(VehicleStockRequestDto request);
}
