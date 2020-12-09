package TicketPackage.PassengerPackage;

/**
 * //TODO - Project Description
 * @author Michelle Loughran
 * Created on: 02/12/2020
 * Updated on: 08/12/2020
 * AirportCheckIn:TicketPackage.PassengerPackage:Child
 */
public class Child extends Passenger{
    private static int numberOfChildren = 0;
    private boolean travelingAlone;
    private String responsibleAdult;

    /**
     *
     */
    public Child(){
        this("Noah", "Walkers", "None", "None");
    }//Child

    /**
     *
     * @param first
     * @param last
     * @param seat
     * @param departureDate
     */
    public Child(String first, String last, String seat, String departureDate){
        Passenger.addPassenger();
        numberOfChildren++;
        travelingAlone = true;
        super.setFirstname(first);
        super.setLastname(last);
        super.setSeat(seat);
        super.setDepartureDate(departureDate);
        super.setTicketNumber(Passenger.getNumberOfPassengers());
    }//Child

    /**
     *
     * @return
     */
    public static int getNumberOfChildren(){
        return numberOfChildren;
    }//getNumberOfChildren

    /**
     *
     * @param numberOfChildren
     */
    public static void setNumberOfChildren(int numberOfChildren){
        Child.numberOfChildren = numberOfChildren;
    }//setNumberOfChildren

    /**
     *
     * @param responsibleAdult
     */
    public void setResponsibleAdult(String responsibleAdult) {
        this.responsibleAdult = responsibleAdult;
        travelingAlone = false;
    }//setResponsibleAdult

    /**
     *
     * @return
     */
    public String getResponsibleAdult(){
        return responsibleAdult;
    }//getResponsibleAdult

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
        if(travelingAlone){
            out += "\nThis child is traveling alone.";
        } else {
            out += "\nThis child is traveling with:   " + responsibleAdult;
        }
        out += "\n  *    *    *    *    *    *    *";
        return out;
    }//toString
}//Child
