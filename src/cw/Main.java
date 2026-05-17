package cw;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static final int OPENING_HOUR = 10;
    public static final int CLOSING_HOUR = 23;
    public static final int RESERVATION_DAYS_ADVANCE = 14;

    private static Reservation createReservation(Timeline tl, LocalDate date, int id) {
        Scanner scanner = new Scanner(System.in);
        Reservation r = null;
        while (r == null) {
            System.out.println("Input time of your reservation (in hours):");
            int hour = scanner.nextInt();
            System.out.println("Input duration of your reservation (in hours):");
            int duration = scanner.nextInt();
            LocalTime time = LocalTime.of(hour, 0);
            if (tl.canAdd(time, duration)) {
                r = new Reservation(Reservation.Status.PENDING, LocalDateTime.of(date, time),
                        0, id, duration, "", "");
            } else {
                System.out.println("Cant add this time with this duration!! Try again!\n");
            }
        }
        return r;
    }

    public static Reservation inputAndVerifyTableId(LocalDate date) {
        Map<Integer, Timeline> map = getTablesByDate(date);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input table number: ");
        int id = scanner.nextInt();
        Timeline timeline = map.get(id);

        return createReservation(timeline, date, id);
    }

    public static Reservation inputAndVerifyReservationDate(int id) {
        Map<LocalDate, Timeline> map = getTablesById(id);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input date (Day.Month.Year): ");
        String s = scanner.next();
        LocalDate date = LocalDate.parse(s, DateTimeFormatter.ofPattern("dd.MM.yy"));
        Timeline timeline = map.get(date);

        return createReservation(timeline, date, id);
    }

    public static boolean makeReservation() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n\n===============================\n");
            System.out.println("1. Reserve by date");
            System.out.println("2. Reserve by table");
            System.out.println("3. Exit");
            System.out.println("\n===============================\n");
            int choice = scanner.nextInt();
            String s;
            int id;
            Reservation newReservation = null;
            switch (choice) {
                case 1:
                    System.out.println("Input date (Day.Month.Year): ");
                    s = scanner.next();
                    LocalDate date = LocalDate.parse(s, DateTimeFormatter.ofPattern("dd.MM.yy"));
                    printTablesByDate(date);
                    newReservation = inputAndVerifyTableId(date);
                    break;
                case 2:
                    TableDb.getInstance().getTables().forEach(System.out::println);
                    System.out.println("\nInput table number: ");
                    id = scanner.nextInt();
                    printTablesById(id);
                    newReservation = inputAndVerifyReservationDate(id);
                    break;
                default:
                    System.out.println("Incorrect input");
                case 3:
        return false;
    }
            int maxPeople = TableDb.getInstance().getById(newReservation.getTableId()).getSeats();
            do {
                System.out.println("How many people will attend (1 - " + maxPeople + ")?");
                newReservation.setPeopleAmount(scanner.nextInt());
            } while (newReservation.getPeopleAmount() > maxPeople
                    && newReservation.getPeopleAmount() < 1);
            // TODO: REGEX
            System.out.println("Input your phone number:");
            newReservation.setPhoneNumber(scanner.next());

            System.out.println("Input your email:");
            newReservation.setEmail(scanner.next());
            ReservationDb.getInstance().addReservation(newReservation);
        }
    }

    public static Map<Integer, Timeline> getTablesByDate(LocalDate date) {
        List<Reservation> occupiedTableReservations = ReservationDb.getInstance()
                .getReservations()
                .stream()
                .filter(r -> r.getDateTime().toLocalDate().equals(date))
                .toList();

        Map<Integer, Timeline> map = new HashMap<>();

        for (Reservation r : occupiedTableReservations) {
            int id = r.getTableId();
            if (map.containsKey(id)) {
                map.put(id, new Timeline());
            }
            Timeline t = map.get(id);
            t.addTimePoint(
                    r.getDateTime().toLocalTime(),
                    r.getHours()
            );
        }
        return map;
    }
    public static void printTablesByDate(LocalDate date) {
        Map<Integer, Timeline> map = getTablesByDate(date);
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

    public static Map<LocalDate, Timeline> getTablesById(int id) {
        List<Reservation> occupiedTableReservations = ReservationDb.getInstance()
                .getReservations()
                .stream()
                .filter(r -> r.getTableId() == id)
                .toList();

        Map<LocalDate, Timeline> map = new HashMap<>();

        for (Reservation r : occupiedTableReservations) {
            LocalDate date = r.getDateTime().toLocalDate();
            if (map.containsKey(date)) {
                map.put(date, new Timeline());
            }
            Timeline t = map.get(date);
            t.addTimePoint(
                    r.getDateTime().toLocalTime(),
                    r.getHours()
            );
        }
        return map;
    }

    public static void printTablesById(int id) {
        Map<LocalDate, Timeline> map = getTablesById(id);
        LocalDate vacantDate = LocalDate.now().plusDays(1);
        for (int i = 0; i < RESERVATION_DAYS_ADVANCE; i++) {
            System.out.println(vacantDate);
            if (!map.containsKey(vacantDate)) {
                System.out.println("\t" + OPENING_HOUR + ":" + CLOSING_HOUR);
                continue;
            }
            for (String s : map.get(vacantDate).getFreeIntervals()) {
                System.out.println("\t" + s);
            }
            vacantDate = vacantDate.plusDays(1);
        }
    }

    public static void viewReservations() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n\n===============================\n");
            System.out.println("1. View by date");
            System.out.println("2. View by table");
            System.out.println("3. Exit");
            System.out.println("\n===============================\n");
            int choice = scanner.nextInt();
            String s;
            int id;
            switch (choice) {
                case 1:
                    System.out.println("Input date (Day.Month.Year): ");
                    s = scanner.next();
                    LocalDate ld = LocalDate.parse(s, DateTimeFormatter.ofPattern("dd.MM.yy"));
                    printTablesByDate(ld);
                    break;
                case 2:
                    TableDb.getInstance().getTables().forEach(System.out::println);
                    System.out.println("\nInput table number: ");
                    id = scanner.nextInt();
                    printTablesById(id);
                    break;
                case 3:
                    return;
            }
        }

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
