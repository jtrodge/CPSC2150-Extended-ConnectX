package cpsc2150.extendedConnectX.models;

import java.util.Scanner;

public class GameScreen {
    public static void main(String[] args) {
        Character players[];
        GameBoard gameBoardInfo = null;
        IGameBoard gameBoard = null;
        BoardPosition position = null;
        final int numPlayers = 2; // Player 0 and Player 1
        int playerCounter = 0;
        int row = 0;
        int column = 0;
        int numToWin = 0;
        Scanner input = new Scanner(System.in);
        int playerInput = 0;
        String choice = " ";
        // Initializes the character to an empty space
        while(true) {
            players = new Character[numPlayers];
            for(int i = 0; i < numPlayers; i++) {
                players[i] = ' ';
            }
            // Sets player to X and O
            // Switches between player 1's and player 2's turn
            while(playerCounter < numPlayers) {
                players[0] = 'X';
                players[1] = 'O';
                choice = String.valueOf(players[1]);
                for(int i = 0; i < numPlayers; i++) {
                    players[playerCounter] = choice.charAt(0);
                    playerCounter++;
                    break;
                }
            }
            playerCounter = 0;
            gameBoard = new GameBoard();
            System.out.println(gameBoard.toString());
            // Allows player to place token in desired column
            while(true) {
                while (true) {
                    System.out.println("Player " + players[playerCounter] + ", what column do you want to place your marker in?");
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
                        gameBoard.placeToken(players[playerCounter], playerInput);
                        System.out.println(gameBoard.toString());
                        break;
                    }
                }
                // Check for player win
                if(gameBoard.checkForWin(playerInput)) {
                    System.out.println("Player " + players[playerCounter] + " Won!");
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
                        gameBoard = new GameBoard();
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
                        gameBoard = new GameBoard();
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
