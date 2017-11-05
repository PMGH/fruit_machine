package com.example.peter.fruitmachine;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Peter on 03/11/2017.
 */

public class FruitMachine {

    private ArrayList<Symbol> symbols;
    private ArrayList<Symbol> slots;
    private int numSlots;
    private boolean matched;
    private int bank;
    private int playCost;
    private int maxPrize;
    private int gameWallet;
    private int gambled;
    private int totalWinnings;

    public FruitMachine(int bank, int numSlots, int playCost) {
        this.bank = bank;
        this.numSlots = numSlots;

        symbols = new ArrayList<>();
        slots = new ArrayList<>();
        this.matched = false;
        this.gameWallet = 0;
        this.playCost = playCost;
        this.gambled = 0;
        this.totalWinnings = 0;

        generateSymbols();
        this.maxPrize = getMaxPrize();
    }

    public void generateSymbols(){
        for (Symbol symbol : Symbol.values()){
            symbols.add(symbol);
        }
    }

    // getters

    public ArrayList<Symbol> getSymbols(){
        ArrayList<Symbol> symbolsCopy = this.symbols;
        return symbolsCopy;
    }

    public ArrayList<Symbol> getSlots(){
        ArrayList<Symbol> slotsCopy = this.slots;
        return slotsCopy;
    }

    public Symbol getSymbolByIndex(int index){
        return symbols.get(index);
    }

    public int getRandomNumber(){
        Random rand = new Random();
        return rand.nextInt(getSymbols().size());
    }

    public int getBank() {
        int bankCopy = this.bank;
        return bankCopy;
    }

    public int getGameWallet(){
        int gameWalletCopy = this.gameWallet;
        return gameWalletCopy;
    }

    public int getPlayCost(){
        int playCostCopy = this.playCost;
        return playCostCopy;
    }

    public int getGambled() {
        return this.gambled;
    }

    public int getWinnings(){
        return this.totalWinnings;
    }

    // setters


    // other behaviour

    public void addFunds(int funds){
        bank += funds;
    }

    public void addToGameWallet(int funds){
        gameWallet += funds;
    }

    public void addToGambled(){
        gambled += playCost;
    }

    public void addToTotalWinnings(int winnings){
        totalWinnings += winnings;
    }

    public void payForSpin(){
        gameWallet -= playCost;
        addToGambled();
    }

    public int getMaxPrize(){
        for (int i = 0; i < symbols.size(); i++){
            if ((symbols.get(i).getValue() * numSlots) > maxPrize){
                maxPrize = (symbols.get(i).getValue() * numSlots) + playCost;
            }
        }
        return maxPrize;
    }

    public void spin(){
        slots.clear();
        for (int i = 0; i < numSlots; i++){
            slots.add(getSymbolByIndex(getRandomNumber()));
        }
        payForSpin();
    }

    public boolean checkWin(){

        for (int i = 0; i < numSlots -1; i++){
            if (slots.get(i).equals(slots.get(i+1))){
                this.matched = true;
            } else {
                this.matched = false;
                return this.matched;
            }
        }

        return this.matched;
    }

    public int calculateWinnings(){
        if (checkWin()) {
            int symbolValue = slots.get(0).getValue();
            int winnings = (symbolValue * numSlots) + playCost;
            return winnings;
        } else {
            return 0;
        }
    }

    public double payout(){
        int winnings = calculateWinnings();
        addToGameWallet(winnings);
        addToTotalWinnings(winnings);
        return winnings;
    }

    public String jackpotSiren(){

        if (calculateWinnings() == this.maxPrize){
            return "Wooo Wooo WOOOOO!!!";
        } else {
            return null;
        }
    }


    public void play(){
        Scanner input = new Scanner(System.in);

        while (gameWallet > 0){
            // return to spin  or  Quit
            System.out.println("--------------------");
            System.out.println("Press return to spin or anything else to exit.");
            String response = input.nextLine();

            if (response.equals("")){
                // spin
                spin();
                // display spin result
                System.out.println(getSlots());
                // payout
                if (calculateWinnings() > 0){
                    payout();
                    jackpotSiren();
                    System.out.println("\nCONGRATULATIONS!! You won £ " + calculateWinnings() + ".\n");
                } else {
                    System.out.println("\nHard luck. Maybe next time.\n");
                }

                // show remaining gameWallet
                System.out.println("\nYou have £ " + getGameWallet() + " to play with.\n");

                // show total winnings
                System.out.println("\nYou have won £ " + getWinnings() + " so far.");

                // gamble aware
                System.out.println("Be aware! You have gambled £ " + getGambled() + " so far.\n");
            } else {
                System.exit(0);
            }
        }
    }


}
