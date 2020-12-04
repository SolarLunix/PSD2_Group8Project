package TicketPackage.PassengerPackage;

import java.util.ArrayList;

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
    private ArrayList<Child> accopanyingChildren;

    public Child(){
        this("Henry Hoover");
    }

    public Child(String name){
        super(name, null, null);
        numChildren++;
    }

    public void addChild(Child child){
        this.accopanyingChildren.add(child);
    }

    public void getNumberOfAccompanyingChildren(){
        System.out.println("Number of accopanying children: " + accopanyingChildren.size());
    }

    public void getNamesOfAccompanyingChildren(){
        System.out.println("The names of the accopanying Children are:");
        for(Child child : this.accopanyingChildren){
            System.out.println("\n\t" + child.getName());
        }
    }

    public String getName(){
        return this.name;
    }
}
