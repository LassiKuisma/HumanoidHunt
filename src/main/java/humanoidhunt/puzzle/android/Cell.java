package humanoidhunt.puzzle.android;

import java.util.Objects;

public class Cell {

    private Step content;

    public Cell(Step content) {
        this.content = content;
    }

    public Step getContent() {
        return this.content;
    }

    public void setContent(Step step) {
        this.content = step;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return content == cell.content;
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }

    @Override
    public String toString() {
        return content.toString();
    }
}
