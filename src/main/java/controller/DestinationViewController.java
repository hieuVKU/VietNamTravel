package controller;

import Util.HibernateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import model.*;
import org.hibernate.Session;
import org.hibernate.query.Query;

import model.Schedule;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DestinationViewController {
    @FXML
    private TextField FromTF;
    @FXML
    private TextField ToTF;
    @FXML
    private DatePicker GoTF;
    @FXML
    private DatePicker ReturnTF;
    @FXML
    private Button searchButton;
    @FXML
    private ListView<Pane> listRoutes;
    @FXML
    private HBox BusBox;
    @FXML
    private HBox TrainBox;
    @FXML
    private HBox PlaneBox;

    private HBox lastSelectedBox;

    private Session session;

    public DestinationViewController(){
        this.session = HibernateUtil.getSessionFactory().openSession();
    }
    @FXML
    private void initialize() {
        // Bắt sự kiện click cho các HBox
        System.out.println("Initialize called");
        System.out.println("FromTF: " + FromTF); // Debugging line
        System.out.println("ToTF: " + ToTF);     // Debugging line
        BusBox.setOnMouseClicked(event -> handleBoxClick(BusBox));
        TrainBox.setOnMouseClicked(event -> handleBoxClick(TrainBox));
        PlaneBox.setOnMouseClicked(event -> handleBoxClick(PlaneBox));
    }

    @FXML
    private void handleSearchButtonClick() {
        System.out.println("Search button clicked");
        System.out.println("FromTF: " + FromTF.getText()); // Debugging line
        System.out.println("ToTF: " + ToTF.getText());     // Debugging line// Xử lý sự kiện click của nút Search
        String from = FromTF.getText();
        String to = ToTF.getText();
        LocalDate goDate = GoTF.getValue();
        LocalDate returnDate = ReturnTF.getValue();

        // Kiểm tra xem đã chọn loại phương tiện chưa
        if (lastSelectedBox == null) {
            // Hiển thị thông báo lỗi hoặc thực hiện hành động phù hợp
            System.out.println("Vui lòng chọn loại phương tiện!");
            return; // Thoát khỏi phương thức nếu chưa chọn
        }

        String loaiPhuongTien = "";
        if (lastSelectedBox == BusBox) {
            loaiPhuongTien = "Bus";
        } else if (lastSelectedBox == TrainBox) {
            loaiPhuongTien = "Train";
        } else if (lastSelectedBox == PlaneBox) {
            loaiPhuongTien = "Plane";
        }

        List<Object> allRoutes = new ArrayList<>();

        if (loaiPhuongTien.equals("Bus")) {
            List<Schedule> routes = queryBusRoutes(from, to, goDate);
            allRoutes.addAll(routes);
            if (returnDate != null) {
                allRoutes.addAll(queryBusRoutes(to, from, returnDate));
            }
        } else if (loaiPhuongTien.equals("Train")) {
            List<Schedule> routes = queryTrainRoutes(from, to, goDate);
            allRoutes.addAll(routes);
            if (returnDate != null) {
                allRoutes.addAll(queryTrainRoutes(to, from, returnDate));
            }
        } else {
            List<Flight> routesFlight = queryFlightRoutes(from, to, goDate);
            allRoutes.addAll(routesFlight);
            if (returnDate != null) {
                allRoutes.addAll(queryFlightRoutes(to, from, returnDate));
            }
        }

        System.out.println("All routes: " + allRoutes);
        displayRoutes(listRoutes, allRoutes, loaiPhuongTien);
    }

    private void handleBoxClick(HBox box) {
        if (lastSelectedBox != null) {
            // Reset the style of the last selected box
            lastSelectedBox.setStyle("");
        }
        // Set the new style for the clicked box
        box.setStyle("-fx-background-color: white;");
        lastSelectedBox = box;
    }

    private List<Schedule> queryBusRoutes(String from, String to, LocalDate goDate ) {
        String hql = "FROM Schedule S JOIN FETCH S.routes R JOIN FETCH S.transportations T " +
                "WHERE T.loaiPhuongTien = 'Bus' AND " +
                "((R.diemKhoiHanh = :from AND R.diemDen = :to AND S.ngayKhoiHanh = :goDate) ";

        Query<Schedule> query = session.createQuery(hql, Schedule.class);
        query.setParameter("from", from);
        query.setParameter("to", to);
        query.setParameter("goDate", goDate);
        return query.list();
    }

    private List<Schedule> queryTrainRoutes(String from, String to, LocalDate goDate) {
        String hql = "FROM Schedule S JOIN FETCH S.routes R JOIN FETCH S.transportations T " +
                "WHERE T.loaiPhuongTien = 'Train' AND " +
                "((R.diemKhoiHanh = :from AND R.diemDen = :to AND S.ngayKhoiHanh = :goDate)) ";

        Query<Schedule> query = session.createQuery(hql, Schedule.class);
        query.setParameter("from", from);
        query.setParameter("to", to);
        query.setParameter("goDate", goDate);

        String sqlQuery = query.getQueryString();
        System.out.println("SQL Query: " + sqlQuery);
        return query.list();
    }

    private List<Flight> queryFlightRoutes(String from, String to, LocalDate goDate) {
        String hql = "FROM Flight F WHERE " +
                "F.diemKhoiHanh = :from AND F.diemDen = :to AND F.ngayKhoiHanh = :goDate ";
        Query<Flight> query = session.createQuery(hql, Flight.class);
        query.setParameter("from", from);
        query.setParameter("to", to);
        query.setParameter("goDate", goDate);

       return query.list();
    }

    private void displayRoutes(ListView<Pane> listRoutes, List<Object> allRoutes, String type) {
        listRoutes.getItems().clear();

        for (Object route : allRoutes) {
            if (route instanceof Schedule) {
                Schedule schedule = (Schedule) route;
                Pane pane = createRoutePane(schedule, type);
                listRoutes.getItems().add(pane);
            } else if (route instanceof Flight) {
                Flight flight = (Flight) route;
                Pane pane = createRoutePane(flight, type);
                listRoutes.getItems().add(pane);
            }
        }
    }

    private Pane createRoutePane(Object entity, String type) {
        Pane pane = new Pane();
        pane.setPrefSize(300, 70); // Kích thước mới: 300x70
        pane.setStyle("-fx-border-color: lightgray; -fx-border-width: 1px;"); // Thêm viền cho dễ nhìn
        Font font = Font.font("Calibri", 20);

        Label labelTop = new Label(); // Label cho hàng trên cùng
        Label labelBottom = new Label(); // Label cho hàng dưới cùng
        labelTop.setFont(font);
        labelBottom.setFont(font);

        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US);

        if (entity instanceof Flight) {
            Flight flight = (Flight) entity;
            labelTop.setText(flight.getSanBayDi() + " -> " + flight.getSanBayDen() + " | " + flight.getHangHangKhong());
            labelBottom.setText(flight.getDiemKhoiHanh() + " -> " + flight.getDiemDen() + " | " + flight.getNgayKhoiHanh().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " | "
                    + flight.getGioKhoiHanh() + " | " +flight.getGioDen()
                    + formatter.format(flight.getGiaVe()) + " VND | " + flight.getSoGheConLai() + " chỗ");
        } else if (entity instanceof Schedule) {
            Schedule schedule = (Schedule) entity;
            Transportation transportation = schedule.getTransportations();

            if (transportation != null) {
                labelTop.setText(schedule.getRoutes().getDiemKhoiHanh() + " -> " + schedule.getRoutes().getDiemDen() + " | " + transportation.getTenHang());
                labelBottom.setText(schedule.getNgayKhoiHanh().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " | "
                        + schedule.getGioKhoiHanh() + " - " + schedule.getGioDen() + " | "
                        + formatter.format(schedule.getGiaVe()) + " VND | " + schedule.getSoChoConLai() + " chỗ");
            } else {
                labelTop.setText("Không tìm thấy thông tin phương tiện");
            }
        }

        // Căn chỉnh và định dạng cho các label
        labelTop.setLayoutX(10);
        labelTop.setLayoutY(10);
        labelBottom.setLayoutX(10);
        labelBottom.setLayoutY(35);

        pane.getChildren().addAll(labelTop, labelBottom);

        return pane;
    }



}

