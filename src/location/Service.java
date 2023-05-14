package location;

/**
 * This class represents a service offered at a fuel station.
 */
public class Service {
    private String serviceName;
    private int price;

    /**
     * Constructs a new Service object.
     *
     * @param serviceName The name of the service.
     * @param price       The price of the service in dollars.
     */
    public Service(String serviceName, int price) {
        this.serviceName = serviceName;
        this.price = price;
    }

    /**
     * Gets the name of the service.
     *
     * @return The name of the service.
     */
    public String getServiceName() {
        return this.serviceName;
    }

    /**
     * Sets the name of the service.
     *
     * @param serviceName The new name of the service.
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    /**
     * Gets the price of the service in dollars.
     *
     * @return The price of the service in dollars.
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * Sets the price of the service in dollars.
     *
     * @param price The new price of the service in dollars.
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Sets the name and returns this Service object.
     *
     * @param serviceName The new name to set.
     * @return This Service object with its name set to `serviceName`.
     */
    public Service serviceName(String serviceName) {
        setServiceName(serviceName);
        return this;
    }

    /**
     * Sets the price and returns this Service object.
     *
     * @param price The new price to set.
     * @return This Service object with its price set to `price`.
     */
    public Service price(int price) {
        setPrice(price);
        return this;
    }

    /**
     * Returns a string representation for this object.
     *
     * @return A string representation for this object
     */
    @Override
    public String toString() {
        return getServiceName() + " Price: " + getPrice() + "$";
    }
}
