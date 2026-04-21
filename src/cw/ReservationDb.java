package cw;

public class ReservationDb {
    private static ReservationDb instance;
    private ReservationDb() {}
    public static ReservationDb getInstance() {
        if (instance == null) {
            instance = new ReservationDb();
        }
        return instance;
    }
}
