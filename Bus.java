import java.io.Serializable;

class Bus implements Serializable {
    private String busNumber;
    private String driverName;
    private Route route;
    private double fare;

    public Bus(){
        busNumber = "NULL";

        driverName = "NULL";
    }
    public Bus(String busNumber, String driverName, Route route, double fare) {
        this.busNumber = busNumber;
        this.driverName = driverName;
        this.route = route;
        this.fare = fare;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public Route getRoute() {
        return route;
    }

    public double getFare() {
        return fare;
    }

    public String toString() {
        return "Bus Number: " + busNumber + "\nDriver Name: " + driverName + "\nAvailable Seats: " + "\nRoute: " + route.getSource()+"-----"+route.getDestination() + "\nFare: " + fare;
    }
}
