package org.example.shiftsprinter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DayDateTime {

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private DateTimeFormatter formatterDayMonthDayOfWeek = DateTimeFormatter.ofPattern("dd/MM EEEE");
    private DateTimeFormatter formatterHourMinute = DateTimeFormatter.ofPattern("HH:mm");

    public DayDateTime(LocalDate date) {
        this.date = date;
        this.startTime = LocalTime.MIN;
        this.endTime = LocalTime.MAX;
    }

    public DayDateTime(LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return date.format(formatterDayMonthDayOfWeek) + ": "
                + startTime.format(formatterHourMinute) + "-"
                + endTime.format(formatterHourMinute);
    }
}
