package org.example.shiftsprinter;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.shiftsprinter.modules.DayDateTime;
import org.example.shiftsprinter.modules.TimePickerSpinner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class InputProcessor {

    private LocalDate localDate;
    private String subject;
    private String shifts;
    private final List<DayDateTime> shiftsList;


    public InputProcessor(LocalDate localDate) {
        this.localDate = localDate;
        shiftsList = new ArrayList<>();
    }

    public void generateText() {
        StringBuilder subjectBuilder = new StringBuilder("Shifts Availability ");
        StringBuilder shiftsBuilder = new StringBuilder("Work around 32 hours a month.\n\n");

//        int numOfDays = 7;

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
        LocalDate currentDate;

        for (int i = 0; i < vBox.getChildren().size(); i++) {
            currentDate = localDate.plusDays(i);
            HBox currentHBox = (HBox) vBox.getChildren().get(i);

            TimePickerSpinner currentStart = (TimePickerSpinner) currentHBox.getChildren().get(1);
            TimePickerSpinner currentEnd = (TimePickerSpinner) currentHBox.getChildren().get(3);
            LocalTime startTime = LocalTime.parse(currentStart.getValue());
            LocalTime endTime = LocalTime.parse(currentEnd.getValue());

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
