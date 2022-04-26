package cpsc2150.extendedConnectX.models;

import java.util.*;

/**
 * Game Board is a numRow x numColumn string of characters.
 * GameBoardMem produces a memory efficient game of the extendedConnectX.
 *
 * Initialization ensures:
 *       Game Board contains nothing, empty strings AND [size of gameBoard = numRow * numColumn]
 *
 * Defines: numRow: N - Total number of rows
 *          numColumn: N - Total number of columns
 *          numToWin: N - Total value of numbers to win
 *
 * Constraints:
 *       Map<Character, List<BoardPosition>> gameBoard[0....numRow] AND
 *       Map<Character, List<BoardPosition>> gameBoard[0....numColumn]
 */
public class GameBoardMem extends AbsGameBoard implements IGameBoard {
    /**
     * @invariant MIN_COLUMN < column < MAX_COLUMN AND
     *            MIN_ROW < row < MAX_ROW
     *            [GameBoard have no gaps between tokens]
     *
     * @Correspondence self = [GameBoard of characters from column 0 to column 10] AND
     *                 self.row = row AND self.column = column AND self.numToWin = numToWin AND
     *                 MIN_ROW <= numRow <= MAX_ROW AND
     *                 MIN_COLUMN <= numColumn <= MAX_COLUMN AND
     *                 MIN_NUMTOWIN <= numToWin <= MAX_NUMTOWIN
     */

    Map<Character, List<BoardPosition>> gameBoardMem = new HashMap<Character, List<BoardPosition>>();
    private int numRow;
    private int numColumn;
    private int numToWin;

    /**
     * This Constructor creates a new game board.
     *
     * @post numRow = r AND
     *       numColumn = c AND
     *       numToWin = n
     */
    public GameBoardMem(int r, int c, int n) {
        numRow = r;
        numColumn = c;
        numToWin = n;
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
    @Override
    public void placeToken(char p, int c)  {
        //places the character p in column c. The token will be placed in the lowest available row in column c.
        if (!gameBoardMem.containsKey(p)) {
            gameBoardMem.put(p, new ArrayList<>());
        }
        for (int i = 0; i < getNumRows(); i++) {
            // If column is not full, place the token
            if (whatsAtPos(new BoardPosition(i, c)) == ' ') {
                gameBoardMem.get(p).add(new BoardPosition(i, c));
                break;
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
    @Override
    public char whatsAtPos(BoardPosition pos)  {
        // Returns the character at the desired position
        for (HashMap.Entry<Character, List<BoardPosition>> m : gameBoardMem.entrySet()) {
            if (isPlayerAtPos(pos, m.getKey())) {
                return m.getKey();
            }
        }
        return ' ';
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
    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player) {
        // Checks to see if the token is at the desired position
        if (!gameBoardMem.containsKey(player)) {
            return false;
        }
        for (BoardPosition board : gameBoardMem.get(player)) {
            if (board.equals(pos)) {
                return true;
            }
        }
        return false;
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
