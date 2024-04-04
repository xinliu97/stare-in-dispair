package com.cardgame.stareindespair.model;
import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Player {

    public enum PlayerStatus {
        READY, WAITING, PLAYING
    }
    private PlayerStatus status;

    private @Id  Long phone_number;
    protected String username;
    // players hands card

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SIDCard> handcards = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "room_id")
    private SIDGameRoom gameRoom;

    // email
    private String email;

    // password
    private String password;


    public Player(){}

    public Player(String username, String email, Long phone_number, String password){
        this.handcards = new ArrayList<>();
        this.username = username;
        this.phone_number = phone_number;
        this.status = PlayerStatus.WAITING;
        this.gameRoom = null;
        this.password = password;

    }

    public PlayerStatus getStatus() {
        return status;
    }

    public void setStatus(PlayerStatus status) {
        this.status = status;
    }




    public Long getPhone_number(){
        return this.phone_number;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String name){
        this.username = name;
    }

    public void setPhone_number(Long id){
        this.phone_number = id;
    }

    public void setHandcards(List<SIDCard> handcards) {
        this.handcards = handcards;
    }

    public SIDGameRoom getGameRoom() {
        return gameRoom;
    }

    public void setGameRoom(SIDGameRoom gameRoom) {
        this.gameRoom = gameRoom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        if(!(o instanceof Player)){
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(this.phone_number,player.phone_number);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.phone_number,this.username);
    }

    @Override
    public String toString(){
        return "This player is:" + this.username;
    }

    public void printHandCards(){

        System.out.println("The handcards of " + this.username + " are:");
        for(SIDCard card : handcards){
            System.out.println(card);
            if (handcards.indexOf(card) % 4 == 3) {
                System.out.println();
            }
        }
    }

    public void playCard(SIDCard card){
        this.handcards.remove(card);
    }

    public void drawCard(SIDCard card){
        this.handcards.add(card);
    }

    public void addHandCard(SIDCard card){
        this.handcards.add(card);
    }

    public List<SIDCard> getHandcards(){
        return this.handcards;
    }

}
