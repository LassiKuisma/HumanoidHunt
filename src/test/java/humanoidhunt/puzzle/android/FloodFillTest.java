package humanoidhunt.puzzle.android;

import humanoidhunt.util.Position;
import org.junit.jupiter.api.Test;

import java.util.List;

import static humanoidhunt.puzzle.android.FloodFill.Node;
import static humanoidhunt.puzzle.android.Step.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FloodFillTest {

    @Test
    public void nodeIsCorrectlyConvertedToSteps() {
        Node start = new Node(new Position(4, 8), null);
        Node n1 = new Node(start.getPosition().up(), start);
        Node n2 = new Node(n1.getPosition().left(), n1);
        Node n3 = new Node(n2.getPosition().left(), n2);
        Node n4 = new Node(n3.getPosition().down(), n3);
        Node n5 = new Node(n4.getPosition().down(), n4);
        Node finish = new Node(n5.getPosition().right(), n5);

        List<Step> expected = List.of(UP, LEFT, LEFT, DOWN, DOWN, RIGHT);

        var result = FloodFill.convertNodeToSteps(finish);
        assertEquals(expected, result);
    }
}
