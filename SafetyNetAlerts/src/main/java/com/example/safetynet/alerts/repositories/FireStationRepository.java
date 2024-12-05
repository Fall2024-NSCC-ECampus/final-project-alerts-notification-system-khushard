package com.example.safetynet.alerts.repositories;

import com.example.safetynet.alerts.model.FireStation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FireStationRepository extends JpaRepository<FireStation, Integer> {
    List<FireStation> findByStation(int station);
}
