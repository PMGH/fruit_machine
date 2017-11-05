package com.example.peter.fruitmachine;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Peter on 03/11/2017.
 */

public class FruitMachineTest {

    FruitMachine fruitMachine;
    FruitMachine spyFruitMachine;
    Player player;

    @Before
    public void before() {
        fruitMachine = new FruitMachine(1000, 3, 1);
        spyFruitMachine = Mockito.spy(new FruitMachine(1000, 3, 1));
        player = new Player("Peter", 50);
    }


    @Test
    public void hasSymbols(){
        assertNotNull(fruitMachine.getSymbols());
    }

    @Test
    public void canGetSymbolByIndex(){
        assertEquals(Symbol.GRAPES, fruitMachine.getSymbolByIndex(1));
    }

    @Test
    public void slotsStartEmpty(){
        assertEquals(0, fruitMachine.getSlots().size());
    }

    @Test
    public void canGetRandomNumber(){
        assertNotNull(fruitMachine.getRandomNumber());
    }

    @Test
    public void canSpinAndPopulateSlots(){
        fruitMachine.spin();
        assertEquals(3, fruitMachine.getSlots().size());
    }

    @Test
    public void canCheckWin__True(){
        Mockito.when(spyFruitMachine.getRandomNumber()).thenReturn(1);
        spyFruitMachine.spin();

        assertEquals(true, spyFruitMachine.checkWin());
    }

//    Sometimes fails test as matches occur
//    @Test
//    public void canCheckWin__False(){
//        fruitMachine.spin();
//
//        assertEquals(false, fruitMachine.checkWin());
//    }

    @Test
    public void canAddFunds(){
        fruitMachine.addFunds(500);

        assertEquals(1500, fruitMachine.getBank());
    }

    @Test
    public void canPayout(){
        Mockito.when(spyFruitMachine.getRandomNumber()).thenReturn(1);
        spyFruitMachine.spin();

        // includes money back for bet
        assertEquals(7, spyFruitMachine.payout(), 0.1);
    }

    @Test
    public void canGetMaxPrize(){
        // includes money back for bet
        assertEquals(46, fruitMachine.getMaxPrize());
    }

    @Test
    public void sirenOnJackpot(){
        Mockito.when(spyFruitMachine.getRandomNumber()).thenReturn(8);
        spyFruitMachine.spin();

        assertEquals("Wooo Wooo WOOOOO!!!", spyFruitMachine.jackpotSiren());
    }

    @Test
    public void silenceWhenNotJackpot(){
        Mockito.when(spyFruitMachine.getRandomNumber()).thenReturn(7);
        spyFruitMachine.spin();

        assertNull(spyFruitMachine.jackpotSiren());
    }

    @Test
    public void canAddFromPlayerWalletToGameWallet(){
        fruitMachine.addToGameWallet(player.depositFunds(50));

        assertEquals(50, fruitMachine.getGameWallet());
        assertEquals(0, player.getWallet());
    }



}
