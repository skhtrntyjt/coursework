package cw;

public class Table {
    public enum SeatingType {
        CHAIR, BENCH, COUCH
    }
    private int id;
    private int seats;
    private SeatingType seatingType;
    private String comment;
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public SeatingType getSeatingType() {
        return seatingType;
    }

    public void setSeatingType(SeatingType seatingType) {
        this.seatingType = seatingType;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Table(int id, int seats, SeatingType seatingType, String comment, String location) {
        this.id = id;
        this.seats = seats;
        this.seatingType = seatingType;
        this.comment = comment;
        this.location = location;
    }

}
