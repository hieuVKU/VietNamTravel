module com.example.vietnamtravel {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.vietnamtravel to javafx.fxml;
    exports com.example.vietnamtravel;

    opens View to javafx.fxml;
    exports View;
}