package org.example.dronepizza.Config;

import org.example.dronepizza.Model.Pizza;
import org.example.dronepizza.Model.Station;
import org.example.dronepizza.Repository.PizzaRepository;
import org.example.dronepizza.Repository.StationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitData implements CommandLineRunner {

    private final StationRepository stationRepository;
    private final PizzaRepository pizzaRepository;

    public InitData(StationRepository stationRepository, PizzaRepository pizzaRepository) {
        this.stationRepository = stationRepository;
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //laver Stationer
        stationRepository.save(new Station(55.44, 12.34));
        stationRepository.save(new Station(55.43, 12.35));
        stationRepository.save(new Station(55.41, 12.33));

        //laver Pizzaer
        pizzaRepository.save(new Pizza("Vesuvio", 57));
        pizzaRepository.save(new Pizza("Hawaii", 62));
        pizzaRepository.save(new Pizza("Pepperoni", 53));
        pizzaRepository.save(new Pizza("Carbona", 63));
        pizzaRepository.save(new Pizza("Pollo", 57));
    }
}
