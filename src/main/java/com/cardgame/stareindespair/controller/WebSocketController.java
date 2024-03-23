package com.cardgame.stareindespair.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;

public class WebSocketController {

    @MessageMapping("/playcard")
    public void playCard() {

    }
}
