package cpsc2150.extendedConnectX.models;

/**
 * Game Board is a 6x9 string of characters.
 *
 * Initialization ensures:
 *       Game Board contains nothing, empty strings.
 *
 * Defines: numRow: N - Total number of rows
 *          numColumn: N - Total number of columns
 *          numToWin: N - Total value of numbers to win
 *
 * Constraints:
 *       MIN_ROW <= self.row <= MAX_ROW
 *       MIN_COLUMN <= self.column <= MAX_COLUMN
 *       MIN_NUMTOWIN <= self.numToWin <= MAX_COLUMN
 */
public interface IGameBoard {
 int MIN_ROW = 3;
 int MAX_ROW = 50;
 int MIN_COLUMN = 3;
 int MAX_COLUMN = 50;
 int MIN_NUMTOWIN = 3;
 int MAX_NUMTOWIN = 25;

    /**
     * This method checks if a column is full or not.
     *
     * @param c the column position of the game board
     *
     * @return true if the column can accept another token.
     * false otherwise.
     *
     * @pre  0 <= c < MAX_COLUMN
     * @post Column = c AND game = #game AND
     *       checkTie iff game = [Blank spaces available]
     */
    public default boolean checkIfFree(int c)  {
       BoardPosition pos = null;
       char space = ' ';
       for (int i = 0; i < this.getNumRows(); i++) {
          pos = new BoardPosition(i, c);
          // converts char to Character
          Character temp = Character.valueOf(whatsAtPos(pos));
          // true if there is no character in that space
          if (temp.equals(' ')) {
             return true;
          }
       }
       return false;
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
    public void placeToken(char p, int c);

    /**
     * This method checks to see if a player has won the game.
     *
     * @param c number of columns
     *
     * @return True if the last token placed in the column resulted in a win, false otherwise.
     *
     * @pre c > 0 [pos is the gameBoard position on the latest play]
     * @post Column = #c AND
     *        checkForWin iff [checkHorizWin equals true] OR
     *       [checkVertWin equals true] OR
     *       [checkDiagWin equals true] OR
     */
    public default boolean checkForWin(int c) {
       BoardPosition pos = null;
       BoardPosition temp = null;
       char p = ' ';
       for (int i = 0; i < getNumRows(); i++) {
          temp = new BoardPosition(i, c);
          // Converts char to Character
          Character temp1 = Character.valueOf(whatsAtPos(temp));
          // Stops checking once an available space is reached
          if (temp1.equals(' ')) {
             pos = new BoardPosition(i - 1, c);
             p = whatsAtPos(pos);
             break;
          }
          // Stops checking once the end of the row is reached
          if (i == (getNumRows() - 1)) {
             pos = new BoardPosition(i, c);
             p = whatsAtPos(pos);
             break;
          }
       }
       if (checkHorizWin(pos, p)) {
          return true;
       }
       if (checkVertWin(pos, p)) {
          return true;
       }
       if (checkDiagWin(pos, p)) {
          return true;
       }
       return false;
    }

    /**
     *  This method check to see if the game ended in a tie.
     *
     * @return True if the game is tied, false otherwise.
     *
     * @pre [game is not won]
     * @post checkTie iff game = [No blank spaces available]
     */
    public default boolean checkTie()  {
       BoardPosition pos = null;
       for (int i = 0; i < getNumColumns(); i++) {
          for (int j = 0; j < getNumRows(); j++) {
             pos = new BoardPosition(j, i);
             // converts char to Character
             Character temp = Character.valueOf(whatsAtPos(pos));
             // if there are any more available spaces
             // return false, else true
             if (temp.equals(' ')) {
                return false;
             }
          }
       }
       return true;
    }

    /**
     * This method checks to see if a player has won horizontally.
     *
     * @param pos The row and column position of the board.
     * @param p The X and O tokens.
     *
     * @return true if a player has won horizontally, false otherwise.
     *
     * @pre 0 <= lastPos.getRow() < MAX_ROW AND 0 <= lastPos.getcolumn() < MAX_COLUMN
     *      AND [p is valid] AND [lastPos was the location where the last marker was placed in]
     *      AND [pos is within valid bounds] AND [pos is a position on the latest play]
     * @post gameBoard = #gameBoard AND [finds whether player p won a horizontal victory at pos]
     */
    public default boolean checkHorizWin(BoardPosition pos, char p) {
       int counter = 0;
       int r = pos.getRow();
       int c = pos.getColumn();
       BoardPosition temp;
       // Checks pos to the right
       while (true) {
          temp = new BoardPosition(r, c);
          Character temp1 = Character.valueOf(whatsAtPos(temp));
          // counter checks tokens to the right
          if (temp1.equals(p)) {
             counter++;
             // make sure c does not go out of bounds
             if ((c + 1) < getNumRows()) {
                c++;
             }
             else {
                break;
             }
          }
          else {
             c = pos.getColumn();
             break;
          }
       }
       // checks to the left of pos
       counter--;
       while (true) {
          temp = new BoardPosition(r, c);
          Character temp1 = Character.valueOf(whatsAtPos(temp));
          // counter checks tokens to the left
          if (temp1.equals(p)) {
             counter++;
             // makes sure c does not go out of bounds
             if ((c - 1) >= 0) {
                c--;
             }
             else {
                break;
             }
          }
          else {
             break;
          }
       }
       if (counter >= getNumToWin()) {
          return true;
       }
       return false;
    }

    /**
     * This method checks to see if a player has won vertically.
     *
     * @param pos The row and column position of the board.
     * @param p The X and O tokens.
     *
     * @return true if a player has won vertically, false otherwise.
     *
     * @pre pos > 0 AND [p is valid] AND 0 <= lastPos.getRow() < MAX_ROW AND
     *      0 <= lastPos.getcolumn() < MAX_COLUMN AND
     *      [lastPos was the location where the last marker was place in] AND
     *      [pos is within valid bounds] AND [pos is a position on the latest play]
     * @post checkVertWin iff [p equals 5 matching characters consecutively in a column]
     */
    public default boolean checkVertWin(BoardPosition pos, char p)  {
       //checks to see if the last token placed (which was placed in position pos by player p) resulted in 5 in a row vertically.
       //Returns true if it does, otherwise false
       int counter = 0;
       int r = pos.getRow();
       int c = pos.getColumn();
       BoardPosition temp;
       // Checks down from pos
       while (true) {
          temp = new BoardPosition(r, c);
          Character temp1 = Character.valueOf(whatsAtPos(temp));
          // counter checks tokens from top to bottom
          if (temp1.equals(p)) {
             counter++;
             // makes sure r does not go out of bounds
             if ((r - 1) >= 0) {
                r--;
             }
             else {
                break;
             }
          }
          else {
             break;
          }
       }
       if (counter >= getNumToWin()) {
          return true;
       }
       else { return false; }
    }

    /**
     * This method checks to see if a player has won diagonally
     *
     * @param pos The row and column position of the board.
     * @param p The X and O tokens.
     *
     * @return true if a player has won diagonally, false otherwise.
     *
     * @pre pos > 0 AND [p is valid] AND [pos is a position on the latest play] AND
     *      [pos is within valid bounds]
     * @post gameBoard = #gameBoard AND
     *       checkDiagWin iff [p matches 5 in a row going in a positive slope OR
     *                         5 in a row going in a negative slope]
     */
    public default boolean checkDiagWin(BoardPosition pos, char p)  {
       int counter1 = 1; // Up and to the right; Down and to the left;
       int counter2 = 1; // Up and to the left; Down and to the right;
       int r = pos.getRow();
       int c = pos.getColumn();
       BoardPosition temp;

       // checks up and to the right
       counter1--;
       while (true) {
          temp = new BoardPosition(r, c);
          Character temp1 = Character.valueOf(whatsAtPos(temp));
          if (temp1.equals(p)) {
             counter1++;
             // checks r does not go out of bounds
             if ((r + 1) < getNumRows()) {
                r++;
             } else {
                break;
             }
             // checks c does not go out of bounds
             if ((c + 1) < getNumColumns()) {
                c++;
             } else {
                break;
             }
          }
          else { break; }
       }

       r = pos.getRow();
       c = pos.getRow();
       // checks up and to the left
       counter2--;
       while (true) {
          temp = new BoardPosition(r, c);
          Character temp1 = Character.valueOf(whatsAtPos(temp));
          if (temp1.equals(p)) {
             counter2++;
             // makes sure r does not go out of bounds
             if ((r + 1) < getNumRows()) {
                r++;
             }
             else {
                break;
             }
             // makes sure c does not go out of bounds
             if ((c - 1) >= 0) {
                c--;
             }
             else {
                break;
             }
          }
          else {
             break;
          }
       }

       r = pos.getRow();
       c = pos.getColumn();

       // checks down and to the left
       counter1--;
       while (true) {
          temp = new BoardPosition(r, c);
          Character temp1 = Character.valueOf(whatsAtPos(temp));
          if (temp1.equals(p)) {
             counter1++;
             // makes sure r does not go out of bounds
             if ((r - 1) >= 0) {
                r--;
             }
             else {
                break;
             }
             // makes sure c does not go out of bounds
             if ((c - 1) >= 0) {
                c--;
             }
             else {
                break;
             }
          }
          else {
             break;
          }
       }

       r = pos.getRow();
       c = pos.getColumn();
       // checks down and to the right
       counter2--;
       while (true) {
          temp = new BoardPosition(r, c);
          Character temp1 = Character.valueOf(whatsAtPos(temp));
          if (temp1.equals(p)) {
             counter2++;
             // makes sure r is in bounds
             if ((r - 1) >= 0) {
                r--;
             }
             else {
                break;
             }
             // makes sure c is in bounds
             if ((c + 1) < getNumColumns()) {
                c++;
             }
             else {
                break;
             }
          }
          else {
             break;
          }
       }

       r = pos.getRow();
       c = pos.getColumn();

       if ((counter1 >= getNumToWin()) || (counter2 >= getNumToWin())) {
          return true;
       }
       else { return false; }
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
    public char whatsAtPos(BoardPosition pos);

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
    public boolean isPlayerAtPos(BoardPosition pos, char player);

    /**
     *
     * @return number of rows
     *
     * @post getNumRows = [number to rows]
     */
    public int getNumRows();

    /**
     *
     * @return number of columns
     *
     * @post getNumColumns = [number of columns]
     */
    public int getNumColumns();

    /**
     *
     * @return number to win
     *
     * @post getNumToWin = [number to win]
     */
    public int getNumToWin();
}
