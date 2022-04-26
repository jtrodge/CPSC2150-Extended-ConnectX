package cpsc2150.extendedConnectX.models;


/**
 * A class that contains different check cases about the game
 */
public class GameBoard extends AbsGameBoard implements IGameBoard  {
    /**
     * @invariant MIN_COLUMN < column < MAX_COLUMN AND
     *            MIN_ROW < row < MAX_ROW
     *            [GameBoard have no gaps between tokens]
     *
     * @Correspondence self = [GameBoard of characters from column 0 to column 10] AND
     *                 self.row = row AND self.column = column AND sself.numToWin = numToWin AND
     *                 MIN_ROW <= numRow <= MAX_ROW AND
     *                 MIN_COLUMN <= numColumn <= MAX_COLUMN AND
     *                 MIN_NUMTOWIN <= numToWin <= MAX_NUMTOWIN
     */

    private int numRow;
    private int numColumn;
    private int numToWin;

    /**
     * This Constructor creates a new game board.
     *
     * @post GameBoard is initialized
     */
    public GameBoard() {

    }

    /**
     * This method places the character p in column c. The token will be placed in the lowest available row in column c.
     *
     * @param p represents the character token that needs to be placed.
     * @param c the column position of the game board
     *
     * @pre 0 <= c < MAX_COLUMN AND [p is a valid character]
     *      AND checkFree(c) = true
     * @post Column = c AND game = #game AND
     *       [places token at c for player p]
     */
    public void placeToken(char p, int c)  {
        //places the character p in column c. The token will be placed in the lowest available row in column c.
    }

    /**
     * This method the token ("", X, O) that is at the Row and Column position
     *
     * @param pos The row and column position of the board.
     *
     * @return the character that is at the current game board position.
     *
     * @pre [pos is within valid bounds]
     * @post whatsAtPos = [character at pos (' ' or game token)] AND
     *       GameBoard = #GameBoard
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
     * @pre [pos is within valid bounds] AND [player is valid]
     * @post isPlayerAtPos = [true if char at pos is player, false otherwise]
     *
     */
    public boolean isPlayerAtPos(BoardPosition pos, char player) {
        //returns true if the player is at pos; otherwise, it returns false
        //Note: this method will be implemented very similarly to whatsAtPos, but it's asking a different question.
        //We only know they will be similar because we know GameBoard will contain a 2D array.
        //If the data structure were to change in the future, these two methods could be radically different.
    }

    /**
     *
     * @return number of rows
     *
     * @post getNumRows = [number to rows]
     */
    public int getNumRows() { return numRow; }

    /**
     *
     * @return number of columns
     *
     * @post getNumColumns = [number of columns]
     */
    public int getNumColumns() { return numColumn; }

    /**
     *
     * @return number to win
     *
     * @post getNumToWin = [number to win]
     */
    public int getNumToWin(){ return numToWin; }
}
