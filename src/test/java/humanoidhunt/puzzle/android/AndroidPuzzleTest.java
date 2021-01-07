package humanoidhunt.puzzle.android;

import humanoidhunt.util.Position;
import org.junit.jupiter.api.Test;

import static assertions.Assertions.assertOptionalCellEquals;
import static humanoidhunt.puzzle.android.Step.*;
import static org.junit.jupiter.api.Assertions.*;

public class AndroidPuzzleTest {

    @Test
    public void inputIsParsedCorrectly() {
        var input = "101,52 U,D,U,L,L,R,R";
        var result = AndroidPuzzle.parseInput(input);

        var expected = new Step[]{UP, DOWN, UP, LEFT, LEFT, RIGHT, RIGHT};

        assertEquals(new Position(101, 52), result.a);
        assertArrayEquals(expected, result.b);
    }

    @Test
    public void inputIsParsedCorrectly_2() {
        var input = "1,2 U,D,F";
        var result = AndroidPuzzle.parseInput(input);

        var expected = new Step[]{UP, DOWN, FINISH};

        assertEquals(new Position(1, 2), result.a);
        assertArrayEquals(expected, result.b);
    }

    @Test
    public void inputWithOnlyCoordinatesIsParsedCorrectly() {
        try {
            var input = "10,20";
            var result = AndroidPuzzle.parseInput(input);

            assertEquals(new Position(10, 20), result.a);
            assertEquals(0, result.b.length);
        } catch (Exception e) {
            fail("Caught exception: " + e.getMessage());
        }
    }

    @Test
    public void pathIsAppliedCorrectly() {
        var puzzle = new AndroidPuzzle();
        puzzle.applyPath(new Position(2, 6), new Step[]{DOWN, RIGHT, RIGHT, UP, WALL});

        var grid = puzzle.getGrid();

        assertOptionalCellEquals(TRAIL_START, grid.getCellAt(new Position(2, 6)));
        assertOptionalCellEquals(RIGHT, grid.getCellAt(new Position(2, 7)));
        assertOptionalCellEquals(RIGHT, grid.getCellAt(new Position(3, 7)));
        assertOptionalCellEquals(UP, grid.getCellAt(new Position(4, 7)));
        assertOptionalCellEquals(WALL, grid.getCellAt(new Position(4, 6)));
    }

    @Test
    public void pathIsAppliedCorrectly_2() {
        var puzzle = new AndroidPuzzle();
        puzzle.applyPath(new Position(1, 5), new Step[]{LEFT, FINISH});

        var grid = puzzle.getGrid();

        assertOptionalCellEquals(TRAIL_START, grid.getCellAt(new Position(1, 5)));
        assertOptionalCellEquals(FINISH, grid.getCellAt(new Position(0, 5)));
    }
}
