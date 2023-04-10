package location;

import java.util.List;

public abstract class Point {
    private String name;
    private List<Point> successors; // points one route away from this point

    public Point(String name, List<Point> successors) {
        this.name = name;
        this.successors = successors;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Point> getSuccessors() {
        return this.successors;
    }

    public void setSuccessors(List<Point> successors) {
        this.successors = successors;
    }

    public Point name(String name) {
        setName(name);
        return this;
    }

    public Point successors(List<Point> successors) {
        setSuccessors(successors);
        return this;
    }
}
