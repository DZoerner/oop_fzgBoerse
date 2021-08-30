public class Vehicle {

    private final int vehicleId;
    private String vehicleType;
    private String brand;
    private String model;
    private String color;
    private int constructionYear;
    private double price;


    public Vehicle(int vehicleId, String vehicleType, String brand, String model, String color, int constructionYear, double price) {
        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.constructionYear = constructionYear;
        this.price = price;
    }

    @Override
    public String toString() {
        return getVehicleId() + " " + getVehicleType() + " " + getBrand() + " " +
                getModel() + " " + getColor() + " " + getConstructionYear() + " " +
                getPrice();
    }

    public String toDataString() {
        return getVehicleId() + "," + getVehicleType() + "," + getBrand() + "," +
                getModel() + "," + getColor() + "," + getConstructionYear() + "," +
                getPrice();
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getConstructionYear() {
        return constructionYear;
    }

    public void setConstructionYear(int constructionYear) {
        this.constructionYear = constructionYear;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    // public int getCounter() {
    //    return counter;
    // }

    // useful?
    // public void setCounter(int counter) {
    // this.counter = counter;
    // }

}
