package com.allcity.repositories;

import com.allcity.entities.VehiclePurchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiclePurchaseRepository extends JpaRepository<VehiclePurchase, Long> {
}
