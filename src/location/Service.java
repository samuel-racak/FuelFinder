package location;

public class Service {
    private String serviceName;
    private int price;

    public Service(String serviceName, int price) {
        this.serviceName = serviceName;
        this.price = price;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Service serviceName(String serviceName) {
        setServiceName(serviceName);
        return this;
    }

    public Service price(int price) {
        setPrice(price);
        return this;
    }

    @Override
    public String toString() {
        return getServiceName() + " Price: " + getPrice() + "$";
    }
}
