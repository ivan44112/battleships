package com.agency04.battleship.repository;

import com.agency04.battleship.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Integer> {
}
