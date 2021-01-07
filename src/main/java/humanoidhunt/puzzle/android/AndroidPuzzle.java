package humanoidhunt.puzzle.android;

import humanoidhunt.util.FileUtil;
import humanoidhunt.util.Position;
import humanoidhunt.util.Tuple;

import java.io.IOException;

public class AndroidPuzzle {

    private final Grid grid;

    public AndroidPuzzle() {
        this.grid = new Grid();
    }

    public static String loadInputsAndSolve() throws IOException {
        final String inputFile = "input/android_input.txt";
        var input = FileUtil.readLinesFrom(inputFile);

        var puzzle = new AndroidPuzzle();
        for (var line : input) {
            var parsed = parseInput(line);
            puzzle.applyPath(parsed.a, parsed.b);
        }


        var optStart = puzzle.getGrid().getStartCellPosition();
        if (optStart.isEmpty()) {
            throw new IllegalStateException("Can't find start position!");
        }

        var floodfill = new FloodFill(puzzle.getGrid(), optStart.get());
        var pathToFinish = floodfill.findPathToFinish();

        StringBuilder sb = new StringBuilder();

        for (var step : pathToFinish) {
            sb.append(step.toString()).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static Tuple<Position, Step[]> parseInput(String line) {
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

    public Grid getGrid() {
        return grid;
    }

    public void applyPath(final Position start, Step[] steps) {

        boolean firstStep = true;
        Position pos = new Position(start);
        for (var step : steps) {

            if (firstStep) {
                firstStep = false;
                grid.setCellAt(pos, new Cell(Step.TRAIL_START));
            } else {
                grid.setCellAt(pos, new Cell(step));
            }

            switch (step) {
                case UP:
                    pos = pos.up();
                    break;
                case DOWN:
                    pos = pos.down();
                    break;
                case LEFT:
                    pos = pos.left();
                    break;
                case RIGHT:
                    pos = pos.right();
                    break;
            }
        }
    }
}
