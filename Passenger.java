import java.io.*;
import java.util.*;

class Passenger extends User {

    public Passenger(){}
    Passenger(String name, String email) {
        super(name,email);
    }


    public static void buyTicket() {
        Scanner input = new Scanner(System.in);
        ArrayList<Bus> buses = loadBusesFromFile();
        System.out.println("Our service is available for the following routes:");
        displayRoutes();

        System.out.println("If your desired route is available, enter '1'. Otherwise, enter '0' to exit:");
        String choice = input.next();

        if (choice.equals("1")) {

            System.out.println("Enter route Number: ");
            int routeNumber = input.nextInt();
            Bus selectedBus = null;
            for (Bus bus : buses) {
                if (bus.getRoute().getRouteNumber() == routeNumber) {
                    selectedBus = bus;
                    break;
                }
            }
            if (selectedBus == null) {
                System.out.println("Route not found.");
                return;
            }
            Ticket ticket = new Ticket(selectedBus.getRoute(), selectedBus);
            saveTicketToFile(ticket);
            System.out.println("Ticket purchased successfully. Ticket ID: " + ticket.getTicketId());
        } else {
            System.out.println("Thank you for using our service!");
        }
    }

    public static void cancelTicket(String ticketId) {
        ArrayList<Ticket> tickets = loadTicketsFromFile();
        boolean removed = false;
        for (int i = 0; i < tickets.size(); i++) {
            Ticket ticket = tickets.get(i);
            if (ticket.getTicketId().equals(ticketId)) {
                tickets.remove(i);
                removed = true;
                break;
            }
        }
        if (removed) {
             saveTicketsToFile(tickets);
            System.out.println("Ticket canceled successfully.");
        } else {
            System.out.println("Ticket not found.");
        }
    }

    public static void viewTicketDetails(String ticketId) {
        List<Ticket> tickets = loadTicketsFromFile();
        for (Ticket ticket : tickets) {
            if (ticket.getTicketId().equals(ticketId)) {
                System.out.println("Ticket ID: " + ticket.getTicketId());
                System.out.println("Route: " + ticket.getRoute().getSource() + " to " + ticket.getRoute().getDestination());
                System.out.println("Bus Number: " + ticket.getBus().getBusNumber());
                System.out.println("Fare: "+ticket.getBus().getFare());
                return;
            }
        }
        System.out.println("Ticket not found.");
    }

    public static void displayRoutes() {
        ArrayList<Bus> buses = loadBusesFromFile();
        if (buses.isEmpty()) {
            System.out.println("No routes available.");
        } else {
            System.out.println("Route Number");
            for (Bus bus : buses) {
                Route route = bus.getRoute();
                System.out.println(route.getRouteNumber() + "\t\t\t" + route.getSource() + "------------>" + route.getDestination() +"\t\t\t"+bus.getFare());
            }
        }
    }

    private static ArrayList<Bus> loadBusesFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("buses.dat"))) {
            return (ArrayList<Bus>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }


    private static ArrayList<Ticket> loadTicketsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("tickets.dat"))) {
            return (ArrayList<Ticket> ) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private static void saveTicketToFile(Ticket ticket) {
        ArrayList<Ticket> tickets = loadTicketsFromFile();
        tickets.add(ticket);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tickets.dat"))) {
            oos.writeObject(tickets);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveTicketsToFile(ArrayList<Ticket> tickets) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tickets.dat"))) {
            oos.writeObject(tickets);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
