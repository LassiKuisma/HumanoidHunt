package humanoidhunt.puzzle.tattoo;

import humanoidhunt.util.FileUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TattooPuzzle {

    private final String input;

    public TattooPuzzle(String input) {
        this.input = input;
    }

    public static String loadInputsAndSolve() throws IOException {
        final String inputFile = "input/tattoo_input.txt";
        var inputs = FileUtil.readLinesFrom(inputFile);

        StringBuilder sb = new StringBuilder();

        for (var input : inputs) {
            var puzzle = new TattooPuzzle(input);
            sb.append(puzzle.getSolution());
        }

        return sb.toString();
    }

    public List<String> splitIntoBytes() {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < input.length() / 8; i++) {
            result.add(input.substring(i * 8, i * 8 + 8));
        }

        return result;
    }

    public char getSolution() {
        var bytes = splitIntoBytes();
        int[] bytesAsInt = bytesToNumbers(bytes);

        int index = 0;
        boolean atStart = true;

        while (true) {
            // 'bytesAsInt' = array of indices. Keep following them until you encounter one that goes out of bounds
            final int newIndex = bytesAsInt[index];

            if (newIndex >= bytesAsInt.length) {
                // going out of bounds: either the solution or ignored stuff at the start

                if (atStart) {
                    // ignore all out-of-bounds at the start of array (result shows up only after first valid index)
                    index++;
                } else {
                    // found the result
                    return (char) newIndex;
                }

            } else {
                index = newIndex;
                atStart = false;
            }
        }
    }

    public int[] bytesToNumbers(List<String> bytes) {
        int[] result = new int[bytes.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(bytes.get(i), 2);
        }
        return result;
    }

}
