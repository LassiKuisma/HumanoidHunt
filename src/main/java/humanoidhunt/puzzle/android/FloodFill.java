package humanoidhunt.puzzle.android;

import humanoidhunt.util.Position;

import java.util.*;

import static humanoidhunt.puzzle.android.Step.*;

public class FloodFill {

    private final Grid grid;
    /**
     * Cells that we're checking on current iteration
     */
    private final Set<Node> current;
    /**
     * Cells that we've already checked
     */
    private final Set<Position> visited;

    private Node pathToFinish;

    public FloodFill(Grid grid, Position start) {
        this.grid = grid;
        this.visited = new HashSet<>();
        this.current = new HashSet<>();

        current.add(new Node(start, null));
    }

    public static List<Step> convertNodeToSteps(final Node path) {
        List<Step> result = new ArrayList<>();
        Map<Position, Step> map = Map.of(
                new Position(0, -1), UP,
                new Position(0, 1), DOWN,
                new Position(-1, 0), LEFT,
                new Position(1, 0), RIGHT);


        Node current = path;
        while (current.parent != null) {
            var diff = current.position.sub(current.parent.position);
            if (!map.containsKey(diff)) {
                String errMsg = String.format("Going from %s to %s is not a valid step.", current.parent.position, current.position);
                throw new IllegalStateException(errMsg);
            }

            result.add(0, map.get(diff));
            current = current.parent;
        }

        return result;
    }

    public List<Step> findPathToFinish() {
        while (pathToFinish == null) {
            if (current.size() == 0) {
                // no solution found
                return new ArrayList<>();
            }

            nextStep();
        }

        return convertNodeToSteps(pathToFinish);
    }

    private void nextStep() {
        // get all adjacent cells of 'current'
        Set<Node> temp = new HashSet<>();

        for (var node : current) {
            var adjacentPositions = getAdjacentPositions(node.position);

            for (var adjPos : adjacentPositions) {
                temp.add(new Node(adjPos, node));
            }
        }

        // filter out walls and ones we've visited
        temp.removeIf(
                node -> visited.contains(node.position)
                        || !isPassableTerrainAt(node.position));

        // check if one of those is finish -> we found it!
        for (var t : temp) {
            var optCell = grid.getCellAt(t.position);
            if (optCell.isPresent() && optCell.get().getContent() == Step.FINISH) {
                pathToFinish = t;
                return;
            }
        }

        // otherwise move all squares in 'current' to visited, and all squares from temp list to 'cells'
        for (var c : current) {
            visited.add(c.position);
        }
        current.clear();

        current.addAll(temp);
    }

    /**
     * Passable terrain means a cell that is not a wall or an empty cell.
     *
     * @param pos position of cell
     * @return true if terrain is passable
     */
    private boolean isPassableTerrainAt(Position pos) {
        var optCell = grid.getCellAt(pos);
        if (optCell.isEmpty()) {
            return false;
        }

        return optCell.get().getContent() != Step.WALL;
    }

    private Position[] getAdjacentPositions(Position pos) {
        return new Position[]{
                pos.up(),
                pos.down(),
                pos.left(),
                pos.right()
        };
    }


    public static class Node {
        private final Position position;
        private final Node parent;

        public Node(Position position, Node parent) {
            this.position = position;
            this.parent = parent;
        }

        public Position getPosition() {
            return position;
        }

        public Optional<Node> getParent() {
            return Optional.ofNullable(parent);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node fCell = (Node) o;
            return position.equals(fCell.position);
        }

        @Override
        public int hashCode() {
            return Objects.hash(position);
        }
    }

}
