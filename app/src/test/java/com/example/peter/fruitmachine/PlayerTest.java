package com.example.peter.fruitmachine;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Peter on 03/11/2017.
 */

public class PlayerTest {

    Player player;

    @Before
    public void before(){
        player = new Player("Peter", 50);
    }

    @Test
    public void hasName(){
        assertEquals("Peter", player.getName());
    }

    @Test
    public void canAddFunds(){
        player.addFunds(20);
        assertEquals(70, player.getWallet());
    }

    @Test
    public void canDepositFunds(){
        player.depositFunds(10);
        assertEquals(40, player.getWallet());
    }

    @Test
    public void wantingToPlayStartsTrue(){
        assertEquals(true, player.isWantingToPlay());
    }

    @Test
    public void canSetWantingToPlayToFalse(){
        player.setWantingToPlay(false);
        assertEquals(false, player.isWantingToPlay());
    }


}
