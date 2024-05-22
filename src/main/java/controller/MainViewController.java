package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Images;
import model.TouristAttraction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javafx.event.EventHandler;

import javax.xml.bind.annotation.XmlRootElement;
import java.text.NumberFormat;
import java.util.Locale;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

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
    private TextField textSearch;

    @FXML
    private VBox vBoxSearch;

    @FXML
    private Label textLocation;

    @FXML
    private Label textPlace;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Label textContent;

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
    public void handleMenuTicketAction(ActionEvent event) throws IOException {

    }

    private void openView(String fxmlPath, ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource(fxmlPath)));
        stage.setScene(scene);
        stage.show();
    }

    //CREATE FILE XML FOR SAVE DATA
    @XmlRootElement
    public static class AttractionData {
        private String ten;
        private String diaChi;
        private String moTa;
        private String giaVe;

        public String getTen() {
            return ten;
        }

        public void setTen(String ten) {
            this.ten = ten;
        }

        public String getMoTa() {
            return moTa;
        }

        public void setMoTa(String moTa) {
            this.moTa = moTa;
        }

        public String getGiaVe() {
            return giaVe;
        }

        public void setGiaVe(String giaVe) {
            this.giaVe = giaVe;
        }

        public String getDiaChi() {
            return diaChi;
        }

        public void setDiaChi(String diaChi) {
            this.diaChi = diaChi;
        }

    }
    //SEARCH BUTTON
    @FXML
    public void handleSearchAction(ActionEvent event) {
        try{
            String keyword = textSearch.getText();

            // Create a Hibernate SessionFactory and Session
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession();

            // Begin a transaction
            session.beginTransaction();

            // Create a Hibernate Query
            Query<TouristAttraction> query = session.createQuery("FROM TouristAttraction WHERE ten LIKE :keyword", TouristAttraction.class);
            query.setParameter("keyword", "%" + keyword + "%");

            // Execute the query and get the result list
            List<TouristAttraction> attractions = query.getResultList();

            // Commit the transaction and close the session
            session.getTransaction().commit();
            session.close();

            // Clear the vBoxSearch
            vBoxSearch.getChildren().clear();

            // For each result, create a new button, set its text to the result, add a style class, and add it to the vBoxSearch
            for (TouristAttraction attraction : attractions) {
                Button resultButton = new Button();
                resultButton.setText(attraction.getTen());
                resultButton.getStyleClass().add("resultButton");

                // Add an action to the result button
                // Add an action to the result button
                resultButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        // Handle the button click here
                        System.out.println("Button clicked: " + resultButton.getText());

                        // Create a Hibernate SessionFactory and Session
                        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
                        Session session = sessionFactory.openSession();

                        // Begin a transaction
                        session.beginTransaction();

                        // Create a Hibernate Query
                        Query<TouristAttraction> query = session.createQuery("FROM TouristAttraction WHERE ten = :ten", TouristAttraction.class);
                        query.setParameter("ten", resultButton.getText());

                        // Execute the query and get the result
                        TouristAttraction attraction = query.getSingleResult();

                        // Commit the transaction and close the session
                        session.getTransaction().commit();
                        session.close();

                        // Parse giaVe from BigDecimal to int
                        int giaVe = attraction.getGiaVe().intValueExact();

                        // Create a NumberFormat instance for the default locale
                        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());

                        // Set the group size to 3
                        numberFormat.setGroupingUsed(true);
                        numberFormat.setMaximumFractionDigits(0);

                        // Format the number
                        String formattedGiaVe = numberFormat.format(giaVe);

                        // Set the text for btPrice
                        btPrice.setText(formattedGiaVe + " VND");


                        // Set the text fields
                        textPlace.setText(attraction.getTen());
                        textLocation.setText(attraction.getDiaChi());
                        textContent.setText(attraction.getMoTa());
//                        btPrice.setText(attraction.getGiaVe()+" VND");

                        AttractionData data = new AttractionData();
                        data.setTen(attraction.getTen());
                        data.setDiaChi(attraction.getDiaChi());
                        data.setMoTa(attraction.getMoTa());
                        data.setGiaVe(formattedGiaVe);

                        try{
                            JAXBContext jaxbContext = JAXBContext.newInstance(AttractionData.class);
                            Marshaller marshaller = jaxbContext.createMarshaller();
                            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

// Write to file
                            marshaller.marshal(data, new File("src/main/resources/data/attractionData.xml"));

                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        try{
                            // Get the image data
                            Images images = attraction.getImages();
                            byte[] imageData = images.getDuLieuAnh();

                            // Decode the image data
                            Image image = new Image(new ByteArrayInputStream(imageData));

                            // Set the background of the root
                            BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, true);
                            BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
                            Background background = new Background(backgroundImage);
                            mainPane.setBackground(background);

                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                });

                vBoxSearch.getChildren().add(resultButton);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void handleBookingAction(ActionEvent event) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(AttractionData.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            // Read from file
            AttractionData data = (AttractionData) unmarshaller.unmarshal(new File("src/main/resources/data/attractionData.xml"));

            // Print to console
            System.out.println("Ten: " + data.getTen());
            System.out.println("Dia Chi: " + data.getDiaChi());
            System.out.println("Mo Ta: " + data.getMoTa());
            System.out.println("Gia Ve: " + data.getGiaVe());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

