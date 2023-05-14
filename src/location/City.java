package location;

import java.util.List;
import java.util.Objects;

public class City extends PointOfInterest {
    int population;
    private List<String> attractions; // list of attractions in a given city such as museums, restaurants etc.

    public City(String name, double latitude, double longitude, List<String> attractions) {
        super(name, latitude, longitude);
        this.attractions = attractions;
    }

    public int getPopulation() {
        return this.population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public List<String> getAttractions() {
        return this.attractions;
    }

    public void setAttractions(List<String> attractions) {
        this.attractions = attractions;
    }

    public City population(int population) {
        setPopulation(population);
        return this;
    }

    public City attractions(List<String> attractions) {
        setAttractions(attractions);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof City)) {
            return false;
        }
        City city = (City) o;
        return population == city.population && Objects.equals(attractions, city.attractions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(population, attractions);
    }

    @Override
    public String toString() {
        return "Population: " + population + "\nAttractions: " + attractions;
    }
}
