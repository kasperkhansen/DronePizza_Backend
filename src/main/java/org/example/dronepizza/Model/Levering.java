package org.example.dronepizza.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Levering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String adresse;
    private LocalDateTime forventetTidspunkt;
    private LocalDateTime leveretTidspunkt;

    @ManyToOne
    private Pizza pizza;

    @ManyToOne
    private Drone drone;

    public Levering() {
    }

    public Levering(String adresse, LocalDateTime forventetTidspunkt, Pizza pizza, Drone drone) {
        this.adresse = adresse;
        this.forventetTidspunkt = forventetTidspunkt;
        this.pizza = pizza;
        this.drone = drone;
    }

    public Long getId() {
        return id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public LocalDateTime getForventetTidspunkt() {
        return forventetTidspunkt;
    }

    public void setForventetTidspunkt(LocalDateTime forventetTidspunkt) {
        this.forventetTidspunkt = forventetTidspunkt;
    }

    public LocalDateTime getLeveretTidspunkt() {
        return leveretTidspunkt;
    }

    public void setLeveretTidspunkt(LocalDateTime leveretTidspunkt) {
        this.leveretTidspunkt = leveretTidspunkt;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }


}
