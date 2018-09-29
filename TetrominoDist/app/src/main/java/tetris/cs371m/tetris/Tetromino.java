package tetris.cs371m.tetris;
import java.util.ArrayList;

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
       //System.out.println(X + ", " + Y);
        if (X >= 0 && X < columns && Y >= 0 && Y < rows) {
            /*if (cell != null) {
                System.out.println(Y + ", " + X);
            }*/
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
        //System.out.println(rows + " " + columns);
        //System.out.println(grid);
        for (int row = 0; row < rows; row++) {
            if (this.grid.get(row) != null) {
                for (int column = 0; column < columns; column++) {
                    if (this.grid.get(row).get(column) != null) {
                        // parent.grid.get(Y + row).set(X + column, this.grid.get(row).get(column));
                        //parent.putCell(X+column, Y+row, this.grid.get(row).get(column));
                        // TODO: nullpointerexception for cannotRotate
                        if (parent.grid.get(Y+row) != null) {
                            if (parent.grid.get(Y + row).get(X + column) != null) {
                                return false;
                            }
                        }
                    }
                }
            }
        }

        for (int row = 0; row < rows; row++) {
            if (this.grid.get(row) != null) {
                for (int column = 0; column < columns; column++) {
                    if (this.grid.get(row).get(column) != null) {
                        parent.putCell(X+column, Y+row, this.grid.get(row).get(column));
                    }
                }
            }
        }

        this.parent = parent;
        this.xPos = X;
        this.yPos = Y;
        /*for (int i = 0; i < parent.rows; i++) {
            System.out.println(parent.grid.get(i));
        }*/
        return true;
    }

    // XXX: remove this Tetromino from the parent grid
    // XXX: do nothing if the tetromino has not been added to a larger grid
    public void removeFromGrid() {
        if (parent == null)
            return;

        //System.out.println("before: " + parent.grid);
        //System.out.println("X: " + xPos + " Y: " + yPos);
        for (int row = yPos; row < parent.rows; row++) {
            for (int column = xPos; column < parent.columns; column++) {
                parent.removeCell(column, row);
            }
        }
        //System.out.println("after: " + parent.grid);
    }

    public void printGrid(String name, TGrid grid) {
        System.out.println(name);
        for (int row = 0; row < grid.rows; row++) {
            for (int col = 0; col < grid.columns; col++) {
                TCell cell = grid.getCellAt(col, row);
                System.out.print(cell);
            }
            System.out.println();
        }
        System.out.println();
    }

    // XXX: shift this Tetromino down one unit
    public boolean shiftDown() {
        boolean result = false;

        // XXX: if we fail to move it down then put it back where it was

        return result;
    }

    // XXX: shift this Tetromino right one unit
    // XXX: return true if the tetromino can shift right
    // XXX: false otherwise
    public boolean shiftRight() {
        boolean result = false;

        //if we fail to move it then put it back where it was

        return result;
    }

    // XXX: shift this Tetromino left one unit
    public boolean shiftLeft() {
        boolean result = false;

        //XXX: if we fail to move it down then put it back where it was

        return result;
    }

    // return a transpose of this Tetromino
    private ArrayList<ArrayList<TCell>> transpose() {
        ////take the transpose
        ArrayList<ArrayList<TCell>> transpose = new ArrayList<>();

        for (int column = 0; column < this.columns; column++) {
            ArrayList<TCell> colList = new ArrayList<TCell>();

            for (int row = 0; row < this.rows; row++) {
                if (grid.get(row) != null && grid.get(row).get(column) != null) {
                    colList.add(grid.get(row).get(column));
                } else {
                    colList.add(null);
                }
            }

            transpose.add(colList);
        }

        /*System.out.println(grid);
        System.out.println();
        System.out.println(transpose);*/

        return transpose;
    }

    // XXX: rotate this Tetromino counter clockwise by 90 degree
    // XXX: this rotation can be achieved by first transpose the Tetromino
    // XXX: then reverse each column
    // XXX: return true if the Tetromino can be rotated
    // XXX: false otherwise
    public boolean rotateCounterClockwise() {
        boolean result = false;
        // XXX rotation doesn't mean anything if it is not a square Tetromino
        if (this.rows != this.columns) {
            return result;
        }

        // XXX get the transpose of this Tetromino
        ArrayList<ArrayList<TCell>> transpose = transpose();

        removeFromGrid();
        for (int column = 0; column < this.columns; column++) {
            for(int row = 0; row < this.rows; row++) {
                int transpose_idx = this.columns - 1 - row;

                if (transpose.get(transpose_idx) != null && transpose.get(transpose_idx).get(column) != null) {
                    putCell(column, row, transpose.get(transpose_idx).get(column));
                } else {
                    putCell(column, row, null);
                }

            }
        }

        insertIntoGrid(xPos, yPos, parent);

        //XXX reverse each column

        // If we fail we can't give up so easily, try a "wall kick"
        // http://tetris.wikia.com/wiki/Wall_kick
        // We will only do a simple try:
        // shift 1 space to the right
        // shift 2 space to the right
        // shift 1 space to the left
        // shift 2 space to the left


        // XXX: if we fail to move it down then put it back where it was

        return result;
    }

    // XXX: rotate this Tetromino clockwise by 90 degree
    // XXX: this rotation can be achieved by first transpose the Tetromino
    // XXX: then reverse each row
    public boolean rotateClockwise() {
        boolean result = false;
        // XXX: rotation doesn't mean anything if it is not a square Tetromino

        // XXX: take the transpose

        // XXX: reverse each row

        //assert (false);

        //If we fail we can't give up so easily, try a "wall kick"
        //http://tetris.wikia.com/wiki/Wall_kick
        // We will only do a simple try:
        // shift 1 space to the right
        // shift 2 space to the right
        // shift 1 space to the left
        // shift 2 space to the left


        //if we fail to move it down then put it back where it was

        return result;

    }

    // XXX: attempt to move down this Tetromino as far as possible
    public void zoomDown() {

    }

}
