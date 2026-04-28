package cw;

public class Table extends DbObject {
    public enum SeatingType {
        CHAIR, BENCH, COUCH
    }
    private int id;
    private int seats;
    private SeatingType seatingType;
    private String comment;
    private String location;

    public Table() {
        id = 0;
        seats = 0;
        seatingType = SeatingType.CHAIR;
        location = "";
        comment = "";
    }

    public Table(int id, int seats, SeatingType seatingType, String location, String comment) {
        this.id = id;
        this.seats = seats;
        this.seatingType = seatingType;
        this.location = location;
        this.comment = comment;
    }

    private void set(int id, int seats, SeatingType seatingType, String location, String comment) {
        this.id = id;
        this.seats = seats;
        this.seatingType = seatingType;
        this.location = location;
        this.comment = comment;
    }

    @Override
    public String serialize() {
        return id + "|" + seats + "|" + seatingType + "|" + location + "|" + comment;
    }

    @Override
    public void deserialize(String s) {
        String[] arguments = s.split("\\|");
        if (arguments.length != 5) {
            throw new IllegalArgumentException("Incorrect amount of arguments");
        }
        int id = -1;
        int seats = -1;
        SeatingType seatingType = null;
        String location = null;
        String comment = null;
        try {
            id = Integer.parseInt(arguments[0]);
            seats = Integer.parseInt(arguments[1]);
            seatingType = SeatingType.valueOf(arguments[2]);
            location = arguments[3];
            comment = arguments[4];
        } catch (IllegalArgumentException e) {
            System.out.println("Cant deserialize string " + s);
            return;
        }
        set(id, seats, seatingType, location, comment);
    }


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


    @Override
    public String toString() {
        return "Table{" +
                "id=" + id +
                ", seats=" + seats +
                ", seatingType=" + seatingType +
                ", location='" + location + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }

}
