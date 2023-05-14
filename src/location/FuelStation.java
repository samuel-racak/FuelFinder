package location;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import vehicle.FuelType;

public class FuelStation extends PointOfInterest {
    private Map<FuelType, Integer> fuels; // map of fuel types and their prices
    private List<Service> services;
    private double rating;
    private int count = 0;

    public FuelStation(String name, double latitude, double longitude, Map<FuelType, Integer> fuels,
            List<Service> services, double rating, int count) {
        super(name, latitude, longitude);
        this.fuels = fuels;
        this.services = services;
        this.rating = rating;
        this.count = count;
    }

    public Map<FuelType, Integer> getFuels() {
        return this.fuels;
    }

    public void setFuels(Map<FuelType, Integer> fuels) {
        this.fuels = fuels;
    }

    public List<Service> getServices() {
        return this.services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public double getRating() {
        return this.rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void updateRating(double newRating) {
        rating = (rating + newRating) / (++count);
        // TODO check if correct weighted rating
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public FuelStation fuels(Map<FuelType, Integer> fuels) {
        setFuels(fuels);
        return this;
    }

    public FuelStation services(List<Service> services) {
        setServices(services);
        return this;
    }

    public FuelStation rating(double rating) {
        setRating(rating);
        return this;
    }

    public FuelStation count(int count) {
        setCount(count);
        return this;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(fuels, services, rating, count);
    }

    @Override
    public String toString() {
        return getName() + "\nFuels: " + getFuels() + "\nServices: " + getServices() + "\nRating: " + getRating()
                + "\n#Reviews: " + getCount();
    }
}
