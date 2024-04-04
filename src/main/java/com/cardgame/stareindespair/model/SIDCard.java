package com.cardgame.stareindespair.model;

import jakarta.persistence.*;

@Entity
public class SIDCard implements Card {
    private final SIDSuit suit;
    private final SIDRank card_rank;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "deck_id") // 假设在数据库中外键列的名称是deck_id
    private SIDDeck deck;

    public SIDCard(SIDSuit suit, SIDRank rank) {
        this.suit = suit;
        this.card_rank = rank;
    }

    public SIDCard() {
        this.suit = SIDSuit.UNDEFINED;
        this.card_rank = SIDRank.HIGHEST_UNDEFINED;
    }


    //Change the output of the suit, easy to read.
    public static String suitToString(SIDSuit suit){
        switch (suit){
            case CLUBS : return "C";
            case DIAMONDS: return "D";
            case HEARTS: return "H";
            case SPADES: return "S";
            case UNDEFINED:return "U";
            default:    return "?";
        }
    }

    public static String rankToString(SIDRank rank){
        switch (rank){
            case ACE : return "A";
            case TWO: return "2";
            case THREE: return "3";
            case FOUR: return "4";
            case FIVE: return "5";
            case SIX: return "6";
            case SEVEN: return "7";
            case EIGHT: return "8";
            case NINE: return "9";
            case TEN: return "10";
            case JACK: return "J";
            case QUEEN: return "Q";
            case KING: return "K";
            case HIGHEST_UNDEFINED: return "U";
            case BLACK_JOKER: return "BJ";
            case RED_JOKER: return "RJ";
            default:    return "?";
        }
    }



    @Override
    public SIDSuit getSuit() {
        return suit;
    }

    @Override
    public SIDRank getRank() {
        return card_rank;
    }

    @Override
    public String toString() {
        return String.format("%-10s",rankToString(card_rank) + suitToString(suit));
    }
}
