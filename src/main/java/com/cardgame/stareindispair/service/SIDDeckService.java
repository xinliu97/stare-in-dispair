package com.cardgame.stareindispair.service;

import com.cardgame.stareindispair.model.SIDDeck;
import org.springframework.stereotype.Service;

@Service
public class SIDDeckService {
    SIDDeck deck = new SIDDeck();

    public void beginGame(){
        deck.shuffle();

    }


}
