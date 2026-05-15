package cw;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Reservation extends DbObject {
    public static ReservationDb getInstance() {
        return null;
    }
    public enum Status {
        PENDING, VERIFIED, CANCELED, COMPLETED
    }

    private Status status;
    private LocalDateTime dateTime;
    private int peopleAmount;
    private int tableId;
    private int hours;
    private String email;
    private String phoneNumber;

    public Reservation() {
        status = Status.COMPLETED;
        dateTime = LocalDateTime.of(1980, 1, 1, 0, 0);
        peopleAmount = 0;
        tableId = 0;
        hours = 0;
        email = "";
        phoneNumber = "";
    }

    public Reservation(Status status, LocalDateTime dateTime, int peopleAmount, int tableId, int hours, String email, String phoneNumber) {
        this.status = status;
        this.dateTime = dateTime;
        this.peopleAmount = peopleAmount;
        this.tableId = tableId;
        this.hours = hours;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void set(Status status, LocalDateTime dateTime, int peopleAmount, int tableId, int hours, String email, String phoneNumber) {
        this.status = status;
        this.dateTime = dateTime;
        this.peopleAmount = peopleAmount;
        this.tableId = tableId;
        this.hours = hours;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String serialize() {
        return status + "|" + dateTime + "|" + peopleAmount + "|" + tableId + "|" + hours + "|" + email + "|" + phoneNumber;
    }

    @Override
    public void deserialize(String s) {
        String[] arguments = s.split("\\|");
        if (arguments.length != 7) {
            throw new IllegalArgumentException("Incorrect amount of arguments");
        }
        Status status = null;
        LocalDateTime dateTime = null;
        int peopleAmount = -1;
        int tableId = -1;
        int hours = -1;
        String email = null;
        String phoneNumber = null;
        try {
            status = Status.valueOf(arguments[0]);
            dateTime = LocalDateTime.parse(arguments[1]);
            peopleAmount = Integer.parseInt(arguments[2]);
            tableId = Integer.parseInt(arguments[3]);
            hours = Integer.parseInt(arguments[4]);
            email = arguments[5];
            phoneNumber = arguments[6];
        } catch (IllegalArgumentException e) {
            System.out.println("Cant deserialize string " + s);
            return;
        }
        set(status, dateTime, peopleAmount, tableId, hours, email, phoneNumber);

    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public int getPeopleAmount() {
        return peopleAmount;
    }

    public void setPeopleAmount(int peopleAmount) {
        this.peopleAmount = peopleAmount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "status=" + status +
                ", dateTime=" + dateTime +
                ", peopleAmount=" + peopleAmount +
                ", tableId=" + tableId +
                ", hours=" + hours +
                ", email=" + email + '\'' +
                ", phoneNumber=" + phoneNumber + '\'' +
                '}';
    }
}