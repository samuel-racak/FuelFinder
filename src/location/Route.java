package location;

import java.io.Serializable;
import java.util.Objects;

public class Route implements Serializable {
    private int length;
    private PointOfInterest destination;
    private int maxSpeed; // will not be used in this project but is here for future use (e.g. for
                          // calculating the time it takes to travel the route)

    public Route(int length, PointOfInterest destination, int maxSpeed) {
        this.length = length;
        this.destination = destination;
        this.maxSpeed = maxSpeed;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public PointOfInterest getDestination() {
        return this.destination;
    }

    public void setDestination(PointOfInterest destination) {
        this.destination = destination;
    }

    public int getMaxSpeed() {
        return this.maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Route length(int length) {
        setLength(length);
        return this;
    }

    public Route destination(PointOfInterest destination) {
        setDestination(destination);
        return this;
    }

    public Route maxSpeed(int maxSpeed) {
        setMaxSpeed(maxSpeed);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Route)) {
            return false;
        }
        Route route = (Route) o;
        return length == route.length && Objects.equals(destination, route.destination) && maxSpeed == route.maxSpeed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, destination, maxSpeed);
    }

    @Override
    public String toString() {
        return getLength() + "km" + "\nDestination: " + getDestination() + "\nMax speed: " + getMaxSpeed() + "km/h";
    }
}
