package tetris.cs371m.tetris;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by cody on 9/23/15.
 */
public class Tetromino extends TGrid {

    protected TGrid parent;
    protected int xPos;
    protected int yPos;

    // XXX: initialize the xPos and yPos to -1
    public Tetromino(int columns, int rows) {
        super(columns,rows);
        this.xPos = -1;
        this.yPos = -1;
    }

    // XXX: Put the cell to (X, Y) in the grid.
    // XXX: But do not set the cell position, this should be set by the parent grid
    @Override
    public boolean putCell(int X, int Y, TCell cell) {
        if (X >= 0 && X < columns && Y >= 0 && Y < rows) {
            grid.get(Y).set(X, cell);
            return true;
        }
        return false;
    }

    // XXX: attempt to insert this Tetromino into a larger grid
    // XXX: returns true if successful, else false
    // XXX: return false if the parent is not present (null)
    public boolean insertIntoGrid(int X, int Y, TGrid parent) {
        // XXX: ensure that every spot we need is empty and is on the grid
        // XXX: go ahead and insert the Tetromino by putting the non-null cell to the parent grid
        if (parent == null) {
            return false;
        }

        // check if spot is available
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (getCellAt(column, row) != null) {
                    if (Y + row >= parent.rows || X + column >= parent.columns) {
                        return false;
                    }

                    if (Y + row < 0 || X + column < 0) {
                        return false;
                    }

                    if (parent.getCellAt(X + column, Y + row) != null) {
                        return false;
                    }
                }
            }
        }

        // insert into the game grid
        for (int row = 0; row < rows; row++) {
            if (this.grid.get(row) != null) {
                for (int column = 0; column < columns; column++) {
                    if (getCellAt(column, row) != null) {
                        parent.putCell(X+column, Y+row, getCellAt(column, row));
                    }
                }
            }
        }

        this.parent = parent;
        this.xPos = X;
        this.yPos = Y;

        return true;
    }

    // XXX: remove this Tetromino from the parent grid
    // XXX: do nothing if the tetromino has not been added to a larger grid
    public void removeFromGrid() {
        if (this.parent == null)
            return;

        for (int row = yPos; row < parent.getHeight(); row++) {
            for (int column = xPos; column < parent.getWidth(); column++) {
                this.parent.removeCell(column, row);
            }
        }

        this.parent = null;
    }

    // XXX: shift this Tetromino down one unit
    public boolean shiftDown() {
        boolean result = true;

        // XXX: if we fail to move it down then put it back where it was
        TGrid saveParent = parent;
        removeFromGrid();

        if (!insertIntoGrid(this.xPos, this.yPos+1, saveParent)) {
            insertIntoGrid(this.xPos, this.yPos, saveParent);
            result = false;
        }

        return result;
    }

    // XXX: shift this Tetromino right one unit
    // XXX: return true if the tetromino can shift right
    // XXX: false otherwise
    public boolean shiftRight() {
        boolean result = true;

        //if we fail to move it then put it back where it was
        TGrid saveParent = parent;
        removeFromGrid();

        if (!insertIntoGrid(this.xPos + 1, this.yPos, saveParent)) {
            insertIntoGrid(this.xPos, this.yPos, saveParent);
            result = false;
        }

        return result;
    }

    // XXX: shift this Tetromino left one unit
    public boolean shiftLeft() {
        boolean result = true;

        //if we fail to move it then put it back where it was
        TGrid saveParent = parent;
        removeFromGrid();

        if (!insertIntoGrid(this.xPos - 1, this.yPos, saveParent)) {
            insertIntoGrid(this.xPos, this.yPos, saveParent);
            result = false;
        }

        return result;
    }

    // return a transpose of this Tetromino
    private ArrayList<ArrayList<TCell>> transpose() {
        //take the transpose
        ArrayList<ArrayList<TCell>> transpose = new ArrayList<>();

        for (int column = 0; column < this.columns; column++) {
            ArrayList<TCell> colList = new ArrayList<TCell>();

            for (int row = 0; row < this.rows; row++) {
                if (getCellAt(column, row) != null) {
                    colList.add(getCellAt(column, row));
                } else {
                    colList.add(null);
                }
            }

            transpose.add(colList);
        }

        return transpose;
    }

    // XXX: rotate this Tetromino counter clockwise by 90 degree
    // XXX: this rotation can be achieved by first transpose the Tetromino
    // XXX: then reverse each column
    // XXX: return true if the Tetromino can be rotated
    // XXX: false otherwise
    public boolean rotateCounterClockwise() {
        boolean result = true;
        // XXX rotation doesn't mean anything if it is not a square Tetromino
        if (this.getHeight() != this.getWidth()) {
            return false;
        }

        // XXX get the transpose of this Tetromino
        ArrayList<ArrayList<TCell>> temp = (ArrayList<ArrayList<TCell>>) this.grid.clone();
        TGrid saveParent = parent;
        removeFromGrid();


        //XXX reverse each column
        for (int row = 0; row < this.getWidth(); row++) {
            Collections.reverse(this.grid.get(row));
        }
        ArrayList<ArrayList<TCell>> transpose = transpose();

        this.grid = transpose;

        // If we fail we can't give up so easily, try a "wall kick"
        // http://tetris.wikia.com/wiki/Wall_kick
        // We will only do a simple try:
        // shift 1 space to the right
        // shift 2 space to the right
        // shift 1 space to the left
        // shift 2 space to the left
        if (!insertIntoGrid(this.xPos, this.yPos, saveParent)) {
            result = wallKick(saveParent);
        }


        // XXX: if we fail to move it down then put it back where it was
        if (!result) {
            this.grid = temp;
            insertIntoGrid(this.xPos, this.yPos, saveParent);
        }

        return result;
    }

    // XXX: rotate this Tetromino clockwise by 90 degree
    // XXX: this rotation can be achieved by first transpose the Tetromino
    // XXX: then reverse each row
    public boolean rotateClockwise() {
        boolean result = true;
        // XXX: rotation doesn't mean anything if it is not a square Tetromino
        if (this.getHeight() != this.getWidth()) {
            return false;
        }

        // XXX: take the transpose
        ArrayList<ArrayList<TCell>> transpose = transpose();
        // XXX: reverse each row
        for (int row = 0; row < this.getWidth(); row++) {
            Collections.reverse(transpose.get(row));
        }

        ArrayList<ArrayList<TCell>> temp = (ArrayList<ArrayList<TCell>>) this.grid.clone();
        TGrid saveParent = parent;
        removeFromGrid();

        grid = transpose;
        if (!insertIntoGrid(this.xPos, this.yPos, saveParent)) {
            result = wallKick(saveParent);
        }

        //If we fail we can't give up so easily, try a "wall kick"
        //http://tetris.wikia.com/wiki/Wall_kick
        // We will only do a simple try:
        // shift 1 space to the right
        // shift 2 space to the right
        // shift 1 space to the left
        // shift 2 space to the left
        if (!result) {
            grid = temp;
            insertIntoGrid(this.xPos, this.yPos, saveParent);
        }


        //if we fail to move it down then put it back where it was

        return result;

    }

    // returns true if wall kick is possible; false otherwise
    public boolean wallKick (TGrid parent) {
        // tried just calling shiftLeft/shiftRight, but it messed with insertIntoGrid operations
        if (insertIntoGrid(this.xPos + 1, this.yPos, parent)) {
            return true;
        }
        if (insertIntoGrid(this.xPos + 2, this.yPos, parent)) {
            return true;
        }
        if (insertIntoGrid(this.xPos - 1, this.yPos, parent)) {
            return true;
        }
        if (insertIntoGrid(this.xPos - 2, this.yPos, parent)) {
            return true;
        }

        return false;
    }

    // XXX: attempt to move down this Tetromino as far as possible
    public void zoomDown() {
        TGrid saveParent = parent;
        removeFromGrid();

        int bottom = saveParent.getHeight() - 1;
        boolean result = false;

        for (int i = bottom; i >= 0; i--) {
            if (insertIntoGrid(this.xPos, i, saveParent)) {
                result = true;
                break;
            }
        }

        if (!result) {
            insertIntoGrid(this.xPos, this.yPos, saveParent);
        }
    }

}
