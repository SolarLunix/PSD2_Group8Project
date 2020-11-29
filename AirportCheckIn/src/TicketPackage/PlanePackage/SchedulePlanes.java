package TicketPackage.PlanePackage;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

/*******
 *   AirportCheckIn:TicketPackage.PlanePackage
 *   File: SchedulePlanes
 *   Created by: Melissa Melaugh
 *   Created on: 29/11/2020
 *   Updated on: 29/11/2020
 *   Project Description: This class helps control the planes ((A completely static class))
 *******/
public class SchedulePlanes {
    private static Hashtable<String, ArrayList<Plane>> schedule = new Hashtable<String, ArrayList<Plane>>();

    private static void generateMonth(int month, int year){
        String key = String.format("%d%d", year, (month+1));

        YearMonth yearMonthObject = YearMonth.of(year, month);
        int daysInMonth = yearMonthObject.lengthOfMonth();

        Calendar c = Calendar.getInstance();
        ArrayList<Plane> monthOfPlanes = new ArrayList<Plane>();

        for(int day = 1; day <= daysInMonth; day++) {
            c.set(year, month, day);
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            Plane plane = new Plane((month), dayOfWeek); //month is 0 indexed, so must add one
            monthOfPlanes.add(plane);
        }

        schedule.put(key, monthOfPlanes);
    }

    public static void updateSchedule(){
        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);

        updateSchedule(month, year);
        updateSchedule((month+1), year);
    }

    private static void updateSchedule(int month, int year){
        String key = String.format("%d%d", year, (month+1));

        if(schedule.containsKey(key)){
            System.out.println("Key good!");
        }else{
            generateMonth(month, year);
        }
    }

}
