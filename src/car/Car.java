package car;

public class Car {
    private int year;
    private String licenseNumber;
    private String model;
    private FuelType fuel;
    private Location location;

    public Car(int year, String licenseNumber, String model, FuelType fuel) {
        this.year = year;
        this.licenseNumber = licenseNumber;
        this.model = model;
        this.fuel = fuel;
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
}
