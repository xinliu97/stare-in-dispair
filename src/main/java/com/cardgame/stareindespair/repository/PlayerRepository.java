package com.cardgame.stareindespair.repository;

import com.cardgame.stareindespair.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}