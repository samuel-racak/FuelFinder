package location;

import java.io.Serializable;
import java.util.Objects;

/**
 * This class represents a route between two points of interest on a map.
 */
public class Route implements Serializable {
    private int length;
    private PointOfInterest destination;
    private int maxSpeed; // will not be used in this project but is here for future use (e.g. for
    // calculating the time it takes to travel the route)

    /**
     * Constructs a new Route object.
     *
     * @param length      The length of the route in kilometers.
     * @param destination The destination point of interest of the route.
     * @param maxSpeed    The maximum speed allowed on the route in km/h.
     */
    public Route(int length, PointOfInterest destination, int maxSpeed) {
        this.length = length;
        this.destination = destination;
        this.maxSpeed = maxSpeed;
    }

    /**
     * Gets the length of the route in kilometers.
     *
     * @return The length of the route in kilometers.
     */
    public int getLength() {
        return this.length;
    }

    /**
     * Sets the length of the route in kilometers.
     *
     * @param length The new length of the route in kilometers.
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * Gets the destination point of interest of the route.
     *
     * @return The destination point of interest of the route.
     */
    public PointOfInterest getDestination() {
        return this.destination;
    }

    /**
     * Sets the destination point of interest of the route.
     *
     * @param destination The new destination point of interest of the route.
     */
    public void setDestination(PointOfInterest destination) {
        this.destination = destination;
    }

    /**
     * Gets the maximum speed allowed on the route in km/h.
     *
     * @return The maximum speed allowed on the route in km/h.
     */
    public int getMaxSpeed() {
        return this.maxSpeed;
    }

    /**
     * Sets the maximum speed allowed on the route in km/h.
     *
     * @param maxSpeed The new maximum speed allowed on the route in km/h.
     */
    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    /**
     * Sets the length and returns this Route object.
     *
     * @param length The new length to set.
     * @return This Route object with its length set to `length`.
     */
    public Route length(int length) {
        setLength(length);
        return this;
    }

    /**
     * Sets the destination and returns this Route object.
     *
     * @param destination The new destination to set.
     * @return This Route object with its destination set to `destination`.
     */
    public Route destination(PointOfInterest destination) {
        setDestination(destination);
        return this;
    }

    /**
     * Sets the maximum speed and returns this Route object.
     *
     * @param maxSpeed The new maximum speed to set.
     * @return This Route object with its maximum speed set to `maxSpeed`.
     */
    public Route maxSpeed(int maxSpeed) {
        setMaxSpeed(maxSpeed);
        return this;
    }

    /**
     * Determines whether two objects are equal or not. Two objects are equal if
     * they are both instances
     * of Route and have equal values for their `length`, `destination`, and
     * `maxSpeed` fields.
     *
     * @param o The object to compare with this instance for equality
     *
     * @return true if o is an instance of Route and has equal values for its
     *         `length`,
     *         `destination`, and `maxSpeed` fields; false otherwise
     */
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

    /**
     * Returns a hash code value for this object. This method is supported for the
     * benefit
     * of hash tables such as those provided by HashMap.
     *
     * @return A hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(length, destination, maxSpeed);
    }

    /**
     * Returns a string representation for this object.
     *
     * @return A string representation for this object
     */
    @Override
    public String toString() {
        return getLength() + "km" + "\nDestination: " + getDestination() + "\nMax speed: " + getMaxSpeed() + "km/h";
    }
}
