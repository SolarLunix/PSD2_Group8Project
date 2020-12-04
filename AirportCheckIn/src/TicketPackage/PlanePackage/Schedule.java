package TicketPackage.PlanePackage;

import java.io.File;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;

/*******
 *   AirportCheckIn:TicketPackage.PlanePackage
 *   File: Schedule
 *   Created by: Melissa Melaugh
 *   Created on: 29/11/2020
 *   Updated on: 29/11/2020
 *   Project Description: This class helps control the planes ((A completely static class))
 *******/
public class Schedule {
    private static Hashtable<String, ArrayList<Plane>> schedule = new Hashtable<String, ArrayList<Plane>>();

    /**
     * This method is meant to generate all of the planes for a given month integer
     * @param month - an int from 0-11 representing the corresponding month -1 (0-January, 1-February, etc)
     * @param year - an int representing the current year
     * @param key - the key for the hashtable entry
     */
    private static void generateMonth(int month, int year, String key){
        YearMonth yearMonthObject = YearMonth.of(year, (month+1));
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

    /**
     * This method is used to update the schedule
     */
    public static void updateSchedule(){
        final int MONTHS_IN_YEAR = 12;
        Calendar today = Calendar.getInstance(); //This gets the current
        int month = today.get(Calendar.MONTH); //This returns the 0-indexed month (aka month-1)
        int year = today.get(Calendar.YEAR); //This returns the full year

        //Remove the previous month if it exists. Can be tested with: addMonthToSchedule((month-1), year);
        String key = String.format("%d-%d", year, month);
        removeMonth(key);

        //Add this month's planes to the schedule if it doesn't exist.
        key = String.format("%d-%d", year, (month + 1));
        addMonthToSchedule(month, year, key);

        //Increase the month by one and create the schedule for the next month if it doesn't exist
        month++;
        month %= MONTHS_IN_YEAR;
        if(month==0){ //if you are in December, January is NEXT year, add one!
            year++;
        }
        key = String.format("%d-%d", year, (month + 1));
        addMonthToSchedule(month, year, key);
    }

    /**
     * This method is to add a month to the schedule
     * @param month - an int from 0-11 representing the corresponding month -1 (0-January, 1-February, etc)
     * @param year - an int representing the current year
     * @param key - the key for the hashtable entry
     */
    private static void addMonthToSchedule(int month, int year, String key){
        if(schedule.containsKey(key)){
            System.out.println("This month already exists.");
        }else{
            generateMonth(month, year, key);
            System.out.println(String.format("Entry %s added.", key));
        }
    }

    /**
     * Removes a month from the Hashtable
     * @param key - the month to remove
     */
    private static void removeMonth(String key){
        if(schedule.containsKey(key)){
            schedule.remove(key);
            System.out.println(String.format("Entry %s removed.", key));
        }
    }

    /**
     *
     * @param file
     */
    public static void loadSchedule(File file){
        //TODO
    }

}
