package ec.edu.ups.icc.pruebatecnica.vehicles.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ec.edu.ups.icc.pruebatecnica.vehicles.dto.OperationResponseDto;
import ec.edu.ups.icc.pruebatecnica.vehicles.dto.VehicleResponseDto;
import ec.edu.ups.icc.pruebatecnica.vehicles.dto.VehicleStockRequestDto;
import ec.edu.ups.icc.pruebatecnica.vehicles.entity.Vehicle;
import ec.edu.ups.icc.pruebatecnica.vehicles.exceptions.ConflictException;
import ec.edu.ups.icc.pruebatecnica.vehicles.exceptions.NotFoundException;
import ec.edu.ups.icc.pruebatecnica.vehicles.mapper.VehicleMapper;
import ec.edu.ups.icc.pruebatecnica.vehicles.repository.VehicleRepository;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository repository;
    private final VehicleMapper mapper;

    public VehicleServiceImpl(VehicleRepository repository, VehicleMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<VehicleResponseDto> getAllActive() {
        return repository.findByDeleted("N")
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VehicleResponseDto> getLowStockExpensive() {
        return repository.findByDeletedAndPriceGreaterThanAndStockLessThan("N", 20000.0, 10)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public OperationResponseDto deleteByModel(String model) {
        Vehicle vehicle = repository.findByModel(model)
                .orElseThrow(() -> new NotFoundException("Vehicle not found"));

        if ("S".equals(vehicle.getDeleted())) {
            throw new ConflictException("Vehicle already deleted");
        }

        vehicle.setDeleted("S");
        repository.save(vehicle);
        return new OperationResponseDto("Vehicle deleted");
    }

    @Override
    public VehicleResponseDto updateStock(VehicleStockRequestDto request) {
        Vehicle vehicle = repository.findById(request.getId())
                .orElseThrow(() -> new NotFoundException("Vehicle not found"));

        if ("S".equals(vehicle.getDeleted())) {
            throw new NotFoundException("Vehicle not found");
        }

        vehicle.setStock(request.getStock());
        repository.save(vehicle);
        return mapper.toDto(vehicle);

    }
    
}