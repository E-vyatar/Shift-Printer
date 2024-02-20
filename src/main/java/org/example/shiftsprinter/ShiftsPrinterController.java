package org.example.shiftsprinter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.time.LocalDate;

public class ShiftsPrinterController {
    @FXML
    private TextArea textArea;

    @FXML
    private Button printButton;

    @FXML
    private DatePicker datePicker;

    @FXML
    private void initialize() {
        // Initialization code
        datePicker.setValue(LocalDate.now());
    }

    @FXML
    private void handlePrintButtonClick() {
        String text = new PrintShifts(datePicker.getValue()).shiftPrinter();
        // Add your existing code here to handle date parsing and printing shifts
        // You can use outputLabel.setText() to display the output
        textArea.setText(text);
    }
}