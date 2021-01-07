package humanoidhunt.puzzle.nanobots;

import humanoidhunt.util.FileUtil;

import java.io.IOException;
import java.util.*;

/**
 * In this puzzle you are given a long string as input. Your job is to decipher it into a password.
 * <p>
 * The first character of the password is the most frequent character.
 * The second character is the most frequent character that appears immediately after the first character.
 * This continues for all following characters, until you encounter ';'.
 */
public class NanobotsPuzzle {

    private final String input;

    public NanobotsPuzzle(String input) {
        this.input = input;
    }

    public static String loadInputAndSolve() throws IOException {
        final String inputFile = "input/nanobots_input.txt";
        var input = FileUtil.readLinesFrom(inputFile);

        return new NanobotsPuzzle(input.get(0)).solve();
    }

    public char getMostFrequentCharacter() {
        Map<Character, Integer> charFrequency = new HashMap<>();

        for (char c : input.toCharArray()) {
            final int oldVal = charFrequency.getOrDefault(c, 0);
            charFrequency.put(c, oldVal + 1);
        }

        return getMostFrequentEntry(charFrequency);
    }

    private char getMostFrequentEntry(Map<Character, Integer> freqs) {
        char result = '?';
        int highest = 0;

        boolean first = true;

        for (var entry : freqs.entrySet()) {
            if (first || entry.getValue() > highest) {
                result = entry.getKey();
                highest = entry.getValue();
                first = false;
            }
        }

        return result;
    }

    public String solve() {
        char prev = getMostFrequentCharacter();
        StringBuilder sb = new StringBuilder();
        sb.append(prev);

        while (true) {
            char c = getMostFrequentCharacterFollowingAnother(prev);

            if (c == ';') {
                break;
            }

            sb.append(c);
            prev = c;
        }

        return sb.toString();
    }

    /**
     * Count every character that appears in the input immediately after the given character, and return the most frequent of those.
     *
     * @param another the result will be the most frequent character following this one
     * @return the most frequent character that appears immediately after the given character
     */
    public char getMostFrequentCharacterFollowingAnother(final char another) {
        // indices where given char occurs
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == another) {
                indices.add(i);
            }
        }

        Map<Character, Integer> charFrequency = new HashMap<>();

        for (var index : indices) {
            if (index + 1 >= input.length()) {
                continue;
            }

            final char c = input.charAt(index + 1);
            final int oldVal = charFrequency.getOrDefault(c, 0);
            charFrequency.put(c, oldVal + 1);
        }

        return getMostFrequentEntry(charFrequency);
    }

}
