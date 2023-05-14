package navigation;

import java.util.List;

import location.PointOfInterest;
import location.Route;
import vehicle.Car;

/**
 * this interface will be implemented by different classes which will try to
 * find a route between given points
 *
 */
interface Strategy {
    /**
     * tries to find Route between two given points
     *
     * @param start  starting point
     * @param finish ending point
     * @param graph  graph of routes
     * @param car    car used to find route
     * @return a {@code List of} {@link Route} from start to finish
     */
    List<Route> findRoute(PointOfInterest start, PointOfInterest finish, Graph graph, Car car);
}
