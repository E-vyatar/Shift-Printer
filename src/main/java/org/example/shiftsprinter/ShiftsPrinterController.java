package org.example.shiftsprinter;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class ShiftsPrinterController {
    @FXML
    private DatePicker datePicker;

    @FXML
    private Button printButton;

    @FXML
    private Slider numDaysChooser;

    @FXML
    private Label sliderValue;

    @FXML
    private TextField subjectTextField;

    @FXML
    private TextArea shiftsTextArea;

    private final int SLIDER_MIN = 1;
    private final int SLIDER_MAX = 30;
    private final int SLIDER_VALUE = 7;



    @FXML
    private void initialize() {
        datePicker.setValue(LocalDate.now());
        sliderValue.setText(String.valueOf(numDaysChooser.getValue()));

        // update label when slider value change
        numDaysChooser.valueProperty().addListener((observable, oldValue, newValue) -> {
            sliderValue.setText(String.valueOf(newValue.intValue()));
        });

        // update slider when label value change
        sliderValue.setOnKeyTyped(event -> {
            String eventText = event.getCharacter();
            String labelValue = sliderValue.getText();
            if (!eventText.matches("\\d")) {
                event.consume();
            } else {
                labelValue += event.getCharacter();
                int intValue = Integer.parseInt(labelValue);
                if (intValue < 1 || intValue > 30) {
                    event.consume();
                } else {
                    numDaysChooser.setValue(intValue);
                }
            }
        });

        numDaysChooser.setMin(SLIDER_MIN);
        numDaysChooser.setMax(SLIDER_MAX);
        numDaysChooser.setValue(SLIDER_VALUE);
    }

    @FXML
    private void handlePrintButtonClick() {
        PrintShifts printShifts = new PrintShifts(datePicker.getValue());
        printShifts.generateText();
        subjectTextField.setText(printShifts.getSubject());
        shiftsTextArea.setText(printShifts.getShifts());
    }
}