package location;

public class Route {
    private int length;
    private Point pointA;
    private Point pointB;
    private int maxSpeed;

    public Route(int length, Point pointA, Point pointB, int maxSpeed) {
        this.length = length;
        this.pointA = pointA;
        this.pointB = pointB;
        this.maxSpeed = maxSpeed;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Point getPointA() {
        return this.pointA;
    }

    public void setPointA(Point pointA) {
        this.pointA = pointA;
    }

    public Point getPointB() {
        return this.pointB;
    }

    public void setPointB(Point pointB) {
        this.pointB = pointB;
    }

    public int getMaxSpeed() {
        return this.maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}