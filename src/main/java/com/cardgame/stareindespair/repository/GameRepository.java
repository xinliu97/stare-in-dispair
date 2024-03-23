package com.cardgame.stareindespair.repository;

import com.cardgame.stareindespair.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {

}