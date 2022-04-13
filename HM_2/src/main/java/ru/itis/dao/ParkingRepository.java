package ru.itis.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.Parking;

import java.io.StringReader;
import java.util.Optional;

public interface ParkingRepository extends JpaRepository<Parking, Long> {
    Integer getSlotsCountByAddress(String address);
    Optional<Parking> findByAddress(String address);
}
