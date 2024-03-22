package com.cardgame.stareindispair.model;


public interface Card {
    // 获取卡片的花色
    SIDSuit getSuit();

    // 获取卡片的数值
    SIDRank getRank();

    // 返回卡片的字符串表示，例如："Ace of Spades"
    @Override
    String toString();
}
