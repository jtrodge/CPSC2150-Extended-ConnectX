package cpsc2150.extendedConnectX.models;

/**
 * This class is used to store information about columns and row
 */
public class BoardPosition {
    /**
     * @invariant Row == 6 AND Column == 9
     *
     */
    private int Row;
    private int Column;


    /**
     * This Constructor creates a game board with the specified column and row.
     *
     * @param r number of rows the board has
     * @param c number of columns the board has
     *
     *
     * @pre r == 6 AND c == 9
     * @post Row = r AND Column = c
     */
    public BoardPosition(int r, int c) {
        Row = r;
        Column = c;
    }

    /**
     * This method gets the value of the Row.
     *
     * @post getRow = Row and
     *       Row = #Row and
     *       Column = #Column
     */
    public int getRow(){
        //returns the row
        return Row;
    }

    /**
     * This method gets the value of the Column.
     *
     * @post getColumn = Column and
     *       Row = #Row and
     *       Column = #Column
     */
    public int getColumn(){
        //returns the column
        return Column;
    }

    /**
     * This method compares the equality of two BoardPositions
     *
     * @return true if [this] BoardPosition is numerically
     * equal to the [other] BoardPosition. False otherwise.
     *
     * @post true if [this] BoardPosition is numerically
     * equal to the [other] BoardPosition. False otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        // checks to see if both objects are not equal
        if (obj.getClass() != getClass()) {
            return false;
        }
        BoardPosition pos = (BoardPosition) obj;
        // returns if both objects are equal
        return (pos.getRow() == getRow() && pos.getColumn() == getColumn());
    }

    /**
     * This method overrides the default implementation of {@code toString} to provide
     * a string representation of the coordinates of the row and column.
     *
     * @return a string representation of the Board positions
     *
     * @post toString = "[row], [column]"
     */
    @Override
    public String toString() {
        // Example: 6,9
        String s = Row + "," + Column;
        return s;
    }

}
