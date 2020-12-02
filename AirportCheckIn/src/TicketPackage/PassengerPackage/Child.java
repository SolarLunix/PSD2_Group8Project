package TicketPackage.PassengerPackage;

/*******
 *   AirportCheckIn:TicketPackage.PassengerPackage
 *   File: Child
 *   Created by: Melissa Melaugh
 *   Created on: 02/12/2020
 *   Updated on: 02/12/2020
 *   Project Description: //TODO
 *******/
public class Child extends Passenger{
    private static int numChildren = 0;

    public Child(){
        this("Henry Hoover");
    }

    public Child(String name){
        super(name, null, null);
        numChildren++;
    }

}
