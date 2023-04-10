package location;

import java.util.Dictionary;
import java.util.List;
import java.util.Map;

import car.FuelType;

public class FuelStation extends Point {
    private Map<FuelType, Integer> fuels;
    private List<Service> services;
    private int rating;

    public FuelStation() {
    }

    public FuelStation(Map<FuelType, Integer> fuels, List<Service> services, int rating) {
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

    public List<Service> getServices() {
        return this.services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public FuelStation fuels(Map<FuelType, Integer> fuels) {
        setFuels(fuels);
        return this;
    }

    public FuelStation services(List<Service> services) {
        setServices(services);
        return this;
    }

    public FuelStation rating(int rating) {
        setRating(rating);
        return this;
    }
}