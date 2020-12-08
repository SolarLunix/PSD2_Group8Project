package TicketPackage.PassengerPackage;

import java.util.ArrayList;

/*******
 *   AirportCheckIn:TicketPackage.PassengerPackage
 *   File: Child
 *   Created by: Michelle Loughran
 *   Created on: 02/12/2020
 *   Updated on: 02/12/2020
 *   Project Description: //TODO
 *******/
public class Child extends Passenger{
    private static int numberOfChildren = 0;
    private boolean travelingAlone;
    private String responsibleAdult;

    public Child(){
        this("Noah", "Walkers", "None", "None");
    }//Child

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

    public static int getNumberOfChildren(){
        return numberOfChildren;
    }//getNumberOfChildren

    public static void setNumberOfChildren(int numberOfChildren){
        Child.numberOfChildren = numberOfChildren;
    }//setNumberOfChildren

    public void setResponsibleAdult(String responsibleAdult) {
        this.responsibleAdult = responsibleAdult;
        travelingAlone = false;
    }//setResponsibleAdult

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
}
