import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BusReservationGUI extends JFrame {
    private JButton adminButton;
    private JButton passengerButton;
    private JButton exitButton;

    public BusReservationGUI() {
        setTitle("Bus Reservation System");
        setLayout(new FlowLayout());
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(false);

        JLabel welcomeLabel = new JLabel("Welcome to Online Bus Reservation System");
        add(welcomeLabel);

        adminButton = new JButton("Admin");
        add(adminButton);

        passengerButton = new JButton("Passenger");
        add(passengerButton);

        exitButton = new JButton("Exit");
        add(exitButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void addAdminButtonListener(ActionListener listener) {
        adminButton.addActionListener(listener);
    }

    public void addPassengerButtonListener(ActionListener listener) {
        passengerButton.addActionListener(listener);
    }

    public void addExitButtonListener(ActionListener listener) {
        exitButton.addActionListener(listener);
    }



    public static void main(String[] args) {

        BusReservationGUI busReservationGUI = new BusReservationGUI();
        busReservationGUI.setVisible(true);

        busReservationGUI.addAdminButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                busReservationGUI.setVisible(false);
                showAdminLoginDialog();

            }
        });

        busReservationGUI.addPassengerButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                busReservationGUI.setVisible(false);

                showPassengerDialog();

            }
        });

        busReservationGUI.addExitButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });



    }

    private static void showAdminLoginDialog() {
        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JTextField emailField = new JTextField(20);

        while(true){
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            panel.add(new JLabel("Username:"));
            panel.add(usernameField);

            panel.add(new JLabel("Password:"));
            panel.add(passwordField);

            panel.add(new JLabel("Email:"));
            panel.add(emailField);


            int result = JOptionPane.showConfirmDialog(null, panel, "Admin Login",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());


                if (verifyAdminCredentials(username, password)) {

                    adminMenu();
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials");
                    break;
                }
            }
            else {

                System.exit(1);
            }
        }
    }
    private static boolean verifyAdminCredentials(String username, String password) {

        if ((username.equalsIgnoreCase("admin") && password.equals("admin123"))) {
            return true;
        }
        return false;
    }

    private static void showPassengerDialog(){
        JTextField usernameField = new JTextField(20);
        JTextField emailField = new JTextField(20);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Passenger details",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            Passenger  p = new Passenger(usernameField.getText(),emailField.getText());
            passengerMenu();


        }

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
            try {
                int choice = input.nextInt();

                switch (choice) {
                    case 1:

                        System.out.print("Enter route number: ");
                        int routeNumber = input.nextInt();
                        String source;
                        String destination;
                        while (true) {
                            System.out.print("Enter source: ");
                            source = input.next();
                            System.out.print("Enter destination: ");
                            destination = input.next();
                            if (source.equals(destination)) {
                                System.out.println("Source and destination cannot be same.");
                            } else
                                break;
                        }
                        System.out.print("Enter bus number: ");
                        String busNumber = input.next();
                        int fare;
                        while (true) {
                            System.out.print("Enter fare: ");
                            fare = input.nextInt();
                            if (fare < 0 || fare == 0) {
                                System.out.println("Are you selling the ticket for free?\n" + "Please try again: ");
                            } else
                                break;
                        }
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
                        System.exit(1);
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid choice.");
                input.nextLine(); // Consume the invalid input
            }
        }
    }

    public static void passengerMenu() {
        Scanner input = new Scanner(System.in);


        while (true) {
            System.out.println();
            System.out.println("\t****Passenger Menu:****");
            System.out.println("1. Buy Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. View Ticket Details");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            try {
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
                        System.exit(1);
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid choice.");
                input.nextLine(); // Consume the invalid input
            }
        }
    }
}
