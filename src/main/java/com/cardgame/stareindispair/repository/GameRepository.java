package com.cardgame.stareindispair.repository;

import com.cardgame.stareindispair.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {

}