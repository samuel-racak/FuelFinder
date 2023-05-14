package navigation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import location.FuelStation;
import location.PointOfInterest;
import location.Route;
import vehicle.Car;

/**
 * This class implements the DijkstraCost strategy for finding the cheapest
 * route
 * between two points of interest (POIs)
 */
public class DijkstraCost implements Strategy {
    private double maxFuelPercentage; // The maximum amount of fuel that can be refilled at a fuel station (in
    // percentage of the cars fuelCapacity) (0-100)

    public DijkstraCost(double maxFuelPercentage) {
        this.maxFuelPercentage = maxFuelPercentage;
    }

    /**
     * finds the cheapest route between two given points using Dijkstra's algorithm
     *
     * @param graph
     * @param start
     * @param destination
     * @param car
     * @return a map of the cheapest routes from start to all other points of
     *         interest
     */
    private Map<PointOfInterest, List<Route>> shortestPath(Graph graph, PointOfInterest start,
            PointOfInterest destination, Car car) {
        Map<PointOfInterest, Double> costs = new HashMap<>();
        Map<PointOfInterest, Boolean> visited = new HashMap<>();
        Map<PointOfInterest, List<Route>> paths = new HashMap<>();
        Map<PointOfInterest, Double> remainingFuel = new HashMap<>();

        // Initialize all costs to infinity
        for (PointOfInterest poi : graph.getRoutes().keySet()) {
            costs.put(poi, Double.MAX_VALUE);
            visited.put(poi, false);
            paths.put(poi, new ArrayList<>());
            remainingFuel.put(poi,
                    ((double) car.getCurrentFuelLevel() / 100.0) * car.getFuelTankCapacity());
        }

        // Set the cost from the source to itself to 0
        costs.put(start, 0.0);

        // Create a priority queue to store vertices that are being preprocessed
        Queue<PointOfInterest> pq = new PriorityQueue<>((a, b) -> Double.compare(costs.get(a), costs.get(b)));
        pq.add(start);

        while (!pq.isEmpty()) {
            // Remove the vertex with the minimum cost from the priority queue
            PointOfInterest u = pq.poll();

            // End if the destination is reached
            if (u.equals(destination)) {
                return paths;
            }

            // Mark the vertex as visited
            visited.put(u, true);

            // Refill the car's fuel tank if u is a fuel station
            double refuelCost = 0;
            if (u instanceof FuelStation) {
                FuelStation fuelStation = (FuelStation) u;
                // Refuel the car with the maximum amount of fuel possible
                double fuelNeeded = Math.min((car.getFuelTankCapacity() * maxFuelPercentage) / 100,
                        car.getFuelTankCapacity() - remainingFuel.get(u));
                refuelCost = fuelNeeded * fuelStation.getFuels().get(car.getFuelType());
                remainingFuel.put(u, remainingFuel.get(u) + fuelNeeded);
            }

            // Update the cost and path of all adjacent vertices
            for (Route route : graph.getRoutesFrom(u)) {
                PointOfInterest v = route.getDestination();
                double length = route.getLength();
                double consumedFuel = car.getFuelConsumption() * (length / 100.0);

                if (!visited.get(v) && costs.get(u) + refuelCost < costs.get(v)
                        && remainingFuel.get(u) >= consumedFuel) { // Check if the car has enough fuel to reach v from u
                    costs.put(v, costs.get(u) + refuelCost);
                    pq.add(v);

                    // Update the remaining fuel level at v
                    remainingFuel.put(v, remainingFuel.get(u) - consumedFuel);

                    // Update the path from start to v
                    List<Route> newPath = new ArrayList<>(paths.get(u));
                    newPath.add(route);
                    paths.put(v, newPath);
                }

                // if (!visited.get(v) && costs.get(u) + length + refuelCost < costs.get(v)
                // && remainingFuel.get(u) >= consumedFuel) { // Check if the car has enough
                // fuel to reach v from u
                // costs.put(v, costs.get(u) + length + refuelCost);
                // pq.add(v);

                // // Update the remaining fuel level at v
                // remainingFuel.put(v, remainingFuel.get(u) - consumedFuel);

                // // Update the path from start to v
                // List<Route> newPath = new ArrayList<>(paths.get(u));
                // newPath.add(route);
                // paths.put(v, newPath);
                // }
            }

        }

        return paths;
    }

    @Override
    public List<Route> findRoute(PointOfInterest start, PointOfInterest finish, Graph graph, Car car) {
        Map<PointOfInterest, List<Route>> paths = shortestPath(graph, start, finish, car);
        return paths.get(finish);
    }
}