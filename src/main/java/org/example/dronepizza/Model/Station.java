package org.example.dronepizza.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double latitude;
    private double longitude;

    @OneToMany(mappedBy = "station")
    private List<Drone> droner = new ArrayList<>();

    public Station() {
    }

    public Station(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<Drone> getDroner() {
        return droner;
    }


    public void setDroner(List<Drone> droner) {
        this.droner = droner;
    }
}
