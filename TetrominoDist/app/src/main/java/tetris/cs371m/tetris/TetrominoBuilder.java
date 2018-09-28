package tetris.cs371m.tetris;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by cody on 9/23/15.
 *
 * http://tetris.wikia.com/wiki/Tetromino
 */
public class TetrominoBuilder {

    //Feel free to modify these colors
    //http://www.colorpicker.com/
    public static final int IColor = Color.parseColor("#96E1FA");
    public static final int JColor = Color.parseColor("#304FC9");
    public static final int LColor = Color.parseColor("#F7CB05");
    public static final int OColor = Color.parseColor("#FFFF0D");
    public static final int SColor = Color.parseColor("#6ED654");
    public static final int TColor = Color.parseColor("#C267DB");
    public static final int ZColor = Color.parseColor("#F54747");

    // do not modify this seed
    private static int seed = 3;

    protected static Random rand = new Random(seed);

    // XXX: fill in the cell for each Tetromino

    public static Tetromino I() {
        Tetromino tet = new Tetromino(4,4);
        for (int i = 0; i < 4; i++) {
            TCell cell = new TCell(IColor);
            tet.putCell(i, 1, cell);
        }
        return tet;
    }

    public static Tetromino J() {
        Tetromino tet = new Tetromino(3,3);
        for (int i = 0; i < 3; i++) {
            TCell cell = new TCell(JColor);
            tet.putCell(i, 1, cell);
        }

        TCell cell = new TCell(JColor);
        tet.putCell(0,0, cell);

        return tet;
    }

    public static Tetromino L() {
        Tetromino tet = new Tetromino(3,3);
        for (int i = 0; i < 3; i++) {
            TCell cell = new TCell(LColor);
            tet.putCell(i, 1, cell);
        }

        TCell cell = new TCell(LColor);
        tet.putCell(2,0, cell);

        return tet;
    }

    public static Tetromino O() {
        Tetromino tet = new Tetromino(4,3);
        tet.putCell(1, 0, new TCell(OColor));
        tet.putCell(2, 0, new TCell(OColor));
        tet.putCell(1, 1, new TCell(OColor));
        tet.putCell(2, 1, new TCell(OColor));

        return tet;
    }

    public static Tetromino S() {
        Tetromino tet = new Tetromino(3,3);

        return tet;
    }

    public static Tetromino T() {
        Tetromino tet = new Tetromino(3,3);

        return tet;
    }

    public static Tetromino Z() {
        Tetromino tet = new Tetromino(3,3);

        return tet;
    }

    // Do not modify this function
    public static Tetromino Random() {
        int num = TetrominoBuilder.rand.nextInt(7);
        if(num == 0) {
            return TetrominoBuilder.I();
        }
        else if(num == 1) {
            return TetrominoBuilder.J();
        }
        else if(num == 2) {
            return TetrominoBuilder.L();
        }
        else if(num == 3) {
            return TetrominoBuilder.O();
        }
        else if(num == 4) {
            return TetrominoBuilder.S();
        }
        else if(num == 5) {
            return TetrominoBuilder.T();
        }
        else if(num == 6) {
            return TetrominoBuilder.Z();
        }
        else {
            return null;
        }
    }
}
