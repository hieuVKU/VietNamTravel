package controller;

import Util.HibernateUtil;
import controller.AbstractController.AccountTextController;
import controller.AbstractController.AdminController;
import controller.AbstractController.MenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import model.Images;
import model.TouristAttraction;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javafx.stage.FileChooser;
import model.Images;
import model.TouristAttraction;
import org.hibernate.Session;
import org.hibernate.Transaction;
import Util.HibernateUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.*;

public class AdminAttractions extends AdminController implements AccountTextController {

    @FXML
    private Button btAccount;

    @FXML
    private Button btSave;

    @FXML
    private ImageView imgViewData;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Button menuDes;

    @FXML
    private Button menuHome;

    @FXML
    private Button menuStay;

    @FXML
    private Button menuTicket;

    @FXML
    private TextArea textContent;

    @FXML
    private TextField textLocation;

    @FXML
    private TextField textPlace;

    @FXML
    private TextField textPrice;

    //Controller MenuBar
    @Override
    public void handleMenuDestiAction(ActionEvent event) throws IOException {

        super.handleMenuDestiAction(event);
    }

    @Override
    public void handleMenuHomeAction(ActionEvent event) throws IOException {

        super.handleMenuHomeAction(event);
    }

    @Override
    public void handleMenuStayAction(ActionEvent event) throws IOException {

        super.handleMenuStayAction(event);
    }

    @Override
    public void handleMenuTicketAction(ActionEvent event) throws IOException {
        super.handleMenuTicketAction(event);
    }

    @Override
    public void handleBtAccountAction(ActionEvent event) throws IOException {
        super.handleBtAccountAction(event);
    }
    //setText ACCOUNT BUTTON
    public void setBtAccountText(String hoTen) {
        btAccount.setText(hoTen);
        btAccount.setMinWidth(Button.USE_PREF_SIZE);
        btAccount.setMaxWidth(Double.MAX_VALUE);
    }
    @FXML
    void handleBtSaveAction(ActionEvent event) {
        String moTa = textContent.getText();
        String diaChi = textLocation.getText();
        String ten = textPlace.getText();
        BigDecimal giaVe = new BigDecimal(textPrice.getText());

        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(mainPane.getScene().getWindow());
        if (file != null) {
            try {

                // Kiểm tra nếu tệp không tồn tại hoặc không đọc được
                if (!file.exists() || !file.canRead()) {
                    showAlert("Error", "The image file cannot be accessed. Make sure the file exists and is readable.");
                    return;
                }else {
                    String filePath = file.getAbsolutePath();
                    System.out.println("File path: " + filePath);
                    String extension = getFileExtension(file);
                    BufferedImage bufferedImage = ImageIO.read(file);

                    // Kiểm tra nếu tệp ảnh không hợp lệ
                    if (bufferedImage == null) {
                        showAlert("Error", "Unable to read image file. Make sure the file is a valid image.");
                        return;
                    }else{
                        ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
                        ImageIO.write(bufferedImage, extension, byteOutput);
                        byte[] imageData = byteOutput.toByteArray();

                        Images image = new Images();
                        image.setTenFile(file.getName());
                        image.setLoaiFile(extension);
                        image.setDuLieuAnh(imageData);

                        TouristAttraction attraction = new TouristAttraction();
                        attraction.setMoTa(moTa);
                        attraction.setDiaChi(diaChi);
                        attraction.setTen(ten);
                        attraction.setGiaVe(giaVe);
                        attraction.setImages(image);

                        Session session = HibernateUtil.getSessionFactory().openSession();
                        Transaction transaction = session.beginTransaction();
                        session.save(image);
                        session.save(attraction);
                        transaction.commit();
//                        session.close();

                        imgViewData.setPreserveRatio(true);
                        imgViewData.setFitWidth(Region.USE_PREF_SIZE);
                        imgViewData.setFitHeight(Region.USE_PREF_SIZE);
                        imgViewData.setImage(new Image(file.toURI().toString()));

//                imgViewData.setImage(new Image(file.toURI().toString()));

                        // Show a success message
                        showAlert("Success", "Data has been saved successfully.");

                        // Clear the input fields
                        textContent.clear();
                        textLocation.clear();
                        textPlace.clear();
                        textPrice.clear();
                        imgViewData.setImage(null);
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            showAlert("Error", "File is not a valid image.");
        }
    }

    private String getFileExtension(File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
