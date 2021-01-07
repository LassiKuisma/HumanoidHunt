package humanoidhunt.puzzle.android;

import humanoidhunt.util.Position;
import org.junit.jupiter.api.Test;

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
}
