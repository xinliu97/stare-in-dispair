package com.cardgame.stareindispair.model;
import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Player {

    public enum PlayerStatus {
        READY, WAITING, PLAYING;
    }
    private PlayerStatus status;

    private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;
    protected String name;
    // players hands card

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SIDCard> handcards = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "room_id")
    private SIDGameRoom gameRoom;

    public Player(){}

    public Player(String name){
        this.name = name;
    }

    public PlayerStatus getStatus() {
        return status;
    }

    public void setStatus(PlayerStatus status) {
        this.status = status;
    }




    public Long getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setId(Long id){
        this.id = id;
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
        return Objects.equals(this.id,player.id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.id,this.name);
    }

    @Override
    public String toString(){
        return "This player is:" + this.name;
    }

    public void printHandCards(){

        System.out.println("The handcards of " + this.name + " are:");
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
