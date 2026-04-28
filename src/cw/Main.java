package cw;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static boolean makeReservation() {
        return false;
    }
    public static void viewReservations() {
        List<Table> list = TablesDb.getInstance().getTables();
        for (Table t : list) {
            System.out.println(t);
        }
    }

    public static void main(String[] args) {
        TablesDb.getInstance().load(new File("tables.txt"));

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
