package com.cardgame.stareindespair.controller;

import com.cardgame.stareindespair.dto.RegisterRequest;
import com.cardgame.stareindespair.model.Player;
import com.cardgame.stareindespair.service.PlayerService;
import com.cardgame.stareindespair.dto.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {

    private PlayerService service;

    public PlayerController(PlayerService playerService){
        this.service = playerService;
    }




    // Login method, set cross-origin to allow requests from different origins, just for testing purposes
    @PostMapping("/login")
    @CrossOrigin(origins = {"http://localhost:3000", "http://192.168.0.102:3000"})
    String login(@RequestBody LoginRequest loginRequest){
        try {
            return service.loginPlayer(loginRequest);
        } catch (Exception e) {
            return "登录失败: " + e.getMessage();
        }
    }

    @PostMapping("/register")
    @CrossOrigin(origins = {"http://localhost:3000", "http://192.168.0.102:3000"})
    public APIResponse register(@RequestBody RegisterRequest registerRequest) {
        return service.registerNewPlayer(registerRequest);
    }

    @PostMapping("/delete")
    @CrossOrigin(origins = {"http://localhost:3000", "http://192.168.0.102:3000"})
    public ResponseEntity<String> deletePlayer(@RequestBody Long id){
        service.deletePlayer(id);
        return ResponseEntity.ok("Player deleted successfully.");
    }

    @PostMapping("/update")
    @CrossOrigin(origins = {"http://localhost:3000", "http://192.168.0.102:3000"})
    public APIResponse updatePlayer(@RequestBody Player updatedPlayer){
        return service.updatePlayer(updatedPlayer);
    }
}
