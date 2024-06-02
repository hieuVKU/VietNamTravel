package controller.AbstractController;

import controller.LogInController;
import controller.MainViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

public abstract class MenuController {
    private Map<String, FXMLLoader> loaders = new HashMap<>();

    protected void openView(String fxmlPath, ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader loader = loaders.get(fxmlPath);
        if (loader == null) {
            loader = new FXMLLoader(getClass().getResource(fxmlPath));
            loaders.put(fxmlPath, loader);
        }

        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

        String hoTen = LogInController.UserSession.getHoTen();
        AccountTextController controller = loader.getController();
        controller.setBtAccountText(hoTen);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleMenuHomeAction(ActionEvent event) throws IOException {
        openView("/com/example/vietnamtravel/MainView.fxml", event);
    }

    @FXML
    public void handleMenuDestiAction(ActionEvent event) throws IOException {
        openView("/com/example/vietnamtravel/DestinationView.fxml", event);
    }

    @FXML
    public void handleMenuStayAction(ActionEvent event) throws IOException {
        openView("/com/example/vietnamtravel/StayView.fxml", event);
    }

    @FXML
    public void handleMenuTicketAction(ActionEvent event) throws IOException{
        // Implement this method in the child classes
    }

}
