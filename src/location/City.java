package location;

import java.util.List;

public class City extends Point {
    private List<String> attractions; // list of attractions in a given city such as museums, restaurants etc.

    public City(String name, List<Point> successors, List<String> attractions) {
        super(name, successors);
        this.attractions = attractions;
    }

    public List<String> getAttractions() {
        return this.attractions;
    }

    public void setAttractions(List<String> attractions) {
        this.attractions = attractions;
    }

    public City attractions(List<String> attractions) {
        setAttractions(attractions);
        return this;
    }
}
