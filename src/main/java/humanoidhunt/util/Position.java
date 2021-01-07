package humanoidhunt.util;

import java.util.Objects;

public class Position {

    public final int x, y;

    public Position(Position another) {
        this(another.x, another.y);
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position up() {
        return new Position(x, y - 1);
    }

    public Position down() {
        return new Position(x, y + 1);
    }

    public Position left() {
        return new Position(x - 1, y);
    }

    public Position right() {
        return new Position(x + 1, y);
    }

    public Position add(Position another) {
        return new Position(this.x + another.x, this.y + another.y);
    }

    public Position sub(Position another) {
        return new Position(this.x - another.x, this.y - another.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }
}
