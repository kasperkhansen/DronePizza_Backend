package org.example.dronepizza.Repository;

import org.example.dronepizza.Model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRepository extends JpaRepository<Drone, Long> {
}
