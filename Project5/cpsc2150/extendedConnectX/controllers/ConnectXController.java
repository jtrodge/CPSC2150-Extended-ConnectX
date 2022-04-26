package cpsc2150.extendedConnectX.controllers;

import cpsc2150.extendedConnectX.models.*;
import cpsc2150.extendedConnectX.views.*;

/**
 * The controller class will handle communication between our View and our Model ({@link IGameBoard})
 * <p>
 * This is where you will write code
 * <p>
 * You will need to include your {@link IGameBoard} interface
 * and both of the {@link IGameBoard} implementations from Project 4
 * If your code was correct you will not need to make any changes to your {@link IGameBoard} implementation class
 *
 * @version 2.0
 */
public class ConnectXController {

    /**
     * <p>
     * The current game that is being played
     * </p>
     */
    private IGameBoard curGame;

    /**
     * <p>
     * The screen that provides our view
     * </p>
     */
    private ConnectXView screen;

    /**
     * <p>
     * Constant for the maximum number of players.
     * </p>
     */
    public static final int MAX_PLAYERS = 10;

    /**
     * <p>
     * Allows for the users to select the character of their choice.
     * </p>
     */
    private char[] playerChar = {'X', 'O', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'} ;

    /**
     * <p>
     * Keeps track of whose player turn it is
     * </p>
     */
    private int playerTurn = 0;

    /**
     * <p>
     * True if the game returns in a win, otherwise false
     * </p>
     */
    private boolean win = false;

    /**
     * <p>
     * True if the game returns in a tie, otherwise false
     * </p>
     */
    private boolean tie = false;
    
    /**
     * <p>
     * The number of players for this game. Note that our player tokens are hard coded.
     * </p>
     */
    private int numPlayers;

    /**
     * <p>
     * This creates a controller for running the Extended ConnectX game
     * </p>
     * 
     * @param model
     *      The board implementation
     * @param view
     *      The screen that is shown
     * 
     * @post [ the controller will respond to actions on the view using the model. ]
     */
    public ConnectXController(IGameBoard model, ConnectXView view, int np) {
        this.curGame = model;
        this.screen = view;
        numPlayers = np;
    }

    /**
     * <p>
     * This processes a button click from the view.
     * </p>
     * 
     * @param col 
     *      The column of the activated button
     * 
     * @post [ will allow the player to place a token in the column if it is not full, otherwise it will display an error
     * and allow them to pick again. Will check for a win as well. If a player wins it will allow for them to play another
     * game hitting any button ]
     */
    public void processButtonClick(int col) {
        // Base Case
        if (win || tie) {
            playerTurn = 0;
            win = false;
            tie = false;
            this.newGame();
            return;
        }
        // Prompt screen to say who's turn it is
        screen.setMessage("Player " + playerChar[playerTurn] + ", it is your turn.");
        // Error message if user selected spot is not available
        if (!curGame.checkIfFree(col)) {
            screen.setMessage("Column is Full! Pick a Different Column.");
            return;
        }
        // Spot is available
        int row = 1;
        // Find valid row position
        for (int i = 0; i < curGame.getNumRows(); i++) {
            BoardPosition pos = new BoardPosition(i, col);
            Character temp = Character.valueOf(curGame.whatsAtPos(pos));
            if (temp.equals(' ')) {
                row = pos.getRow();
                break;
            }
        }
        // Place User Token in Valid Position
        curGame.placeToken(playerChar[playerTurn], col);
        screen.setMarker(row, col, playerChar[playerTurn]);
        // Check Tie Condition
        if (curGame.checkTie()) {
            screen.setMessage("You Tied. Click Any Button To Start A New Game.");
            tie = true;
        }
        // Check Win Condition
        if (curGame.checkForWin(col)) {
            screen.setMessage("Player " + playerChar[playerTurn] + " Wins! Click Any Button To Start A New Game.");
            win = true;
        }
        if (playerTurn + 1 < numPlayers) {
            playerTurn++;
        }
        else {
            playerTurn = 0;
        }
    }

    /**
     * <p>
     * This method will start a new game by returning to the setup screen and controller
     * </p>
     * 
     * @post [ a new game gets started ]
     */
    private void newGame() {
        //close the current screen
        screen.dispose();
        
        //start back at the set up menu
        SetupView screen = new SetupView();
        SetupController controller = new SetupController(screen);
        screen.registerObserver(controller);
    }
}