package cpsc2150.extendedConnectX.models;

import java.util.Scanner;

import static java.lang.Character.toUpperCase;

public class GameScreen {
    public static void main(String[] args) {
        Character players[];
        IGameBoard gameBoard = null;
        int numPlayers = 0; // Player 0 and Player 1
        int playerCounter = 0;
        int row = 0;
        int column = 0;
        int numToWin = 0;
        Scanner input = new Scanner(System.in);
        int playerInput = 0;
        String choice = " ";
        char typeGame = ' ';
        // Initializes the character to an empty space
        while(true) {
            while (true) {
                System.out.println("How many players?");
                playerInput = input.nextInt();
                if (playerInput <= gameBoard.MAX_PLAYERS && playerInput >= gameBoard.MIN_PLAYERS) {
                    numPlayers = playerInput;
                    break;
                }
                else {
                    if (playerInput > gameBoard.MAX_PLAYERS) {
                        System.out.println("Must be 10 players or fewer");
                    }
                    else {
                        System.out.println("Must be at least 2 players");
                    }
                }
            }
            players = new Character[numPlayers];
            for (int i = 0; i < numPlayers; i++) {
                players[i] = ' ';
            }
            input.nextLine();
            while(playerCounter < numPlayers) {
                System.out.println("Enter the player character to represent player " + (playerCounter + 1));
                choice = input.nextLine();
                for(int i = 0; i < numPlayers; i++) {
                    if (choice.equals(players[i].toString())) {
                        System.out.println(choice + " is already taken as a player token!");
                        break;
                    }
                    else {
                        players[playerCounter] = choice.charAt(0);
                        playerCounter++;
                        break;
                    }
                }
            }
            playerCounter = 0;
            // Row input
            while (true) {
                System.out.println("How many rows should be on the board?");
                playerInput = input.nextInt();
                input.nextLine();
                if (playerInput <= gameBoard.MAX_ROW && playerInput >= gameBoard.MIN_ROW) {
                    row = playerInput;
                    break;
                }
                else {
                    System.out.println("Number of rows must be greater than 3 and less than 100");
                }
            }
            // Column input
            while (true) {
                System.out.println("How many columns should be on the board?");
                playerInput = input.nextInt();
                input.nextLine();
                if (playerInput <= gameBoard.MAX_COLUMN && playerInput >= gameBoard.MIN_COLUMN) {
                    column = playerInput;
                    break;
                }
                else {
                    System.out.println("Number of columns must be greater than 3 and less than 100");
                }
            }
            // numToWin input
            while (true) {
                System.out.println("How many in a row to win?");
                playerInput = input.nextInt();
                input.nextLine();
                if (playerInput <= gameBoard.MAX_NUMTOWIN && playerInput >= gameBoard.MIN_NUMTOWIN) {
                    if (playerInput > column || playerInput > row) {
                        System.out.println("Number in a row must be greater than or equal to number of rows and columns");
                    }
                    else {
                        numToWin = playerInput;
                        break;
                    }
                }
                else {
                    System.out.println("Number in a row must be greater than 3 and less than 25");
                }
            }
            while (true) {
                System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m");
                choice = input.nextLine();
                if (choice.equalsIgnoreCase("f")) {
                    typeGame = 'f';
                    break;
                }
                else if (choice.equalsIgnoreCase("m")) {
                    typeGame = 'm';
                    break;
                }
                else {
                    System.out.println("Please enter F or M");
                }
            }
            if (typeGame == 'f') {
                gameBoard = new GameBoard(row, column, numToWin);
            }
            else {
                gameBoard = new GameBoard(row, column, numToWin);
            }
            System.out.println(gameBoard.toString());
            // Allows player to place token in desired column
            while(true) {
                while (true) {
                    System.out.println("Player " + toUpperCase(players[playerCounter]) + ", what column do you want to place your marker in?");
                    playerInput = input.nextInt();
                    // Error message: playerInput < 0
                    if (playerInput < 0) {
                        System.out.println("Column cannot be less than 0");
                    }
                    // Error message: playerInput > 8
                    else if (playerInput >= (gameBoard.getNumColumns())) {
                        System.out.println("Column cannot be greater than " +
                                (gameBoard.getNumColumns() - 1));
                    }
                    // Error message: Column is full
                    else if (!gameBoard.checkIfFree(playerInput)) {
                        System.out.println("Column is full");
                    }
                    // playerInput choice of column is valid
                    else {
                        gameBoard.placeToken(toUpperCase(players[playerCounter]), playerInput);
                        System.out.println(gameBoard.toString());
                        break;
                    }
                }
                // Check for player win
                if(gameBoard.checkForWin(playerInput)) {
                    System.out.println("Player " + toUpperCase(players[playerCounter]) + " Won!");
                    input.nextLine();
                    while(true) {
                        System.out.println("Would you like to play again? Y/N");
                        choice = input.nextLine();
                        if((choice).equalsIgnoreCase("Y")) {
                            break;
                        }
                        if((choice).equalsIgnoreCase("N")) {
                            break;
                        }
                    }
                    // User prompts to play again
                    if((choice).equalsIgnoreCase("Y")) {
                        gameBoard = new GameBoard(row, column, numToWin);
                        playerCounter = 0;
                        break;
                    }
                    // User prompts to not play again
                    else {
                        break;
                    }
                }
                // Game ends in a tie
                else if(gameBoard.checkTie()) {
                    System.out.println("It's a tie!");
                    input.nextLine();
                    while(true) {
                        System.out.println("Would you like to play again? Y/N");
                        choice = input.nextLine();
                        if((choice).equalsIgnoreCase("Y")) {
                            break;
                        }
                        if((choice).equalsIgnoreCase("N")) {
                            break;
                        }
                    }
                    // User prompts to play again
                    if((choice).equalsIgnoreCase("Y")) {
                        gameBoard = new GameBoard(row, column, numToWin);
                        break;
                    }
                    // User prompts to not play again
                    else {
                        break;
                    }
                }
                playerCounter++;
                if(playerCounter >= numPlayers) {
                    playerCounter = 0;
                }
            }
            // User prompts to not play again
            if((choice).equalsIgnoreCase("N")) {
                break;
            }
        }
    }
}