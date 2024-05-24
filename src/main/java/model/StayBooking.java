package model;
import javax.persistence.*;

@Entity
@Table(name = "StayBookings")
public class StayBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Stay_ID")
    private int stayId;

    @ManyToOne
    @JoinColumn(name = "Accommodations_ID", nullable = false)
    private Accommodation accommodations;

    @ManyToOne
    @JoinColumn(name = "PassengerInformation_ID", nullable = false)
    private PassengerInformation passengerInformation;

    @ManyToOne
    @JoinColumn(name = "Users_ID", nullable = false)
    private User users;

    @Column(name = "SoNguoi", nullable = false)
    private int soNguoi;

    @Column(name = "SoPhong", nullable = false)
    private int soPhong;

    @Column(name = "CCCD", nullable = false, unique = true)
    private String cccd;

    // Getters and Setters
}
