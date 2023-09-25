
    import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

    public class bus {
        static Scanner input = new Scanner(System.in);
        static ArrayList<String> destination = new ArrayList<>();

        static List<String> arrival = new ArrayList<>();


        public static void createFile(String name) {
            File myFile = new File(name);
            try {
                myFile.createNewFile();
                System.out.println("File created!");
            } catch (IOException e) {
                System.out.println("Unable to create file");
                throw new RuntimeException(e);
            }
        }

        public static void writeFile(String name, ArrayList<String> list1) {

            try {
                FileWriter out = new FileWriter(name);
                for (int i = 0; i < list1.size(); i++) {
                    out.write(list1.get(i) + "\t\t");

                }
                out.close();

            }
            catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }

        }


        public static void main(String[] args) throws IOException {
            createFile("ticketIds.txt");
            createFile("destination.txt");
            createFile("arrival.txt");
            createFile("fare.txt");
            createFile("time.txt");
            createFile("bus.txt");
            createFile("name.txt");
            Scanner list = null;
            Scanner obj = new Scanner(System.in);

            try {
                list = new Scanner(new File("destination.txt"));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            while (list.hasNext()) {
                destination.add(list.next());

            }
            System.out.println("------BUS MANAGEMENT SYSTEM------");

            while (true) {
                System.out.println("press 1 to goto admin, 2 to book ticket, 3 to cancel ticket, 4 to view ticket:");
                System.out.println("____");
                try {
                    int choice = new Scanner(System.in).nextInt();

                    switch (choice) {

                        case 1:
                            Ticket.Admin.admin();
                            break;
                        case 2:
                            Ticket.create().saveToFile();
                            break;
                        case 3:
                            System.out.println("Enter ID");
                            String ID = obj.nextLine();
                            Ticket.Cancel(ID);
                            break;
                        case 4:
                            Ticket.viewTicket();
                        default:
                            break;
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Enter valid input!");
                    input.nextLine();
                }
            }
        }


    }

    class Ticket {
        static List<String> ticketIds = new ArrayList<>();
        static List<String> cancel = new ArrayList<>();
        static List<String> name = new ArrayList<>();
        static int MAX_VALID_YR = 9999;
        static int MIN_VALID_YR = 2023;
        static ArrayList<String> display = new ArrayList<>();
        static int MAX_POSSIBLE_TICKETS = 52;
        int ticketID;
        String passengerName;
        String ticketDate, departureDate;
        int seatNo;
        int age;
        static int bill;
        String from;
        String to;
        int busNumber;

        // -
        public Ticket(String passengerName, String departureDate, int seatNo, String dep, String ari, int cost, int busNumber) {
            Scanner list = null;

            try {
                list = new Scanner(new File("ticketIds.txt"));
                while (list.hasNext()) {

                    ticketIds.add(list.next());

                }
                list.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }


            this.ticketID = (int) (100000.0 * Math.random());
            ticketIds.add(Integer.toString(ticketID));
            try {
                FileWriter out = new FileWriter("ticketIds.txt");
                for (int i = 0; i < ticketIds.size(); i++) {
                    out.write(ticketIds.get(i) + "\n");

                }
                out.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ticketIds.clear();


            this.passengerName = passengerName;
            this.departureDate = departureDate;
            this.seatNo = seatNo;
            this.from = String.valueOf(dep);
            this.to = String.valueOf(ari);
            //this.age = age;
            this.bill = Integer.valueOf(cost);
            this.busNumber = busNumber;

        }

        void saveToFile() {
            try {
                // Adding System Record
                FileWriter systemTicketRecordWriter = new FileWriter("TICKET_RECORDS.txt", true);
                systemTicketRecordWriter.write(String.format(
                        "Seat No: %d " + "Departure Date: %s " + "Bus Number: %d " + "Ticket ID # %d " +
                                "Arrival: %s " + "Departure: %s " +
                                "Passenger Name: %s\n",
                        seatNo, departureDate, busNumber, ticketID, to, from, passengerName
                ));
                systemTicketRecordWriter.close();
                readFile("name.txt", (ArrayList<String>)name);
                name.add(passengerName);
                writeFile("name.txt", (ArrayList<String>)name);
                name.clear();

                // Passenger Copy:
                FileWriter writer = new FileWriter("Ticket No." + ticketID + ".txt" , false);
                writer.write(String.format(
                        "Ticket ID # %d for Passenger %s\n" +
                                "Ticket Dated: %s\n" +
                                "Departure Date: %s\n" +
                                "Bus Number: %d\n" +
                                "Seat # %d\n" +
                                "From %s to %s\n" +
                                "Fair is: %1d",
                        ticketID, passengerName, LocalDateTime.now().toString(), departureDate, busNumber,
                        seatNo, from, to, bill
                ));
                writer.close();
            } catch (IOException e) {
            }
        }

        static boolean isLeap(int year) {
            // Return true if year is a multiple of 4 and
            //  not multiple of 100 or year is multiple of 400.
            return (((year % 4 == 0) &&
                    (year % 100 != 0)) ||
                    (year % 400 == 0));
        }

        static boolean isValidDate(int d, int m, int y) {
            // If year, month and day
            // are not in given range
            if (y > MAX_VALID_YR ||
                    y < MIN_VALID_YR)
                return false;
            if (m < 1 || m > 12)
                return false;
            if (d < 1 || d > 31)
                return false;

            // Handle February month with leap year
            if (m == 2) {
                if (isLeap(y))
                    return (d <= 29);
                else
                    return (d <= 28);
            }

            // Months of April, June, Sept and Nov must have number of days less than or equal to 30.
            if (m == 4 || m == 6 ||
                    m == 9 || m == 11)
                return (d <= 30);

            return true;

        }

        //static boolean isValidDate(String date){ return true; }
        public static void writeFile(String name, ArrayList<String> list1) {
            try {
                FileWriter out = new FileWriter(name);
                for (int i = 0; i < list1.size(); i++) {
                    out.write(list1.get(i) + "\n");

                }
                out.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        public static void readFile(String name, ArrayList<String> list1) {
            //createFile(name);

            Scanner list = null;

            try {
                list = new Scanner(new File(name));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            while (list.hasNext()) {
                list1.add(list.next());

            }
        }

        public static void readFileInt(String name, ArrayList<Integer> List2) {
            //createFile(name);


            Scanner list = null;


            try {
                list = new Scanner(new File(name));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            while (list.hasNext()) {
                List2.add(Integer.valueOf(list.next()));

            }
        }

        static Ticket create() throws IOException {
            System.out.println("****");
            System.out.println("|||||||SEAT BOOKING||||||||||");
            System.out.println("****");
            System.out.println("-----Enter passenger name----- ");
            Scanner only = new Scanner(System.in);
            String name = only.next();
            int n = 0;
            while (!(name.length() <= 30) || n != name.length()) {
                char x = name.charAt(n);
                n++;
                if (!(Character.isAlphabetic(x))) {
                    System.out.println("||Your name should only contain letters||");
                    name = only.next();
                    n = 0;
                }
                if (!(name.length() <= 31)) {
                    System.out.println("||Error - Your name length should be maximum 30 letters||");
                    name = only.next();
                }
            }
            String departureDate;
            int seatNo;
            int age;
            String gender;
            String from, to;
            int busNumber = 0;
            String cnic = "\\d{13}";
            //boolean invalidInput = true;
            System.out.println("-----Enter passenger age------ ");
            while (true) {
                try {
                    age = new Scanner(System.in).nextInt();
                    if(age>100){
                        System.out.println("--You entered an invalid age. Please enter again--");
                        continue;
                    }
                    if(age<=0){
                        System.out.println("--You entered an invalid age. Please enter again--");
                        continue;
                    }
                    if (age < 18){
                        break;
                    }
                    else if (age >= 18) {

                        boolean num;
                        Scanner sc = new Scanner(System.in);
                        do {
                            String cnicPattern = "\\d{13}";
                            System.out.print("-----Input your cnic(13 digits)-----\n");
                            String input = sc.next();
                            num = input.matches(cnicPattern);
                            if (!num) System.out.println("--Invalid Cnic!--");
                        }
                        while (!num);
                        System.out.println("--Valid Cnic--");
                    }
                    break;
                }
                catch(InputMismatchException inp)
                {
                    System.out.println("--Invalid Input.Enter again--");
                }


            }

            //String gender;
            while (true) {
                System.out.println("-----GENDER: male or female?-----");
                gender = new Scanner(System.in).nextLine();
                if( gender.equalsIgnoreCase("female") || gender.equalsIgnoreCase("f")) {
                    System.out.println("Gender is female" );
                    System.out.println("****");
                    break;
                }
                if( gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("m")) {
                    System.out.println("Gender is male.");
                    System.out.println("****");
                    break;
                }
                else{
                    System.out.println("--Invalid gender--");
                }
            }

            boolean num;
            Scanner sc = new Scanner(System.in);
            do {
                String phonePattern = "\\d{2}-\\d{7}";
                System.out.print("-----Input Emergency Contact(xx-xxxxxxx)-----\n");
                String input = sc.nextLine();
                num = input.matches(phonePattern);
                if (!num) System.out.println("--Invalid Number!. Enter Again--");
            }
            while (!num);
            System.out.println("--Number is entered--");
            System.out.println("***");
            while (true) {
                try {
                    //Scanner input = new Scanner(System.in);
                    int day;
                    int month;
                    int year;
                    System.out.println("-----Enter day-----");
                    day = new Scanner(System.in).nextInt();
                    System.out.println("-----Enter month-----");
                    month = new Scanner(System.in).nextInt();
                    System.out.println("-----Enter yyyy-----");
                    year = new Scanner(System.in).nextInt();
                    departureDate = day + "/" + month + "/" + year;
                    if (isValidDate(day, month, year)) {
                        System.out.println(departureDate);

                        break;
                    }
                    else {
                        System.out.println("--You entered an invalid date. Please enter again-- ");
                    }
                }
                catch (InputMismatchException e) {
                    System.out.println("--Invalid input--");
                    //input.nextLine();
                }

            }


            // }

            //if (isSeatAvailable(seatNo, departureDate)) break;
            ArrayList<Integer> bus = new ArrayList<>();
            ArrayList<String> time = new ArrayList<>();
            ArrayList<String> departure = new ArrayList<>();
            ArrayList<String> arrival = new ArrayList<>();
            ArrayList<Integer> fair = new ArrayList<>();
            ArrayList<String> depChoice = new ArrayList<>();
            ArrayList<String> choice = new ArrayList<>();
            ArrayList<String> timeChoice = new ArrayList<>();
            readFile("destination.txt", (ArrayList<String>) departure);
            readFile("arrival.txt", (ArrayList<String>) arrival);
            readFileInt("fare.txt", (ArrayList<Integer>) fair);
            readFile("time.txt", (ArrayList<String>) time);
            readFile("time.txt", (ArrayList<String>) time);
            readFileInt("bus.txt", (ArrayList<Integer>) bus);
            Set<String> set = new HashSet<>();

            // Add each element from the array list to the set
            for (String s : departure) {
                set.add(s);
            }
            int k = 1;
            // Print out the elements in the set
            for (String s : set) {
                depChoice.add(s);
                System.out.println(k + ". " + s);
                k++;
            }

            Scanner in = new Scanner(System.in);
            System.out.println("--From-- ");
            int y = in.nextInt();
            String From = depChoice.get(y - 1);
            for (int j = 0; j < departure.size(); j++) {
                if (Objects.equals(From, departure.get(j))) {
                    choice.add(arrival.get(j));
                    timeChoice.add(time.get(j));
                }
            }
            for (int i = 0; i < choice.size(); i++) {
                System.out.print((i + 1) + ". " + choice.get(i));
                System.out.println((i + 1) + ". time: " + timeChoice.get(i));
            }

            System.out.println("--To-- ");
            int x = in.nextInt();
            String To = choice.get(x - 1);
            String dep = null;
            int cost = 0;
            String ari = null;
            for (int i = 0; i < departure.size(); i++) {
                if (Objects.equals(From, departure.get(i)) && Objects.equals(To, arrival.get(i))) {
                    busNumber = bus.get(i);
                    System.out.println("your destination is from: " + "'" + departure.get(i) + "'" + " to " + "'" + arrival.get(i) + "'" + " and fare will be: " + "'" + fair.get(i) + "'" + " Bus No. " + bus.get(i));
                    dep = departure.get(i);
                    ari = arrival.get(i);
                    cost = fair.get(i);

               /* System.out.println("\nDo you want to add lunch box, Price = 500 ? Y or N");
                String moreRest = in.next().toUpperCase();

                while (!(moreRest.equals("Y") || moreRest.equals("N"))) {

                    System.out.println("Invalid input. Pls Enter \"Y\" or \"N\"");
                    moreRest = in.next().toUpperCase();
                }
                if (moreRest.equals("Y"))
                    cost = cost + 500;

                else if (moreRest.equals("N"))

                    break;*/
                }
            }
            choice.clear();
            depChoice.clear();
            while (true) {
                try {
                    printAvailableSeats(busNumber, departureDate);
                    System.out.println("-----Enter seat no (1 to 52)-----");
                    seatNo = new Scanner(System.in).nextInt();
                    if (seatNo <= 52 && seatNo>0) {
                        if (isSeatAvailable(seatNo, departureDate, busNumber)) {
                            System.out.println("Your seat no is: " + seatNo);
                            //totalSeats--;
                            break;
                        } else {
                            System.out.println("--This is seat is already booked! Please enter an unoccupied seat--");
                        }
                    } else {
                        System.out.println("--Seat is not Available--");
                        System.out.println("--Please enter again within range--");
                        //seatNo = new Scanner(System.in).nextInt();
                    }
                } catch (InputMismatchException e) {
                    System.out.println("--Invalid Input--");
                }
            }


            System.out.println("Your Seat is Successfully Booked\n");
            System.out.println("-----------------------------------");

            return new Ticket(name, departureDate, seatNo, dep, ari, cost, busNumber);

        }

        private static void printAvailableSeats(int busNumber, String departureDate){
            try{
                //store all seats booked in an array on the paticular bus number and departure date
                ArrayList<Integer> bookedSeats = new ArrayList<>();
                Scanner scn = new Scanner(new File("TICKET_RECORDS.txt"));
                while(scn.hasNextLine()){
                    String[] currentLine = scn.nextLine().split(" ");
                    if(currentLine[5].equals(departureDate) && (Integer.parseInt(currentLine[8])) == busNumber)
                    {
                        bookedSeats.add(Integer.parseInt(currentLine[2]));
                    }

                }

                int[][] seats = new int[13][4];
                String seatsAvailability = "";

                for(int i=1; i<=13; i++){
                    for(int j=1; j <= 4; j++){

                        int seatNo = j + (4)*(i-1);

                        if(bookedSeats.contains(seatNo))
                        {
                            seatsAvailability += "| X| ";
                        }
                        else{
                            if(seatNo <= 9)
                            {
                                seatsAvailability += "| " + seatNo + "| ";
                            }
                            else{
                                seatsAvailability += "|" + seatNo + "| ";

                            }
                        }

                        if(j==2) seatsAvailability += "  ";

                        //if(j==4) seatsAvailability += "\n";

                    }
                }
                System.out.println("\n" + seatsAvailability);
            }
            catch(Exception e){
                System.out.println("Atleast one ticket record is required to display seatsAvailability.");
            }
        }


        private static boolean isSeatAvailable(int seatNo, String date, int busNumber) {
            try {
                Scanner scn = new Scanner(new File("TICKET_RECORDS.txt"));
                while(scn.hasNextLine())
                {
                    String[] currentLine = scn.nextLine().split(" ");
                    if(busNumber == Integer.parseInt(currentLine[8])
                            && seatNo == Integer.parseInt(currentLine[2])
                            && date.equals(currentLine[5])){

                        return false;
                    }

                }
            }

            catch (Exception e) {
                //System.out.println("Error while reading file. In method: isSeatAvailable()");
            }
            return true;
        }


        public static void Cancel(String ticketNo) {
            File oriFile = new File("TICKET_RECORDS.txt");
            File tempFile = new File("TempTICKET_RECORDS.txt");
            FileWriter fw = null;
            try {
                fw = new FileWriter("TempTICKET_RECORDS.txt");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                Scanner reader = new Scanner(oriFile);
                String lineContent = "Ticket ID # ";
                lineContent = lineContent + ticketNo;
                File fileToDelete = new File("Ticket No." + ticketNo + ".txt");
                System.out.println(lineContent);

                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    if (!line.contains(lineContent)) {
                        fw.write(line + "\n");
                    }
                }
                reader.close();
                fw.close();
                if (oriFile.delete()) {
                    // System.out.println("File Deleted Succesfully");
                }
                else {
                    System.out.println("There was some error.");
                }
                if (fileToDelete.delete()) {
                    System.out.println("The ticket was deleted Successfully");
                }
                else {
                    System.out.println("File not found");
                }
                if (tempFile.renameTo(oriFile)) {
                    // System.out.println("Renamed Successfully");
                } else {
                    System.out.println("There was a error!!!");
                }

            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        public static void viewTicket() throws FileNotFoundException {
            while(true){
                try{
                    Scanner input = new Scanner(System.in);
                    System.out.println("Enter your name: ");

                    String user = input.next();
                    readFile("name.txt", (ArrayList<String>) name);
                    readFile("ticketIds.txt", (ArrayList<String>) ticketIds);
                    int i;
                    for (i = 0; i < name.size(); i++) {
                        if (user.equals(name.get(i))) {
                            ticketIds.get(i);
                            System.out.println(ticketIds.get(i));
                            break;
                        }
                        else
                            System.out.println("ticket not available");
                    }

                    File testfile = new File("Ticket No." + ticketIds.get(i)+ ".txt");
                    ticketIds.clear();
                    name.clear();
                    Scanner scnr = new Scanner(testfile);

                    while (scnr.hasNextLine()) {
                        System.out.println(scnr.nextLine());
                    }
                    //}
                    break;
                }
                catch(Exception e){
                    System.out.println("Name not found.Enter again!");
                }
            }
        }





        class Admin {
            static List<String> ticket = new ArrayList<>();
            static List<String> destination = new ArrayList<>();
            static List<String> arrival = new ArrayList<>();
            static List<Integer> fare = new ArrayList<>();
            static List<String> time = new ArrayList<>();
            static List<Integer> bus = new ArrayList<>();
            static Scanner input = new Scanner(System.in);


            public static void readFile(String name, ArrayList<String> list1) {
                //createFile(name);
                Scanner list = null;
                try {
                    list = new Scanner(new File(name));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                while (list.hasNext()) {
                    list1.add(list.next());
                }
            }

            public static void readFileInt(String name, ArrayList<Integer> List2) {
                //createFile(name);
                Scanner list = null;
                try {
                    list = new Scanner(new File(name));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                while (list.hasNext()) {
                    List2.add(Integer.valueOf(list.next()));
                }
            }

            public static void writeFile(String name, ArrayList<String> list1) {
                try {
                    FileWriter out = new FileWriter(name);
                    for (int i = 0; i < list1.size(); i++) {
                        out.write(list1.get(i) + "\n");
                    }
                    out.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public static void writeFileInt(String name, ArrayList<Integer> list2) {
                try {
                    FileWriter out = new FileWriter(name);
                    for (int i = 0; i < list2.size(); i++) {
                        out.write(list2.get(i) + "\n");
                    }
                    out.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            static void admin() throws IOException {
                System.out.println("------ADMIN------");
                System.out.println("***");
                while (true) {

                    System.out.println("1.login\n2.Create an account ");
                    System.out.println("_____");
                    String account = input.nextLine();
                    if (account.equals("1") || account.equals("2")){
                        if (account.equals("1")) {
                            System.out.println("Enter user name:");
                            System.out.println("__");
                            String name = input.next();
                            System.out.println("Enter password: ");
                            System.out.println("__");
                            String password = input.next();
                            int validity = checkaccount_reader(name, password);
                            if (validity == 1) {
                                System.out.println("You are logged in");
                                System.out.println("___");

                                boolean validCity = false;
                                while (!validCity) {
                                    System.out.println("1.Add City\n2.Remove City\n3.Check Ticket Records");
                                    System.out.println("_________");
                                    String city = input.nextLine();
                                    if (city.equals("1") || city.equals("2") || city.equals("3")) {

                                        if (city.equals("1")) {
                                            addCity();
                                            break;
                                        } else if (city.equals("2")) {
                                            removeCity();
                                            break;
                                        } else if (city.equals("3")) {
                                            ticketRecord();
                                            break;
                                        }
                                    } else {
                                        System.out.println("Invalid input!Please try again.");
                                        validCity=false;
                                    }
                                }
                                break;

                            } else if (validity == 2) {
                                System.out.println("Your account does not exist");
                            }

                        }else if (account.equals("2")) {
                            createaccont_reader();
                        }
                    }
                    else {
                        System.out.println("Invalid input!");
                        continue;
                    }
                }
            }
            public static void createaccont_reader() throws IOException {
                System.out.println("Enter your name: ");
                System.out.println("___");

                String name = input.next();

                System.out.println("Enter password: ");
                System.out.println("__");

                String password = input.next();
                System.out.println("Enter your contact number: ");
                System.out.println("___");

                String phoneNumber = "";

                //Take input from the user until user enters the phone number
                while (true) {
                    try {
                        phoneNumber = input.next();
                        //check the length of the number
                        if (phoneNumber.length() != 11) {
                            System.out.println("Invalid phone number!\n Please Enter valid number!");
                            continue;
                        }
                        boolean validNumber = true;
                        //Loop to validate if each character entered is a digit
                        for (int i = 0; i < phoneNumber.length(); i++) {
                            if (!(Character.isDigit(phoneNumber.charAt(i))))
                                validNumber = false;
                        }
                        if (!(validNumber)) {
                            System.out.println("Invalid phone number!\nPlease Enter valid number!");
                            continue;
                        }
                        break;

                    } catch (InputMismatchException e) {
                        //Exception if the user enters any character or string
                        System.out.println("Invalid phone number! Enter valid number");
                        input.nextLine();
                    }
                }
                System.out.println("Enter your age: ");
                System.out.println("__");


                int age;
                do {
                    System.out.print("Enter your age: ");
                    while (!input.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a valid age (integer less than 100): ");
                        input.next();
                    }
                    age = input.nextInt();
                } while (age >= 100);
                System.out.println("Your age is: " + age);
                String account = name + password + ".txt";
                try {
                    File myObj = new File(account);
                    if (myObj.createNewFile()) {
                        System.out.println("Account created: " + myObj.getName());
                    }
                    else {
                        System.out.println("Account already exists.");
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }

                System.out.println("" + "");
                System.out.println("1.Do you want to login \n2.exit");
                System.out.println("_______");

                int a = input.nextInt();
                if (a == 1) {
                    System.out.println("Enter user name:");
                    System.out.println("__");
                    name = input.next();
                    System.out.println("Enter password: ");
                    System.out.println("__");
                    password = input.next();

                    int validity = checkaccount_reader(name, password);
                    if (validity == 1) {
                        System.out.println("You are logged in");
                        System.out.println("___");
                        System.out.println("1.Add City\n2.Remove City");
                        System.out.println("___");

                        int city = input.nextInt();
                        if (city == 1) {
                            addCity();
                        } else if (city == 2) {
                            removeCity();
                        }
                    } else if (validity == 2) {
                        System.out.println("Your account does not exist");
                        System.out.println("___");
                    }
                } else if (a == 2) {
                    System.out.println("");

                }
            }

            public static int checkaccount_reader(String name1, String password1) throws IOException {
                String account = name1 + password1 + ".txt";
                File confirm_name = new File(account);
                if (confirm_name.exists()) {
                    return 1;
                } else {
                    return 2;
                }
            }

            public static void addCity() {
                while (true) {
                    readFile("destination.txt", (ArrayList<String>) destination);

                    readFile("arrival.txt", (ArrayList<String>) arrival);

                    readFileInt("fare.txt", (ArrayList<Integer>) fare);

                    readFile("time.txt", (ArrayList<String>) time);

                    readFileInt("bus.txt", (ArrayList<Integer>) bus);

                    System.out.println("--ADD CITY--");
                    System.out.println("Departure\t\tArrival\t\tFare\t\tTime");
                    System.out.println("_______");

                    int maxSize = Math.max(Math.max(destination.size(), arrival.size()), fare.size());

                    for (int i = 0; i < maxSize; i++) {
                        if (i < destination.size()) {
                            System.out.print("[" + destination.get(i) + "]" + "\t");

                        } else {
                            System.out.print(" " + " ");
                        }

                        if (i < arrival.size()) {
                            System.out.print("[" + arrival.get(i) + "]" + "\t");

                        } else {
                            System.out.print(" " + " ");
                        }

                        if (i < fare.size()) {
                            System.out.print("[" + fare.get(i) + "]" + "\t");

                        } else {
                            System.out.print(" " + " ");
                        }

                        if (i < time.size()) {
                            System.out.print("[" + time.get(i) + "]" + "\t");

                        } else {
                            System.out.print(" " + " ");
                        }

                        if (i < bus.size()) {
                            System.out.print("[Bus No." + bus.get(i) + "]" + " ");

                        } else {
                            System.out.print(" " + " ");
                        }
                        System.out.println();
                    }

                    System.out.println("Enter Departure city ");
                    System.out.println("___");
                    String city = input.next();
                    city = city.toUpperCase();
                    destination.add(city);
                    System.out.println("Enter Arrival city ");
                    System.out.println("___");
                    String arrive = input.next();
                    arrive = arrive.toUpperCase();
                    arrival.add(arrive);
                    System.out.println("Enter Fare ");
                    System.out.println("___");
                    int far;
                    while (true) {
                        System.out.print("Enter money in integer form: ");
                        if (input.hasNextInt()) {
                            far = input.nextInt();
                            break;
                        } else {
                            System.out.println("Invalid input. Please enter an integer.");
                            input.next();
                        }
                    }

                    fare.add(far);
                    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                    timeFormat.setLenient(false);

                    String busTime;
                    while (true) {
                        System.out.println("Enter a time in the format HH:MM: ");
                        System.out.println("____");
                        busTime = input.nextLine();
                        try {
                            timeFormat.parse(busTime);
                            System.out.println("Valid time input: " + busTime);
                            break;

                        } catch (ParseException e) {
                            System.out.println("Invalid time input. Please try again.");
                        }
                    }
                    time.add(busTime);
                    System.out.println("Add the bus assigned to this route, total buses = 3 ");
                    System.out.println("______");
                    int buses;
                    while (true) {
                        try {
                            buses = input.nextInt();
                            if (buses < 1 || buses > 3) {
                                System.out.println("Company only has 3 buses,try again");
                                continue;
                            }
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please try again\n");
                            input.nextLine();
                        }
                    }
                    bus.add(buses);
                    writeFile("destination.txt", (ArrayList<String>) destination);

                    writeFile("arrival.txt", (ArrayList<String>) arrival);

                    writeFileInt("fare.txt", (ArrayList<Integer>) fare);

                    writeFile("time.txt", (ArrayList<String>) time);

                    writeFileInt("bus.txt", (ArrayList<Integer>) bus);

                    destination.clear();
                    arrival.clear();
                    fare.clear();
                    time.clear();
                    bus.clear();

                    System.out.println("\nDo you want to add more Cities? Y or N");
                    System.out.println("______");

                    String moreRest = input.next().toUpperCase();

                    while (!(moreRest.equals("Y") || moreRest.equals("N"))) {

                        System.out.println("Invalid input. Pls Enter \"Y\" or \"N\"");
                        moreRest = input.next().toUpperCase();
                    }
                    if (moreRest.equals("Y"))
                        continue;
                    else if (moreRest.equals("N"))
                        break;
                }
            }

            public static void removeCity() {
                System.out.println("------REMOVE CITY------");
                System.out.println("___");
                System.out.println("1.REMOVE AN ENTIRE CITY" + "\n" + "2.REMOVE INDIVIDUAL ROUTES");
                System.out.println("_______");

                while (true) {
                    try {
                        int remove = input.nextInt();
                        if (remove == 1) {
                            entireCity();
                            break;
                        } else if (remove == 2) {
                            removeRoutes();
                            break;
                        } else
                            System.out.println("Invalid input, Try again: ");
                        continue;
                    }
                    catch (InputMismatchException e) {
                        System.out.println("Invalid input! Please try again\n");
                        input.nextLine();
                    }
                }
            }

            public static void entireCity() {
                while (true) {
                    readFile("destination.txt", (ArrayList<String>) destination);

                    readFile("arrival.txt", (ArrayList<String>) arrival);

                    readFile("time.txt", (ArrayList<String>) time);

                    readFileInt("fare.txt", (ArrayList<Integer>) fare);

                    readFileInt("bus.txt", (ArrayList<Integer>) bus);

                    System.out.println("\t\t\t\tRemove City");
                    System.out.println("_____");

                    System.out.println("Departure Cities");
                    System.out.println("__");
                    for (int i = 0; i < destination.size(); i++) {

                        System.out.println((i + 1) + ". " + destination.get(i));
                    }
                    System.out.println("\nEnter the name of the City you want to remove: ");
                    System.out.println("_______");
                    input.nextLine();

                    while (true) {
                        String delRest = input.nextLine();
                        delRest = delRest.toUpperCase();
                        if (destination.contains(delRest)) {
                            while (true) {

                                arrival.remove(destination.indexOf(delRest));

                                fare.remove(destination.indexOf(delRest));

                                time.remove(destination.indexOf(delRest));

                                bus.remove(destination.indexOf(delRest));

                                destination.remove(delRest);

                                if (!(destination.contains(delRest))) {
                                    break;

                                } else {
                                    continue;
                                }
                            }
                            break;
                        } else
                            System.out.println("City does not exists, Please try again: ");
                        continue;
                    }
                    writeFile("destination.txt", (ArrayList<String>) destination);

                    writeFile("arrival.txt", (ArrayList<String>) arrival);

                    writeFile("time.txt", (ArrayList<String>) time);

                    writeFileInt("bus.txt", (ArrayList<Integer>) bus);

                    writeFileInt("fare.txt", (ArrayList<Integer>) fare);

                    destination.clear();
                    arrival.clear();
                    fare.clear();
                    time.clear();
                    bus.clear();

                    System.out.println("\nDo you want to delete more Cities? Y or N");
                    System.out.println("_____");

                    String moreRest = input.next().toUpperCase();

                    while (!(moreRest.equals("Y") || moreRest.equals("N"))) {

                        System.out.println("Invalid input. Pls Enter \"Y\" or \"N\"");

                        moreRest = input.next().toUpperCase();

                    }
                    if (moreRest.equals("Y"))
                        continue;

                    else if (moreRest.equals("N"))
                        break;
                }
            }
            public static void removeRoutes() {

                while (true) {

                    readFile("destination.txt", (ArrayList<String>) destination);

                    readFile("arrival.txt", (ArrayList<String>) arrival);

                    readFile("time.txt", (ArrayList<String>) time);

                    readFileInt("fare.txt", (ArrayList<Integer>) fare);

                    readFileInt("bus.txt", (ArrayList<Integer>) bus);

                    System.out.println(" REMOVE INDIVIDUAL ROUTES ");
                    System.out.println("__");

                    System.out.println("Departure\t\t\tArrival\t\t\tFare\t\t\tTime\t\t\tBus");
                    System.out.println("__________");

                    int maxSize = Math.max(Math.max(destination.size(), arrival.size()), fare.size());

                    for (int i = 0; i < maxSize; i++) {
                        if (i < destination.size()) {
                            System.out.print((i + 1) + "." + "[" + destination.get(i) + "]" + "\t");
                        }
                        else {
                            System.out.print(" " + " ");
                        }
                        if (i < arrival.size()) {
                            System.out.print("[" + arrival.get(i) + "]" + "\t");
                        }
                        else {
                            System.out.print(" " + " ");
                        }
                        if (i < fare.size()) {
                            System.out.print("[" + fare.get(i) + "]" + "\t");
                        }
                        else {
                            System.out.print(" " + " ");
                        }
                        if (i < time.size()) {
                            System.out.print("[" + time.get(i) + "]" + "\t");
                        }
                        else {
                            System.out.print(" " + " ");
                        }
                        if (i < bus.size()) {
                            System.out.print("[ Bus No." + bus.get(i) + "]" + " ");
                        }
                        else {
                            System.out.print(" " + " ");
                        }
                        System.out.println();
                    }

                    System.out.println("Enter the route you want to remove: ");
                    System.out.println("____");
                    int route;
                    while (true) {
                        try {
                            route = input.nextInt();
                            if (route < 0 || route > destination.size()) {
                                System.out.println("Invalid input! Please try again\n");
                                continue;
                            }
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please try again\n");
                            input.nextLine();
                        }
                    }
                    String from = destination.get(route - 1);
                    String to = arrival.get(route - 1);
                    String busTime = time.get(route - 1);
                    int price = fare.get(route - 1);
                    int buses = bus.get(route - 1);

                    destination.remove(destination.indexOf(from));
                    arrival.remove(arrival.indexOf(to));
                    fare.remove(fare.indexOf(price));
                    time.remove(time.indexOf(busTime));
                    bus.remove(bus.indexOf(buses));

                    writeFile("destination.txt", (ArrayList<String>) destination);

                    writeFile("arrival.txt", (ArrayList<String>) arrival);

                    writeFileInt("fare.txt", (ArrayList<Integer>) fare);

                    writeFileInt("bus.txt", (ArrayList<Integer>) bus);

                    writeFile("time.txt", (ArrayList<String>) time);

                    destination.clear();
                    arrival.clear();
                    fare.clear();
                    time.clear();
                    bus.clear();
                    System.out.println("\nDo you want to delete more Routes? Y or N");
                    System.out.println("_____");

                    String moreRest = input.next().toUpperCase();

                    while (!(moreRest.equals("Y") || moreRest.equals("N"))) {

                        System.out.println("Invalid input. Pls Enter \"Y\" or \"N\"");

                        moreRest = input.next().toUpperCase();
                    }
                    if (moreRest.equals("Y"))
                        continue;
                    else if (moreRest.equals("N"))
                        break;
                }
            }

            public static void ticketRecord() {

                System.out.println("------TICKET RECORDS------");
                System.out.println("____");
                try (BufferedReader br = new BufferedReader(new FileReader("TICKET_RECORDS.txt"))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        ticket.add(line);
                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                // Print the contents of the ArrayList
                for (String line : ticket) {
                    System.out.println(line);
                }
            }
        }
    }

