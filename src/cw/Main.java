package cw;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static final int OPENING_HOUR = 10;
    public static final int CLOSING_HOUR = 23;

    public static boolean makeReservation() {
        return false;
    }
    public static void printTablesByDate(LocalDate date) {
        List<Table> list;
        if (date == null) {
            list = TableDb.getInstance().getTables();
        } else {
            List<Reservation> occupiedTableReservations = ReservationDb.getInstance()
                    .getReservations()
                    .stream()
                    .filter(r -> r.getDateTime().toLocalDate().equals(date))
                    .toList();

            Map<Integer, TimeLine> map = new HashMap<>();

            for (Reservation r : occupiedTableReservations) {
                int id = r.getTableId();
                if (map.containsKey(id)) {
                    map.put(id, new TimeLine());
                }
                TimeLine t = map.get(id);
                t.addTimePoint(
                        r.getDateTime().toLocalDate(),
                        r.getHours()
                );
            }
            for (Table t : TableDb.getInstance().getTables()) {
                System.out.println(t);
                if (!map.containsKey(t.getId())) {
                    System.out.println("\t" + OPENING_HOUR + ":" + CLOSING_HOUR);
                    continue;
                }
                for (String s : map.get(t.getId()).getFreeIntervals()) {
                    System.out.println("\t" + s);
                }
            }
        }
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
