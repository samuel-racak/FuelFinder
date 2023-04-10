package location;

public class Route {
    private int length;
    private Point start;
    private Point finish;
    private int maxSpeed;

    public Route(int length, Point start, Point finish, int maxSpeed) {
        this.length = length;
        this.start = start;
        this.finish = finish;
        this.maxSpeed = maxSpeed;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Point getStart() {
        return this.start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getFinish() {
        return this.finish;
    }

    public void setFinish(Point finish) {
        this.finish = finish;
    }

    public int getMaxSpeed() {
        return this.maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}