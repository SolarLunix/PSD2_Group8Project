package TicketPackage.Test;

import java.util.Scanner;

/**
 * Ciaran O'Boyle
 */
public class testAcChild2 {
    public static void main(String[] args) {
        Scanner Keyboard = new Scanner(System.in);
        String name1,name2,name3,name4,name5;
        acChild2 a = new acChild2();
        a.showAllDetails();

        System.out.println("Enter name1:");
        name1 = Keyboard.nextLine();
        a.setName1("Accompanied Child 1 name: " + name1 + "\n");
        Keyboard.nextLine();

        System.out.println("Enter name2:");
        name2 = Keyboard.nextLine();
        a.setName2("Accompanied Child 2 name: " + name2+"\n");
        Keyboard.nextLine();

        System.out.println("Enter name3:");
        name3 = Keyboard.nextLine();
        a.setName3("Accompanied Child 3 name: " + name3 + "\n");
        Keyboard.nextLine();

        System.out.println("Enter name4:");
        name4 = Keyboard.nextLine();
        a.setName4("Accompanied Child 4 name: " + name4 + "\n");
        Keyboard.nextLine();

        System.out.println("Enter name5:");
        name5 = Keyboard.nextLine();
        a.setName5("Accompanied Child 5 name: " + name5 + "\n");
        Keyboard.nextLine();
        a.showAllDetails();

    }
}
