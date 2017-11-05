package com.example.peter.fruitmachine;

import java.util.Scanner;

import static junit.framework.Assert.fail;

/**
 * Created by Peter on 03/11/2017.
 */

public class Runner {

    public static void main(String[] args) {
        FruitMachine fruitMachine = new FruitMachine(1000, 3, 1);
        Scanner sc = new Scanner(System.in);

        printItems(fruitMachine);
        System.out.println("\n\n£ " + fruitMachine.getPlayCost() + " per spin.\n");

        String name = getUsername(sc);
        int userFunds = getUserWallet(sc);
        Player player = new Player(name, userFunds);

        // play loop

        while (player.getWallet() >= fruitMachine.getPlayCost() && player.isWantingToPlay()) {
            int gameFunds = getGameFunds(fruitMachine, player, sc);
            fruitMachine.addToGameWallet(player.depositFunds(gameFunds));
            fruitMachine.play();
            tryAgain(fruitMachine, player, sc);
        }
    }


    public static String getUsername(Scanner sc) {
        System.out.print("What is your name?  ");
        String userName = sc.nextLine();
        return userName;

    }

    public static int getUserWallet(Scanner sc) {
        // stack overflow helped with input validation:
        int userWallet;
        do {
            System.out.print("\nHow much do you have in your wallet?  £ ");
            while (!sc.hasNextInt()) {
                String input = sc.next();
                System.out.printf("%s is not a valid integer.\n", input);
            }
            userWallet = sc.nextInt();
        } while (userWallet < 0);

        return userWallet;
    }

    public static int getGameFunds(FruitMachine fruitMachine, Player player, Scanner sc){
        System.out.println("\nYou have £ " + player.getWallet() + ".");

        while (fruitMachine.getGameWallet() == 0){
            // stack overflow helped with input validation:
            int gameFunds;
            do {
                System.out.print("\nHow much would you like to play with?  £ ");
                while (!sc.hasNextInt()) {
                    String input = sc.next();
                    System.out.printf("%s is not a valid integer.\n", input);
                }
                gameFunds = sc.nextInt();
            } while (gameFunds < 0);

            if (gameFunds <= player.getWallet()){
                return gameFunds;
            } else {
                System.out.println("Please enter a value less than or equal to " + player.getWallet() + ".");
            }
        }
        return 0;
    }

    public static void printItems(FruitMachine fruitMachine){
        System.out.print("Items and Prize Value: | ");
        for (Symbol symbol : fruitMachine.getSymbols()){
//            System.out.print(symbol.getEmoji() + " (" + (symbol.getValue()*3) + ") | ");
            System.out.print(symbol.getName() + " (" + (symbol.getValue()*3) + ") | ");

        }
    }

    public static void tryAgain(FruitMachine fruitMachine, Player player, Scanner sc){
        if (player.getWallet() >= fruitMachine.getPlayCost()){
            System.out.println("\n\nPlay again? Y/N");
            String response = sc.nextLine().toLowerCase();
            if (response.equals("n") || response.equals("no")){
                player.setWantingToPlay(false);
            }
        } else {
            System.out.print("---------------------------------------------");
            System.out.print("  -----------------------------");
            System.out.println("\nOh no, looks like you're out of game credits.  Please deposit to play again.");
            System.out.print("---------------------------------------------");
            System.out.print("  -----------------------------");
        }
    }

}
