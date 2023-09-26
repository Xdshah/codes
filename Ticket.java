import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

class Ticket  implements Serializable {


    private String ticketId;
    private Route route;
    private Bus bus;

    public Ticket(){
        ticketId = "NULL";
    }
    public Ticket(Route route, Bus bus) {
        this.ticketId = generateTicketId();
        this.route = route;
        this.bus = bus;
    }

    private String generateTicketId() {
        int randomNumber = (int) (Math.random() * 9000) + 1000; // Generate a random 4-digit number
        int ticketId = randomNumber;
        return "T" + ticketId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public Route getRoute() {
        return route;
    }

    public Bus getBus() {
        return bus;
    }
}
