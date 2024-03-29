package org.example.shiftsprinter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ShiftsPrinterApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ShiftsPrinterApplication.class.getResource("UI.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Shifts Printer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}