package com.cardgame.stareindespair.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Game {
    private @Id
    @GeneratedValue Long id;
}
