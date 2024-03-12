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

    public void generateShifts(VBox vBox) {

        for (int i = 0; i < vBox.getChildren().size(); i++) {
            HBox currentHBox = (HBox) vBox.getChildren().get(i);

            TimePickerSpinner currentStart = (TimePickerSpinner) currentHBox.getChildren().get(2);
            TimePickerSpinner currentEnd = (TimePickerSpinner) currentHBox.getChildren().get(4);
            LocalTime startTime = LocalTime.parse(currentStart.getValue());
            LocalTime endTime = LocalTime.parse(currentEnd.getValue());

            shiftsList.add(new DayDateTime(localDate.plusDays(i), startTime, endTime));
        }

        generateText();
    }

    public void generateText() {
        StringBuilder subjectBuilder = new StringBuilder("Shifts Availability ");
        StringBuilder shiftsBuilder = new StringBuilder("Work around 32 hours a month.\n\n");

        // date formats
        DateTimeFormatter formatterDayMonth = DateTimeFormatter.ofPattern("dd/MM");
        DateTimeFormatter formatterDay = DateTimeFormatter.ofPattern("dd");

        LocalDate currentDate = shiftsList.get(shiftsList.size() -1).getDate();

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
