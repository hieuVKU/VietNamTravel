package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class FlightBookingsController {
    @FXML
    private Pane enterFlightBookingPane;

    @FXML
    private Pane PayPane;

    @FXML
    private TextField PersonNumber;

    @FXML
    private Button plusButt;

    @FXML
    private Button minusButt;

    @FXML
    private Button continueButt;

    @FXML
    private Button backButton;

    @FXML
    private Pane QRBankingPaneButton;

    @FXML
    private Pane CreditCardPaneButton2;

    @FXML
    private Pane CreditCardPane;

    @FXML
    private Pane QRBankingPane;


    private ButtonController bc = new ButtonController();

    @FXML
    private void initialize()
    {
        setupButtonActions();
    }

    private void setupButtonActions() {
//        plusButt.setOnMouseClicked(event -> addPassengerPane());
//        minusButt.setOnMouseClicked(event -> removePassengerPane());
        continueButt.setOnMouseClicked(event -> {
            enterFlightBookingPane.setVisible(false);
            PayPane.setVisible(true);
//            handleContinueButton();
        });
        backButton.setOnMouseClicked(event -> {
            enterFlightBookingPane.setVisible(true);
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

    @FXML
    private void PlusButton() {
        int currentValue = Integer.parseInt(PersonNumber.getText());
        PersonNumber.setText(String.valueOf(currentValue + 1));
//        updateTotalAmount();
    }

    @FXML
    private void MinusButton() {
        try {
            int currentValue = Integer.parseInt(PersonNumber.getText());
            if (currentValue > 1) {
                PersonNumber.setText(String.valueOf(currentValue - 1));
//                updateTotalAmount();
            } else {
                bc.showErrorAlert("ERROR", "Person number cannot be less than 1");
            }
        } catch (NumberFormatException e) {
            bc.showErrorAlert("ERROR", "Invalid number format");
        }
    }

}
