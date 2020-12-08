package TicketPackage.PassengerPackage;

import java.util.ArrayList;

/*******
 *   AirportCheckIn:TicketPackage.PassengerPackage
 *   File: Senior
 *   Created by: Michelle Loughran
 *   Created on: 08/12/2020
 *   Updated on: 08/12/2020
 *   Project Description: //TODO
 *******/
public class Senior extends Passenger{
    private static int numberOfSeniors = 0;
    private ArrayList<Child> accompanyingChildren;

    /**
     *
     */
    public Senior(){
        this("Noah", "Walkers", "None", "None");
    }//Senior

    /**
     *
     * @param first - the first name of the passenger
     * @param last - the last name of the passenger
     * @param seat - the chosen seat of the passenger
     * @param departureDate - the departure date of the passenger
     */
    public Senior(String first, String last, String seat, String departureDate){
        Passenger.addPassenger();
        numberOfSeniors++;

        super.setFirstname(first);
        super.setLastname(last);
        super.setSeat(seat);
        super.setDepartureDate(departureDate);
        super.setTicketNumber(Passenger.getNumberOfPassengers());

        this.accompanyingChildren = new ArrayList<>();
    }//Senior

    /**
     *
     * @return
     */
    public int getNumberOfAccompanyingChildren(){
        return accompanyingChildren.size();
    }//getNumberOfAccompanyingChildren

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
    }

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
    }
}
