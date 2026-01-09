package ec.edu.ups.icc.pruebatecnica.vehicles.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.ups.icc.pruebatecnica.vehicles.dto.OperationResponseDto;
import ec.edu.ups.icc.pruebatecnica.vehicles.dto.VehicleResponseDto;
import ec.edu.ups.icc.pruebatecnica.vehicles.dto.VehicleStockRequestDto;
import ec.edu.ups.icc.pruebatecnica.vehicles.service.VehicleService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService service;

    public VehicleController(VehicleService service) {
        this.service = service;
    }

    @GetMapping
    public List<VehicleResponseDto> getAll() {
        return service.getAllActive();
    }

    @GetMapping("/low-stock-expensive")
    public List<VehicleResponseDto> getLowStockExpensive() {
        return service.getLowStockExpensive();
    }

    @PatchMapping("/delete/{model}")
    public OperationResponseDto delete(@PathVariable String model) {
        return service.deleteByModel(model);
    }

    @PatchMapping("/stock")
    public VehicleResponseDto updateStock(@Valid @RequestBody VehicleStockRequestDto request) {
        return service.updateStock(request);
    }
}