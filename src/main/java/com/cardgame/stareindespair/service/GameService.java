package com.cardgame.stareindespair.service;

import com.cardgame.stareindespair.model.Player;
import com.cardgame.stareindespair.model.SIDDeck;
import com.cardgame.stareindespair.model.SIDGameRoom;
import com.cardgame.stareindespair.repository.SIDGameRoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GameService {
    private Map<Long, SIDGameRoom> gameRooms = new HashMap<>();
    private static final Logger log = LoggerFactory.getLogger(GameService.class);

    final private SIDGameRoomRepository gameRoomRepository;

    public GameService(SIDGameRoomRepository gameRoomRepository) {
        this.gameRoomRepository = gameRoomRepository;
    }

    public Long createRoom() {
        try {
            SIDGameRoom gameRoom = new SIDGameRoom();
            gameRoom = gameRoomRepository.save(gameRoom);
            gameRooms.put(gameRoom.getRoom_id(), gameRoom);
            log.info("Game room created with ID: " + gameRoom.getRoom_id());
            return gameRoom.getRoom_id();
        } catch (Exception e) {
            log.error("Failed to create game room", e);
            return null;
        }
    }

    public void joinRoom(Long roomId, Player player) {
        SIDGameRoom gameRoom = gameRooms.get(roomId);
        if (gameRoom != null) {
            gameRoom.addPlayer(player);
            log.info("Player " + player.getUsername() + " joined room " + roomId);
        } else {
            log.warn("Attempted to join non-existent room: " + roomId);
        }
    }

    public void startGame(Long roomId) {
        SIDGameRoom gameRoom = gameRooms.get(roomId);
        if (gameRoom != null && gameRoom.getPlayers().size() > 1) {
            gameRoom.setStatus(SIDGameRoom.GameStatus.IN_PROGRESS);
            SIDDeck deck = gameRoom.getDeck();
            log.info("Shuffling and dealing cards for room " + roomId);
            deck.shuffle();
            deck.deal(gameRoom.getPlayers());
            // Assume each player gets their cards and is ready to play
            log.info("Game started in room " + roomId);
            takeTurns(roomId);
        } else {
            log.warn("Game could not be started in room " + roomId);
        }
    }

    public void takeTurns(Long roomId) {
        SIDGameRoom gameRoom = gameRooms.get(roomId);
        if (gameRoom != null) {
            while (!gameRoom.isGameOver()) {
                for (Player player : gameRoom.getPlayers()) {
                    if (gameRoom.isGameOver()) {
                        break;
                    }
                    playCard(gameRoom, player);
                }
            }
            log.info("Game over in room " + roomId);
            announceWinner(gameRoom);
        }
    }

    public void playCard(SIDGameRoom gameRoom, Player player) {
        if (gameRoom == null || player == null) {
            log.error("Game room or player is null");
            return;
        }

        // Simulating card play: player plays the first card from their hand
        String cardPlayed = (String) player.getHand().remove(0);  // Assuming getHand() gives us access to player's cards
        gameRoom.getCurrentRound();  // Assuming the game room keeps track of current round's played cards

        // Log the card played
        log.info("Player " + player.getUsername() + " played a card: " + cardPlayed);

        // Check if this play ends the round
        if (gameRoom.getCurrentRound()) {
            resolveRound(gameRoom);
        }
    }

    private void resolveRound(SIDGameRoom gameRoom) {
        // Logic to determine the round winner, update scores, etc.
        String roundWinner = String.valueOf(gameRoom.getCurrentRound());  // Simplified logic
        gameRoom.updateScores(roundWinner);  // Assuming there's a method to update scores based on round winner

        log.info("Round completed. Winner: " + roundWinner);
        // Optionally, reset for next round or check if the game should end
    }


    public void announceWinner(SIDGameRoom gameRoom) {
        // Determine and announce the winner of the game
        log.info("Winner announced for game room " + gameRoom.getRoom_id());
    }

    public SIDGameRoom getGameRoom(long l) {
        return gameRooms.get(l);
    }
}
