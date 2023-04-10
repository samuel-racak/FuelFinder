package location;

import java.util.List;
import java.util.Map;

import car.FuelType;

public class FuelStation extends Point {
    private Map<FuelType, Integer> fuels;
    private List<Service> services;
    private double rating;
    private int count = 0;

    public FuelStation(String name, List<Point> successors, Map<FuelType, Integer> fuels, List<Service> services,
            double rating) {
        super(name, successors);
        this.fuels = fuels;
        this.services = services;
        this.rating = rating;
    }

    public Map<FuelType, Integer> getFuels() {
        return this.fuels;
    }

    public void setFuels(Map<FuelType, Integer> fuels) {
        this.fuels = fuels;
    }

    public void addFuel(FuelType fuelType, int price) {
        fuels.putIfAbsent(fuelType, price);
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

    // public void setRating(int rating) {
    // this.rating = rating;
    // }

    public void updateRating(double newRating) {
        rating = (rating + newRating) / (++count);
        // TODO check if correct weighted rating
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
        updateRating(rating);
        return this;
    }
}
