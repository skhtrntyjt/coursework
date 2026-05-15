package cw;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReservationDb {
    private static ReservationDb instance = null;
    private List<Reservation> reservations = new ArrayList<>();
    private ReservationDb() {}
    public List<Reservation> getReservations() {
        return new ArrayList<>(reservations);
    }
    public static ReservationDb getInstance() {
        if (instance == null) {
            instance = new ReservationDb();
        }
        return instance;
    }
    public void load(File file) throws FileNotFoundException {
        if (!file.exists() || !file.canRead()) {
            throw new IllegalArgumentException("File not found!");
        }
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String data = scanner.nextLine();
                Reservation obj = new Reservation();
                obj.deserialize(data);
                reservations.add(obj);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void save(File file) {
        if (!file.exists() || !file.canWrite()) {
            throw new IllegalArgumentException("File not found!");
        }
        try (FileWriter fw = new FileWriter(file)) {
            for (Reservation obj : reservations) {
                String data = obj.serialize();
                fw.write(data);
                fw.write("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
