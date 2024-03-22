package com.cardgame.stareindispair.controller;

import com.cardgame.stareindispair.model.Player;
import com.cardgame.stareindispair.service.GameService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static java.lang.Long.parseLong;


@RestController
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/api/games/create")
    public ResponseEntity<?> createGame() {
        Long gameId = gameService.createRoom();
        if (gameId != null) {
            return ResponseEntity.ok().body("Game created successfully. Game ID: " + gameId);
        }
        else {
            return ResponseEntity.badRequest().body("Could not create game.");
        }
    }

    @PostMapping("/api/games/{gameId}/join")
    public ResponseEntity<?> joinGame(@PathVariable String gameId, @RequestBody Player player) {
        try {
            gameService.joinRoom(parseLong(gameId), player);
            return ResponseEntity.ok().body("Player joined game successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Could not join game: " + e.getMessage());
        }
    }

    //52   --- > 18  18  18

    @PostMapping("/api/games/{gameId}/players/{playerId}/ready")
    public ResponseEntity<?> playerReady(@PathVariable String gameId, @PathVariable String playerId) {
        try {
            Player player = gameService.getGameRoom(parseLong(gameId)).getPlayer(parseLong(playerId));
            if (player == null) {
                return ResponseEntity.status(404).body(APIResponse.failure("Player not found.", null));
            }

            player.setStatus(Player.PlayerStatus.READY);

            // Check whether all players are ready
            boolean allPlayersReady = gameService.getGameRoom(parseLong(gameId)).getPlayers().stream().allMatch(p -> p.getStatus() == Player.PlayerStatus.READY);

            Map<String, Object> data = Map.of("allPlayersReady", allPlayersReady);

            if (allPlayersReady) {
                gameService.startGame(parseLong(gameId));
                return ResponseEntity.ok().body(APIResponse.success("All players are ready, game started!", data));
            }
            else {
                return ResponseEntity.ok().body(APIResponse.success("Player is ready.", data));
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Could not mark player as ready: " + e.getMessage());
        }
    }

    @PostMapping("/{gameId}/start")
    public ResponseEntity<?> startGame(@PathVariable String gameId) {
        try {
            gameService.startGame(parseLong(gameId));
            return ResponseEntity.ok().body("Game started successfully.");
        } catch (Exception e) {
            // 处理异常，例如如果找不到游戏或游戏不能被启动
            return ResponseEntity.badRequest().body("Could not start game: " + e.getMessage());
        }
    }
}
