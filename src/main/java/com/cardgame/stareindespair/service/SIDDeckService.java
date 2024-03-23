package com.cardgame.stareindespair.service;

import com.cardgame.stareindespair.model.SIDDeck;
import org.springframework.stereotype.Service;

@Service
public class SIDDeckService {
    SIDDeck deck = new SIDDeck();

    public void beginGame(){
        deck.shuffle();

    }


}
