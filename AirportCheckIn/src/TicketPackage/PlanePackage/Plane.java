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
    private static final double[][][] PRICES = createWeekdayVariations();
    private static final ArrayList<String> SEATS = createSeatingChart();
    private String month;
    private String weekday;
    private double[] prices;
    //private Adult[] adults;
    //private Child[] children;
    //private Seniors[] seniors;
    private ArrayList<String> availableSeats;
    private ArrayList<String> takenSeats;
    private int passengers;

    public Plane(){
        this(2,3); //creates a plane at the standard price
    }

    public Plane(int month, int weekday){
        convertMonth(month);
        convertWeekday(weekday);
        this.prices = PRICES[month][(weekday-1)];
        this.availableSeats = SEATS;
    }

    //THESE ARE THE MANIPULATION METHODS
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

    public void takeSeat(String seat) throws Exception {
        if(availableSeats.contains(seat)){
            availableSeats.remove(seat);
            takenSeats.add(seat);
            System.out.println(String.format("Seat %s booked!", seat));
        }else{
            throw new Exception("Seat unavailable!");
        }
    }

    //THIS IS WHERE THE GETTERS ARE
    public double getChildPrice(){
        return this.prices[0];
    }

    public double getAdultPrice(){
        return this.prices[1];
    }

    public double getSeniorPrice(){
        return this.prices[2];
    }


    //THIS IS WHERE THE STATIC METHODS BEGIN
    private static double[][][] createWeekdayVariations(){
        final double childDiscount = 25; //in percent (https://www.norwegian.com/uk/travel-info/travelling-with-children/discounts-for-children/)
        final double seniorDiscount = 10; //in percent (https://www.cheapflights.com/news/how-to-find-senior-airfare-discounts)

        final double adultPrice = 50.00; //Approximate Belfast to London
        final double childPrice = adultPrice - (adultPrice/childDiscount);
        final double seniorPrice = adultPrice - (adultPrice/seniorDiscount);

        final double[] basePrice = {childPrice, childDiscount, seniorPrice};

        //(https://www.skyscanner.com/tips-and-inspiration/search-and-save-look-cheapest-flight-prices-month-and-year)
        final double[] weekdayMarkups = {15, 10, -5, 0, 5, 10, 15}; //starts with Sunday
        final double[] monthMarkups = {-5, -10, 0, 0, 5, 10, 15, 15, -5, 0, 0, 20}; //starts with January

        double[][][] prices = new double[12][7][3]; //Month, Weekday, AgeCategory

        for(int month = 0; month < monthMarkups.length; month++) {
            for (int weekday = 0; weekday < weekdayMarkups.length; weekday++) {
                for (int pricePosition = 0; pricePosition < basePrice.length; pricePosition++) {
                    double priceAdjustmentPercent = monthMarkups[month] + weekdayMarkups[weekday];
                    double priceAdjustment = 0;
                    if(priceAdjustment != 0){ //handling a divide by zero error
                        priceAdjustment = basePrice[pricePosition]/priceAdjustmentPercent;
                    }
                    double price = basePrice[pricePosition] + priceAdjustment;
                    prices[month][weekday][pricePosition] = price;

                    System.out.printf("Price %d Month %d Weekday %d has a price of £%8.2f\n", pricePosition, month, weekday, price);
                }
            }
        }
        return prices;
    }

    private static ArrayList<String> createSeatingChart(){
        char[] rows = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L'};
        int[] seats = {1, 2, 3, 4, 5, 6};
        ArrayList<String> seatList = new ArrayList<String>();

        for(char row : rows){
            for(int seat: seats){
                String theSeat = String.format("%c%d", row, seat);
                seatList.add(theSeat);
                System.out.println(theSeat + " was created.");
            }
        }
        return seatList;
    }
}
