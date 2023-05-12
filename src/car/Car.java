package car;

import location.Location;
import java.util.Objects;

public class Car {
    private int year;
    private String licenseNumber;
    private String model;
    private FuelType fuel;
    private double fuelConsumption; // in energy e.g. l/100km or watts...
    private int maximumRange; // maximum fuel capacity
    private int currentFuelLevel; // in percentage
    // TODO create new class of vehicle
    private Location location;

    public Car() {
    }

    public Car(int year, String licenseNumber, String model, FuelType fuel, double fuelConsumption, int maximumRange,
            int currentFuelLevel, Location location) {
        this.year = year;
        this.licenseNumber = licenseNumber;
        this.model = model;
        this.fuel = fuel;
        this.fuelConsumption = fuelConsumption;
        this.maximumRange = maximumRange;
        this.currentFuelLevel = currentFuelLevel;
        this.location = location;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getLicenseNumber() {
        return this.licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public FuelType getFuel() {
        return this.fuel;
    }

    public void setFuel(FuelType fuel) {
        this.fuel = fuel;
    }

    public double getFuelConsumption() {
        return this.fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public int getMaximumRange() {
        return this.maximumRange;
    }

    public void setMaximumRange(int maximumRange) {
        this.maximumRange = maximumRange;
    }

    public int getCurrentFuelLevel() {
        return this.currentFuelLevel;
    }

    public void setCurrentFuelLevel(int currentFuelLevel) {
        this.currentFuelLevel = currentFuelLevel;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Car year(int year) {
        setYear(year);
        return this;
    }

    public Car licenseNumber(String licenseNumber) {
        setLicenseNumber(licenseNumber);
        return this;
    }

    public Car model(String model) {
        setModel(model);
        return this;
    }

    public Car fuel(FuelType fuel) {
        setFuel(fuel);
        return this;
    }

    public Car fuelConsumption(double fuelConsumption) {
        setFuelConsumption(fuelConsumption);
        return this;
    }

    public Car maximumRange(int maximumRange) {
        setMaximumRange(maximumRange);
        return this;
    }

    public Car currentFuelLevel(int currentFuelLevel) {
        setCurrentFuelLevel(currentFuelLevel);
        return this;
    }

    public Car location(Location location) {
        setLocation(location);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Car)) {
            return false;
        }
        Car car = (Car) o;
        return year == car.year && Objects.equals(licenseNumber, car.licenseNumber) && Objects.equals(model, car.model)
                && Objects.equals(fuel, car.fuel) && fuelConsumption == car.fuelConsumption
                && maximumRange == car.maximumRange && currentFuelLevel == car.currentFuelLevel
                && Objects.equals(location, car.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, licenseNumber, model, fuel, fuelConsumption, maximumRange, currentFuelLevel,
                location);
    }

    @Override
    public String toString() {
        return "{" +
                " year='" + getYear() + "'" +
                ", licenseNumber='" + getLicenseNumber() + "'" +
                ", model='" + getModel() + "'" +
                ", fuel='" + getFuel() + "'" +
                ", fuelConsumption='" + getFuelConsumption() + "'" +
                ", maximumRange='" + getMaximumRange() + "'" +
                ", currentFuelLevel='" + getCurrentFuelLevel() + "'" +
                ", location='" + getLocation() + "'" +
                "}";
    }
}
