package location;

import java.io.Serializable;

// this class will remember on which route we are and what is our actual position which will be calculated from the length of the route
public class Location implements Serializable {
    private Route currentRoute;
    private int exactKilometer;

    public Location(Route currentRoute, int exactKilometer) {
        this.currentRoute = currentRoute;
        this.exactKilometer = exactKilometer;
    }

    public Route getCurrentRoute() {
        return this.currentRoute;
    }

    public void setCurrentRoute(Route currentRoute) {
        this.currentRoute = currentRoute;
    }

    public int getExactKilometer() {
        return this.exactKilometer;
    }

    public void setExactKilometer(int exactKilometer) {
        this.exactKilometer = exactKilometer;
    }

    public Location currentRoute(Route currentRoute) {
        setCurrentRoute(currentRoute);
        return this;
    }

    public Location exactKilometer(int exactKilometer) {
        setExactKilometer(exactKilometer);
        return this;
    }
}
