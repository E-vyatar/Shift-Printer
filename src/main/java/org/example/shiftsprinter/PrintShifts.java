package org.example.shiftsprinter;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PrintShifts {

    private LocalDate localDate;
    private String subject;
    private String shifts;
    private List<DayDateTime> shiftsList;


    public PrintShifts(LocalDate localDate) {
        this.localDate = localDate;
        shiftsList = new ArrayList<>();
    }

    public void generateText() {
        StringBuilder subjectBuilder = new StringBuilder("Shifts Availability ");
        StringBuilder shiftsBuilder = new StringBuilder("Work around 32 hours a month.\n\n");

        int numOfDays = 7;

        // date formats
        DateTimeFormatter formatterDayMonthDayOfWeek = DateTimeFormatter.ofPattern("dd/MM EEEE");
        DateTimeFormatter formatterDayMonth = DateTimeFormatter.ofPattern("dd/MM");
        DateTimeFormatter formatterDay = DateTimeFormatter.ofPattern("dd");

        LocalDate currentDate = shiftsList.get(shiftsList.size() -1).getDate();
        // Iterate through the next seven days and print the formatted date and day
//        for (int i = 0; i < numOfDays; i++) {
//            currentDate = localDate.plusDays(i);
//            String currentDateFormatted = currentDate.format(formatterDayMonthDayOfWeek);
//
//            shiftsBuilder.append(currentDateFormatted).append(":\n");
//        }
        for (DayDateTime dayDateTime : shiftsList) {
            shiftsBuilder.append(dayDateTime.toString()).append("\n");
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
    }

    public void generateShifts(VBox vBox) {
        LocalDate currentDate = null;

        for (int i = 0; i < 7; i++) {
            currentDate = localDate.plusDays(i);
            HBox currentHBox = (HBox) vBox.getChildren().get(i);

            TextField currentStart = (TextField) currentHBox.getChildren().get(1);
            TextField currentEnd = (TextField) currentHBox.getChildren().get(3);
            LocalTime startTime = LocalTime.parse(currentStart.getText());
            LocalTime endTime = LocalTime.parse(currentEnd.getText());

            shiftsList.add(new DayDateTime(currentDate, startTime, endTime));
        }

        generateText();
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

    public List<DayDateTime> getShiftsList() {
        return shiftsList;
    }
}
