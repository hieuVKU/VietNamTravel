package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MyTicketController {

    @FXML
    private Button btAccount;

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
    private VBox vBoxTicket;

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
    public void handleMenuTicketAction(ActionEvent event) throws IOException {

    }

    private void openView(String fxmlPath, ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource(fxmlPath)));
        stage.setScene(scene);
        stage.show();
    }


}
