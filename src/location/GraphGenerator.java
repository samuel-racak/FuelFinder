package location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import navigation.Graph;
import vehicle.FuelType;

/**
 * This class generates a graph representing a map with cities and fuel
 * stations.
 */
public class GraphGenerator {
    private static final int MAX_CITIES = 50;
    private static final int MAX_FUEL_STATIONS = 30;
    // private static final int MAX_ROUTES_PER_POI = 3; // will not be used in this
    // project
    private static final int MAX_SPEED_LIMIT = 130;
    private static final int MAX_ROUTE_LENGTH = 150;
    private static final int MAX_FUEL_PRICE = 10;
    private static final String[] attractions = { "Museum", "Park", "Restaurant", "Cinema", "Theater", "Stadium",
            "Mall", "Candy store", "Bookstore", "Library", "Zoo", "Aquarium", "Beach", "Mountain", "Lake", "River" };
    private static final String[] ServiceNames = { "Car Wash", "Car Repair", "Car Painting", "Car Tuning", "Car Rental",
            "Car Parking", "Car Inspection", "Car Insurance", "Car Towing", "Condom Wending Machine", "Food" };

    /**
     * Generates a graph representing a map with cities and fuel stations.
     *
     * @return A graph representing a map with cities and fuel stations.
     */
    public static Graph generateGraph() {
        Graph graph = new Graph();
        List<PointOfInterest> pointsOfInterest = new ArrayList<>();
        Random random = new Random();

        // Generate cities
        for (int i = 0; i < MAX_CITIES; i++) {
            String name = "City " + i;
            double latitude = random.nextDouble();
            double longitude = random.nextDouble();
            List<String> attractions = random.ints(0, GraphGenerator.attractions.length)
                    .distinct()
                    .limit(random.nextInt(GraphGenerator.attractions.length) + 1)
                    .mapToObj(j -> GraphGenerator.attractions[j])
                    .toList();
            int population = random.nextInt(1000000);
            City city = new City(name, population, latitude, longitude, attractions);
            pointsOfInterest.add(city);
        }

        // Generate fuel stations
        for (int i = 0; i < MAX_FUEL_STATIONS; i++) {
            String name = "Fuel Station " + i;
            double latitude = random.nextDouble();
            double longitude = random.nextDouble();
            Map<FuelType, Integer> fuels = new HashMap<>();
            fuels.put(FuelType.DIESEL, random.nextInt(MAX_FUEL_PRICE));
            fuels.put(FuelType.PETROL, random.nextInt(MAX_FUEL_PRICE));
            fuels.put(FuelType.LPG, random.nextInt(MAX_FUEL_PRICE));
            fuels.put(FuelType.HYDROGEN, random.nextInt(MAX_FUEL_PRICE));
            List<Service> services = new ArrayList<>();
            double rating = random.nextDouble() * 5.0;
            int count = random.nextInt(100);
            List<String> serviceNames = random.ints(0, GraphGenerator.ServiceNames.length)
                    .distinct()
                    .limit(random.nextInt(GraphGenerator.ServiceNames.length) + 1)
                    .mapToObj(j -> GraphGenerator.ServiceNames[j])
                    .toList();
            for (String serviceName : serviceNames) {
                int price = random.nextInt(500);
                Service service = new Service(serviceName, price);
                services.add(service);
            }
            FuelStation fuelStation = new FuelStation(name, latitude, longitude, fuels, services, rating, count);
            pointsOfInterest.add(fuelStation);
        }

        // Generate routes between every pair of points of interest
        for (PointOfInterest start : pointsOfInterest) {
            for (PointOfInterest destination : pointsOfInterest) {
                int length = random.nextInt(MAX_ROUTE_LENGTH) + 1;
                int maxSpeed;
                if (start instanceof City) {
                    maxSpeed = random.nextInt((MAX_SPEED_LIMIT - 30) / 2) + 30; // cities are not on highways (usually)
                } else {
                    maxSpeed = random.nextInt(MAX_SPEED_LIMIT - 30) + 30; // fuel stations are on highways
                }
                graph.addRoute(start, destination, length, maxSpeed);
            }
        }
        return graph;
    }
}
