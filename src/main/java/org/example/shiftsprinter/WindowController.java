package org.example.shiftsprinter;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
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
    private Button generateButton;

    @FXML
    private Slider numDaysSlider;

    @FXML
    private TextField numDaysTextField;

    @FXML
    private TextField subjectTextField;

    @FXML
    private TextArea shiftsTextArea;

    @FXML
    private VBox timePickersVBox;


    @FXML
    private void initialize() {
        datePicker.setValue(LocalDate.now());

        generateButton.setOnAction(event -> {
            InputProcessor inputProcessor = new InputProcessor(datePicker.getValue());
            inputProcessor.generateShifts(timePickersVBox);
            subjectTextField.setText(inputProcessor.getSubject());
            shiftsTextArea.setText(inputProcessor.getShifts());
        });

        // slider
        // set initial value and boundaries
        int SLIDER_MIN = 1;
        int SLIDER_MAX = 30;
        int SLIDER_VALUE = 7;
        numDaysSlider.setMin(SLIDER_MIN);
        numDaysSlider.setMax(SLIDER_MAX);
        numDaysSlider.setValue(SLIDER_VALUE);
        // update text field when slider value change
        numDaysSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            numDaysTextField.setText(String.valueOf(newValue.intValue()));
        });
        // update number of time pickers when slider value change
        numDaysSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            int sliderValue = (int) numDaysSlider.getValue();
            timePickersVBox.getChildren().clear();
            timePickersVBox.getChildren().addAll(addHBoxers(sliderValue));
        });

        // text field
        // set initial value
        numDaysTextField.setText(String.valueOf(SLIDER_VALUE));
        // define allowed input
        TextFormatter<String> textFormatter = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d+")) {
                int value = Integer.parseInt(newText);
                if (SLIDER_MIN <= value && value <= SLIDER_MAX){
                    return change;
                }
            }
            return null;
        });
        numDaysTextField.setTextFormatter(textFormatter);
        // update slider when text field value change
        numDaysTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            numDaysSlider.setValue(Double.parseDouble(newValue));
        });

        timePickersVBox.getChildren().addAll(addHBoxers(SLIDER_VALUE));
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