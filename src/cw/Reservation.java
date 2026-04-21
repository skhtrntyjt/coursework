package cw;

import java.time.LocalDateTime;

public class Reservation {
    public enum Status {
        PENDING, VERIFIED, CANCELED, COMPLETED
    }
    private Status status;
    private LocalDateTime dateTime;
    private int peopleAmount;
    private int tableId;

    public Reservation(Status status, LocalDateTime dateTime, int peopleAmount, int tableId, int hours) {
        this.status = status;
        this.dateTime = dateTime;
        this.peopleAmount = peopleAmount;
        this.tableId = tableId;
        this.hours = hours;
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

    private int hours;
}
