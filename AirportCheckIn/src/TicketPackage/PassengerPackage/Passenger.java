package TicketPackage.PassengerPackage;

/*******
 *   AirportCheckIn:TicketPackage.PassengerPackage
 *   File: Passenger
 *   Created by: Melissa Melaugh
 *   Created on: 25/11/2020
 *   Updated on: 25/11/2020
 *   Project Description: //TODO
 *******/
public class Passenger {
    static int number = 0;
    String name;

    String depatureDate;

    public Passenger(){
        this("Henry Hoover", null, null);
    }

    public Passenger(String passengerName){
        this(passengerName, null, null);
    }

    public Passenger(String passengerName, String seat, String depatureDate1){
        number++;
        name = passengerName;
        depatureDate = depatureDate1;
    }

    @Override
    public String toString() {
        String out = "blaha blahaldkfashdkah df";
        out += "Departure date:             " + depatureDate;
        return out;
    }
}
