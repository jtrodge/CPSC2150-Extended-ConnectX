package cpsc2150.extendedConnectX.models;

import java.util.*;


/**
 * A class that contains different check cases about the game
 */
public class GameBoard {
    /**
     * @invariant position > 0 AND [game is valid]
     *
     */

    private BoardPosition position = new BoardPosition(6, 9);
    private String[][] game = new String[position.getRow()][position.getColumn()];

    /**
     * This Constructor creates a new game board.
     *
     * @post returns the 2d array of the game board.
     */
    public GameBoard() {
       for (int r = 0; r < position.getRow(); r++) {
           for (int c = 0; c < position.getColumn(); c++) {
               game[r][c] = "";
           }
       }
    }

    /**
     * This method checks if a column is full or not.
     *
     * @param c the column position of the game board
     *
     * @return true if the column can accept another token.
     * false otherwise.
     *
     * @pre  c > 0
     * @post Column = c AND game = #game AND
     *       checkTie iff game = [Blank spaces available]
     */
    public boolean checkIfFree(int c)  {
        //returns true if the column can accept another token; false otherwise.
    }

    /**
     * This method places the character p in column c. The token will be placed in the lowest available row in column c.
     *
     * @param p represents the character token that needs to be placed.
     * @param c the column position of the game board
     *
     * @pre c > 0 AND 0 <= c <= 8 AND [p is a valid character]
     * @post Column = c AND game = #game
     */
    public void placeToken(char p, int c)  {
        //places the character p in column c. The token will be placed in the lowest available row in column c.
    }

    /**
     * This method checks to see if a player has won the game.
     *
     * @param c number of columns
     *
     * @return True if the last token placed in the column resulted in a win, false otherwise.
     *
     * @pre c > 0
     * @post Column = c AND
     *       [checkHorizWin equals true] OR
     *       [checkVertWin equals true] OR
     *       [checkDiagWin equals true] OR
     */
    public boolean checkForWin(int c) {
        //this function will check to see if the last token placed in column c resulted in the player winning the game.
        //If so it will return true, otherwise false.
        //Note: this is not checking the entire board for a win, it is just checking if the last token placed results in a win.
        //You may call other methods to complete this method
    }

    /**
     *  This method check to see if the game ended in a tie.
     *
     * @return True if the game is tied, false otherwise.
     *
     * @post checkTie iff game = [No blank spaces available]
     */
    public boolean checkTie()  {
        //this function will check to see if the game has resulted in a tie. A game is tied if there are no free board positions remaining.
        //You do not need to check for any potential wins because we can assume hat the players were checking for win conditions as they played the
        //game. It will return true if the game is tied and false otherwise.
    }

    /**
     * This method checks to see if a player has won horizontally.
     *
     * @param pos The row and column position of the board.
     * @param p The X and O tokens.
     *
     * @return true if a player has won horizontally, false otherwise.
     *
     * @pre pos > 0 AND [p is valid]
     * @post [checkHorizWin iff [game's pos equals p] equals 5 in a row horizontally]
     */
    public boolean checkHorizWin(BoardPosition pos, char p) {
        //checks to see if the last token placed (which was placed in
        //position pos by player p) resulted in 5 in a row horizontally.
        // Returns true if it does, otherwise false
    }

    /**
     * This method checks to see if a player has won vertically.
     *
     * @param pos The row and column position of the board.
     * @param p The X and O tokens.
     *
     * @return true if a player has won vertically, false otherwise.
     *
     * @pre pos > 0 AND [p is valid]
     * @post [checkVertWin iff [game's pos equals p] equals 5 in a row vertically]
     */
    public boolean checkVertWin(BoardPosition pos, char p)  {
        //checks to see if the last token placed (which was placed in position pos by player p) resulted in 5 in a row vertically.
        //Returns true if it does, otherwise false
    }

    /**
     * This method checks to see if a player has won diagonally
     *
     * @param pos The row and column position of the board.
     * @param p The X and O tokens.
     *
     * @return true if a player has won diagonally, false otherwise.
     *
     * @pre pos > 0 AND [p is valid]
     * @post [checkDiagWin iff [game's pos equals p] equals 5 in a row diagonally]
     */
    public boolean checkDiagWin(BoardPosition pos, char p)  {
        //checks to see if the last token placed (which was placed in position pos by player p) resulted in 5 in a row diagonally.
        //Returns true if it does, otherwise false
        //Note: there are two diagonals to check
    }

    /**
     * This method the token ("", X, O) that is at the Row and Column position
     *
     * @param pos The row and column position of the board.
     *
     * @return the character that is at the current game board position.
     *
     * @pre pos > 0
     * @post [whatsAtPos is valid] AND
     *       whatsAtPos = #game
     */
    public char whatsAtPos(BoardPosition pos)  {
        //returns what is in the GameBoard at position pos
        //If no marker is there, it returns a blank space char.
    }

    /**
     * This method checks to see if a player is at a designated position.
     *
     * @param pos the row and column the player is currently at.
     * @param player represents the game X and O tokens.
     *
     * @return True if the player is at the desired position, false otherwise.
     *
     * @pre pos > 0 AND [player is valid]
     * @post isPlayerAtPos iff [player equals game's pos]
     *
     */
    public boolean isPlayerAtPos(BoardPosition pos, char player) {
        //returns true if the player is at pos; otherwise, it returns false
        //Note: this method will be implemented very similarly to whatsAtPos, but it's asking a different question.
        //We only know they will be similar because we know GameBoard will contain a 2D array.
        //If the data structure were to change in the future, these two methods could be radically different.
    }

    /**
     * This method overrides the default implementation of {@code toString} to provide
     * a string representation of the coordinates of the entire board.
     *
     * @param obj represents the entire game board.
     *
     * @return A string representation of the game board.
     *
     * @post toString = [entire game board]
     */
    @Override
    public String toString(Object obj) {

    }

}
