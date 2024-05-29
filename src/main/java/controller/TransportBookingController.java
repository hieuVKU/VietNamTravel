package controller;

import Util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TransportBookingController {
    @FXML
    private Pane EnterAttractionPane;

    @FXML
    private TextField goDateTF;

    @FXML
    private TextField TransportationTF;

    @FXML
    private TextField startTF;

    @FXML
    private TextField endTF;

    @FXML
    private Pane PayPane;

    @FXML
    private Button continueButt;

    @FXML
    private Button backButton;

    @FXML
    private Pane QRBankingPaneButton;

    @FXML
    private Pane QRBankingPane;

    @FXML
    private Pane CreditCardPane;

    @FXML
    private Pane CreditCardPaneButton2;

    @FXML
    private TextField PersonNumber;

    @FXML
    private Label FromToLabel;

    @FXML
    private Button plusButt;

    @FXML
    private Button minusButt;

    @FXML
    private Label totalMoney;

    @FXML
    private VBox passengerContainer;

    private Session session;

    private double totalAmount = 0.0;

    private ObservableList<Pane> passengerPanes;

    private ButtonController bc = new ButtonController();

    public TransportBookingController()
    {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }

    @FXML
    private void initialize() {
        passengerPanes = FXCollections.observableArrayList();

        setupButtonActions();
        plusButt.setOnMouseClicked(event -> {
            addPassengerPane();
            updateTotalAmount();
        });
        minusButt.setOnMouseClicked(event -> {
            removePassengerPane();
            updateTotalAmount();
        });
    }
    private Pane createPassengerPane(int passengerNumber) {
        Pane pane = new Pane();
        pane.setPrefSize(200, 150);
        pane.setStyle("-fx-border-color: black;");

        Label nameLabel = new Label("Name");
        nameLabel.relocate(8, 32); // Sử dụng phương thức relocate để đặt vị trí
        nameLabel.setFont(new Font("Berlin Sans FB", 16));

        TextField nameField = new TextField();
        nameField.relocate(8, 57); // Sử dụng phương thức relocate để đặt vị trí
        nameField.setPrefSize(185, 25);

        Label birthLabel = new Label("Birth");
        birthLabel.relocate(8, 92); // Sử dụng phương thức relocate để đặt vị trí
        birthLabel.setFont(new Font("Berlin Sans FB", 16));

        TextField birthField = new TextField();
        birthField.relocate(8, 117); // Sử dụng phương thức relocate để đặt vị trí
        birthField.setPrefSize(185, 25);

        Label passengerNumberLabel = new Label("Passenger " + passengerNumber);
        passengerNumberLabel.relocate(63, 5); // Sử dụng phương thức relocate để đặt vị trí
        passengerNumberLabel.setFont(new Font("Berlin Sans FB", 16));
        passengerNumberLabel.setTextFill(Color.web("#797272"));

        pane.getChildren().addAll(nameLabel, nameField, birthLabel, birthField, passengerNumberLabel);

        return pane;
    }



    private void setupButtonActions() {
        plusButt.setOnMouseClicked(event -> addPassengerPane());
        minusButt.setOnMouseClicked(event -> removePassengerPane());
        continueButt.setOnMouseClicked(event -> {
            EnterAttractionPane.setVisible(false);
            PayPane.setVisible(true);
        });
        backButton.setOnMouseClicked(event -> {
            EnterAttractionPane.setVisible(true);
            PayPane.setVisible(false);
        });
        QRBankingPaneButton.setOnMouseClicked(event -> {
            CreditCardPane.setVisible(false);
            QRBankingPane.setVisible(true);
        });
        CreditCardPaneButton2.setOnMouseClicked(event -> {
            QRBankingPane.setVisible(false);
            CreditCardPane.setVisible(true);
        });
    }

    public void setBookingDetails(String from, String to, LocalDate goDate, LocalTime startTime, LocalTime endTime, String transportation) {
        FromToLabel.setText(from + " to " + to);
        this.goDateTF.setText(String.valueOf(goDate));
        this.startTF.setText(String.valueOf(startTime));
        this.endTF.setText(String.valueOf(endTime));
        this.TransportationTF.setText(transportation);
    }

    @FXML
    private void PlusButton() {
        int currentValue = Integer.parseInt(PersonNumber.getText());
        PersonNumber.setText(String.valueOf(currentValue + 1));
    }

    @FXML
    private void MinusButton() {
        try {
            int currentValue = Integer.parseInt(PersonNumber.getText());
            if (currentValue > 1) {
                PersonNumber.setText(String.valueOf(currentValue - 1));
            } else {
                bc.showErrorAlert("ERROR", "Person number cannot be less than 1");
            }
        } catch (NumberFormatException e) {
            bc.showErrorAlert("ERROR", "Invalid number format");
        }
    }

    private void addPassengerPane() {
        Pane passengerPane = createPassengerPane(passengerPanes.size() + 1);
        passengerPanes.add(passengerPane);
        passengerContainer.getChildren().add(passengerPane);
    }

    private void removePassengerPane() {
        if (!passengerPanes.isEmpty()) {
            Pane removedPane = passengerPanes.remove(passengerPanes.size() - 1);
            passengerContainer.getChildren().remove(removedPane);
        }
    }
    private void updateTotalAmount() {
        int numberPassenger = Integer.parseInt(PersonNumber.getText());
        String vehicleType = TransportationTF.getText();
        LocalDate goDate = LocalDate.parse(goDateTF.getText());
        LocalTime startTime = LocalTime.parse(startTF.getText());
        LocalTime endTime = LocalTime.parse(endTF.getText());

        double farePerPassenger = getTicketPrice(vehicleType, goDate, startTime, endTime);
        totalAmount = numberPassenger * farePerPassenger;
        totalMoney.setText(totalAmount + " VND");
    }

    private double getTicketPrice(String vehicleType, LocalDate goDate, LocalTime startTime, LocalTime endTime) {
        double farePerPassenger = 0.0;
        try {
            // Chuyển đổi LocalTime sang java.sql.Time
//            java.sql.Time goTimeSQL = java.sql.Time.valueOf(startTime);
//            java.sql.Time returnTimeSQL = java.sql.Time.valueOf(endTime);

            // Sử dụng java.sql.Time trong câu truy vấn HQL
            String hql = "SELECT S.giaVe " +
                    "FROM Schedule S JOIN S.transportations T " +
                    "WHERE T.loaiPhuongTien = :vehicleType " +
                    "AND S.ngayKhoiHanh = :goDate " +
                    "AND S.gioKhoiHanh = :goTime " +
                    "AND S.gioDen = :returnTime";

            Query<Double> query = session.createQuery(hql, Double.class);
            query.setParameter("vehicleType", vehicleType);
            query.setParameter("goDate", goDate); // Sử dụng LocalDate
            query.setParameter("goTime", startTime); // Sử dụng java.sql.Time
            query.setParameter("returnTime", endTime); // Sử dụng java.sql.Time

            // Ghi log thông tin truy vấn và các tham số
            System.out.println("Query: " + query.getQueryString());
            System.out.println("Parameters:");
            System.out.println("  vehicleType: " + vehicleType + " (" + vehicleType.getClass().getName() + ")");
            System.out.println("  goDate: " + goDate + " (" + goDate.getClass().getName() + ")");
            System.out.println("  goTime: " + startTime + " (" + startTime.getClass().getName() + ")");
            System.out.println("  returnTime: " + endTime + " (" + endTime.getClass().getName() + ")");

            farePerPassenger = query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return farePerPassenger;
    }

}
