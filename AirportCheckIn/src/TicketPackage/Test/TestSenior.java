package TicketPackage.Test;

import TicketPackage.PassengerPackage.Senior;

/**
 * Created by michelleloughran on  12/12/2020
 * comments about package here
 */
public class TestSenior
{
   public static void main(String[] args)
   {
      Senior senior = new Senior("Tom","Short", "A5","16.03.21");//create a new object);

      String firstName, lastName,details;//
     //declare static variables
      int ticketNumber, noChildren=0;//declare static variables


      System.out.println("\t\t\tTEST SENIOR");
      System.out.println("=============================");
      firstName= senior.getFirstName();//get Senior first name
      lastName = senior.getLastname();//get Senior last name
      System.out.println("\t\t\t" + firstName+ " "+ lastName + " \n");

      details= senior.toString();//from senior class
      System.out.println(details);//print out the details


   }//main
}//class
