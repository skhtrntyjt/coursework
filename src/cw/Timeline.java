package cw;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TimeLine {
    List<TimePoint> timeline = new ArrayList<>();

    public void addTimePoint(LocalTime time, int durationHours) {
        timeline.add(new TimePoint(time, durationHours));
    }
    public boolean canAdd(LocalTime time, int durationHours) {
        List<TimePoint> check = new ArrayList<>(timeline);
        TimePoint tp = new TimePoint(time, durationHours);
        check.add(tp);
        check.sort(Comparator.comparing(t -> t.time));
        int i = check.indexOf(tp);
        if (i == check.size() - 1) {
            return Main.CLOSING_HOUR >= time.getHour() + durationHours;
        }
        return check.get(i + 1).getTime().getHour() >= time.getHour() + durationHours;
    }

    List<String> getFreeIntervals() {
        List<String> res = new ArrayList<>();
        timeline.sort(Comparator.comparing(t -> t.time));
        int hourStart = Main.OPENING_HOUR;
        for (TimePoint p : timeline) {
            if (hourStart != p.time.getHour()) {
                String temp = hourStart + ":00 - " + p.time
                        .format(DateTimeFormatter.ofPattern("hh:mm"));
                res.add(temp);
            }
            hourStart = p.time.getHour() + p.durationHours;
        }
        if (hourStart < 23) {
            res.add(hourStart + ":00 - " + Main.CLOSING_HOUR + ":00");
        }
        return res;
    }

    private static class TimePoint {
        public LocalTime time;
        public int durationHours;

        public TimePoint(LocalTime time, int durationHours) {
            this.time = time;
            this.durationHours = durationHours;
        }

        public LocalTime getTime() {
            return time;
        }

        public void setTime(LocalTime time) {
            this.time = time;
        }

        public int getDurationHours() {
            return durationHours;
        }

        public void setDurationHours(int durationHours) {
            this.durationHours = durationHours;
        }
    }
}
