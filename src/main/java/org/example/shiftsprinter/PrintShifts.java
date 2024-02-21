package org.example.shiftsprinter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PrintShifts {

    private LocalDate localDate;
    private String subject;
    private String shifts;

    public PrintShifts(LocalDate localDate) {
        this.localDate = localDate;
    }

    public void generateText() {
        StringBuilder subjectBuilder = new StringBuilder("Shifts Availability ");
        StringBuilder shiftsBuilder = new StringBuilder("Work around 32 hours a month.\n\n");

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

            shiftsBuilder.append(currentDateFormatted).append(":\n");
        }

        // check if the whole week is in the same month
        String subjectStartDate;
        if (localDate.getMonth() == currentDate.getMonth()) {
            subjectStartDate = localDate.format(formatterDay);
        }
        else {
            subjectStartDate = localDate.format(formatterDayMonth);
        }

        subjectBuilder.append(subjectStartDate);
        subjectBuilder.append("-").append(currentDate.format(formatterDayMonth)).append("\n");

        subject = subjectBuilder.toString();
        shifts = shiftsBuilder.toString();

//        return subjectBuilder.append(shiftsBuilder).toString();
    }

    public String getSubject() {
        return subject;
    }

    public String getShifts() {
        return shifts;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
}
