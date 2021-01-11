package TicketPackage.PassengerPackage;

/**
 * //TODO - Project Description
 * @author Michelle Loughran
 * Created on: 03/11/2020
 * Updated on: 08/12/2020
 * AirportCheckIn:TicketPackage.PassengerPackage:Child
 */
public class Child extends Passenger{
    //declares the Child class access as public

    private static int numberOfChildren = 0;// static variable number of children travelling
    // Public classes are visible to all other classes.
    // All the other classes in the package can access the public fields or methods

    private boolean travelingAlone;
    //whether the child is travelling alone or not true / false
    // private boolean = private will allow the use of the variables access within the class

    private String responsibleAdult; //name of responsible adult with the child

    /**
     * default constructor creates a Child with first name, last name seat and departure date
     */
    public Child(){
        this("Noah", "Walkers", "None", "None");
    }//Child

    /**
     * Constructor with first name, last name, seat, and departure date
     * @param first
     * @param last
     * @param seat
     * @param departureDate
     */
    public Child(String first, String last, String seat, String departureDate){
        Passenger.addPassenger();
        numberOfChildren++;
        travelingAlone = true;
        // super refers to superclass (Passenger) objects in this class
        // It is used to call Passenger methods, and to access
        // the superclass constructor.
        super.setFirstname(first);
        super.setLastname(last);
        super.setSeat(seat);
        super.setDepartureDate(departureDate);
        super.setTicketNumber(Passenger.getNumberOfPassengers());
    }//Child

    /**
     *
     * @return the total number of children travelling
     */
    public static int getNumberOfChildren(){
        return numberOfChildren;
    }//getNumberOfChildren

    /**
     * Sets the number of children travelling
     * @param numberOfChildren
     */
    public static void setNumberOfChildren(int numberOfChildren){
        Child.numberOfChildren = numberOfChildren;
    }//setNumberOfChildren

    /**
     * name of responsible adult who is travelling with the child
     * travelingAlone set to false
     * @param responsibleAdult
     */
    public void setResponsibleAdult(String responsibleAdult) {
        this.responsibleAdult = responsibleAdult;
        travelingAlone = false;
    }//setResponsibleAdult

    /**
     *
     * @return name of responsible adult who is travelling with the child
     */
    public String getResponsibleAdult(){
        return responsibleAdult;
    }//getResponsibleAdult

    @Override
    /**
     * outputs to screen the passenger details
     */
    public String toString(){
        String out = "  *  *  *  PASSENGER DETAILS  *  *  *";
        out += "\nThe seat reference is:          " + super.getSeat();
        out += "\nThe passenger first name is:    " + super.getFirstName();
        out += "\nThe passenger last name is:     " + super.getLastname();
        out += "\nThe ticket number is:           " + super.getTicketNumber();
        out += "\nThe departure date is:          " + super.getDepartureDate();
        if(travelingAlone){
            out += "\nThis child is traveling alone.";
        } else {
            out += "\nThis child is traveling with:   " + responsibleAdult;
        }
        out += "\n  *    *    *    *    *    *    *";
        return out;
    }//toString
}//Child
