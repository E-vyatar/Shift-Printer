package org.example.shiftsprinter;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.shiftsprinter.modules.TimePickerSpinner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
            timePickersVBox.getChildren().addAll(generateHBoxers(sliderValue));
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

        timePickersVBox.getChildren().addAll(generateHBoxers(SLIDER_VALUE));
    }

    private HBox hBoxer(int offset) {
        TimePickerSpinner timePickerSpinner1 = new TimePickerSpinner(LocalTime.of(9,0));
        TimePickerSpinner timePickerSpinner2 = new TimePickerSpinner(LocalTime.of(22,0));

        Label dateLabel = new Label(datePicker.getValue().plusDays(offset).format(DateTimeFormatter.ofPattern("dd/MM")));
        Label startLabel = new Label("from:");
        Label endLabel = new Label("until:");
        dateLabel.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
        startLabel.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
        endLabel.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);

        HBox hBox = new HBox(
                dateLabel,
                startLabel,
                timePickerSpinner1,
                endLabel,
                timePickerSpinner2

        );
        hBox.setSpacing(15);
        hBox.setAlignment(Pos.CENTER);

        return hBox;
    }

    private List<HBox> generateHBoxers(int amount) {
        List<HBox> res = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            res.add(hBoxer(i));
        }

        return res;
    }
}