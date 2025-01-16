package org.example.dronepizza.Controller;

import org.example.dronepizza.Model.Drone;
import org.example.dronepizza.Repository.DroneRepository;
import org.example.dronepizza.Repository.StationRepository;
import org.example.dronepizza.Service.DroneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drones")
public class DroneController {

    private final DroneService droneService;

    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    @GetMapping
    public List<Drone> getAllDrones() {
        return droneService.getAllDroner();
    }

    @PostMapping("/add")
    public ResponseEntity<String> addDrone() {
        return droneService.addDrone();
    }

    @PostMapping("/{id}/enable")
    public ResponseEntity<String> enableDrone(@PathVariable Long id) {
        return droneService.updateDroneStatus(id, "i drift");
    }

    @PostMapping("/{id}/disable")
    public ResponseEntity<String> disableDrone(@PathVariable Long id) {
        return droneService.updateDroneStatus(id, "ude af drift");
    }

    @PostMapping("/{id}/retire")
    public ResponseEntity<String> retireDrone(@PathVariable Long id) {
        return droneService.updateDroneStatus(id, "udfaset");
    }
}
