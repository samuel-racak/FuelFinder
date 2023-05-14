package navigation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.List;

import location.PointOfInterest;
import location.Route;
import vehicle.Car;

/**
 * This class implements the Dijkstra algorithm to find the shortest route
 * between two points of interest (POIs) in a graph while ending sooner than
 * Dijkstra.java
 */
public class DijkstraOptimized implements Strategy {

    /**
     * Finds the shortest route between two points of interest (POIs) using the
     * Dijkstra algorithm
     *
     * @param graph
     * @param start
     * @param destination
     * @return Map<PointOfInterest, List<Route>>
     */
    public static Map<PointOfInterest, List<Route>> shortestPath(Graph graph, PointOfInterest start,
            PointOfInterest destination) {
        Map<PointOfInterest, Double> distances = new HashMap<>();
        Map<PointOfInterest, Boolean> visited = new HashMap<>();
        Map<PointOfInterest, List<Route>> paths = new HashMap<>(); // this will be used to store all the routes from the
                                                                   // start to the destination

        // Initialize all distances to infinity
        for (PointOfInterest poi : graph.getRoutes().keySet()) {
            distances.put(poi, Double.MAX_VALUE);
            visited.put(poi, false);
            paths.put(poi, new ArrayList<>());
        }

        // Set the distance from the source to itself to 0
        distances.put(start, 0.0);

        // Create a priority queue to store vertices that are being preprocessed
        Queue<PointOfInterest> pq = new PriorityQueue<>((a, b) -> Double.compare(distances.get(a), distances.get(b)));
        pq.add(start);

        while (!pq.isEmpty()) {
            // Remove the vertex with the minimum distance from the priority queue
            PointOfInterest u = pq.poll();

            // end if the destination is reached
            if (u.equals(destination)) {
                return paths;
            }
            // Mark the vertex as visited
            visited.put(u, true);

            // Update the distance of all adjacent vertices
            for (Route route : graph.getRoutesFrom(u)) {
                PointOfInterest v = route.getDestination(); // v is the destination of the currently processed route
                double length = route.getLength(); // length is the length of the currently processed route
                if (!visited.get(v) && distances.get(u) + length < distances.get(v)) {
                    distances.put(v, distances.get(u) + length);
                    pq.add(v);

                    // get the list of routes from the source to u and save it in newPath
                    List<Route> newPath = new ArrayList<>(paths.get(u));
                    newPath.add(route); // add the currently processed route to the list as it is the last route
                                        // before reaching v
                    paths.put(v, newPath); // save the list of routes from the source to v
                }
            }
        }

        return paths; // return the list of routes from the source to all other vertices
    }

    @Override
    public List<Route> findRoute(PointOfInterest start, PointOfInterest finish, Graph graph, Car car) {
        // this will return the list of routes from the source to all other vertices
        Map<PointOfInterest, List<Route>> allPaths = shortestPath(graph, start, finish);
        return allPaths.get(finish); // return the distance from the source to the destination (finish)
    }
}
