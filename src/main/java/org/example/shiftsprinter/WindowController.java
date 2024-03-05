package org.example.shiftsprinter;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.shiftsprinter.modules.TimePickerSpinner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class WindowController {
    @FXML
    private DatePicker datePicker;

    @FXML
    private Slider numDaysSlider;

    @FXML
    private TextField sliderValueTextField;

    @FXML
    private TextField subjectTextField;

    @FXML
    private TextArea shiftsTextArea;

    @FXML
    private VBox timePickersVBox;


    @FXML
    private void initialize() {
        datePicker.setValue(LocalDate.now());

        int SLIDER_MIN = 1;
        int SLIDER_MAX = 30;
        int SLIDER_VALUE = 7;
        numDaysSlider.setMin(SLIDER_MIN);
        numDaysSlider.setMax(SLIDER_MAX);
        numDaysSlider.setValue(SLIDER_VALUE);
        // update label when slider value change
        numDaysSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            sliderValueTextField.setText(String.valueOf(newValue.intValue()));
        });

        sliderValueTextField.setText(String.valueOf(SLIDER_VALUE));

        // update slider when text field value change
        sliderValueTextField.setOnKeyTyped(event -> {
            String eventText = event.getCharacter();
            String textFieldValue = sliderValueTextField.getText();
            if (!eventText.matches("\\d")) {
                event.consume();
            } else {
                textFieldValue += event.getCharacter();
                int intValue = Integer.parseInt(textFieldValue);
                if (intValue < 1 || intValue > 30) {
                    event.consume();
                } else {
                    numDaysSlider.setValue(intValue);
                }
            }
        });

        numDaysSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            var x = (int) numDaysSlider.getValue();
            timePickersVBox.getChildren().clear();
            timePickersVBox.getChildren().addAll(addHBoxers(x));
        });

        timePickersVBox.getChildren().addAll(addHBoxers(SLIDER_VALUE));
    }

    @FXML
    private void handlePrintButtonClick() {
        InputProcessor inputProcessor = new InputProcessor(datePicker.getValue());
        inputProcessor.generateShifts(timePickersVBox);
        subjectTextField.setText(inputProcessor.getSubject());
        shiftsTextArea.setText(inputProcessor.getShifts());
    }

    private HBox hBoxer() {
        TimePickerSpinner tf1 = new TimePickerSpinner(LocalTime.of(9,0));
        TimePickerSpinner tf2 = new TimePickerSpinner(LocalTime.of(22,0));
        HBox hBox = new HBox(
                new Label("from:"),
                tf1,
                new Label("until:"),
                tf2

        );
        hBox.setSpacing(15);
        hBox.setAlignment(Pos.CENTER);

        return hBox;
    }

    private List<HBox> addHBoxers(int amount) {
        List<HBox> res = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            res.add(hBoxer());
        }

        return res;
    }
}