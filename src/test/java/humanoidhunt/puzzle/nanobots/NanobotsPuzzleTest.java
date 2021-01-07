package humanoidhunt.puzzle.nanobots;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NanobotsPuzzleTest {

    @Test
    public void mostFrequentCharacterIsCorrect() {
        String input = "aaeeAAeebbA";
        var puzzle = new NanobotsPuzzle(input);
        assertEquals('e', puzzle.getMostFrequentCharacter());
    }

    @Test
    public void mostFrequentCharacterFollowingAnotherIsCorrect() {
        String input = "XaXbYcYdXeXbXaZbZbZbZb";
        var puzzle = new NanobotsPuzzle(input);
        assertEquals('a', puzzle.getMostFrequentCharacterFollowingAnother('X'));
    }
}
