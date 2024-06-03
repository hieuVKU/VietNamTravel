package com.example.vietnamtravel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import Util.HibernateUtil;

import java.io.IOException;

public class Test extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainView.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LogIn.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TourBookingView.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminAttractions.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminDestinations.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HotelAdministrator.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DestinationView.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SignUpView.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StayView.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MyTicketView.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StayBookingsView.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 1350, 730);
        stage.setTitle("Vietnam Travel");

        // Thêm logo cho cửa sổ ứng dụng
        Image logo = new Image(getClass().getResourceAsStream("/img/MainLogo.png"));
        stage.getIcons().add(logo);

        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest((WindowEvent event) -> {
            // Gọi shutdown() của HibernateUtil khi ứng dụng kết thúc
            HibernateUtil.shutdown();
        });
    }

    public static void main(String[] args) {
        launch();
    }
}