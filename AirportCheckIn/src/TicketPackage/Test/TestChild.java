package TicketPackage.Test;

import TicketPackage.PassengerPackage.Child;
import TicketPackage.PassengerPackage.Passenger;

import java.util.Scanner;
/**
 * Created by michelleloughran on  09/12/2020
 * comments about package here
 */
public class TestChild
{

   public static void main(String[] args)
   {
      Child child = new Child("Mary","Rogers", "A3","16.03.21");//create a new object);

      String firstName, lastName,details;//
      int ticketNumber, noChildren=0;//declare static variables


      System.out.println("\t\t\tTEST CHILD");
      System.out.println("=============================");
      firstName= child.getFirstName();//get child first name
      lastName = child.getLastname();//get child last name
      System.out.println("\t\t\t" + firstName+ " "+ lastName + " \n");

     details= child.toString();//from child class
     System.out.println(details);//print out the details


   }//main
}//class
