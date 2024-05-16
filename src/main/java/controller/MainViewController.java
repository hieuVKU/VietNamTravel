package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class MainViewController {



    @FXML
    private Button btBooking;

    @FXML
    private Button btLocation;

    @FXML
    private Button btLogin;

    @FXML
    private Button btSearch;

    @FXML
    private Button btSignUp;

    @FXML
    private Menu menuDesti;

    @FXML
    private Menu menuHome;

    @FXML
    private Menu menuHotel;

    @FXML
    private Menu menuTicket;

    @FXML
    public void openSignUpView(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/example/vietnamtravel/SignUpView.fxml")));
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void openLoginView(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/example/vietnamtravel/Login.fxml")));
        stage.setScene(scene);
        stage.show();
    }
}

