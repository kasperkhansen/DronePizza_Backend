package org.example.dronepizza.Repository;

import org.example.dronepizza.Model.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, Long> {
}
