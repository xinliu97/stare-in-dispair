package com.cardgame.stareindispair.service;

import com.cardgame.stareindispair.model.Player;
import com.cardgame.stareindispair.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@Service
public class PlayerService {
    private final PlayerRepository repository;


    public PlayerService(PlayerRepository playerRepository) {
        this.repository = playerRepository;
    }

    //Show all players there.
    List<Player> all(){
        return repository.findAll();
    }

    public Player newPlayer(Player newPlayer){
        return repository.save(newPlayer);
    }

    public void deletePlayer(Long id){
        repository.deleteById(id);
    }
}
