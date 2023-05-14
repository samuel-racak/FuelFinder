package location;

import java.io.Serializable;
import java.util.Objects;

public abstract class PointOfInterest implements Serializable {
    String name;
    double latitude;
    double longitude;

    public PointOfInterest(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public PointOfInterest name(String name) {
        setName(name);
        return this;
    }

    public PointOfInterest latitude(double latitude) {
        setLatitude(latitude);
        return this;
    }

    public PointOfInterest longitude(double longitude) {
        setLongitude(longitude);
        return this;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(name, latitude, longitude);
    }

    @Override
    public String toString() {
        return "Name: " + getName() + "\n" + "Latitude: " + getLatitude() + "\n" + "Longitude: " + getLongitude();
    }

}
