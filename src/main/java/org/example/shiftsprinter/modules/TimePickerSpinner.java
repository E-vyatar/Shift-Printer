package org.example.shiftsprinter.modules;

import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimePickerSpinner extends Spinner<String> {

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public TimePickerSpinner(LocalTime localTime) {
        // Call superclass constructor
        super();

        // Define the value factory for the Spinner
        SpinnerValueFactory<String> valueFactory = new SpinnerValueFactory<>() {
            {
                // Initialize with the current time
                setValue(localTime.format(TIME_FORMATTER));
            }

            // Define how to increment and decrement the value
            @Override
            public void decrement(int steps) {
                setValue(LocalTime.parse(getValue(), TIME_FORMATTER).minusMinutes(steps).format(TIME_FORMATTER));
            }

            @Override
            public void increment(int steps) {
                setValue(LocalTime.parse(getValue(), TIME_FORMATTER).plusMinutes(steps).format(TIME_FORMATTER));
            }
        };

        // Set the value factory to the Spinner
        setValueFactory(valueFactory);
//        getEditor().setPrefColumnCount(5);
//        setEditable(true);

        // Add event handler for key pressed to validate input
//        getEditor().addEventFilter(KeyEvent.KEY_TYPED, event -> {
////            if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.RIGHT) {
////                getEditor().selectBackward();
////            }
//            String character = event.getCharacter();
//            String currentText = getEditor().getText();
//            int caretPosition = getEditor().getCaretPosition();
//            getEditor().selectBackward();
//            String newText = currentText.substring(0, caretPosition) + character + currentText.substring(caretPosition);
//
//            if (!isValidTime(currentText)) {
//                event.consume();
//            }
//        });
    }

//    private boolean isValidTime(String input) {
//        String pattern = "([01][0-9]|2[0-3]):([0-5][0-9])";
//        return input.matches(pattern);
////        try {
////            LocalTime.parse(input, TIME_FORMATTER);
////            return true;
////        } catch (Exception e) {
////            return false;
////        }
//    }
}