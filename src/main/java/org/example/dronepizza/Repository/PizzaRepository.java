package org.example.dronepizza.Repository;

import org.example.dronepizza.Model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
}
