package humanoidhunt.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionTest {

    @Test
    public void directionsWorkCorrectly() {
        Position original = new Position(10, 5);
        assertAll(() -> {
            assertEquals(new Position(10, 4), original.up());
            assertEquals(new Position(10, 6), original.down());
            assertEquals(new Position(9, 5), original.left());
            assertEquals(new Position(11, 5), original.right());
        });
    }

    @Test
    public void addingPositionsWorksCorrectly() {
        var pos1 = new Position(3, -7);
        var pos2 = new Position(6, 5);

        assertEquals(new Position(9, -2), pos1.add(pos2));
    }

    @Test
    public void subtractingPositionsWorksCorrectly() {
        var pos1 = new Position(3, -7);
        var pos2 = new Position(4, -1);

        assertEquals(new Position(-1, -6), pos1.sub(pos2));
    }
}
