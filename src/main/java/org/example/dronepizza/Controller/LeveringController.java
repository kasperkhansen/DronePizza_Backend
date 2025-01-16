package org.example.dronepizza.Controller;

import jakarta.persistence.Id;
import org.example.dronepizza.Model.Levering;
import org.example.dronepizza.Service.LeveringService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
public class LeveringController {

    private final LeveringService leveringService;

    public LeveringController(LeveringService leveringService) {
        this.leveringService = leveringService;
    }

    @GetMapping
    public List<Levering> getAlleIkkeLeveret() {
        List<Levering> leveringList = leveringService.getAllIkkeLeveret();
        return leveringList;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addLevering(@RequestParam Long pizzaId, @RequestParam String adresse) {
        return leveringService.addLevering(pizzaId, adresse);
    }

    @GetMapping("/queue")
    public List<Levering> getLeveringerUdenDrone() {
        return leveringService.getLeveringerUdenDrone();
    }

    @PostMapping("/{id}/plan")
    public ResponseEntity<String> planlægLevering(@PathVariable Long id, @RequestParam("droneId") Long droneId) {
        return leveringService.planlægLevering(id, droneId);
    }

    @PostMapping("/{id}/finish")
    public ResponseEntity<String> leveringFuldført(@PathVariable Long id) {
        return leveringService.færddiggørLevering(id);
    }
}
