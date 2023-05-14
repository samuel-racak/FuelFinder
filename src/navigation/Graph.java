package navigation;

import java.util.ArrayList;
import java.util.HashMap;

import location.PointOfInterest;
import location.Route;

/**
 * A graph of routes between points of interest
 * start is the index of the outer array list
 * dest is the index of the inner array list
 */
public class Graph {
    private HashMap<PointOfInterest, ArrayList<Route>> routes;

    public Graph() {
        routes = new HashMap<>();
    }

    /**
     * Adds a route to the graph
     *
     * @param start       the starting point of the route
     * @param destination the destination of the route
     * @param length      the length of the route
     * @param maxSpeed    the maximum speed allowed on the route
     */
    public void addRoute(PointOfInterest start, PointOfInterest destination, int length, int maxSpeed) {
        ArrayList<Route> routesFromStart = routes.get(start);
        if (routesFromStart == null) {
            routesFromStart = new ArrayList<>();
            routes.put(start, routesFromStart);
        }
        routesFromStart.add(new Route(length, destination, maxSpeed));
    }

    /**
     * Adds a route to the graph from start to start with the given length and
     * maxSpeed
     *
     * @param route
     */
    public void addRoute(Route route) {
        addRoute(route.getDestination(), route.getDestination(), route.getLength(), route.getMaxSpeed());
    }

    /**
     * Returns the routes from a given point of interest
     *
     * @param start
     * @return ArrayList<Route> routes from start
     */
    public ArrayList<Route> getRoutesFrom(PointOfInterest start) {
        return routes.get(start);
    }

    /**
     * Returns all routes in the graph
     *
     * @return HashMap<PointOfInterest, ArrayList<Route>> routes
     */
    public HashMap<PointOfInterest, ArrayList<Route>> getRoutes() {
        return routes;
    }
}
