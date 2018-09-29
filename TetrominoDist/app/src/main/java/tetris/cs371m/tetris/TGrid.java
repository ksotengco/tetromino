package tetris.cs371m.tetris;


import java.util.ArrayList;
import java.util.Collections;
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
        this.columns = columns;
        this.rows    = rows;

        this.grid = new ArrayList<ArrayList<TCell>>();

        //Generate an empty grid
        for (int i = 0; i < rows; i++) {
            ArrayList<TCell> colList = new ArrayList<>(rows);
            for (int j = 0; j < columns; j++) {
                colList.add(null);
            }
            this.grid.add(colList);
        }


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
        if (X >= 0 && X < columns && Y >= 0 && Y < rows) {
            if (grid.get(Y) != null) {
                if (grid.get(Y).get(X) != null) {
                    return grid.get(Y).get(X);
                }
            }
        }

        return null;
    }

    // XXX: same as getCellAt(X,Y), except this function also removes it from the grid (by setting the cell to null)
    public TCell extractCellAt(int X, int Y) {
        TCell cell = grid.get(Y).get(X);
        grid.get(Y).set(X, null);
        return cell;
    }

    // XXX: X, Y: position in the grid
    // XXX: insert a cell into the grid
    // XXX: return true if successful, false otherwise
    public boolean putCell(int X, int Y, TCell cell) {
        if (X >= 0 && X < columns) {
            if (Y >= 0 && Y < rows) {
                grid.get(Y).set(X, cell);
                cell.setXPosition(X);
                cell.setYPosition(Y);

                return true;
            }
        }
        return false;
    }

    // XXX: remove a cell that locates at (X, Y) from the grid
    // XXX: return true if successful, false otherwise
    public boolean removeCell(int X, int Y) {
        if (X >= 0 && X < columns) {
            if (Y >= 0 && Y < rows) {
                TCell cell = getCellAt(X, Y);
                if (cell != null) {
                    cell.setXPosition(-1);
                    cell.setYPosition(-1);
                    grid.get(Y).set(X, null);
                    return true;
                }
            }
        }
        return false;
    }

    // XXX: look for the first row that is full and return the row number
    // XXX: returns -1 if not found
    public int getFirstFullRow() {
        int counter = 0;
        for (int y = 0; y < rows; y++) {
            //System.out.println(grid.size());
            if (grid.get(y) != null) {
                for (int x = 0; x < columns; x++) {
                    //System.out.println(grid.get(y).size());
                    if (grid.get(y).get(x) != null) {
                        counter++;
                    }
                }
            }

            if (counter == columns)
                return y;
            counter = 0;
        }

        return -1;
    }

    //XXX: deletes a row with row number `row`, shifts everything down
    //XXX: return true if successful, false otherwise
    public boolean deleteRow(int row) {
        if (row >= 0 || row < rows) {
            for (int x = 0; x < columns; x++) {
                TCell cell = grid.get(row).get(x);
                if (cell != null) {
                    cell.setXPosition(-1);
                    cell.setYPosition(-1);
                    removeCell(x, row);
                }
            }

            int counter = 0;
            for (int y = row - 1; y >=0; y--) {
                for (int x = 0; x < columns; x++) {
                    if (grid.get(y).get(x) != null) {
                        putCell(x, y+1, grid.get(y).get(x));
                        removeCell(x, y);
                    } else {
                        counter++;
                    }
                }

                if (counter == columns) {
                    break;
                }

                counter = 0;
            }

            return true;
        }
        return false;
    }

    //XXX: delete all cells in the grid
    public void clear() {
        TCell cell;
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                cell = grid.get(y).get(x);
                if (cell != null) {
                    cell.setXPosition(-1);
                    cell.setYPosition(-1);
                    grid.get(y).set(x, null);
                }
            }
        }
    }

}
