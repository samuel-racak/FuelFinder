package location;

import java.util.List;
import java.util.Objects;

/**
 * The City class represents a city.
 * It extends the PointOfInterest class.
 */
public class City extends PointOfInterest {
    int population;
    private List<String> attractions; // list of attractions in a given city such as museums, restaurants etc.

    /**
     * Constructor for the City class.
     * Initializes the name, population, latitude, longitude and attractions to the
     * given values.
     *
     * @param name        the name of the city
     * @param population  the population of the city
     * @param latitude    the latitude of the city
     * @param longitude   the longitude of the city
     * @param attractions the list of attractions in the city
     */
    public City(String name, int population, double latitude, double longitude, List<String> attractions) {
        super(name, latitude, longitude);
        this.attractions = attractions;
        this.population = population;
    }

    /**
     * Returns the population of the city.
     *
     * @return the population of the city
     */
    public int getPopulation() {
        return this.population;
    }

    /**
     * Sets the population of the city.
     *
     * @param population the new population to set
     */
    public void setPopulation(int population) {
        this.population = population;
    }

    /**
     * Returns the list of attractions in the city.
     *
     * @return the list of attractions in the city
     */
    public List<String> getAttractions() {
        return this.attractions;
    }

    /**
     * Sets the list of attractions in the city.
     *
     * @param attractions the new list of attractions to set
     */
    public void setAttractions(List<String> attractions) {
        this.attractions = attractions;
    }

    /**
     * Sets the population of the city and returns this City object.
     *
     * @param population the new population to set
     * @return this City object
     */
    public City population(int population) {
        setPopulation(population);
        return this;
    }

    /**
     * Sets the list of attractions in the city and returns this City object.
     *
     * @param attractions the new list of attractions to set
     * @return this City object
     */
    public City attractions(List<String> attractions) {
        setAttractions(attractions);
        return this;
    }

    /**
     * Compares this city to another object for equality.
     *
     * @param o the other object to compare to
     * @return true if o is a City and has the same population and attractions as
     *         this city; false otherwise
     */
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

    /**
     * Returns a hash code for this city.
     *
     * @return a hash code for this city
     */
    @Override
    public int hashCode() {
        return Objects.hash(population, attractions);
    }

    /**
     * Returns a string representation of this city.
     *
     * @return a string representation of this city
     */
    @Override
    public String toString() {
        return getName() + "\nPopulation: " + getPopulation() + "\nAttractions: " + getAttractions();
    }
}
