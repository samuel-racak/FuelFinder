package location;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import vehicle.FuelType;

/**
 * The FuelStation class represents a fuel station.
 * It extends the PointOfInterest class.
 */
public class FuelStation extends PointOfInterest {
    private Map<FuelType, Integer> fuels; // map of fuel types and their prices
    private List<Service> services;
    private double rating;
    private int count = 0;

    /**
     * Constructor for the FuelStation class.
     * Initializes the name, latitude, longitude, fuels, services, rating and count
     * to the given values.
     *
     * @param name      the name of the fuel station
     * @param latitude  the latitude of the fuel station
     * @param longitude the longitude of the fuel station
     * @param fuels     the map of fuel types and their prices
     * @param services  the list of services offered by the fuel station
     * @param rating    the rating of the fuel station
     * @param count     the number of ratings for the fuel station
     */
    public FuelStation(String name, double latitude, double longitude, Map<FuelType, Integer> fuels,
            List<Service> services, double rating, int count) {
        super(name, latitude, longitude);
        this.fuels = fuels;
        this.services = services;
        this.rating = rating;
        this.count = count;
    }

    /**
     * Returns the map of fuel types and their prices.
     *
     * @return the map of fuel types and their prices
     */
    public Map<FuelType, Integer> getFuels() {
        return this.fuels;
    }

    /**
     * Sets the map of fuel types and their prices.
     *
     * @param fuels the new map of fuel types and their prices to set
     */
    public void setFuels(Map<FuelType, Integer> fuels) {
        this.fuels = fuels;
    }

    /**
     * Returns the list of services offered by the fuel station.
     *
     * @return the list of services offered by the fuel station
     */
    public List<Service> getServices() {
        return this.services;
    }

    /**
     * Sets the list of services offered by the fuel station.
     *
     * @param services the new list of services to set
     */
    public void setServices(List<Service> services) {
        this.services = services;
    }

    /**
     * Returns the rating of the fuel station.
     *
     * @return the rating of the fuel station
     */
    public double getRating() {
        return this.rating;
    }

    /**
     * Sets the rating of the fuel station.
     *
     * @param rating the new rating to set
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * Updates the rating of the fuel station with a new rating.
     *
     * @param newRating the new rating to add
     */
    public void updateRating(double newRating) {
        rating = (rating + newRating) / (++count);
    }

    /**
     * Returns the number of ratings for the fuel station.
     *
     * @return the number of ratings for the fuel station
     */
    public int getCount() {
        return this.count;
    }

    /**
     * Sets the number of ratings for the fuel station.
     *
     * @param count the new number of ratings to set
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Sets the map of fuel types and their prices and returns this FuelStation
     * object.
     *
     * @param fuels the new map of fuel types and their prices to set
     * @return this FuelStation object
     */
    public FuelStation fuels(Map<FuelType, Integer> fuels) {
        setFuels(fuels);
        return this;
    }

    /**
     * Sets the list of services offered by the fuel station and returns this
     * FuelStation object.
     *
     * @param services the new list of services to set
     * @return this FuelStation object
     */
    public FuelStation services(List<Service> services) {
        setServices(services);
        return this;
    }

    /**
     * Sets the rating of the fuel station and returns this FuelStation object.
     *
     * @param rating the new rating to set
     * @return this FuelStation object
     */
    public FuelStation rating(double rating) {
        setRating(rating);
        return this;
    }

    /**
     * Sets the number of ratings for the fuel station and returns this FuelStation
     * object.
     *
     * @param count the new number of ratings to set
     * @return this FuelStation object
     */
    public FuelStation count(int count) {
        setCount(count);
        return this;
    }

    /**
     * Compares this fuel station to another object for equality.
     *
     * @param o the other object to compare to
     * @return true if o is a FuelStation and has the same fuels, services, rating
     *         and count as this fuel station; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof FuelStation)) {
            return false;
        }
        FuelStation fuelStation = (FuelStation) o;
        return Objects.equals(fuels, fuelStation.fuels) && Objects.equals(services, fuelStation.services)
                && rating == fuelStation.rating && count == fuelStation.count;
    }

    /**
     * Returns a hash code for this fuel station.
     *
     * @return a hash code for this fuel station
     */
    @Override
    public int hashCode() {
        return Objects.hash(fuels, services, rating, count);
    }

    /**
     * Returns a string representation of this fuel station.
     *
     * @return a string representation of this fuel station
     */
    @Override
    public String toString() {
        return getName() + "\nFuels: " + getFuels() + "\nServices: " + getServices() + "\nRating: " + getRating()
                + "\n#Reviews: " + getCount();
    }
}
