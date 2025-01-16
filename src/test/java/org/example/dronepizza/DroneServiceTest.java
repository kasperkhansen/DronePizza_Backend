package org.example.dronepizza;

import org.example.dronepizza.Model.Drone;
import org.example.dronepizza.Model.Station;
import org.example.dronepizza.Repository.DroneRepository;
import org.example.dronepizza.Repository.StationRepository;
import org.example.dronepizza.Service.DroneService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class DroneServiceTest {

    @Autowired
    private DroneService droneService;

    @MockBean
    private DroneRepository droneRepository;

    @MockBean
    private StationRepository stationRepository;

    @Test
    public void testAddDrone_NoStationsAvailable() {
        Mockito.when(stationRepository.findAll()).thenReturn(Collections.emptyList());

        ResponseEntity<String> response = droneService.addDrone();

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertEquals("No stations available.", response.getBody());
    }

    @Test
    public void testAddDrone_Success() {
        Station station = new Station();
        station.setDroner(new ArrayList<>());

        Mockito.when(stationRepository.findAll()).thenReturn(List.of(station));
        Mockito.when(droneRepository.save(Mockito.any(Drone.class))).thenReturn(new Drone());

        ResponseEntity<String> response = droneService.addDrone();

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals("Drone tilføjet.", response.getBody());
    }

    @Test
    public void testUpdateDroneStatus_NotFound() {
        Mockito.when(droneRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        ResponseEntity<String> response = droneService.updateDroneStatus(1L, "i drift");

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertEquals("Drone blev ikke fundet.", response.getBody());
    }

    @Test
    public void testUpdateDroneStatus_Success() {
        Drone drone = new Drone();

        Mockito.when(droneRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(drone));
        Mockito.when(droneRepository.save(Mockito.any(Drone.class))).thenReturn(drone);

        ResponseEntity<String> response = droneService.updateDroneStatus(1L, "i drift");

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Drone status updateret til i drift.", response.getBody());
    }
}
