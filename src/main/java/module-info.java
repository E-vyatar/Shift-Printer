module org.example.shiftsprinter {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens org.example.shiftsprinter to javafx.fxml;
    exports org.example.shiftsprinter;
}