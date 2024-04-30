package View;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class MainView extends Application {

    private Menu menuMyTicket;
    private Menu menuHome;
    private Menu menuDestinations;
    private Menu menuHotel;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Tạo background hình ảnh
        Image backgroundImage = new Image("file:./src/main/java/img/HaLongBay.jpg");
        BackgroundSize backgroundSize = new BackgroundSize(1200, 600, true, true, true, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background backgroundStyle = new Background(background);

//        colorPane.setStyle("-fx-background-color: rgba(145,137,107,0.79);");

        // Tạo MenuBar
        MenuBar menuBar = new MenuBar();

// Tạo các Menu và thêm chúng vào MenuBar
        menuHome = new Menu("Home");
        MenuItem homeItem = new MenuItem("Home Item");
        homeItem.getStyleClass().add("menu-item");
        menuHome.getItems().add(homeItem);

        menuDestinations = new Menu("Destinations");
        MenuItem destinationsItem = new MenuItem("Destinations Item");
        destinationsItem.getStyleClass().add("menu-item");
        menuDestinations.getItems().add(destinationsItem);

        menuHotel = new Menu("Hotel");
        MenuItem hotelItem = new MenuItem("Hotel Item");
        hotelItem.getStyleClass().add("menu-item");
        menuHotel.getItems().add(hotelItem);

        menuMyTicket = new Menu("MyTicket");
        MenuItem myTicketItem = new MenuItem("MyTicket Item");
        myTicketItem.setStyle("-fx-text-fill: #263e2a;"); // Thay màu chữ
        menuMyTicket.getItems().add(myTicketItem);

        menuBar.getMenus().addAll(menuHome, menuDestinations, menuHotel, menuMyTicket);

        //set cho menuBar style
        menuBar.getStyleClass().add("custom-menuBar");

// Tạo hai nút "Sign In" và "Login"
        Button signInButton = new Button("Sign In");
        signInButton.getStyleClass().add("sign-in-button");

        Button loginButton = new Button("Login");
        loginButton.getStyleClass().add("login-button");

//        // Tạo label trống để đẩy các nút về phía bên phải
        Label labelSpacer = new Label();
        Label labelSpacer1 = new Label();
        Label labelSpacer2 = new Label();
        Label labelSpacer3 = new Label();

// Tạo HBox mới và thêm MenuBar và các nút vào HBox
        HBox menuPane = new HBox(menuBar, signInButton, loginButton,labelSpacer,labelSpacer1,labelSpacer2,labelSpacer3);
        menuPane.setSpacing(10);
        menuPane.getStyleClass().add("custom-mainPane"); // Áp dụng màu nền cho HBox

// Đặt MenuBar để mở rộng và chiếm hết không gian còn lại trong HBox
        HBox.setHgrow(menuBar, Priority.ALWAYS);
        // Tạo phần dưới
        Button bottomButton1 = new Button("Bottom Button 1");
        Button bottomButton2 = new Button("Bottom Button 2");
        Button bottomButton3 = new Button("Bottom Button 3");

        Button buttonDes = new Button("hahahahaha");
        buttonDes.setLayoutX(300);
        buttonDes.setLayoutY(300);

        HBox bottomButtonBox = new HBox(10, bottomButton1, bottomButton2, bottomButton3);
        bottomButtonBox.setAlignment(Pos.CENTER);
        bottomButtonBox.setTranslateY(500);


        Label label = new Label("Vịnh Hạ Long ");
        label.setStyle("-fx-font-size: 20px;"); // Cài đặt kích thước chữ cho label
        label.setLayoutY(300);

        //tạo mainPane
        Pane extraPane = new Pane(bottomButtonBox, label, buttonDes);
//        extraPane.setBackground(backgroundStyle);

        VBox mainPane = new VBox(menuPane,extraPane);
        mainPane.setBackground(backgroundStyle);

        Scene scene = new Scene(mainPane, 1200, 600);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("VietNamTravel");
        primaryStage.show();
    }


}

