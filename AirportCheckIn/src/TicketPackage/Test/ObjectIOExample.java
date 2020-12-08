package TicketPackage.Test;

/*******
 *   AirportCheckIn:TicketPackage.Test
 *   File: ObjectIOExample
 *   Created by: Melissa Melaugh
 *   Created on: 04/12/2020
 *   Updated on: 04/12/2020
 *   Project Description: //TODO
 *******/
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;

import TicketPackage.PlanePackage.*;

public class ObjectIOExample {

    private static final String filepath="C:\\Users\\Solar\\IdeaProjects\\PSD2_Group8Project\\AirportCheckIn\\src\\TicketPackage\\Test\\obj";

    public static void main(String args[]) {

        ObjectIOExample objectIO = new ObjectIOExample();
        Schedule.updateSchedule();

        Plane myPlane = Schedule.getPlane(25, 12, 2020);
        try {
            myPlane.takeSeat("A1");
            myPlane.takeSeat("A2");
        } catch (Exception e){
            System.out.println("Seat Unavailable");
        }

        myPlane.showAvailableSeats();

        objectIO.WriteObjectToFile(filepath, Schedule.getSchedule());

        //Read object from file
        Schedule.setSchedule((Hashtable<String, ArrayList<Plane>>) objectIO.ReadObjectFromFile(filepath));
        Plane loadedPlane = Schedule.getPlane(25, 12, 2020);
        loadedPlane.showAvailableSeats();
    }

    public void WriteObjectToFile(String filepath,Object serObj) {

        try {
            FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Object ReadObjectFromFile(String filepath) {

        try {

            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Object obj = objectIn.readObject();

            System.out.println("The Object has been read from the file");
            objectIn.close();
            return obj;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
