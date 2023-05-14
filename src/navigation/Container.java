package navigation;

import java.util.List;

import location.PointOfInterest;
import location.Route;
import vehicle.Car;

/**
 * this class will be used to change the strategy of finding a route between two
 * points of interest (POIs) this is Implementation of Strategy Pattern
 *
 */
public class Container {
    private Strategy strategy;

    /**
     * Constructor
     *
     * @param strategy
     */
    public Container(Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Getter for strategy
     *
     * @return
     */
    public Strategy getStrategy() {
        return this.strategy;
    }

    /**
     * Setter for strategy
     *
     * @param strategy
     */
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