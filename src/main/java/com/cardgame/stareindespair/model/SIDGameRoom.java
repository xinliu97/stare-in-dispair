package com.cardgame.stareindespair.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class SIDGameRoom {
    public enum GameStatus {
        WAITING, IN_PROGRESS, FINISHED
    }
    private GameStatus status = GameStatus.WAITING;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long room_id; // 游戏房间的唯一标识符

    @OneToOne(cascade = CascadeType.ALL)
    private SIDDeck deck; // 牌组
    @OneToMany(mappedBy = "gameRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Player> players = new ArrayList<>();

    // 构造函数、getter和setter方法
    public SIDGameRoom() {

        this.deck = new SIDDeck();

    }

    public Long getID(){
        return this.room_id;
    }


    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public GameStatus getStatus() {
        return status;
    }

    public Long getRoom_id(){
        return this.room_id;
    }

    public SIDDeck getDeck(){
        return this.deck;
    }

    public List<Player> getPlayers(){
        return this.players;
    }

    public Player getPlayer(Long id){
        for (Player player : players) {
            if (Objects.equals(player.getPhone_number(), id)) {
                return player;
            }
        }
        return null;
    }

    // check whether game is finished
    public boolean isFinished(){
        // if any player has no cards, the game is finished
        for (Player player : players) {
            if (player.getHandcards().isEmpty()) {
                this.setStatus(GameStatus.FINISHED);
            }
        }
        // if the deck is empty, the game is finished
        if (deck.getCards().isEmpty()) {
            this.setStatus(GameStatus.FINISHED);
        }

        return this.status == GameStatus.FINISHED;
    }
}
