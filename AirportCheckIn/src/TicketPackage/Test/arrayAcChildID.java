package TicketPackage.Test;

import java.util.Scanner;

/**
 * Ciaran O'Boyle
 */
public class arrayAcChildID {
    public static void main(String[] args) {
        final int MAX = 5;
        String[] acChild = new String[MAX];
        Scanner Keyboard = new Scanner(System.in);
        for (int index = 0; index < MAX; index++) {
            System.out.print("Enter acChildID " + (index+1) + ": ");
            acChild[index] = Keyboard.nextLine();
//            System.out.print("Enter acChildID 2  :");
//            acChild[index] = Keyboard.nextInt();
//            System.out.print("Enter acChildID 3  :");
//            acChild[index] = Keyboard.nextInt();
//            System.out.print("Enter acChildID 4  :");
//            acChild[index] = Keyboard.nextInt();
//            System.out.print("Enter acChildID 5 :");
//            acChild[index] = Keyboard.nextInt();

        }
    }
}
