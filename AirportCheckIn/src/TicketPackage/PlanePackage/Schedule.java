package TicketPackage.PlanePackage;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;

/**
 *   This class helps control the planes in a schedule ((A completely static class))
 *   @author Melissa Melaugh
 *   Created on: 29/11/2020
 *   Updated on: 08/12/2020
 *   AirportCheckIn:TicketPackage.PlanePackage:Schedule
 */
public class Schedule {
    //static variables
    private static Hashtable<String, ArrayList<Plane>> schedule = new Hashtable<String, ArrayList<Plane>>();

    /**
     * This method returns the schedule so that it can be used or saved.
     * @return the Hashtable<String, ArrayList<Plane>> schedule
     */
    public static Hashtable<String, ArrayList<Plane>> getSchedule(){
        return schedule;
    }//end getSchedule

    /**
     * This method is responsible for setting the schedule after it has been loaded in
     * @param loadedSchedule The loaded Hashtable<String, ArrayList<Plane>> schedule.
     */
    public static void setSchedule(Hashtable<String, ArrayList<Plane>> loadedSchedule){
        schedule = loadedSchedule;
    }//end setSchedule

    /**
     * This method is meant to generate all of the planes for a given month integer
     * @param month an int from 0-11 representing the corresponding month -1 (0-January, 1-February, etc)
     * @param year an int representing the current year
     * @param key the key for the hashtable entry
     */
    private static void generateMonth(int month, int year, String key){
        //Get the number of days in the month, adjusting for the 0 indexed month
        YearMonth yearMonthObject = YearMonth.of(year, (month+1));
        int daysInMonth = yearMonthObject.lengthOfMonth();

        //Get the instance of a calendar to adjust
        Calendar c = Calendar.getInstance();
        ArrayList<Plane> monthOfPlanes = new ArrayList<Plane>();

        //For every day in a month, ensure it has a plane object to leave on that day
        for(int day = 1; day <= daysInMonth; day++) {
            //set the calendar to the day we're looking at
            c.set(year, month, day);
            //get the day of the week as an integer to get the proper price
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            //Create the departure date string
            String departureDate = String.format("%d-%d-%d", year, month, day);
            //Create a plane with this information
            Plane plane = new Plane(month, dayOfWeek, departureDate);
            //Add the plane to the month schedule
            monthOfPlanes.add(plane);
        }//end for

        //Add the month to the schedule with a the provided key
        schedule.put(key, monthOfPlanes);
    }//end generateMonth

    /**
     * This method is used to update the schedule
     */
    public static void updateSchedule(){
        final int MONTHS_IN_YEAR = 12;

        //Get information about the current day
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
        }//end if
        key = String.format("%d-%d", year, (month + 1));
        addMonthToSchedule(month, year, key);
    }//end updateSchedule

    /**
     * This method is to add a month to the schedule
     * @param month an int from 0-11 representing the corresponding month -1 (0-January, 1-February, etc)
     * @param year an int representing the current year
     * @param key the key for the hashtable entry
     */
    private static void addMonthToSchedule(int month, int year, String key){
        if(schedule.containsKey(key)){
            System.out.println("This month already exists.");
        }else{
            generateMonth(month, year, key);
            System.out.println(String.format("Entry %s added.", key));
        }//end if/else
    }

    /**
     * Removes a month from the Hashtable
     * @param key the month to remove
     */
    private static void removeMonth(String key){
        //Only try to remove the month if its in the schedule or it will cause an error
        if(schedule.containsKey(key)){
            schedule.remove(key);
            System.out.println(String.format("Entry %s removed.", key));
        }//end if
    }//end removeMonth

    /**
     * This method gets the plane if you know the day, month, and year its scheduled in
     * @param day the day that the plane is scheduled to take off
     * @param month the month that the plane is scheduled to take off
     * @param year the year that the month is scheduled to take off
     * @return the plane that is linked to that day/month/year
     */
    public static Plane getPlane(int day, int month, int year){
        //create the proper key to get the plane from the schedule
        String key = String.format("%d-%d", year, month);
        day--; //adjusts the day to be zero indexed.
        //return the plane
        return schedule.get(key).get(day);
    }//end getPlane

    /**
     * This method gets the plane relative to the current schedule
     * @param day the day of the month you want to get the plane of
     * @param monthChoice either this month (1) or next month (2)
     * @return the plane at the give month/day key
     */
    public static Plane getPlane(int day, int monthChoice){
        final int MONTHS_IN_YEAR = 12;

        //Get the current day
        Calendar today = Calendar.getInstance(); //This gets the current day
        int month = today.get(Calendar.MONTH); //This returns the 0-indexed month (aka month-1)
        int year = today.get(Calendar.YEAR); //This returns the full year

        //Adjust the day and month to be properly indexed for the year, and correct for the year if December -> January
        day--; //adjusts the day to be zero indexed.
        month += monthChoice;
        month %= (MONTHS_IN_YEAR);
        if(month==1){ //if you are in December, January is NEXT year, add one!
            year++;
        } else if(month==0){
            month = 12; //If you are in December month would be changed to 0, therefore put it back to 12.
        }//end if/else

        //Create the key string
        String key = String.format("%d-%d", year, month);
        //System.out.println(key); //Ensure that the proper year-month was gotten for testing.
        //Return the plane
        return schedule.get(key).get(day);
    }//end getPlane
}//end Schedule
