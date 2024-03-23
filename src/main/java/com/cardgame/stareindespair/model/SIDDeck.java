package com.cardgame.stareindespair.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class SIDDeck implements Deck{
    @OneToMany(mappedBy = "deck", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SIDCard> cards = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // 游戏房间的唯一标识符

    @OneToOne(mappedBy = "deck", cascade = CascadeType.ALL)
    private SIDGameRoom gameRoom;

    public SIDDeck() {
        for (SIDSuit suit : SIDSuit.values()) {
            for (SIDRank ranks : SIDRank.values()) {
                if ((ranks == SIDRank.BLACK_JOKER || ranks == SIDRank.RED_JOKER)&& suit != SIDSuit.UNDEFINED) {
                    continue;
                }
                else if (suit == SIDSuit.UNDEFINED && ranks != SIDRank.BLACK_JOKER && ranks != SIDRank.RED_JOKER) {
                    continue;
                }
                else
                if(ranks == SIDRank.HIGHEST_UNDEFINED){
                    continue;
                }
                cards.add(new SIDCard(suit, ranks));
            }
        }
    }

    public void print(java.io.PrintStream out, int n) {
        int counter = 0;

        // Print the first n cards
        for (Card card : cards) {
            out.print(card + ", ");
            counter++;
            if (counter == n) {
                break;
            }
            if (counter % 4 == 0) { // 每4张牌换一行，仅作为示例
                out.println();
            }
        }
    }

    // Print all cards
    public void print(java.io.PrintStream out) {
        print(out, cards.size());
    }

    //draw a card from the deck
    public SIDCard draw(){
        return cards.remove(0);
    }

    // Shuffle the deck
    public void shuffle() {
        // Implement the shuffle method
        for (int i = 0; i < cards.size(); i++) {
            int j = (int) (Math.random() * cards.size());
            SIDCard temp = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, temp);
        }
    }

    public void deal(List<Player> players) {
        // Implement the deal method, every one get 5 cards, the first player get 6 cards
        for (int i = 0; i < 5; i++) {
            for (Player player : players) {
                player.addHandCard(cards.remove(0));
            }
        }
        players.get(0).addHandCard(cards.remove(0));
    }


    public static void main(String[] args) {
        SIDDeck deck = new SIDDeck();
        deck.print(System.out, 54);
        deck.shuffle();
        System.out.println();
        deck.print(System.out, 54);
    }

    public List<SIDCard> getCards(){
        return this.cards;
    }

}