package TicketPackage.Test;

import TicketPackage.PlanePackage.Plane;
import TicketPackage.PlanePackage.SchedulePlanes;

/*******
 *   AirportCheckIn:TicketPackage.Test
 *   File: TestPlane
 *   Created by: Melissa Melaugh
 *   Created on: 25/11/2020
 *   Updated on: 29/11/2020
 *   Project Description: A place to check the planes and schedules!
 *******/
public class TestPlane {
    public static void main(String[] args) {
        Plane myPlane = new Plane();
        SchedulePlanes.updateSchedule();
        SchedulePlanes.updateSchedule();
        try {
            myPlane.takeSeat("A1");
            myPlane.takeSeat("A2");
            myPlane.takeSeat("A1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
