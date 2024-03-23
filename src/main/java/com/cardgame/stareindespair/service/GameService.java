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

    // Constructor
    public GameService(SIDGameRoomRepository gameRoomRepository) {
        this.gameRoomRepository = gameRoomRepository;
    }

    public Long createRoom() {
        try {
            SIDGameRoom gameRoom = new SIDGameRoom();
            gameRoom = gameRoomRepository.save(gameRoom);
            gameRooms.put(gameRoom.getRoom_id(), gameRoom);
            log.info("Creating " + gameRoom + " with ID: " + gameRoom.getRoom_id());
            return gameRoom.getRoom_id();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void joinRoom(Long roomId, Player player) {
        SIDGameRoom gameRoom = gameRooms.get(roomId);
        if (gameRoom != null) {
            gameRoom.addPlayer(player);
            // 可能还需要其他逻辑，比如检查房间是否已满
        }
        // 发送更新到客户端，比如玩家列表更新等
    }

    public void startGame(Long roomId) {
        SIDGameRoom gameRoom = gameRooms.get(roomId);
        if (gameRoom != null) {
            // 初始化牌组，洗牌，发牌等
            // 更新游戏状态，通知玩家游戏开始
            gameRoom.setStatus(SIDGameRoom.GameStatus.IN_PROGRESS);
            SIDDeck deck = gameRoom.getDeck();
            System.out.println("Shuffling the deck.");
            deck.shuffle();

            // 发牌
            System.out.println("Dealing the cards.");
            deck.deal(gameRoom.getPlayers());

            // 出牌，Player0出牌



        }
    }

    public SIDGameRoom getGameRoom(Long roomId){
        return gameRooms.get(roomId);
    }

//    public static void main(String[] args) {
//        GameService gameService = new GameService();
//        Long room1_id = gameService.createRoom();
//
//        Player player1 = new Player("Alice");
//        Player player2 = new Player("Bob");
//        gameService.joinRoom(room1_id, player1);
//        gameService.joinRoom(room1_id, player2);
//
//        gameService.startGame(room1_id);
//        SIDGameRoom room1 = gameService.getGameRoom(room1_id);
//        System.out.println("Room 1 status: " + room1.getStatus());
//        room1.getDeck().print(System.out);
//        for (Player player : room1.getPlayers()) {
//            System.out.println(player.getName() + "'s hand:");
//            player.printHandCards();
//        }
//    }
}
