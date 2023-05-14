package car;

import java.io.Serializable;
import java.util.Objects;

public class Car implements Serializable {
    private int year;
    private String licenseNumber;
    private String model;
    private FuelType fuel;
    private double fuelConsumption; // in energy e.g. l/100km or watts...
    private int fuelTankCapacity; // maximum fuel capacity
    private int currentFuelLevel; // in percentage

    public Car() {
    }

    public Car(int year, String licenseNumber, String model, FuelType fuel, double fuelConsumption,
            int fuelTankCapacity, int currentFuelLevel) {
        this.year = year;
        this.licenseNumber = licenseNumber;
        this.model = model;
        this.fuel = fuel;
        this.fuelConsumption = fuelConsumption;
        this.fuelTankCapacity = fuelTankCapacity;
        this.currentFuelLevel = currentFuelLevel;
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

    public int getFuelTankCapacity() {
        return this.fuelTankCapacity;
    }

    public void setFuelTankCapacity(int maximumRange) {
        this.fuelTankCapacity = maximumRange;
    }

    public int getCurrentFuelLevel() {
        return this.currentFuelLevel;
    }

    public void setCurrentFuelLevel(int currentFuelLevel) {
        this.currentFuelLevel = currentFuelLevel;
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
        setFuelTankCapacity(maximumRange);
        return this;
    }

    public Car currentFuelLevel(int currentFuelLevel) {
        setCurrentFuelLevel(currentFuelLevel);
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
                && fuelTankCapacity == car.fuelTankCapacity && currentFuelLevel == car.currentFuelLevel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, licenseNumber, model, fuel, fuelConsumption, fuelTankCapacity, currentFuelLevel);
    }

    @Override
    public String toString() {
        return "Year: " + getYear() + "\n" +
                "License Number: " + getLicenseNumber() + "\n" +
                "Model: " + getModel() + "\n" +
                "Fuel: " + getFuel() + "\n" +
                "Fuel Consumption: " + getFuelConsumption() + "\n" +
                "Fuel Tank Capacity: " + getFuelTankCapacity() + "\n" +
                "Current Fuel Level: " + getCurrentFuelLevel() + "%" + "\n";
    }
}
