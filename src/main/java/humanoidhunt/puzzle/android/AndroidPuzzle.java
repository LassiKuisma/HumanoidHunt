package humanoidhunt.puzzle.android;

import humanoidhunt.util.FileUtil;
import humanoidhunt.util.Position;
import humanoidhunt.util.Tuple;

import java.io.IOException;

public class AndroidPuzzle {

    public AndroidPuzzle() {
    }

    public static void loadInputsAndSolve() throws IOException {
        final String inputFile = "input/android_input.txt";
        var input = FileUtil.readLinesFrom(inputFile);


    }

    public static Tuple<Position, Step[]> parseInput(String line) {
        var split = line.split(" ");

        var strPos = line.split(" ")[0].split(","); // "X,Y U,D,..." -> "X,Y" -> ["X", "Y"]
        final var x = Integer.parseInt(strPos[0]);
        final var y = Integer.parseInt(strPos[1]);


        if (!line.contains(" ")) {
            return new Tuple<>(new Position(x, y), new Step[0]);
        }

        var strSteps = line.split(" ")[1].split(","); // "X,Y U,D,L,R,..." -> "U,D,L,R" -> ["U", "D", "L",...]
        var steps = new Step[strSteps.length];

        for (int i = 0; i < steps.length; i++) {
            var optStep = Step.fromString(strSteps[i]);

            if (optStep.isEmpty()) {
                throw new IllegalStateException(String.format("Unknown symbol: '%s'.", strSteps[i]));
            }

            steps[i] = optStep.get();
        }

        return new Tuple<>(new Position(x, y), steps);
    }

    public void applyPath(Position start, Step[] steps) {

    }


}
