package com.example.peter.fruitmachine;

/**
 * Created by Peter on 03/11/2017.
 */

public class Player {

    private String name;
    private int wallet;
    private boolean wantingToPlay;

    public Player(String name, int wallet) {
        this.name = name;
        this.wallet = wallet;
        this.wantingToPlay = true;
    }

    public String getName() {
        return name;
    }

    public int getWallet() {
        return wallet;
    }

    public boolean isWantingToPlay() {
        return wantingToPlay;
    }

    public void setWantingToPlay(boolean wantingToPlay) {
        this.wantingToPlay = wantingToPlay;
    }

    public void addFunds(int funds){
        wallet += funds;
    }

    public int depositFunds(int stake){
        wallet -= stake;
        return stake;
    }
}
