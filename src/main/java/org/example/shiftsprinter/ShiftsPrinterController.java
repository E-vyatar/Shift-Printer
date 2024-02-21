package org.example.shiftsprinter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class ShiftsPrinterController {
    @FXML
    private DatePicker datePicker;

    @FXML
    private Button printButton;

    @FXML
    private TextField subjectTextField;

    @FXML
    private TextArea shiftsTextArea;



    @FXML
    private void initialize() {
        // Initialization code
        datePicker.setValue(LocalDate.now());
    }

    @FXML
    private void handlePrintButtonClick() {
        PrintShifts printShifts = new PrintShifts(datePicker.getValue());
        printShifts.generateText();
        subjectTextField.setText(printShifts.getSubject());
        shiftsTextArea.setText(printShifts.getShifts());
    }
}