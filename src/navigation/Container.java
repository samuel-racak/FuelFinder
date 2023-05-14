package navigation;

import java.util.List;

import location.PointOfInterest;
import location.Route;
import vehicle.Car;

public class Container {
    private Strategy strategy;

    public Container(Strategy strategy) {
        this.strategy = strategy;
    }

    public Strategy getStrategy() {
        return this.strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Finds the shortest route between two points of interest (POIs) using strategy
     * with the given graph and car
     *
     * @param start  the starting POI
     * @param finish the destination POI
     * @param graph  the graph of routes between POIs
     * @param car    the car that will be used to travel the route
     * @return
     */
    public List<Route> findRoute(PointOfInterest start, PointOfInterest finish, Graph graph, Car car) {
        return strategy.findRoute(start, finish, graph, car);
    }
}