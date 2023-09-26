import java.io.*;
import java.lang.management.ThreadInfo;
import java.util.ArrayList;
import java.util.List;

class Admin extends User {

    protected String password;

    public Admin(){

        password = "NULL";
    }
    public Admin(String name,String email, String password){
        super(name, email);

        this.password = password;
    }


    public String getPassword() {
        return password;
    }



    public static void addBus(int routeNumber,String source, String destination, String busNumber, int fare , String driver) {
        ArrayList<Bus> buses = loadBusesFromFile();
        Route route = new Route(routeNumber, source,destination);
        Bus bus = new Bus(busNumber,driver,route,fare);
        buses.add(bus);
        saveBusesToFile(buses);
        System.out.println("Bus added successfully.");
    }

    public static void removeBus(String busNumber) {
        ArrayList<Bus> buses = loadBusesFromFile();
        boolean removed = false;
        for (int i = 0; i < buses.size(); i++) {
            Bus bus = buses.get(i);
            if (bus.getBusNumber().equals(busNumber) ) {
                buses.remove(i);
                removed = true;
                break;
            }
        }
        if (removed) {
            saveBusesToFile(buses);
            System.out.println("Bus removed successfully.");
        } else {
            System.out.println("Bus not found.");
        }
    }

    public static void viewBuses() {
        ArrayList<Bus> buses = loadBusesFromFile();
        if (buses.isEmpty()) {
            System.out.println("No buses available.");
        } else {
            System.out.println("BusNo.\tDriver\t\tRoute\t\t\tFare");
            for (Bus bus : buses) {
                System.out.println(
                        bus.getBusNumber() + "\t  " +
                                bus.getDriverName() + "\t" +
                                bus.getRoute().getSource() + " to " +
                                bus.getRoute().getDestination() + "\t" +
                                bus.getFare()
                );
            }
        }
    }

    public static void viewTickets() {
        ArrayList<Ticket> tickets = loadTicketsFromFile();
        if (tickets.isEmpty()) {
            System.out.println("No tickets available.");
        } else {
            System.out.println("Ticket ID\tRoute\t\t\tBus Number");
            for (Ticket ticket : tickets) {
                System.out.println(
                        ticket.getTicketId() + "\t" +
                                ticket.getRoute().getSource() + " to " +
                                ticket.getRoute().getDestination() + "\t\t\t" +
                                ticket.getBus().getBusNumber()
                );
            }
        }
    }

    public static void viewRoutes() {
        ArrayList<Bus> buses = loadBusesFromFile();
        if (buses.isEmpty()) {
            System.out.println("No routes available.");
        } else {
            System.out.println("Source\t\tDestination\t\t\tFare\t\tBus Number");
            for (Bus bus : buses) {
                System.out.println(bus.getRoute().getSource() + "\t\t\t" + bus.getRoute().getDestination() + "\t\t\t" + bus.getFare() +"\t\t\t"+ bus.getBusNumber()
                );
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

    private static void saveBusesToFile(ArrayList<Bus> buses) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("buses.dat"))) {
            oos.writeObject(buses);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Ticket> loadTicketsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("tickets.dat"))) {
            return (ArrayList<Ticket>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }



}