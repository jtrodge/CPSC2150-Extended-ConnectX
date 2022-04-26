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

    private Character gameBoard[][];
    private int numRow;
    private int numColumn;
    private int numToWin;

    /**
     * This Constructor creates a new game board.
     *
     * @param r the number of rows in the game board.
     * @param c the number of columns in the game board.
     * @param n the number of consecutive tokens to win the game.
     *
     * @pre r > 0 AND c > 0 AND (n > 0 AND n <= r)
     * @post GameBoard is initialized AND [every position is filled with blank spaces] AND
     *       numRow = r AND
     *       numColumn = c AND
     *       numToWin = n
     */
    public GameBoard(int r, int c, int n) {
        numRow = r;
        numColumn = c;
        numToWin = n;
        gameBoard = new Character[getNumRows()][getNumColumns()];
        // Initializes the gameBoard
        for (int i = 0; i < getNumRows(); i++) {
            for (int j = 0; j < getNumColumns(); j++) {
                gameBoard[i][j] = ' ';
            }
        }
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
        for (int i = 0; i < getNumRows(); i++) {
            // If column is not full, place the token
            if (gameBoard[i][c].equals(' ')) {
                gameBoard[i][c] = p;
                return;
            }
        }
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
        // Returns the character at the desired position
        return gameBoard[pos.getRow()][pos.getColumn()];
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
