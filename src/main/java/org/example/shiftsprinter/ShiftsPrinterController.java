package org.example.shiftsprinter;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @FXML
    private VBox timePickers;

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

        timePickers.getChildren().addAll(addHBoxers(7));
    }

    @FXML
    private void handlePrintButtonClick() {
        PrintShifts printShifts = new PrintShifts(datePicker.getValue());
        printShifts.generateShifts(timePickers);
        subjectTextField.setText(printShifts.getSubject());
        shiftsTextArea.setText(printShifts.getShifts());
    }

    private HBox hBoxer() {
        TextField tf1 = new TextField("09:00");
        tf1.setPrefColumnCount(5);
        TextField tf2 = new TextField("22:00");
        tf2.setPrefColumnCount(5);
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