import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {

        BusReservationGUI busReservationGUI = new BusReservationGUI();


        busReservationGUI.addAdminButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                showAdminLoginDialog();
            }
        });

        busReservationGUI.addPassengerButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                passengerMenu();
            }
        });

        busReservationGUI.addExitButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });


        busReservationGUI.setVisible(true);
    }

    private static void showAdminLoginDialog() {
        JTextField usernameField = new JTextField(10);
        JPasswordField passwordField = new JPasswordField(10);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Admin Login",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());


            if (verifyAdminCredentials(username, password)) {

                adminMenu();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid credentials");
            }
        }
    }

    private static boolean verifyAdminCredentials(String username, String password) {

        if ((username.equalsIgnoreCase("admin") && password.equals("admin123"))) {
            return true;
        }
        return false;
    }

    public static void adminMenu() {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Admin Menu:");
            System.out.println("1. Add Bus");
            System.out.println("2. Remove Bus");
            System.out.println("3. View Buses");
            System.out.println("4. View Tickets");
            System.out.println("5. View Routes");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter route number: ");
                    int routeNumber = input.nextInt();
                    System.out.print("Enter source: ");
                    String source = input.next();
                    System.out.print("Enter destination: ");
                    String destination = input.next();
                    System.out.print("Enter bus number: ");
                    String busNumber = input.next();
                    System.out.print("Enter fare: ");
                    int fare = input.nextInt();
                    System.out.print("Enter driver name: ");
                    String driver = input.next();
                    Admin.addBus(routeNumber, source, destination, busNumber, fare, driver);
                    break;
                case 2:
                    System.out.print("Enter bus number: ");
                    String removeBusNumber = input.next();
                    Admin.removeBus(removeBusNumber);
                    break;
                case 3:
                    Admin.viewBuses();
                    System.out.println("********\n");
                    break;
                case 4:
                    Admin.viewTickets();
                    System.out.println("********\n");
                    break;
                case 5:
                    Admin.viewRoutes();
                    System.out.println("********\n");
                    break;
                case 6:
                    System.out.println("Logging out...");
                    System.out.println("********\n");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void passengerMenu() {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Passenger Menu:");
            System.out.println("1. Buy Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. View Ticket Details");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    Passenger.buyTicket();
                    System.out.println("********\n");
                    break;
                case 2:
                    System.out.print("Enter ticket ID: ");
                    String ticketId = input.next();
                    Passenger.cancelTicket(ticketId);
                    System.out.println("********\n");
                    break;
                case 3:
                    System.out.print("Enter ticket ID: ");
                    String viewTicketId = input.next();
                    Passenger.viewTicketDetails(viewTicketId);
                    System.out.println("********\n");
                    break;
                case 4:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}


