package car;

import java.util.Random;

public class CarGenerator {
    /**
     * this method generates a random car
     *
     * @return a random car
     */
    public static Car generateRandomCar() {
        Random rand = new Random();
        int year = rand.nextInt(2022 - 1980 + 1) + 1980; // random year between 1980 and 2022
        String licenseNumber = "XYZ" + rand.nextInt(1000); // random license number
        String[] models = { "Toyota", "Honda", "Ford", "Chevrolet", "Nissan", "Hyundai", "Kia", "Jeep", "Dodge",
                "Tesla", "BMW", "Mercedes", "Audi", "Volkswagen", "Volvo", "Mazda", "Lexus", "Subaru", "Buick",
                "Acura", "Infiniti", "Lincoln", "Cadillac", "Mitsubishi", "Chrysler", "Porsche", "Land Rover", "Jaguar",
                "Fiat", "Mini", "Ram", "Bentley", "Ferrari", "Lamborghini", "Maserati", "McLaren", "Bugatti",
                "Rolls-Royce", "Alfa Romeo", "Genesis", "Lotus", "Aston Martin", "Koenigsegg", "Pagani", "Maybach",
                "GMC", "Smart", "Scion", "Hummer", "Saturn", "Pontiac", "Suzuki", "Saab", "Oldsmobile", "Plymouth",
                "Mercury" };
        String model = models[rand.nextInt(models.length)]; // random model
        FuelType fuel = FuelType.values()[rand.nextInt(FuelType.values().length)]; // random fuel type
        double fuelConsumption = Math.round(rand.nextDouble() * 20 * 10) / 10; // random fuel consumption between 0
                                                                               // and 20
        int fuelTankCapacity = rand.nextInt(50) + 30; // random fuel tank capacity between 30 and 80
        int currentFuelLevel = rand.nextInt(101); // random current fuel level between 0 and 100

        return new Car(year, licenseNumber, model, fuel, fuelConsumption,
                fuelTankCapacity, currentFuelLevel);
    }
}
