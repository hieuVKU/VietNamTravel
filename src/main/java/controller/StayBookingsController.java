package controller;

import Util.HibernateUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import Util.HibernateUtil;
import model.StayBookings;


import controller.LogInController.UserSession;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class StayBookingsController {

    @FXML
    private Button btSub;

    @FXML
    private TextField cccdTF;

    @FXML
    private DatePicker ngayDiDate;

    @FXML
    private DatePicker ngayVeDate;

    @FXML
    private TextField soLuongPhongTF;

    @FXML
    private Label textDC;

    @FXML
    private Label textDoRongPhong;

    @FXML
    private Label textTenKhachSan;

    @FXML
    private Label textTongTien;

    @FXML
    private AnchorPane rootPane;

    public void setBackgroundImage(Image image) {
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        rootPane.setBackground(new Background(backgroundImage));
    }


    public void setTextTenKhachSan(String tenKhachSan) {
        textTenKhachSan.setText(tenKhachSan);
    }

    public void setTextDC(String diaChi) {
        textDC.setText(diaChi);
    }

    public void setTextDoRongPhong(String doRongPhong) {
        textDoRongPhong.setText(doRongPhong);
    }

    @FXML
    public void handleBtSubAction(ActionEvent event) {

        ButtonController buttonController = new ButtonController();
        if (cccdTF.getText().isEmpty() || soLuongPhongTF.getText().isEmpty() || ngayDiDate.getValue() == null || ngayVeDate.getValue() == null) {

            buttonController.showErrorAlert("Null","Please fill in all fields.");
            return;
        }


        if (!cccdTF.getText().matches("\\d+")) {
            buttonController.showErrorAlert("Invalid Input","CCCD must be a number.");
            return;
        }

        if (!soLuongPhongTF.getText().matches("\\d+")) {
            buttonController.showErrorAlert("Invalid Input","Please enter a valid number for the number of rooms.");
            return;
        }

        // Get LocalDate from DatePicker
        LocalDate ngayDi = ngayDiDate.getValue();
        LocalDate ngayVe = ngayVeDate.getValue();

        // Define formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Convert LocalDate to String with defined format
        String formattedNgayDi = ngayDi.format(formatter);
        String formattedNgayVe = ngayVe.format(formatter);

        if (ngayDi.isAfter(ngayVe)) {
            buttonController.showErrorAlert("Invalid Date","Check-in date must be before check-out date.");
            return;
        }

        try{



        int soLuongPhong = Integer.parseInt(soLuongPhongTF.getText());
        BigDecimal giaPhong = StayController.BookingData.getGiaPhong();
        long soNgay = ChronoUnit.DAYS.between(ngayDi, ngayVe);

        BigDecimal tongTien = giaPhong.multiply(BigDecimal.valueOf(soLuongPhong * soNgay));
        textTongTien.setText(tongTien.toString());


        // Save
        StayBookings stayBookings = new StayBookings();

        // Get userID from UserSession
        Integer userID = UserSession.getUserID();

        // Get User object from database using userID
        Session session = HibernateUtil.getSessionFactory().openSession();
        model.User user = session.get(model.User.class, userID);

        // Set User object to StayBookings object
        stayBookings.setUserID(user);

        // Get accommodationsId from BookingData
        Integer accommodationsId = StayController.BookingData.getAccommodationsId();

        // Get Accommodation object from database using accommodationsId
        model.Accommodation accommodation = session.get(model.Accommodation.class, accommodationsId);

        // Set Accommodation object to StayBookings object
        stayBookings.setAccommodationID(accommodation);


        stayBookings.setCccd(cccdTF.getText());
        stayBookings.setSoLuongPhong(Integer.parseInt(soLuongPhongTF.getText()));
        stayBookings.setTongGiaPhong(tongTien.doubleValue());

        // Convert String to java.sql.Date
        Date sqlNgayDi = Date.valueOf(formattedNgayDi);
        Date sqlNgayVe = Date.valueOf(formattedNgayVe);

        stayBookings.setNgayDi(sqlNgayDi);
        stayBookings.setNgayVe(sqlNgayVe);

        //SAVE DATA
            try {
                // Start a new transaction
                Transaction transaction = session.beginTransaction();

                // Save the StayBookings object
                session.save(stayBookings);

                // Commit the transaction
                transaction.commit();
            } catch (Exception e) {
                if (session.getTransaction() != null) {
                    session.getTransaction().rollback();
                }
                e.printStackTrace();
            }
        //END SAVE METHOD


        buttonController.showInformationAlert("Success","Booking successful!");
        }
        catch (Exception e){
            e.printStackTrace();
            buttonController.showErrorAlert("Error","Something Wrong:(");
        }
    }


}
