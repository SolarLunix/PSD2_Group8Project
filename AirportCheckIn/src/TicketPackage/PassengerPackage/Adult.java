package TicketPackage.PassengerPackage;

import java.util.ArrayList;

/**
 * //TODO: Project Description
 * @author Ciaran O'Boyle
 * Created on: 08/12/2020
 * Updated on: 08/12/2020
 * AirportCheckIn:TicketPackage.PassengerPackage:Adult
 */
public class Adult extends Passenger{
    private static int numberOfAdults = 0;
    private ArrayList<Child> accompanyingChildren;

    /**
     *
     */
    public Adult(){
        this("Henry", "Hoover", "Cargo Hold", "Never");
    }//Adult

    /**
     *
     * @param first
     * @param last
     * @param seat
     * @param departureDate
     */
    public Adult(String first, String last, String seat, String departureDate){
        Passenger.addPassenger();
        numberOfAdults++;

        super.setFirstname(first);
        super.setLastname(last);
        super.setSeat(seat);
        super.setTicketNumber(Passenger.getNumberOfPassengers());
        super.setDepartureDate(departureDate);

        this.accompanyingChildren = new ArrayList<>();
    }//Adult

    /**
     *
     * @return
     */
    public static int getNumberOfAdults(){
        return numberOfAdults;
    }//getNumberOfAdults

    /**
     *
     * @return
     */
    public int getNumberOfAccompanyingChildren(){
        return accompanyingChildren.size();
    }//getNumberOfAccompanyingChildren

    /**
     *
     * @param numberOfAdults
     */
    public static void setNumberOfAdults(int numberOfAdults){
        Adult.numberOfAdults = numberOfAdults;
    }//setNumberOfAdults

    /**
     *
     */
    public void printAccompanyingChildren(){
        int numberOfKids = getNumberOfAccompanyingChildren();
        if(numberOfKids == 0){
            System.out.println("This passenger has no kids with them.");
        } else if(numberOfKids >= 1){
            for(int childNumber = 0; childNumber < numberOfKids; childNumber++){
                Child theChild = accompanyingChildren.get(childNumber);
                String childName = theChild.getFirstName() + " " + theChild.getLastname();
                System.out.println("Child " + (childNumber+1) + " is named " + childName);
            }//for
        }//if
    }//printAccompanyingChildren

    /**
     *
     * @param acChild
     */
    public void addChild (Child acChild){
        accompanyingChildren.add(acChild);
    }

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
}//Adult
