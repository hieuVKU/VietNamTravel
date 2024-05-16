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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainViewController {

    @FXML
    private Button btAccount;

    @FXML
    private Button btBooking;

    @FXML
    private Button btLocation;

    @FXML
    private Button btLogin;

    @FXML
    private Button btPrice;

    @FXML
    private Button btSearch;

    @FXML
    private Button btSignUp;

    @FXML
    private Button menuDes;

    @FXML
    private Button menuHome;

    @FXML
    private Button menuStay;

    @FXML
    private Button menuTicket;

    @FXML
    private AnchorPane paneAccount;

    @FXML
    private AnchorPane paneLogin;

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

    public void showAccountPane() {
        if (paneAccount != null) {
            paneAccount.setVisible(true);
            paneLogin.setVisible(false);
        }
    }

    //Controller MenuBar
    @FXML
    public void handleMenuHomeAction(ActionEvent event) throws IOException {
        openView("/com/example/vietnamtravel/MainView.fxml", event);
        menuHome.setStyle("-fx-background-color: #327da8;");
        menuDes.setStyle("");
        menuStay.setStyle("");
    }

    @FXML
    public void handleMenuDestiAction(ActionEvent event) throws IOException {
        openView("/com/example/vietnamtravel/DestinationView.fxml", event);
        menuHome.setStyle("");
        menuDes.setStyle("-fx-background-color: #327da8;");
        menuStay.setStyle("");
    }

    @FXML
    public void handleMenuStayAction(ActionEvent event) throws IOException {
        openView("/com/example/vietnamtravel/StayView.fxml", event);
        menuHome.setStyle("");
        menuDes.setStyle("");
        menuStay.setStyle("-fx-background-color: #327da8;");
    }

    @FXML
    public void handleMenuTicketAction(ActionEvent event) throws IOException{

    }

    private void openView(String fxmlPath, ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource(fxmlPath)));
        stage.setScene(scene);
        stage.show();
    }
}

