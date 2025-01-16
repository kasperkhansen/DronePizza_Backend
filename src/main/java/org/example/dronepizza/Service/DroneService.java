package org.example.dronepizza.Service;

import org.example.dronepizza.Model.Drone;
import org.example.dronepizza.Model.Station;
import org.example.dronepizza.Repository.DroneRepository;
import org.example.dronepizza.Repository.StationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DroneService {

    private final DroneRepository droneRepository;
    private final StationRepository stationRepository;

    public DroneService(DroneRepository droneRepository, StationRepository stationRepository) {
        this.droneRepository = droneRepository;
        this.stationRepository = stationRepository;
    }

    public List<Drone> getAllDroner() {
        return droneRepository.findAll();
    }

    public ResponseEntity<String> addDrone() {
        List<Station> stations = stationRepository.findAll();
        if (stations.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No stations available.");
        }

        Station stationWithFewestDrones = stations.stream()
                .min(Comparator.comparingInt(s -> s.getDroner().size()))
                .orElseThrow();

        Drone newDrone = new Drone(UUID.randomUUID().toString(), "i drift", stationWithFewestDrones);
        droneRepository.save(newDrone);

        return ResponseEntity.status(HttpStatus.CREATED).body("Drone tilføjet.");
    }

    public ResponseEntity<String> updateDroneStatus(Long id, String status) {
        Optional<Drone> droneOptional = droneRepository.findById(id);
        if (droneOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Drone blev ikke fundet.");
        }

        droneOptional.get().setStatus(status);
        droneRepository.save(droneOptional.get());
        return ResponseEntity.ok("Drone status updateret til " + status + ".");
    }
}
