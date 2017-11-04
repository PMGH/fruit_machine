package com.example.peter.fruitmachine;

import android.support.text.emoji.EmojiCompat;
import android.support.text.emoji.widget.EmojiExtractTextLayout;

/**
 * Created by Peter on 03/11/2017.
 */

public enum Symbol {

    APPLE("Apple", 1, "\uD83C\uDF4F"),
    GRAPES("Grapes", 2, "\uD83C\uDF47"),
    STRAWBERRY("Strawberry", 3, "\uD83C\uDF53"),
    PEAR("Pear", 4, "\uD83C\uDF50"),
    BELL("Bell", 5, "\uD83D\uDD14"),
    HORSESHOE("Horseshoe", 6, "\uD83D\uDC0E"),
    SEVEN("Seven", 7, "⓻"),
    DIAMOND("Diamond", 10, "\uD83D\uDC8E"),
    CLOVER("Clover", 15, "\uD83C\uDF40");

    private String name;
    private int value;
    private String emoji;

    Symbol(String name, int value, String emoji) {
        this.name = name;
        this.value = value;
        this.emoji = emoji;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public String getEmoji() {
        return emoji;
    }
}
