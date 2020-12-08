package TicketPackage.PlanePackage;

import TicketPackage.PassengerPackage.*;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

/*******
 *   AirportCheckIn:TicketPackage.PlanePackage
 *   File: Plane
 *   Created by: Melissa Melaugh
 *   Created on: 25/11/2020
 *   Updated on: 29/11/2020
 *   Project Description: Stores information about a plane
 *******/
public class Plane implements Serializable {
    //static variables
    private static final char[] ROWS = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L'};
    private static final int[] SEATS_IN_ROW = {1, 2, 3, 4, 5, 6};
    private static final double[][][] PRICES = createWeekdayVariations();
    private static final ArrayList<String> SEATS = createSeatingChart();
    private static int totalSeats = 0;

    //instance variables
    private String departureDate;
    private double[] prices;
    private ArrayList<Adult> adults;
    private ArrayList<Child> children;
    private ArrayList<Senior> seniors;
    private ArrayList<String> availableSeats;
    private ArrayList<String> takenSeats;

    /**
     * The default constructor, makes a plane for a Wednesday in March. (The default price)
     */
    public Plane(){
        this(2,3, "never"); //creates a plane at the standard price
    }//end Plane constructor

    /**
     * This is the complex constructor that initialises everything
     * @param month - the month, zero indexed, that the plane is to be created for
     * @param weekday - the day of the week
     * @param departureDate - the departure date in year-month-day format.
     */
    public Plane(int month, int weekday, String departureDate){
        this.adults = new ArrayList<>();
        this.children = new ArrayList<>();
        this.seniors = new ArrayList<>();
        this.prices = PRICES[month][(weekday-1)];
        this.availableSeats = (ArrayList<String>) SEATS.clone();
        this.takenSeats = new ArrayList<String>();
        this.departureDate = departureDate;
    }//end Plane constructor

    /**
     * This method moves an available seat from the available seat list to the taken seat list if its available
     * @param seat - the seat the passenger wishes to take
     * @throws Exception if the seat is unavailable
     */
    public void takeSeat(String seat) throws Exception {
        //If the seat is available take the seat, otherwise throw an error to handle
        if(availableSeats.contains(seat)){
            //Remove the seat from the available seat list
            availableSeats.remove(seat);
            //Add the seat to the taken seats list
            takenSeats.add(seat);
            //Tell the user that it was successfully booked
            System.out.println(String.format("Seat %s booked!", seat));
        }else{
            //Throw an unavailable exception.
            throw new Exception("Seat unavailable!");
        }//end if
    }//end takeSeat

    /**
     * Prints out the full list of the available seats.
     */
    public void showAvailableSeats(){
        //Orient the user
        System.out.println(" * * * AVAILABLE SEATS * * *");
        String out = "     (Front of plane)";
        //Create the current row for formatting
        char currentRow = 'z';
        for(String seat : availableSeats){
            if(seat.charAt(0) != currentRow){ //if the seat is in another row, move to the next line
                out += String.format("\n    %s", seat);
                currentRow = seat.charAt(0);
            } else { //if the seat is in the same row, add a space and put the seat next to the last seat
                out += String.format(" %s", seat);
            }//end if
        }//end for
        //Print out the seats, and complete orientation of the user
        System.out.println(out);
        System.out.println("     (Back of plane)");
        System.out.println(" *   *   *   *   *   *   *");
    }//end showAvailableSeats

    /**
     * Adds an adult to the adults list.
     * @param adult - the adult to add to the plane
     */
    public void addAdult(Adult adult){
        this.adults.add(adult);
    }//end addAdult

    /**
     * Adds a child to the children list.
     * @param child - the child to add to the plane
     */
    public void addChild(Child child){
        this.children.add(child);
    }//end addChild

    /**
     * Adds a senior to the seniors list.
     * @param senior - the senior to add to the plane
     */
    public void addSenior(Senior senior){
        this.seniors.add(senior);
    }//end addSenior

    /**
     * Gets the price of the child for this plane
     * @return - a double representing the price of a child seat
     */
    public double getChildPrice(){
        return this.prices[0];
    }//end getChildPrice

    /**
     * Gets the price of the adult for this plane
     * @return - a double representing the price of an adult's seat
     */
    public double getAdultPrice(){
        return this.prices[1];
    }//end getAdultPrice

    /**
     * Gets the price of the senior for this plane
     * @return - a double representing the price of a senior's ticket
     */
    public double getSeniorPrice(){
        return this.prices[2];
    }//end getSeniorPrice

    /**
     * This method returns a string representing the departure date of the plane
     * @return - a string representing the departure date in year-month-day format
     */
    public String getDepartureDate(){
        return this.departureDate;
    }//end getDepartureDate

    /**
     * Create the different prices for every month and weekday within the month
     * @return - the price variations
     */
    private static double[][][] createWeekdayVariations(){
        //Discounts
        final double childDiscount = 25; //in percent (https://www.norwegian.com/uk/travel-info/travelling-with-children/discounts-for-children/)
        final double seniorDiscount = 10; //in percent (https://www.cheapflights.com/news/how-to-find-senior-airfare-discounts)

        //Base Prices
        final double adultPrice = 50.00; //Approximate Belfast to London
        final double childPrice = adultPrice - (adultPrice/childDiscount);
        final double seniorPrice = adultPrice - (adultPrice/seniorDiscount);

        final double[] basePrice = {childPrice, adultPrice, seniorPrice};

        //Markups
        //(https://www.skyscanner.com/tips-and-inspiration/search-and-save-look-cheapest-flight-prices-month-and-year)
        final double[] weekdayMarkups = {15, 10, -5, 0, 5, 10, 15}; //starts with Sunday
        final double[] monthMarkups = {-5, -10, 0, 0, 5, 10, 15, 15, -5, 0, 0, 20}; //starts with January

        double[][][] prices = new double[12][7][3]; //Month, Weekday, AgeCategory

        //Create full list of prices
        for(int month = 0; month < monthMarkups.length; month++) {
            for (int weekday = 0; weekday < weekdayMarkups.length; weekday++) {
                for (int pricePosition = 0; pricePosition < basePrice.length; pricePosition++) {
                    //get the full price adjustment percent
                    double priceAdjustmentPercent = monthMarkups[month] + weekdayMarkups[weekday];
                    //Default the priceAdjustment to 0
                    double priceAdjustment = 0;
                    if(priceAdjustmentPercent != 0){ //handling a divide by zero error
                        //If the priceAdjustmentPercent is not zero, change priceAdjustment to the correct amount
                        priceAdjustment = basePrice[pricePosition] / priceAdjustmentPercent;
                    }
                    //Get the price for the month/weekday/passenger type
                    double price = basePrice[pricePosition] + priceAdjustment;
                    prices[month][weekday][pricePosition] = price;

                    //System.out.printf("Month %d Weekday %d has a price of £%8.2f for a person of type %d\n", month+1, weekday, price, pricePosition); //TESTING
                }//end pricePosition for loop
            }//end weekday for loop
        }//end month for loop

        //return the prices that were calculated
        return prices;
    }//end createWeekdayVariations

    /**
     * Creates a seating chart from the finals ROWS and COLUMNS - making sure each row and column are even
     * @return - the full seating chart in ArrayList<String> format.
     */
    private static ArrayList<String> createSeatingChart(){
        //reinitialise total seats to 0
        totalSeats = 0;
        //Set up the rows, and seats in a row
        ArrayList<String> seatList = new ArrayList<String>();

        //Make sure that each row has the full amount of seats
        for(char row : ROWS){
            for(int seat: SEATS_IN_ROW){
                String theSeat = String.format("%c%d", row, seat);
                seatList.add(theSeat);
                totalSeats++;
                //System.out.println(theSeat + " was created."); //TESTING
            }//end for loop
        }//end for loop
        return seatList;
    }//end createSeatingChart

    /**
     * This method returns a string that allows the plane to be human readable and informative.
     */
    @Override
    public String toString() {
        //Add all information to one string, format, and return that string.
        DecimalFormat df = new DecimalFormat("0.00");
        String out = "\n  *    *    *    *    *    *    *";
        out += String.format("\nThis plane departs on %s", getDepartureDate());
        out += String.format("\nThis Plane has a total of %d seats.", totalSeats);
        out += String.format("\nThere are %d passengers on this flight.", takenSeats.size());
        out += String.format("\n\t- %d Adults (Price: £%s)", adults.size(), df.format(getAdultPrice()));
        out += String.format("\n\t- %d Children (Price: £%s)", children.size(), df.format(getChildPrice()));
        out += String.format("\n\t- %d Seniors  (Price: £%s)", seniors.size(), df.format(getChildPrice()));
        out += String.format("\nThere are %d seats still available.", availableSeats.size());
        out += "\n  *    *    *    *    *    *    *";
        return out;
    }//end toString
}//end Plane
