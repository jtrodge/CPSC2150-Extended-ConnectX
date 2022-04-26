package cpsc2150.extendedConnectX.models;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class TestGameBoardMem {
    // Function that creates the Object
    private IGameBoard makeBoard(int userR, int userC, int userT) {
        return new GameBoardMem(userR, userC, userT);
    }

    // Function that creates the board as a String
    // private helper method to create expected version of GameBoard to compare
    private String compareString(char[][] board, int row, int col) {
        String compare = "";
        for (int i = 0; i < col; i++) {
            if (i > 9) {
                compare += "|" + i;
            }
            else {
                compare += "| " + i;
            }
        }
        compare += "|\n";

        for (int i = row - 1; i > -1; i--) {
            compare += "|";
            for (int j = 0; j < col; j++) {
                compare += " " + board[i][j] + "|";
            }
            compare += "\n";
        }
        return compare;
    }

    @Test
    // Checks 3x3 square board
    public void test_constructor_SquareMinimum_BoardSize() {
        char[][] board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
        IGameBoard gameBoard = makeBoard(3, 3, 3);
        assertEquals(gameBoard.toString(), compareString(board, 3, 3));
    }

    @Test
    // Checks 100x100 square board
    public void test_constructor_SquareMax_BoardSize() {
        char[][] board = new char[100][100];
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                board[i][j] = ' ';
            }
        }
        IGameBoard gameBoard = makeBoard(100, 100, 25);
        assertEquals(gameBoard.toString(), compareString(board, 100, 100));
    }

    @Test
    // Checks 100x100 square board
    public void test_constructor_Square50x50_BoardSize() {
        char[][] board = new char[50][50];
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                board[i][j] = ' ';
            }
        }
        IGameBoard gameBoard = makeBoard(50, 50, 15);
        assertEquals(gameBoard.toString(), compareString(board, 50, 50));
    }

    @Test
    // Checks checkIfFree functional and position is empty
    public void test_checkIfFree_columnEmpty() {
        IGameBoard gameBoard = makeBoard(8, 8, 4);
        assertTrue(gameBoard.checkIfFree(0));
    }

    @Test
    // Checks checkIfFree functional and column is not full
    public void test_checkIfFree_column_not_full() {
        IGameBoard gameBoard = makeBoard(8, 8, 4);
        for (int i = 0; i < 7; i++) {
            gameBoard.placeToken('X', 0);
        }
        assertTrue(gameBoard.checkIfFree(0));
    }

    @Test
    // Checks checkIfFree functional and position is full
    public void test_checkIfFree_columnFull() {
        IGameBoard gameBoard = makeBoard(8, 8, 4);
        for (int i = 0; i < 8; i++) {
            gameBoard.placeToken('X', 0);
        }
        assertTrue(!gameBoard.checkIfFree(0));
    }

    @Test
    // Checks horizontal win at the beginning
    public void test_checkHorizWin_beginning_row0() {
        IGameBoard gameBoard = makeBoard(5, 5, 3);
        BoardPosition pos = new BoardPosition(0, 0);
        for (int i = 0; i < 3; i++) {
            gameBoard.placeToken('X', i);
        }
        assertTrue(gameBoard.checkHorizWin(pos, 'X'));
    }

    @Test
    // Checks horizontal win at the beginning of the row
    public void test_checkHorizWin_beginningRow() {
        BoardPosition pos = new BoardPosition(0, 0);
        IGameBoard gameBoard = makeBoard(5, 5, 5);
        for (int i = 0; i < 5; i++) {
            gameBoard.placeToken('X', i);
        }
        assertTrue(gameBoard.checkHorizWin(pos, 'X'));
    }

    @Test
    // Checks horizontal win that does not exist from beginning of row
    public void test_checkHorizWin_beginningRowFalse() {
        IGameBoard gameBoard = makeBoard(5, 5, 3);
        BoardPosition pos = new BoardPosition(0, 0);
        for (int i = 0; i < 2; i++) {
            gameBoard.placeToken('X', i);
        }
        for (int i = 2; i < 4; i++) {
            gameBoard.placeToken('O', i);
        }
        assertTrue(!gameBoard.checkHorizWin(pos, 'X'));
    }

    @Test
    // Checks horizontal win that does not exist from beginning of row
    public void test_checkHorizWin_beginningRowFalseEnd() {
        IGameBoard gameBoard = makeBoard(5, 5, 3);
        BoardPosition pos = new BoardPosition(0, 4);
        for (int i = 1; i < 3; i++) {
            gameBoard.placeToken('X', i);
        }
        for (int i = 3; i < 5; i++) {
            gameBoard.placeToken('O', i);
        }
        assertTrue(!gameBoard.checkHorizWin(pos, 'o'));
    }

    // Tests a checkVertWin from the top of a vertical win
    @Test
    public void test_checkVertWin_from_top(){
        IGameBoard gameBoard = makeBoard(5, 5, 3);
        BoardPosition pos = new BoardPosition (2,0);
        for(int i = 0; i < 3; i++)
            gameBoard.placeToken('X', 0);
        assertTrue(gameBoard.checkVertWin( pos, 'X'));
    }


    // Tests a checkVertWin for row 0 from empty spot above top spot,
    // with spots below
    @Test
    public void test_checkVertWin_false_with_empty_spot_above(){
        IGameBoard gameBoard = makeBoard(5, 5, 3);
        BoardPosition pos = new BoardPosition (4,0);
        for(int i = 0; i < 2; i++)
            gameBoard.placeToken('O', 0);
        for(int i = 2; i < 4; i++)
            gameBoard.placeToken('X', 0);
        assertTrue(!gameBoard.checkVertWin(pos, 'X'));

    }

    // Tests a checkVertWin for row 2 from top spot, with spots below
    @Test
    public void test_checkVertWin_false_top_with_spots_below(){
        BoardPosition pos = new BoardPosition (3,0);
        IGameBoard gameBoard = makeBoard(5, 5, 3);
        for(int i = 0; i < 2; i++)
            gameBoard.placeToken('O', 2);
        for(int i = 2; i < 4; i++)
            gameBoard.placeToken('X', 2);
        assertTrue(!gameBoard.checkVertWin( pos, 'X'));
    }

    // Tests checkVertWin for row 0 from the top, with none below
    // checkVertWin should not  check non existing spots which would cause a null ptr exception
    @Test
    public void test_checkVertWin_false_with_empty_board(){
        BoardPosition pos = new BoardPosition (0,0);
        IGameBoard gameBoard = makeBoard(5, 5, 3);
        assertTrue(!gameBoard.checkVertWin( pos, 'X'));
    }

    // Tests checkDiagWin for row 0 from the top, with none below
    // checDiagWin should not  check non existing spots which would cause a null ptr exception
    @Test
    public void test_checkDiagWin_false_with_empty_board(){
        BoardPosition pos = new BoardPosition (0,0);
        IGameBoard gameBoard = makeBoard(5, 5, 3);
        assertTrue(!gameBoard.checkDiagWin( pos, 'X'));
    }

    // Tests diagonal bottom left to top right
    @Test
    public void test_checkDiagWin_bottom_left_to_topRight(){
        BoardPosition pos = new BoardPosition (2,2);
        IGameBoard gameBoard = makeBoard(5, 5, 3);
        for(int i = 0; i < 3; i++)
            gameBoard.placeToken('X', i);
        for(int i = 1; i < 3; i++)
            gameBoard.placeToken('X', i);
        gameBoard.placeToken('X', 2);
        assertTrue(gameBoard.checkDiagWin(pos,'X'));
    }

    // Tests diagonal bottom right to top left
    @Test
    public void test_checkDiagWin_bottom_right_to_topLeft(){
        BoardPosition pos = new BoardPosition (2,0);
        IGameBoard gameBoard = makeBoard(5, 5, 3);
        for(int i = 0; i < 3; i++)
            gameBoard.placeToken('X', i);
        for(int i = 0; i < 2; i++)
            gameBoard.placeToken('X', i);
        gameBoard.placeToken('X', 0);
        assertTrue(gameBoard.checkDiagWin(pos,'X'));
    }

    // Tests diagonal bottom left to top right with spots filled under it
    @Test
    public void test_checkDiagWin_bottom_left_to_top_right_filled_under(){
        BoardPosition pos = new BoardPosition (4,4);
        IGameBoard gameBoard = makeBoard(5, 5, 3);
        for(int i = 0; i < 5; i++){
            gameBoard.placeToken('O', i);
            gameBoard.placeToken('O', i);
        }
        for(int i = 2; i < 5; i++)
            gameBoard.placeToken('X', i);
        for(int i = 3; i < 5; i++)
            gameBoard.placeToken('X', i);
        gameBoard.placeToken('X', 4);
        assertTrue(gameBoard.checkDiagWin(pos,'X'));
    }


    // makes sure the bottom left to top right check doesn't work on not enough chars
    @Test
    public void test_checkDiagWin_false_bottom_left_to_top_right_insufficient_chars(){
        BoardPosition pos = new BoardPosition (0,0);
        IGameBoard gameBoard = makeBoard(5, 5, 3);
        for(int i = 0; i < 2; i++) {
            gameBoard.placeToken('X', i);
        }
        gameBoard.placeToken('X', 1);
        assertFalse(gameBoard.checkDiagWin(pos, 'X'));
    }

    // makes sure the bottom left to top right check doesn't work on not enough chars
    @Test
    public void test_checkDiagWin_false_top_left_to_bottom_right_insufficient_chars(){
        IGameBoard gameBoard = makeBoard(5, 5, 3);
        for(int i = 1; i < 3; i++)
            gameBoard.placeToken('X', i);
        gameBoard.placeToken('X', 1);
        BoardPosition pos = new BoardPosition (1,1);
        assertTrue(!gameBoard.checkDiagWin(pos, 'X'));
    }

    // checkDiagWin should be false in the event of a full board (tie) without diagonals
    @Test
    public void test_checkDiagWin_false_full_tied_board()
    {
        IGameBoard gameBoard = makeBoard(5, 5, 3);
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++) {
                if (i%2 == 1)
                    gameBoard.placeToken('X', j);
                else
                    gameBoard.placeToken('O', j);
            }
        BoardPosition pos = new BoardPosition (2,2);
        assertTrue(!gameBoard.checkDiagWin(pos, 'O'));
    }

    //  checkTie should be TRUE for a full board
    @Test
    public void test_checkTie_true_full_board(){
        IGameBoard gameBoard = makeBoard(5, 5, 3);
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
                gameBoard.placeToken('X', j);
        assertTrue(gameBoard.checkTie());
    }

    // checkTie should be false for an empty board
    @Test
    public void test_checkTie_false_empty_board(){
        IGameBoard gameBoard = makeBoard(5, 5, 3);
        assertTrue(!gameBoard.checkTie());
    }

    // check checkTie for a board that has some columns full
    @Test
    public void test_checkTie_some_full_columns(){
        IGameBoard gameBoard = makeBoard(5, 5, 3);
        for(int j = 0; j < 3; j++)
            for(int i = 0; i < 5; i++)
                gameBoard.placeToken('X', j);
        assertTrue(!gameBoard.checkTie());
    }

    // test checkTie for a full board of alternating chars
    @Test
    public void test_checkTie_full_alternating_board()
    {
        IGameBoard gameBoard = makeBoard(5, 5, 3);
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++) {
                if (i%2 == 1)
                    gameBoard.placeToken('X', j);
                else
                    gameBoard.placeToken('O', j);
            }
        assertTrue(gameBoard.checkTie());
    }

    // tests WhatsAtPos on an empty board for an empty space
    @Test
    public void test_whatsAtPos_empty_space_empty_board(){
        IGameBoard gameBoard = makeBoard(5, 5, 3);
        BoardPosition pos = new BoardPosition(0,0);
        assertEquals(' ', gameBoard.whatsAtPos(pos));
    }

    // tests WhatsAtPos on board with one row full on an empty space
    @Test
    public void test_whatsAtPos_one_full_row_empty_space(){
        IGameBoard gameBoard = makeBoard(5, 5, 3);
        for(int i = 0; i < 5; i++)
            gameBoard.placeToken('X', i);
        BoardPosition pos = new BoardPosition(1,0);
        assertEquals(' ', gameBoard.whatsAtPos(pos));
    }

    // tests WhatsAtPos on an empty space on an almost full board
    @Test
    public void test_whatsAtPos_almost_full_board_empty_space(){
        IGameBoard gameBoard = makeBoard(5, 5, 3);
        for(int j = 0; j < 4; j++)
            for(int i = 0; i < 5; i++)
                gameBoard.placeToken('X', i);
        for(int i = 0; i < 4; i++)
            gameBoard.placeToken('X', i);
        BoardPosition pos = new BoardPosition(4,4);
        assertEquals(' ', gameBoard.whatsAtPos(pos));
    }

    // Tests WhatsAtPos on the only spot on the board with a character
    @Test
    public void test_whatsAtPos_one_char_on_board(){
        BoardPosition pos = new BoardPosition(0,3);
        IGameBoard gameBoard = makeBoard(5, 5, 3);
        gameBoard.placeToken('X', 3);
        assertEquals('X', gameBoard.whatsAtPos(pos));
    }


    // Tests WhatsAtPos on empty spot surrounded by chars
    @Test
    public void test_whatsAtPos_spot_surrounded_by_chars(){
        IGameBoard gameBoard = makeBoard(5, 5, 3);
        gameBoard.placeToken('O', 0);
        gameBoard.placeToken('X', 0);
        gameBoard.placeToken('O', 0);
        BoardPosition pos = new BoardPosition(1,0);
        assertEquals('X', gameBoard.whatsAtPos(pos));
    }

    // test isPlayerAtPos should be false on an empty spot on an empty  board
    @Test
    public void test_isPlayerAtPos_false_empty_spot_empty_board() {
        IGameBoard gameBoard = makeBoard(5, 5, 3);
        BoardPosition pos = new BoardPosition(0,0);
        assertFalse(gameBoard.isPlayerAtPos(pos, 'X'));

    }

    // test isPlayerAtPos on a board with only one char on the board
    @Test
    public void test_isPlayerAtPos_one_char_on_board() {
        IGameBoard gameBoard = makeBoard(5, 5, 3);
        gameBoard.placeToken('O',4);
        BoardPosition pos = new BoardPosition(0,4);
        assertTrue(gameBoard.isPlayerAtPos(pos, 'O'));

    }

    // test isPlayerAtPos on a board with only one full row
    @Test
    public void test_isPlayerAtPos_one_filled_row() {
        IGameBoard gameBoard = makeBoard(5, 5, 3);
        for(int i = 0; i < 5; i++)
            gameBoard.placeToken('X', i);
        BoardPosition pos = new BoardPosition(0,1);
        assertTrue(gameBoard.isPlayerAtPos(pos, 'X'));

    }


    // test isPlayerAtPos on a almost full board with one empty space
    @Test
    public void test_isPlayerAtPos_false_almost_full_board_empty_space() {
        IGameBoard gameBoard = makeBoard(5, 5, 3);
        for(int j = 0; j < 4; j++)
            for(int i = 0; i < 5; i++)
                gameBoard.placeToken('X', i);
        for(int i = 0; i < 4; i++)
            gameBoard.placeToken('X', i);
        BoardPosition pos = new BoardPosition(4,4);
        assertFalse(gameBoard.isPlayerAtPos(pos, 'O'));

    }

    // test isPlayerAtPos on a almost full board with one empty space
    @Test
    public void test_isPlayerAtPos_full_board() {
        IGameBoard gameBoard = makeBoard(5, 5, 3);
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
                gameBoard.placeToken('X', j);
        BoardPosition pos = new BoardPosition(4,4);
        assertTrue(gameBoard.isPlayerAtPos(pos,'X'));

    }

    // test placeToken on empty board
    @Test
    public void test_placeToken_on_empty_board(){
        IGameBoard gameBoard = makeBoard(5, 5, 3);
        gameBoard.placeToken('X', 0);
        BoardPosition pos = new BoardPosition(0,0);
        assertEquals('X', gameBoard.whatsAtPos(pos));
    }

    // test placeToken on partly filled column
    @Test
    public void test_placeToken_in_partly_filled_column(){
        IGameBoard gameBoard = makeBoard(5, 5, 3);
        gameBoard.placeToken('O', 0);
        gameBoard.placeToken('X', 0);
        BoardPosition pos = new BoardPosition(1,0);
        assertEquals('X', gameBoard.whatsAtPos(pos));
    }

    // test placeToken to fill up a column completely
    @Test
    public void test_placeToken_fill_up_column(){
        IGameBoard gameBoard = makeBoard(5, 5, 3);
        for(int i = 0; i < 4; i++)
            gameBoard.placeToken('O', 0);
        gameBoard.placeToken('X', 0);
        BoardPosition pos = new BoardPosition(4,0);
        assertEquals('X', gameBoard.whatsAtPos(pos));
    }

    // tests placeToken to fill up a row completely
    @Test
    public void test_placeToken_to_fill_up_row(){
        IGameBoard gameBoard = makeBoard(5, 5, 3);
        for(int i = 0; i < 4; i++)
            gameBoard.placeToken('O', i);
        gameBoard.placeToken('X', 4);
        BoardPosition pos = new BoardPosition(0,4);
        assertEquals('X', gameBoard.whatsAtPos(pos));
    }

    // tests placeToken to fill up a board completely
    @Test
    public void test_placeToken_to_fill_board(){
        IGameBoard gameBoard = makeBoard(5, 5, 3);
        for(int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == 4 && j == 4)
                    gameBoard.placeToken('X', 4);
                else
                    gameBoard.placeToken('O', j);
            }
        }
        BoardPosition pos = new BoardPosition(4,4);
        assertEquals('X', gameBoard.whatsAtPos(pos));
    }
}
