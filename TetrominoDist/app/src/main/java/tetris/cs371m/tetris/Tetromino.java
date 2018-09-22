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
        return true;
    }

    // XXX: attempt to insert this Tetromino into a larger grid
    // XXX: returns true if successful, else false
    // XXX: return false if the parent is not present (null)
    public boolean insertIntoGrid(int X, int Y, TGrid parent) {
        // XXX: ensure that every spot we need is empty and is on the grid

        // XXX: go ahead and insert the Tetromino by putting the non-null cell to the parent grid

        return true;
    }

    // XXX: remove this Tetromino from the parent grid
    // XXX: do nothing if the tetromino has not been added to a larger grid
    public void removeFromGrid() {

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

        // XXX get the transpose of this Tetromino

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