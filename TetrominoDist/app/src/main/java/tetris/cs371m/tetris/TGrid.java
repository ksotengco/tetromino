package tetris.cs371m.tetris;


import java.util.ArrayList;
import java.util.function.Function;

/**
 * Created by cody on 9/23/15.
 */
public class TGrid {

    protected ArrayList<ArrayList<TCell>> grid;

    protected int columns;
    protected int rows;

    // Do not modify. You will need this in the next homework
    public interface CellVisitor {
        public void visitCell(TCell cell);
    }

    // XXX: Constructor: Generate an empty grid(Fill all the cell as null)
    public TGrid(int columns, int rows) {

        //Generate an empty grid

    }

    // Do not modify. You will need this in the next homework
    public void visitCells(CellVisitor visitor) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++)
                visitor.visitCell(grid.get(i).get(j));
        }
    }

    // XXX: get the width of the grid
    public int getWidth() {
        return this.columns;
    }

    // XXX: get the height of the grid
    public int getHeight() {
        return this.rows;
    }

    // XXX: return the cell at a position (X,Y)
    public TCell getCellAt(int X, int Y) {

       return null;
    }

    // XXX: same as getCellAt(X,Y), except this function also removes it from the grid (by setting the cell to null)
    public TCell extractCellAt(int X, int Y) {

        return null;
    }

    // XXX: X, Y: position in the grid
    // XXX: insert a cell into the grid
    // XXX: return true if successful, false otherwise
    public boolean putCell(int X, int Y, TCell cell) {

        return false;
    }

    // XXX: remove a cell that locates at (X, Y) from the grid
    // XXX: return true if successful, false otherwise
    public boolean removeCell(int X, int Y) {

        return false;
    }

    // XXX: look for the first row that is full and return the row number
    // XXX: returns -1 if not found
    public int getFirstFullRow() {

        return -1;
    }

    //XXX: deletes a row with row number `row`, shifts everything down
    //XXX: return true if successful, false otherwise
    public boolean deleteRow(int row) {

        return false;
    }

    //XXX: delete all cells in the grid
    public void clear() {

    }

}
