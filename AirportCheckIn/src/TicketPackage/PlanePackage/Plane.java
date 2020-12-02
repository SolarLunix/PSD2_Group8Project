package TicketPackage.PlanePackage;

import java.util.ArrayList;

/*******
 *   AirportCheckIn:TicketPackage.PlanePackage
 *   File: Plane
 *   Created by: Melissa Melaugh
 *   Created on: 25/11/2020
 *   Updated on: 29/11/2020
 *   Project Description: Stores information about a plane
 *******/
public class Plane {
    //static variables
    private static final char[] ROWS = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L'};
    private static final int[] SEATS_IN_ROW = {1, 2, 3, 4, 5, 6};
    private static final double[][][] PRICES = createWeekdayVariations();
    private static final ArrayList<String> SEATS = createSeatingChart();

    //instance variables
    private String month;
    private String weekday;
    private double[] prices;
    //private Adult[] adults;
    //private Child[] children;
    //private Seniors[] seniors;
    private ArrayList<String> availableSeats;
    private ArrayList<String> takenSeats;

    /**
     * The default constructor, makes a plane for a Tuesday in February. (The default price)
     */
    public Plane(){
        this(2,3); //creates a plane at the standard price
    }

    /**
     *
     * @param month
     * @param weekday
     */
    public Plane(int month, int weekday){
        convertMonth(month);
        convertWeekday(weekday);
        this.prices = PRICES[month][(weekday-1)];
        this.availableSeats = SEATS;
        this.takenSeats = new ArrayList<String>();
    }

    /**
     *
     * @param weekday
     */
    private void convertWeekday(int weekday){
        switch(weekday){
            case 1:
                this.weekday = "Sunday";
                break;
            case 2:
                this.weekday = "Monday";
                break;
            case 3:
                this.weekday = "Tuesday";
                break;
            case 4:
                this.weekday = "Wednesday";
                break;
            case 5:
                this.weekday = "Thursday";
                break;
            case 6:
                this.weekday = "Friday";
                break;
            default:
                this.weekday = "Saturday";
        }
    }

    /**
     *
     * @param month
     */
    private void convertMonth(int month){
        if (month == 1) {
            this.month = "January";
        } else if (month == 2){
            this.month = "February";
        } else if (month == 3){
            this.month = "March";
        } else if (month == 4){
            this.month = "April";
        } else if (month == 5){
            this.month = "May";
        } else if (month == 6){
            this.month = "June";
        } else if (month == 7){
            this.month = "July";
        } else if (month == 8){
            this.month = "August";
        } else if (month == 9){
            this.month = "September";
        } else if (month == 10){
            this.month = "October";
        } else if (month == 11){
            this.month = "November";
        } else {
            this.month = "December";
        }
    }

    /**
     *
     * @param seat
     * @throws Exception if the seat is unavailable
     */
    public void takeSeat(String seat) throws Exception {
        if(availableSeats.contains(seat)){
            availableSeats.remove(seat);
            takenSeats.add(seat);
            System.out.println(String.format("Seat %s booked!", seat));
        }else{
            throw new Exception("Seat unavailable!");
        }
    }

    public void showAvailableSeats(){
        System.out.println(" * * * AVAILABLE SEATS * * *");
        char currentRow = 'z';
        String out = "     (Front of plane)";
        for(String seat : availableSeats){
            if(seat.charAt(0) != currentRow){
                out += String.format("\n    %s", seat);
                currentRow = seat.charAt(0);
            } else {
                out += String.format(" %s", seat);
            }
        }
        System.out.println(out);
        System.out.println("     (Back of plane)");
        System.out.println(" *   *   *   *   *   *   *");
    }

    /**
     *
     * @return
     */
    public double getChildPrice(){
        return this.prices[0];
    }

    /**
     *
     * @return
     */
    public double getAdultPrice(){
        return this.prices[1];
    }

    /**
     *
     * @return
     */
    public double getSeniorPrice(){
        return this.prices[2];
    }

    /**
     *
     * @return
     */
    private static double[][][] createWeekdayVariations(){
        final double childDiscount = 25; //in percent (https://www.norwegian.com/uk/travel-info/travelling-with-children/discounts-for-children/)
        final double seniorDiscount = 10; //in percent (https://www.cheapflights.com/news/how-to-find-senior-airfare-discounts)

        final double adultPrice = 50.00; //Approximate Belfast to London
        final double childPrice = adultPrice - (adultPrice/childDiscount);
        final double seniorPrice = adultPrice - (adultPrice/seniorDiscount);

        final double[] basePrice = {childPrice, adultPrice, seniorPrice};

        //(https://www.skyscanner.com/tips-and-inspiration/search-and-save-look-cheapest-flight-prices-month-and-year)
        final double[] weekdayMarkups = {15, 10, -5, 0, 5, 10, 15}; //starts with Sunday
        final double[] monthMarkups = {-5, -10, 0, 0, 5, 10, 15, 15, -5, 0, 0, 20}; //starts with January

        double[][][] prices = new double[12][7][3]; //Month, Weekday, AgeCategory

        for(int month = 0; month < monthMarkups.length; month++) {
            for (int weekday = 0; weekday < weekdayMarkups.length; weekday++) {
                for (int pricePosition = 0; pricePosition < basePrice.length; pricePosition++) {
                    double priceAdjustmentPercent = monthMarkups[month] + weekdayMarkups[weekday];
                    double priceAdjustment = 0;
                    if(priceAdjustmentPercent != 0){ //handling a divide by zero error
                        priceAdjustment = basePrice[pricePosition] / priceAdjustmentPercent;
                    }
                    double price = basePrice[pricePosition] + priceAdjustment;
                    prices[month][weekday][pricePosition] = price;

                    //System.out.printf("Month %d Weekday %d has a price of £%8.2f for a person of type %d\n", month+1, weekday, price, pricePosition);
                }
            }
        }
        return prices;
    }

    /**
     *
     * @return
     */
    private static ArrayList<String> createSeatingChart(){
        //Set up the rows, and seats in a row
        ArrayList<String> seatList = new ArrayList<String>();

        //Make sure that each row has the full amount of seats
        for(char row : ROWS){
            for(int seat: SEATS_IN_ROW){
                String theSeat = String.format("%c%d", row, seat);
                seatList.add(theSeat);
                //System.out.println(theSeat + " was created.");
            }
        }
        return seatList;
    }
}
