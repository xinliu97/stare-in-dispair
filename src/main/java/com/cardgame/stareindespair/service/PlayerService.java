package com.cardgame.stareindespair.service;

import com.cardgame.stareindespair.controller.APIResponse;
import com.cardgame.stareindespair.dto.LoginRequest;
import com.cardgame.stareindespair.dto.RegisterRequest;
import com.cardgame.stareindespair.model.Player;
import com.cardgame.stareindespair.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public APIResponse registerNewPlayer(RegisterRequest registerRequest){
        if (repository.findByPhoneNumber(Long.valueOf(registerRequest.getPhone_number())).isPresent()) {
            return APIResponse.failure("Player with this phone number already exists.", null);
        }

        Player player = new Player();
        player.setUsername(registerRequest.getUsername());
        player.setPassword(registerRequest.getPassword()); // 应加密密码
        player.setEmail(registerRequest.getEmail());
        player.setPhoneNumber(Long.valueOf(registerRequest.getPhone_number()));
        repository.save(player);
        return APIResponse.success("Player registered successfully.", player);
    }

    public void deletePlayer(Long id){
        repository.deleteById(id);
    }

    public String loginPlayer(LoginRequest loginRequest) {
        Long phoneNumber = loginRequest.getPhoneNumber();
        String password = loginRequest.getPassword();
        Optional<Player> player = repository.findByPhoneNumberAndPassword(phoneNumber, password);
        if (player.isPresent()) {
            return "登录成功";
        }
        return "登录失败";
    }
}
