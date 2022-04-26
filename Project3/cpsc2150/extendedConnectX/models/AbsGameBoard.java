package cpsc2150.extendedConnectX.models;

/**
 * @return a string representation of the gameBoard
 *
 * @post toString = [A string representation of the gameBoard]
 */
public abstract class AbsGameBoard implements IGameBoard{
    public String toString() {
        String str = "|";
        //prints top line of gameBoard
        for(Integer i = 0; i < getNumColumns(); i++) {
            if(i < 10) {
                // Placement of where the token is being placed
                str = str.concat(" ");
            }
            // Prints out the desired numbers 0-8
            str = str.concat(i.toString());
            str = str.concat("|");
        }
        str = str.concat("\n");
        //prints varying part of gameBoard
        for(Integer i = (getNumRows() - 1); i >= 0; i--) {
            for(Integer k = 0; k < getNumColumns(); k++) {
                BoardPosition pos = new BoardPosition(i, k);
                str = str.concat("|");
                // Converts char to String
                String temp = String.valueOf(whatsAtPos(pos));
                str = str.concat(temp.toString());
                // Placement of where the token is being placed
                str = str.concat(" ");
            }
            str = str.concat("|");
            str = str.concat("\n");
        }
        return str;
    }
}