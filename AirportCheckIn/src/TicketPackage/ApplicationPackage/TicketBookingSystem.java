package TicketPackage.ApplicationPackage;

import TicketPackage.PassengerPackage.*;
import TicketPackage.PlanePackage.*;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

/**
 * This is the main class which runs our Ticket Booking system
 * @author Melissa Melaugh, Ciaran O'Boyle, Michelle Loughran
 * Created on: 25/11/2020
 * Updated on: 08/12/2020
 * AirportCheckIn:TicketPackage.ApplicationPackage:TicketBookingSystem
 */
public class TicketBookingSystem {
    //File Paths
    private static final String FILEPATH_SCHEDULE = "C:\\Users\\Solar\\IdeaProjects\\PSD2_Group8Project\\AirportCheckIn\\obj";
    private static final String FILEPATH_NUMBERS = "C:\\Users\\Solar\\IdeaProjects\\PSD2_Group8Project\\AirportCheckIn\\num";

    //Scanner for methods
    private static Scanner in = new Scanner(System.in);

    /**
     * This method is responsible for loading in any objects that were saved.
     */
    private static void startUp(){
        try {
            //Create a file stream from where the file is located
            FileInputStream fileIn = new FileInputStream(FILEPATH_SCHEDULE);
            //Create an object stream from the file stream
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            //Get the generic object out of the object stream
            Object obj = objectIn.readObject();
            //Cast the object as the hashtable holding the Planes and set the schedule to be this hashtable
            Schedule.setSchedule((Hashtable<String, ArrayList<Plane>>) obj);
            //Close the object stream
            objectIn.close();

            //Get the scanner to read from the file of numbers
            Scanner scanner = new Scanner(FILEPATH_NUMBERS);
            //Create an Array to hold the numbers
            ArrayList<Integer> numbers = new ArrayList<Integer>();
            //Read the numbers in one by one (in the order Passenger, Adult, Child, Senior)
            while (scanner.hasNext()) {
                if(scanner.hasNextInt()) {
                    numbers.add(scanner.nextInt());
                } else {
                    scanner.next();
                }//end if
            }//end while
            //Close the scanner
            scanner.close();

            //Populate the correct fields with the numbers
            Passenger.setNumberOfPassengers(numbers.get(0));
            Adult.setNumberOfAdults(numbers.get(1));
            Child.setNumberOfChildren(numbers.get(2));
            Senior.setNumberOfSeniors(numbers.get(3));

            //Inform user
            System.out.println("Data Loaded!");
        } catch (Exception ex) {
            //If the file is unable to be loaded, start fresh.
            System.out.println("This program is starting new.");
            //Create the new schedule:
            Schedule.updateSchedule();
        }//end try/catch
    }//end Startup

    /**
     * This method is responsible for saving the state of the program when it shuts down
     */
    private static void shutDown(){
        try {
            //Create an output stream to write the object out to
            FileOutputStream fileOut = new FileOutputStream(FILEPATH_SCHEDULE);
            //Create an object stream for the object to go into
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            //Write out the plane schedule
            objectOut.writeObject(Schedule.getSchedule());
            //close the outObject
            objectOut.close();

            //Get the counts in the order Passenger, Adult, Child, Senior
            int[] counts = {Passenger.getNumberOfPassengers(),
                    Adult.getNumberOfAdults(), Child.getNumberOfChildren(), Senior.getNumberOfSeniors()};

            //Get tha file writer ready to put the numbers into their file
            FileWriter writer = new FileWriter(FILEPATH_NUMBERS);
            //Put the counts into the file separated by a new line
            for(int count : counts){
                writer.write(String.format("%d\n", count));
            }//end for
            //Close the writer
            writer.close();

            //Inform the user that the program was saved
            System.out.println("Your program was successfully saved.");
        } catch (Exception ex) {
            //Inform the user that the program wasn't able to be saved.
            System.out.println("Your program was unable to be saved");
        }//end try/catch
    }//end shutDown

    /**
     * This method is responsible for getting the next integer from the user
     * @param request - Information about what is wanted from the user
     * @return - the integer input from the user
     */
    private static int getNextInt(String request){
        //Make sure that nextInt will definitely have something.
        int nextInt = 0;
        //Print out the incoming request
        System.out.print(request);
        //Get the next int
        if(in.hasNextInt()) {
            nextInt = in.nextInt();
        }
        //Clear the line to be sure
        in.nextLine();
        //Return the int
        return nextInt;
    }//end getNextInt

    /**
     * This method is responsible for getting the next string from the user
     * @param request Information about what is wanted from the user
     * @return the string input from the user
     */
    private static String getNextString(String request){
        //Print out the request and return the input from the user.
        System.out.print(request);
        return in.nextLine();
    }//end getNextString

    /**
     * This method is responsible for getting the month and day from the user and return the plane for that day/month
     * @return A plane object for the day/month that the user is looking to travel on
     */
    private static Plane getTravelDate(){
        //Create the request
        String request = "\nAre you buying for this month or next month?";
        request += "\nType 1 if you wish to travel this month";
        request += "\nType 2 if you wish to travel next month";
        request += "\nOption input: ";

        //Get the choice from the user
        int monthChoice = getNextInt(request);

        //Create the request
        request = "\nWhat day do you wish to travel on?";
        request += "\nOption input: ";

        //Get the choice from the user
        int dayChoice = getNextInt(request);

        //Return the plane that is on the day that the user requests.
        return Schedule.getPlane(dayChoice, monthChoice);
    }//end getTravelDate

    /**
     * This method is responsible for getting the basic passenger information and returning it
     * @param plane the plane that the passenger intends to travel on
     * @return a list of information about the passenger [first name, last name, seat, departure date]
     */
    private static String[] getPassengerInfo(Plane plane){
        String departure = plane.getDepartureDate();

        //Get passenger Name:
        String request = "\nPlease enter your first name: ";
        String firstName = getNextString(request);
        request = "\nPlease enter your last name: ";
        String lastName = getNextString(request);

        //Choose seat using a while loop to catch the error until a seat is correctly chosen
        boolean gotSeat = false;
        String seat = "";
        //create request so it can be used multiple times if seat is unavailable
        request = "\nPlease choose your seat from the list above.";
        request += "\nOption input: ";
        while(!gotSeat){
            try {
                //Print out available seats
                plane.showAvailableSeats();
                //Get the seat that they picked
                seat = getNextString(request);
                //Attempt to take a seat
                plane.takeSeat(seat);
                //If the seat is available, the passenger has gotten their seat, exit while loop, else do it again
                gotSeat = true;
            } catch (Exception e) {
                //Tell the user that the seat they wanted is able to be purchased and to try a different one.
                System.out.println("Seat unavailable try again.");
            }//end try/catch
        }//end while

        //Create an array to return the information and return it.
        String[] information = {firstName, lastName, seat, departure};
        return information;
    }//end getPassengerInfo

    /**
     * This method is responsible for letting the user buy tickets.
     */
    private static void buyTickets(){
        //Get the plane they want to travel on
        Plane theirPlane = getTravelDate();

        //Get how many passengers they wish to book on this flight
        String request = "\nHow many tickets do you want to purchase for this flight? ";
        int numberOfTickets = getNextInt(request);
        //TODO - Future direction would be to say if there are enough seats for the number of passengers;
        //Currently the program will get stuck in an infinite loop if there are not enough seats.

        //initialise cost to zero
        double cost = 0;

        //Initialise lists to hold the passengers
        ArrayList<Adult> adults = new ArrayList<>();
        ArrayList<Child> children = new ArrayList<>();
        ArrayList<Senior> seniors = new ArrayList<>();

        //Print instruction if you're booking more than one passenger.
        if(numberOfTickets > 1){
            System.out.println("Please start with the person responsible for everyone. " +
                    "(I.E. book yourself before you book your children)");
        }//end if

        //This is to be able to attach children to 1=Adult 3=Senior
        int responsiblePartyType = 1;

        //For each ticket to be purchased get the needed information to create a Passenger subtype
        for(int passenger = 0; passenger < numberOfTickets; passenger++){
            //Create the menu for choosing passenger type
            request =  "\nType 1 for an adult";
            request += "\nType 2 for a child";
            request += "\nType 3 for a senior";
            request += "\nOption input: ";
            //Get the passenger type
            int passengerType = getNextInt(request);

            //Make the first passenger entered responsible for everyone - update passenger type
            if(passenger == 0){
                responsiblePartyType = passengerType;
            }//end if

            //Get the information from the passenger
            String[] information = getPassengerInfo(theirPlane);

            //Create the right passenger type, add them to the correct list both in this method and in the plane and
            //add the price of their ticket to the running total.
            switch (passengerType) {
                case 1:
                    Adult adult = new Adult(information[0], information[1], information[2], information[3]);
                    adults.add(adult);
                    theirPlane.addAdult(adult);
                    cost += theirPlane.getAdultPrice();
                    break;
                case 2:
                    Child child = new Child(information[0], information[1], information[2], information[3]);
                    children.add(child);
                    theirPlane.addChild(child);
                    cost += theirPlane.getChildPrice();
                    break;
                case 3:
                    Senior senior = new Senior(information[0], information[1], information[2], information[3]);
                    seniors.add(senior);
                    theirPlane.addSenior(senior);
                    cost += theirPlane.getSeniorPrice();
                    break;
                default:
                    //TODO: Make this not become bad PR (it made us laugh though)
                    System.out.println("You fail as a human, get someone else to do it.");
            }//end switch
        }//end for loop

        //Print Adult Tickets
        for(Adult adult : adults){
            System.out.println("\n");
            System.out.println(adult.toString());
        }//end for loop

        //Print Children tickets and attach them to the responsible adult
        for(Child kid : children){
            System.out.println("\n");
            System.out.println(kid.toString());
            if(responsiblePartyType == 1){ //If the responsible party is an adult
                adults.get(0).addChild(kid);
                String adultName = adults.get(0).getFirstName() + " " + adults.get(0).getLastname();
                kid.setResponsibleAdult(adultName);
            } else if (responsiblePartyType == 3){ //If the responsible party is a senior
                seniors.get(0).addChild(kid);
                String adultName = seniors.get(0).getFirstName() + " " + seniors.get(0).getLastname();
                kid.setResponsibleAdult(adultName);
            }//if the responsible party is a child nothing else needs done
        }//end for loop

        //Print Senior tickets
        for(Senior senior : seniors){
            System.out.println("\n");
            System.out.println(senior.toString());
        }//end for loop

        //Print out price owed
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println("\nYour total cost is £" + df.format(cost));
    }//end buyTickets

    /**
     * This method asks the user for a month and day, and displays information about the plane requested
     */
    private static void viewPlaneInformation(){
        //Create the menu
        String request = "\nAre you looking to view a plane for this month or next month?";
        request += "\nType 1 for this month";
        request += "\nType 2 for next month";
        request += "\nOption input: ";

        //Get user choice from menu
        int monthChoice = getNextInt(request);

        //Ask what day that they want to view
        request = "\nWhat day do you wish to view?";
        request += "\nOption input: ";

        //Get user choice
        int dayChoice = getNextInt(request);

        //Return plane for the month and day that the user chose
        Plane plane = Schedule.getPlane(dayChoice, monthChoice);

        //Print out plane details.
        System.out.println(plane.toString());
    }//end viewPlaneInformation

    /**
     * This method runs the entire Airport Check In Program.
     * @param args any incoming arguments
     */
    public static void main(String[] args) {
        //Load any available data into the program.
        startUp();
        //Welcome the user to the program
        System.out.println("\n\nWelcome to the IntelliJ Airport!");
        int option = 0;

        //Create the menu
        String menu = "\nType 1 to buy tickets";
        menu += "\nType 2 to view information on a plane";
        menu += "\nType 0 to exit";
        menu += "\nOption input: ";

        //Prompt user for what they want to do
        do{
            //ensure the plane schedule is up to date in case of overnight use.
            Schedule.updateSchedule();
            option = getNextInt(menu);
            if (option == 0) {
                //Thank user for using the system, and exit the do while loop
                System.out.println("Thank you for using our system.");
            } else if(option == 1){
                //Buy Tickets
                buyTickets();
            } else if(option == 2){
                //View Plane information
                viewPlaneInformation();
            } else {
                //Tell the user that they didn't choose a valid option and start again.
                System.out.println("Invalid response.");
            }
        }while(option != 0); //end do/while

        //Go through shut down sequence
        shutDown();
    }//end main
}//end TicketBookingSystem
