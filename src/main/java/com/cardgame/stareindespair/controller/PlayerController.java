package com.cardgame.stareindespair.controller;

import com.cardgame.stareindespair.dto.RegisterRequest;
import com.cardgame.stareindespair.model.Player;
import com.cardgame.stareindespair.service.PlayerService;
import com.cardgame.stareindespair.dto.LoginRequest;
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

    @PostMapping("/newplayers")
    Player newPlayer(@RequestBody Player newPlayer){
        return service.newPlayer(newPlayer);
    }


    // Login method, set cross-origin to allow requests from different origins, just for testing purposes
    @PostMapping("/login")
    @CrossOrigin(origins = {"http://localhost:3000", "http://192.168.0.102:3000"})
    String login(@RequestBody LoginRequest loginRequest){
        return "Success";
    }

    @PostMapping("/register")
    @CrossOrigin(origins = {"http://localhost:3000", "http://192.168.0.102:3000"})
    Player register(@RequestBody RegisterRequest registerRequest){



    }
}
