package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LogInController {

    @FXML
    private TextField PhoneNumTF;

    @FXML
    private Button btSubmit;

    @FXML
    private Hyperlink linkSignUp;

    @FXML
    private PasswordField passTF;

    public void openSignUpView(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/example/vietnamtravel/SignUpView.fxml")));
        stage.setScene(scene);
        stage.show();
    }

}
