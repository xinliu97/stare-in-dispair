package com.cardgame.stareindespair.service;

import com.cardgame.stareindespair.dto.RegisterRequest;
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

    public Player registerNewPlayer(RegisterRequest registerRequest){
        Player player = new Player();
        player.setUsername(registerRequest.getUsername());
        player.setPassword(registerRequest.getPassword()); // 应加密密码
        player.setEmail(registerRequest.getEmail());
        player.setPhone_number(Long.valueOf(registerRequest.getPhone_number()));
        return repository.save(player);

    }

    public void deletePlayer(Long id){
        repository.deleteById(id);
    }
}
