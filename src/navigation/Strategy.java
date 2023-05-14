package navigation;

import location.PointOfInterest;
import location.Route;

/**
 * this interface will be implemented by different classes which will try to
 * find a route between given points
 *
 */
public interface Strategy {
    /**
     * tries to find Route between two given points
     *
     * @param start  starting point
     * @param finish ending point
     * @return a route from start to finish
     */
    Route findRoute(PointOfInterest start, PointOfInterest finish);
}
