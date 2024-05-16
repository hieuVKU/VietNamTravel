package com.example.vietnamtravel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Test extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainView.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LogIn.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Destination.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SignUpView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1350, 730);
        stage.setTitle("Vietnam Travel");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}