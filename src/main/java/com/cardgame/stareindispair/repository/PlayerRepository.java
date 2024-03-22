package com.cardgame.stareindispair.repository;

import com.cardgame.stareindispair.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}