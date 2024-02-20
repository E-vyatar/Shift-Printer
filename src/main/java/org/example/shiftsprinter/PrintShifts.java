package org.example.shiftsprinter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class PrintShifts {

    private LocalDate localDate;

    public PrintShifts(LocalDate localDate) {
        this.localDate = localDate;
    }

    public String shiftPrinter() {
        StringBuilder subject = new StringBuilder("Shifts Availability ");
        StringBuilder shifts = new StringBuilder("Work around 32 hours a month.\n\n");

        int numOfDays = 7;

        // date formats
        DateTimeFormatter formatterDayMonthDayOfWeek = DateTimeFormatter.ofPattern("dd/MM EEEE");
        DateTimeFormatter formatterDayMonth = DateTimeFormatter.ofPattern("dd/MM");
        DateTimeFormatter formatterDay = DateTimeFormatter.ofPattern("dd");

        LocalDate currentDate = null;
        // Iterate through the next seven days and print the formatted date and day
        for (int i = 0; i < numOfDays; i++) {
            currentDate = localDate.plusDays(i);
            String currentDateFormatted = currentDate.format(formatterDayMonthDayOfWeek);

            shifts.append(currentDateFormatted).append(":\n");
        }

        // check if the whole week is in the same month
        String subjectStartDate;
        if (localDate.getMonth() == currentDate.getMonth()) {
            subjectStartDate = localDate.format(formatterDay);
        }
        else {
            subjectStartDate = localDate.format(formatterDayMonth);
        }

        subject.append(subjectStartDate);
        subject.append("-").append(currentDate.format(formatterDayMonth)).append("\n");

        return subject.append(shifts).toString();
    }

    // Helper method to get the day of the week as a string
    private static String getDayOfWeek(Calendar calendar) {
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        return days[dayOfWeek - 1];
    }
}
