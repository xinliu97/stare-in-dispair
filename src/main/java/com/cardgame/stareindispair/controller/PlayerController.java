package com.cardgame.stareindispair.controller;

import com.cardgame.stareindispair.model.Player;
import com.cardgame.stareindispair.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
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



}
