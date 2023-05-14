package location;

import java.io.Serializable;
import java.util.Objects;

/**
 * This class represents a point of interest on a map.
 */
public abstract class PointOfInterest implements Serializable {
    String name;
    double latitude;
    double longitude;

    /**
     * Constructs a new PointOfInterest object.
     *
     * @param name      The name of the point of interest.
     * @param latitude  The latitude of the point of interest.
     * @param longitude The longitude of the point of interest.
     */
    public PointOfInterest(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Gets the name of the point of interest.
     *
     * @return The name of the point of interest.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the point of interest.
     *
     * @param name The new name of the point of interest.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the latitude of the point of interest.
     *
     * @return The latitude of the point of interest.
     */
    public double getLatitude() {
        return this.latitude;
    }

    /**
     * Sets the latitude of the point of interest.
     *
     * @param latitude The new latitude of the point of interest.
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets the longitude of the point of interest.
     *
     * @return The longitude of the point of interest.
     */
    public double getLongitude() {
        return this.longitude;
    }

    /**
     * Sets the longitude of the point of interest.
     *
     * @param longitude The new longitude of the point of interest.
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Sets the name and returns this PointOfInterest object.
     *
     * @param name The new name to set.
     * @return This PointOfInterest object with its name set to `name`.
     */
    public PointOfInterest name(String name) {
        setName(name);
        return this;
    }

    /**
     * Sets the latitude and returns this PointOfInterest object.
     *
     * @param latitude The new latitude to set.
     * @return This PointOfInterest object with its latitude set to `latitude`.
     */
    public PointOfInterest latitude(double latitude) {
        setLatitude(latitude);
        return this;
    }

    /**
     * Sets the longitude and returns this PointOfInterest object.
     *
     * @param longitude The new longitude to set.
     * @return This PointOfInterest object with its longitude set to `longitude`.
     */
    public PointOfInterest longitude(double longitude) {
        setLongitude(longitude);
        return this;
    }

    /**
     * Determines whether two objects are equal or not. Two objects are equal if
     * they are both instances
     * of PointOfInterest and have equal values for their `name`, `latitude`, and
     * `longitude` fields.
     *
     * @param o The object to compare with this instance for equality
     *
     * @return true if o is an instance of PointOfInterest and has equal values for
     *         its `name`,
     *         `latitude`, and `longitude` fields; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PointOfInterest)) {
            return false;
        }
        PointOfInterest pointOfInterest = (PointOfInterest) o;
        return Objects.equals(name, pointOfInterest.name) && latitude == pointOfInterest.latitude
                && longitude == pointOfInterest.longitude;
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
        return Objects.hash(name, latitude, longitude);
    }

    /**
     * Returns a string representation for this object.
     *
     * @return A string representation for this object
     */
    @Override
    public String toString() {
        return getName() + "\n" + "Latitude: " + getLatitude() + "\n" + "Longitude: " + getLongitude();
    }
}
