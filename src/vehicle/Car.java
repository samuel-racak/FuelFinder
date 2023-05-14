package vehicle;

import java.io.Serializable;
import java.util.Objects;

/**
 * The Car class represents a car.
 * It implements the Serializable interface to allow it to be serialized.
 */
public class Car implements Serializable {
    private int year;
    private String licenseNumber;
    private String model;
    private FuelType fuelType;
    private double fuelConsumption; // l/100km
    private int fuelTankCapacity; // maximum fuel capacity in liters
    private int currentFuelLevel; // in percentage of fuelTankCapacity

    /**
     * Default constructor for the Car class.
     */
    public Car() {
    }

    /**
     * Constructor for the Car class.
     * Initializes the year, license number, model, fuel type, fuel consumption,
     * fuel tank capacity and current fuel level to the given values.
     *
     * @param year             the year of the car
     * @param licenseNumber    the license number of the car
     * @param model            the model of the car
     * @param fuel             the fuel type of the car
     * @param fuelConsumption  the fuel consumption of the car (in l/100km)
     * @param fuelTankCapacity the maximum fuel capacity of the car (in liters)
     * @param currentFuelLevel the current fuel level of the car (in percentage of
     *                         fuelTankCapacity)
     */
    public Car(int year, String licenseNumber, String model, FuelType fuel, double fuelConsumption,
            int fuelTankCapacity, int currentFuelLevel) {
        this.year = year;
        this.licenseNumber = licenseNumber;
        this.model = model;
        this.fuelType = fuel;
        this.fuelConsumption = fuelConsumption;
        this.fuelTankCapacity = fuelTankCapacity;
        this.currentFuelLevel = currentFuelLevel;
    }

    /**
     * Returns the year of the car.
     *
     * @return the year of the car
     */
    public int getYear() {
        return this.year;
    }

    /**
     * Sets the year of the car.
     *
     * @param year the new year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Returns the license number of the car.
     *
     * @return the license number of the car
     */
    public String getLicenseNumber() {
        return this.licenseNumber;
    }

    /**
     * Sets the license number of the car.
     *
     * @param licenseNumber the new license number to set
     */
    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    /**
     * Returns the model of the car.
     *
     * @return the model of the car
     */
    public String getModel() {
        return this.model;
    }

    /**
     * Sets the model of the car.
     *
     * @param model the new model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Returns the fuel type of the car.
     *
     * @return the fuel type of the car
     */
    public FuelType getFuelType() {
        return this.fuelType;
    }

    /**
     * Sets the fuel type of the car.
     *
     * @param fuel the new fuel type to set
     */
    public void setFuelType(FuelType fuel) {
        this.fuelType = fuel;
    }

    /**
     * Returns the fuel consumption of the car (in l/100km).
     *
     * @return the fuel consumption of the car (in l/100km)
     */
    public double getFuelConsumption() {
        return this.fuelConsumption;
    }

    /**
     * Sets the fuel consumption of the car (in l/100km).
     *
     * @param fuelConsumption the new fuel consumption to set (in l/100km)
     */
    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    /**
     * Returns the maximum fuel capacity of the car (in liters).
     *
     * @return the maximum fuel capacity of the car (in liters)
     */
    public int getFuelTankCapacity() {
        return this.fuelTankCapacity;
    }

    /**
     * Sets the maximum fuel capacity of the car (in liters).
     *
     * @param maximumRange the new maximum fuel capacity to set (in liters)
     */
    public void setFuelTankCapacity(int maximumRange) {
        this.fuelTankCapacity = maximumRange;
    }

    /**
     * Returns the current fuel level of the car (in percentage of
     * fuelTankCapacity).
     *
     * @return the current fuel level of the car (in percentage of fuelTankCapacity)
     */
    public int getCurrentFuelLevel() {
        return this.currentFuelLevel;
    }

    /**
     * Sets the current fuel level of the car (in percentage of fuelTankCapacity).
     *
     * @param currentFuelLevel the new current fuel level to set (in percentage of
     *                         fuelTankCapacity)
     */
    public void setCurrentFuelLevel(int currentFuelLevel) {
        this.currentFuelLevel = currentFuelLevel;
    }

    /**
     * Sets the year of the car and returns this Car object.
     *
     * @param year the new year to set
     * @return this Car object
     */
    public Car year(int year) {
        setYear(year);
        return this;
    }

    /**
     * Sets the license number of the car and returns this Car object.
     *
     * @param licenseNumber the new license number to set
     * @return this Car object
     */
    public Car licenseNumber(String licenseNumber) {
        setLicenseNumber(licenseNumber);
        return this;
    }

    /**
     * Sets the model of the car and returns this Car object.
     *
     * @param model the new model to set
     * @return this Car object
     */
    public Car model(String model) {
        setModel(model);
        return this;
    }

    /**
     * Sets the fuel type of the car and returns this Car object.
     *
     * @param fuel the new fuel type to set
     * @return this Car object
     */
    public Car fuel(FuelType fuel) {
        setFuelType(fuel);
        return this;
    }

    /**
     * Sets the fuel consumption of the car (in l/100km) and returns this Car
     * object.
     *
     * @param fuelConsumption the new fuel consumption to set (in l/100km)
     * @return this Car object
     */
    public Car fuelConsumption(double fuelConsumption) {
        setFuelConsumption(fuelConsumption);
        return this;
    }

    /**
     * Sets the maximum fuel capacity of the car (in liters) and returns this Car
     * object.
     *
     * @param maximumRange the new maximum fuel capacity to set (in liters)
     * @return this Car object
     */
    public Car maximumRange(int maximumRange) {
        setFuelTankCapacity(maximumRange);
        return this;
    }

    /**
     * Sets the current fuel level of the car (in percentage of fuelTankCapacity)
     * and returns this Car object.
     *
     * @param currentFuelLevel the new current fuel level to set (in percentage of
     *                         fuelTankCapacity)
     * @return this Car object
     */
    public Car currentFuelLevel(int currentFuelLevel) {
        setCurrentFuelLevel(currentFuelLevel);
        return this;
    }

    /**
     * Compares this car to another object for equality.
     *
     * @param o the other object to compare to
     * @return true if o is a Car and has the same year, license number, model, fuel
     *         type, fuel consumption, fuel tank capacity and current fuel level as
     *         this car; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Car)) {
            return false;
        }
        Car car = (Car) o;
        return year == car.year && Objects.equals(licenseNumber, car.licenseNumber) && Objects.equals(model, car.model)
                && Objects.equals(fuelType, car.fuelType) && fuelConsumption == car.fuelConsumption
                && fuelTankCapacity == car.fuelTankCapacity && currentFuelLevel == car.currentFuelLevel;
    }

    /**
     * Returns a hash code for this car.
     *
     * @return a hash code for this car
     */
    @Override
    public int hashCode() {
        return Objects.hash(year, licenseNumber, model, fuelType, fuelConsumption, fuelTankCapacity, currentFuelLevel);
    }

    /**
     * Returns a string representation of this car.
     *
     * @return a string representation of this car
     */
    @Override
    public String toString() {
        return "Year: " + getYear() + "\n" +
                "License Number: " + getLicenseNumber() + "\n" +
                "Model: " + getModel() + "\n" +
                "Fuel: " + getFuelType() + "\n" +
                "Fuel Consumption: " + getFuelConsumption() + "\n" +
                "Fuel Tank Capacity: " + getFuelTankCapacity() + "\n" +
                "Current Fuel Level: " + getCurrentFuelLevel() + "%" + "\n";
    }
}
