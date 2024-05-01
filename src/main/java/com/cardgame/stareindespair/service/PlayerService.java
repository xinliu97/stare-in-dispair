package com.cardgame.stareindespair.service;

import com.cardgame.stareindespair.controller.APIResponse;
import com.cardgame.stareindespair.dto.LoginRequest;
import com.cardgame.stareindespair.dto.RegisterRequest;
import com.cardgame.stareindespair.model.Player;
import com.cardgame.stareindespair.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class PlayerService {
    private final PlayerRepository repository;

    public PlayerService(PlayerRepository playerRepository) {
        this.repository = playerRepository;
    }

    // Show all players in the repository
    public List<Player> all(){
        return repository.findAll();
    }

    // Register a new player with validation
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

    // Delete a player by ID
    public void deletePlayer(Long id){
        repository.deleteById(id);
    }

    // Login a player with credentials check
    public String loginPlayer(LoginRequest loginRequest) {
        Long phoneNumber = loginRequest.getPhoneNumber();
        String password = loginRequest.getPassword();
        Optional<Player> player = repository.findByPhoneNumberAndPassword(phoneNumber, password);
        if (player.isPresent()) {
            return "登录成功";
        }
        return "登录失败";
    }

    // Update player details
    public APIResponse updatePlayer(Player updatedPlayer) {
        Optional<Player> existingPlayer = repository.findByUsername(updatedPlayer.getUsername());
        if (existingPlayer.isPresent()) {
            Player player = existingPlayer.get();
            player.setUsername(updatedPlayer.getUsername());
            player.setEmail(updatedPlayer.getEmail());
            player.setPhoneNumber(updatedPlayer.getPhoneNumber());
            repository.save(player);
            return APIResponse.success("Player updated successfully.", player);
        }
        return APIResponse.failure("Player not found.", null);
    }

    // Get player details by ID
    public Optional<Player> getPlayerById(Long id) {
        return repository.findById(id);
    }

    // Check if username is available
    public boolean isUsernameAvailable(String username) {
        return repository.findByUsername(username).isEmpty();
    }
}
