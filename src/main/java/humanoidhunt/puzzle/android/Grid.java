package humanoidhunt.puzzle.android;

import humanoidhunt.util.Position;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Grid {

    private final Map<Position, Cell> cells;

    public Grid() {
        this.cells = new HashMap<>();
    }

    public void setCellAt(Position pos, Cell cell) {
        cells.put(pos, cell);
    }

    public Cell getOrCreateEmptyCellAt(Position pos) {
        if (cells.containsKey(pos)) {
            return cells.get(pos);
        }

        Cell cell = new Cell(Step.EMPTY);
        cells.put(pos, cell);

        return cell;
    }

    public Optional<Cell> getCellAt(Position pos) {
        if (cells.containsKey(pos)) {
            return Optional.of(cells.get(pos));
        }
        return Optional.empty();
    }

    public Optional<Position> getStartCellPosition() {
        for (var e : cells.entrySet()) {
            if (e.getValue().getContent() == Step.START) {
                return Optional.of(e.getKey());
            }
        }
        return Optional.empty();
    }

    public void print() {
        // "GUI is just a bloatware so we use console to print the grid"

        if (cells.size() == 0) {
            System.out.println("Empty grid");
            return;
        }

        var bounds = getBounds();
        final int minX = bounds[0];
        final int minY = bounds[1];
        final int maxX = bounds[2];
        final int maxY = bounds[3];

        StringBuilder sb = new StringBuilder();

        // print x-axis
        for (int x = minX; x <= maxX; x++) {
            sb.append("\t").append(x);
        }
        sb.append("\n");


        for (int y = minY; y <= maxY; y++) {
            sb.append(y).append("\t");

            for (int x = minX; x <= maxX; x++) {

                var opt = getCellAt(new Position(x, y));
                if (opt.isPresent()) {
                    sb.append(opt.get().toString()).append("\t");
                } else {
                    sb.append(" ").append("\t");
                }

            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    private int[] getBounds() {
        int minX = 0, minY = 0;
        int maxX = 0, maxY = 0;
        boolean first = true;

        for (var t : cells.keySet()) {
            final int x = t.x;
            final int y = t.y;

            if (first) {
                first = false;
                minX = x;
                maxX = x;

                minY = y;
                maxY = y;
                continue;
            }

            minX = Math.min(minX, x);
            maxX = Math.max(maxX, x);

            minY = Math.min(minY, y);
            maxY = Math.max(maxY, y);
        }
        return new int[]{minX, minY, maxX, maxY};
    }
}
