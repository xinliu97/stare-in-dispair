package com.cardgame.stareindespair.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.handler.annotation.SendTo;

@Controller
public class WebSocketController {

    // 接收玩家打出的卡牌并广播
    @MessageMapping("/playcard")
    @SendTo("/topic/gameupdates")
    public String playCard(String card) {
        System.out.println("Received card: " + card);
        return "Card played: " + card;
    }

    // 开始新游戏
    @MessageMapping("/startgame")
    @SendTo("/topic/gameupdates")
    public String startGame() {
        System.out.println("A new game has started!");
        return "New game started!";
    }

    @MessageMapping("/updatestatus")
    @SendTo("/topic/statusupdates")
    public String updatePlayerStatus(String status) {
        System.out.println("Player status updated: " + status);
        return "Status updated: " + status;
    }

    // 广播游戏结束的消息
    @MessageMapping("/endgame")
    @SendTo("/topic/gameupdates")
    public String endGame(String result) {
        System.out.println("Game ended: " + result);
        return "Game ended, result: " + result;
    }
}
