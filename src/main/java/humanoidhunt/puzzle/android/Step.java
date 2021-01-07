package humanoidhunt.puzzle.android;

import java.util.Optional;

public enum Step {
    UP("U"),
    DOWN("D"),
    LEFT("L"),
    RIGHT("R"),

    WALL("X"),
    START("S"),
    FINISH("F"),
    EMPTY(" ");

    private final String content;

    Step(String s) {
        this.content = s;
    }

    @Override
    public String toString() {
        return content;
    }

    public static Optional<Step> fromString(String input) {
        for (var s : Step.values()) {
            if (s.content.equals(input)) {
                return Optional.of(s);
            }
        }

        return Optional.empty();
    }
}
