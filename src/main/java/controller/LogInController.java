package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class LogInController {

    @FXML
    private PasswordField passTF;

    @FXML
    private AnchorPane paneLogin;

    @FXML
    private AnchorPane paneAccount;

    @FXML
    private TextField PhoneNumTF;

    @FXML
    private Button btSubmit;

    @FXML
    private Hyperlink linkSignUp;

    private ButtonController bc = new ButtonController();

    public void openSignUpView(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/example/vietnamtravel/SignUpView.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleSubmit(ActionEvent event) throws IOException {
        String phoneNumber = PhoneNumTF.getText();
        String password = passTF.getText();

        if (phoneNumber.isEmpty() || password.isEmpty()) {
            bc.showErrorAlert("Error", "Phone number and password cannot be empty.");
            return;
        }

        if (isLoginValid(phoneNumber, password)) {
            bc.showInformationAlert("Success", "Login successful!");


//Open MainView when successfully login
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/vietnamtravel/MainView.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();

            MainViewController mainViewController = loader.getController();
            mainViewController.showAccountPane();
        } else {
            bc.showErrorAlert("Error", "Invalid phone number or password.");
        }
    }

    private static SessionFactory factory; // Tạo SessionFactory tĩnh

    public boolean isLoginValid(String phoneNumber, String password) {
        if (factory == null) {
            factory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        }

        try (Session session = factory.openSession()) { // Sử dụng try-with-resources để tự động đóng Session
            session.beginTransaction();

            Query<User> query = session.createQuery("FROM User u WHERE u.soDienThoai = :phoneNumber AND u.matKhau = :password", User.class);
            query.setParameter("phoneNumber", phoneNumber);
            query.setParameter("password", password);

            List<User> users = query.getResultList();

            session.getTransaction().commit();
            return !users.isEmpty();
        }
    }

}
