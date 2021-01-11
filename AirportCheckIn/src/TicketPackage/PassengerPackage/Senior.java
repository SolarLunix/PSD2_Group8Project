package TicketPackage.PassengerPackage;

import java.util.ArrayList;

/**
 * //TODO - Project Description
 * @author  Michelle Loughran
 * Created on: 30/11/2020
 * Updated on: 08/12/2020
 * AirportCheckIn:TicketPackage.PassengerPackage:Senior
 */
public class Senior extends Passenger{
    //declares the Senior class access as public
    // Public classes are visible to all other classes.
    // All the other classes in the package can access the public fields or methods

    private static int numberOfSeniors = 0;
    // private static integer = private will allow the use of the variables access within the class
    // and static means we can access the variable in a static method.
    // as a non-static variable cannot be accessed in a static method.
    // the number of over65's travelling

    private ArrayList<Child> accompanyingChildren;
    //private ArrayList <Child> = creates an array for the accompanying children

    /**
     * Default constructor
     */
    public Senior(){
        this("Noah", "Walkers", "None", "None");
    }//Senior

    /**
     * constructor with first name, last name, seat, and departure date
     * @param first the first name of the passenger
     * @param last the last name of the passenger
     * @param seat the chosen seat of the passenger
     * @param departureDate the departure date of the passenger
     */
    public Senior(String first, String last, String seat, String departureDate){
        Passenger.addPassenger();
        numberOfSeniors++;

        // super keyword refers to superclass (Passenger) objects in this class
        // It is used to call superclass methods, and to access
        // the superclass constructor.
        super.setFirstname(first);
        super.setLastname(last);
        super.setSeat(seat);
        super.setDepartureDate(departureDate);
        super.setTicketNumber(Passenger.getNumberOfPassengers());

        this.accompanyingChildren = new ArrayList<>();
    }//Senior

    /**
     *
     * @return the number of children accompanying the senior
     */
    public int getNumberOfAccompanyingChildren(){
        return accompanyingChildren.size();
    }//getNumberOfAccompanyingChildren

    /**
     * Print accompanying children to screen
     */
    public void printAccompanyingChildren(){
        int numberOfKids = getNumberOfAccompanyingChildren();
        if(numberOfKids == 0){
            System.out.println("This passenger has no kids with them.");
            //if the number of children = 0 there are no children
        } else if(numberOfKids >= 1){
            //otherwise get the number of children and their names and print their names
            for(int childNumber = 0; childNumber < numberOfKids; childNumber++){
                Child theChild = accompanyingChildren.get(childNumber);
                String childName = theChild.getFirstName() + " " + theChild.getLastname();
                System.out.println("Child " + (childNumber+1) + " is named " + childName);
            }//for
        }//if
    }//printAccompanyingChildren

    /**
     * set the number of seniors
     * @param numberOfSeniors
     */
    public static void setNumberOfSeniors(int numberOfSeniors){
        Senior.numberOfSeniors = numberOfSeniors;
    }//setNumberOfAdults

    /**
     *
     * @return
     */
    public static int getNumberOfSeniors(){
        return numberOfSeniors;
    }//getNumberOfSeniors

    /**
     *
     * @param acChild
     */
    public void addChild (Child acChild){
        accompanyingChildren.add(acChild);
    }//addChild

    /**
     *
     * @param abandonedKid
     */
    public void removeChild(Child abandonedKid){
        System.out.println("Sorry you can't abandon your kid here.");
        //TODO Actually be able to remove a child
    }//removeChild


    @Override
    /**
     *
     */
    public String toString(){
        String out = "  *  *  *  PASSENGER DETAILS  *  *  *";
        out += "\nThe seat reference is:          " + super.getSeat();
        out += "\nThe passenger first name is:    " + super.getFirstName();
        out += "\nThe passenger last name is:     " + super.getLastname();
        out += "\nThe ticket number is:           " + super.getTicketNumber();
        out += "\nThe departure date is:          " + super.getDepartureDate();
        out += "\nNumber of Children:             " + getNumberOfAccompanyingChildren();
        out += "\n  *    *    *    *    *    *    *";
        return out;
    }//toString
}//Senior


//Senior: <Michelle>
//    • Variables:
//          ◦ String seat
//          ◦ Static Int numberOfSeniors = 0
//          ◦ String firstName
//          ◦ String lastName
//          ◦ String seat
//          ◦ Int TicketNumber
//         ◦ Boolean travelingAlone
//          ◦String departure date
//          • Methods:
//          ◦ toString
//          ▪ Lists full name, seat number, ticketNumber, and departure date
