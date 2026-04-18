package cw;

import java.util.Scanner;

public class Main {
    public static boolean makeReservation() {
        return false;
    }
    public static void viewReservations() {

    }
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n\n====================================\n");
            System.out.println("1. Reserve a table");
            System.out.println("2. View available reservations");
            System.out.println("3 Exit");
            System.out.println("\n========================================\n");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    if (makeReservation()) {
                        System.out.println("Your reservation made");
                    }
                    break;
                case 2:
                    viewReservations();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("No such option");
            }
        }
    }
}
