package org.example.dronepizza.Repository;

import org.example.dronepizza.Model.Levering;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeveringRepository extends JpaRepository<Levering, Long> {

    @EntityGraph(attributePaths = {"pizza", "drone"})
    List<Levering> findAll();
}
