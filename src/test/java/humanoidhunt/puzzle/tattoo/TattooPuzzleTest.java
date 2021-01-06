package humanoidhunt.puzzle.tattoo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TattooPuzzleTest {

    @Test
    public void inputIsCorrectlySplitIntoBytes() {
        var puzzle = new TattooPuzzle("001101011111111100000000");
        var bytes = puzzle.splitIntoBytes();

        assertEquals(3, bytes.size());
        assertEquals("00110101", bytes.get(0));
        assertEquals("11111111", bytes.get(1));
        assertEquals("00000000", bytes.get(2));
    }
}
