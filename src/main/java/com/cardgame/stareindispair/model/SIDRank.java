package com.cardgame.stareindispair.model;

public enum SIDRank {
    THREE(3), FOUR(4), FIVE(5), SIX(6),
    SEVEN(7), EIGHT(8), NINE(9), TEN(10),
    JACK(11), QUEEN(12), KING(13), ACE(14),TWO(15),
    BLACK_JOKER(16),RED_JOKER(17),
    HIGHEST_UNDEFINED(Integer.MAX_VALUE);

    private final int value;
    SIDRank(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    public SIDRank next(){
        if (this == ACE) {
            return HIGHEST_UNDEFINED;
        }
        return SIDRank.values()[this.ordinal() + 1];
    }
}
