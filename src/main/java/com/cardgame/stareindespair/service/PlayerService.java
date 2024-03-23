package com.cardgame.stareindespair.service;

import com.cardgame.stareindespair.model.Player;
import com.cardgame.stareindespair.repository.PlayerRepository;
import org.springframework.stereotype.Service;

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
