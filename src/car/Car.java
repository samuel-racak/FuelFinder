package car;

import location.Location;

public class Car {
    private int year;
    private String licenseNumber;
    private String model;
    private FuelType fuel;
    // TODO add liters of fuel in tank
    // TODO create new class of vehicle
    private Location location;

    public Car(int year, String licenseNumber, String model, FuelType fuel, Location location) {
        this.year = year;
        this.licenseNumber = licenseNumber;
        this.model = model;
        this.fuel = fuel;
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

    public Car location(Location location) {
        setLocation(location);
        return this;
    }
}