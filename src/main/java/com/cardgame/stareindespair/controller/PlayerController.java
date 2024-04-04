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
        return "Success";
    }

    @PostMapping("/register")
    @CrossOrigin(origins = {"http://localhost:3000", "http://192.168.0.102:3000"})
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
            Player player = service.registerNewPlayer(registerRequest);
            return ResponseEntity.ok(player); // 成功时返回Player信息
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("注册失败: " + e.getMessage()); // 失败时返回错误信息
        }
    }
}
