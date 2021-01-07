package humanoidhunt.puzzle.android;

import humanoidhunt.util.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GridTest {

    @Test
    public void startPositionIsFoundCorrectly(){
        Grid grid = new Grid();
        grid.setCellAt(new Position(12,34), new Cell(Step.START));
        grid.setCellAt(new Position(22,33), new Cell(Step.WALL));
        grid.setCellAt(new Position(44,55), new Cell(Step.UP));
        grid.setCellAt(new Position(66,77), new Cell(Step.DOWN));

        var result = grid.getStartCellPosition();
        assertTrue(result.isPresent());
        assertEquals(new Position(12,34), result.get());
    }
}
