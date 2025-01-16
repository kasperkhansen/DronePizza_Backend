package org.example.dronepizza.Service;

import org.example.dronepizza.Model.Drone;
import org.example.dronepizza.Model.Levering;
import org.example.dronepizza.Model.Pizza;
import org.example.dronepizza.Repository.DroneRepository;
import org.example.dronepizza.Repository.LeveringRepository;
import org.example.dronepizza.Repository.PizzaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LeveringService {

    private final LeveringRepository leveringRepository;
    private final PizzaRepository pizzaRepository;
    private final DroneRepository droneRepository;

    public LeveringService(LeveringRepository leveringRepository, PizzaRepository pizzaRepository, DroneRepository droneRepository) {
        this.leveringRepository = leveringRepository;
        this.pizzaRepository = pizzaRepository;
        this.droneRepository = droneRepository;
    }

    public List<Levering> getAllIkkeLeveret(){
        return leveringRepository.findAll();
    }

    public ResponseEntity<String> addLevering(Long pizzaId, String adresse){
        Optional<Pizza> pizzaOptional = pizzaRepository.findById(pizzaId);
        if (pizzaOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pizza blev ikke fundet.");
        }

        Levering nyLevering = new Levering(adresse, LocalDateTime.now().plusMinutes(30), pizzaOptional.get(), null);
        leveringRepository.save(nyLevering);

        return ResponseEntity.status(HttpStatus.CREATED).body("Levering tilføjet.");
    }

    public List<Levering> getLeveringerUdenDrone(){
        return leveringRepository.findAll().stream()
                .filter(levering -> levering.getDrone() == null)
                .toList();
    }

    public ResponseEntity<String> planlægLevering(Long id, Long droneId) {
        Optional<Levering> leveringOptional = leveringRepository.findById(id);
        if (leveringOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Levering blev ikke fundet.");
        }

        Levering levering = leveringOptional.get();
        if (levering.getDrone() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Levering er allerede planlagt.");
        }

        if (droneId != null){
            Optional<Drone> droneOptional = droneRepository.findById(droneId);
            if(droneOptional.isEmpty() || !"i drift".equals(droneOptional.get().getStatus())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Drone blev ikke fundet eller er ikke i drift.");
            }
            levering.setDrone(droneOptional.get());
        }

        leveringRepository.save(levering);
        return ResponseEntity.ok("Levering planlagt.");
    }

    public ResponseEntity<String> færddiggørLevering(Long id){
        Optional<Levering> leveringOptional = leveringRepository.findById(id);
        if (leveringOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Levering blev ikke fundet.");
        }

        Levering levering = leveringOptional.get();
        if (levering.getDrone() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Levering er ikke planlagt.");
        }

        levering.setLeveretTidspunkt(LocalDateTime.now());
        leveringRepository.save(levering);
        return ResponseEntity.ok("Levering færdiggjort.");
    }
}
