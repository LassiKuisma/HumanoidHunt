package assertions;

import humanoidhunt.puzzle.android.Cell;
import humanoidhunt.puzzle.android.Step;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.fail;

public class Assertions {
    private Assertions() {
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public static <T> void assertOptionalEquals(T expected, Optional<T> actual) {
        if (actual.isEmpty() || !expected.equals(actual.get())) {
            fail();
        }
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public static void assertOptionalCellEquals(Step expected, Optional<Cell> actual) {
        if (actual.isEmpty() || !expected.equals(actual.get().getContent())) {
            fail();
        }
    }
}
