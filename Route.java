import java.io.Serializable;

class Route implements Serializable {
    private int routeNumber;
    private String source;
    private String destination;

    public Route (){
        routeNumber = 0;

        source = "NULL";

        destination = "NULL";
    }
    public Route(int routeNumber, String source, String destination) {
        this.routeNumber = routeNumber;
        this.source = source;
        this.destination = destination;
    }

    public int getRouteNumber() {
        return routeNumber;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }
}
