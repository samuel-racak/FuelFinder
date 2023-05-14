package managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import location.GraphGenerator;
import location.PointOfInterest;
import location.Route;
import navigation.DijkstraOptimized;
import navigation.DijkstraCost;
import navigation.Graph;
import vehicle.Car;
import navigation.Container;

public class POIManager {
    private static POIManager instance;
    private Graph graph; // this is the graph of routes between points of interest (POIs)
    private Container container;

    private POIManager() {
        graph = GraphGenerator.generateGraph();
        container = new Container(new DijkstraOptimized());
    }

    public static POIManager getInstance() {
        if (instance == null) {
            instance = new POIManager();
        }
        return instance;
    }

    /**
     * Finds the shortest route between two points of interest (POIs) using strategy
     * which is set in the container
     *
     * @param start       the starting POI
     * @param destination the destination POI
     * @param car         the car that will be used to travel the route
     * @return List<Route>
     */
    public List<Route> findRoute(PointOfInterest start, PointOfInterest destination, Car car) {
        return container.findRoute(start, destination, graph, car);
    }

    /**
     * Changes the strategy of the container to either DijkstraOptimized or
     * DijkstraCost
     *
     * @param shortest if true, the strategy will be DijkstraOptimized, otherwise
     *                 DijkstraCost
     */
    public void changeStrategy(boolean shortest) {
        if (shortest) {
            container.setStrategy(new DijkstraOptimized());
        } else {
            container.setStrategy(new DijkstraCost(100));
        }
    }

    /**
     * Returns a list of POIs
     *
     * @return List<PointOfInterest>
     */
    public List<PointOfInterest> getPOIs() {
        return new ArrayList<>(graph.getRoutes().keySet());
    }

    /**
     * Returns a map of POIs with their names as keys
     *
     * @return Map<String, PointOfInterest>
     */
    public Map<String, PointOfInterest> getPOIsMap() {
        List<PointOfInterest> pointsOfInterests = getPOIs();
        Map<String, PointOfInterest> pointsOfInterestsMap = new HashMap<>();
        for (PointOfInterest poi : pointsOfInterests) {
            pointsOfInterestsMap.put(poi.toString(), poi);
        }
        return pointsOfInterestsMap;
    }
}
