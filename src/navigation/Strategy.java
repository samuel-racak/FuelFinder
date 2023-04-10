package navigation;

import location.Point;
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
     * @return a route from start to finish
     */
    public Route findRoute(Point start, Point finish);
}
