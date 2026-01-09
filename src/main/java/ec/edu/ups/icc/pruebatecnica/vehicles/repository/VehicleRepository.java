package ec.edu.ups.icc.pruebatecnica.vehicles.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.edu.ups.icc.pruebatecnica.vehicles.entity.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findByDeleted(String deleted);

    List<Vehicle> findByDeletedAndPriceGreaterThanAndStockLessThan(
            String deleted, Double price, Integer stock);

    Optional<Vehicle> findByModel(String model);
}