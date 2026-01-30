package com.allcity.repositories;

import com.allcity.entities.VehicleSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VehicleSaleRepository extends JpaRepository<VehicleSale, Long> {
    List<VehicleSale> findByDate(LocalDate date);
    List<VehicleSale> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
