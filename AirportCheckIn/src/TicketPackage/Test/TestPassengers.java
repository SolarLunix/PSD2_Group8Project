package TicketPackage.Test;

import TicketPackage.PassengerPackage.*;

import java.util.Scanner;

/*******
 *   AirportCheckIn:TicketPackage.Test
 *   File: TestPassengers
 *   Created by: Ciaran O'Boyle
 *   Created on: 25/11/2020
 *   Updated on: 08/12/2020
 *   Project Description: //TODO
 *******/
public class TestPassengers {
    public static void main(String[] args) {
        Scanner Keyboard = new Scanner(System.in);
        String seat, firstName, lastName;
        int ticketNumber;//declare variables
        //Passenger p = new Passenger(); //Passenger was made abstract
        Adult p = new Adult();

        System.out.print("What is the seat reference?:  ");
        seat=Keyboard.nextLine();
        p.setSeat(seat);
        System.out.print("What is the passenger first name?:  ");
        firstName=Keyboard.nextLine();
        p.setFirstname(firstName);
        System.out.print("What is the passenger last name?:  ");
        lastName=Keyboard.nextLine();
        p.setLastname(lastName);
        System.out.print("What is the passenger ticket number?:  ");
        ticketNumber=Keyboard.nextInt();
        p.setTicketNumber(ticketNumber);

        p.showAllDetails();
        System.out.println(p.getSeat());
        System.out.println(p.getFirstName());
        System.out.println(p.getLastname());
        System.out.println(p.getTicketNumber());
    }
}
