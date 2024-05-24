package model;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TourBookings")
public class TourBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TourBookings_ID")
    private int tourBookingsId;

    @ManyToOne
    @JoinColumn(name = "TouristAttractions_ID", nullable = false)
    private TouristAttraction touristAttractions;

    @ManyToOne
    @JoinColumn(name = "PassengerInformation_ID", nullable = false)
    private PassengerInformation passengerInformation;

    @ManyToOne
    @JoinColumn(name = "Users_ID", nullable = false)
    private User users;

    @Column(name = "EmailNhanVe", nullable = false)
    private String emailNhanVe;

    @Column(name = "NgayThamQuan", nullable = false)
    private Date ngayThamQuan;

    @Column(name = "SoVe", nullable = false)
    private int soVe;

    // Getters and Setters
}
