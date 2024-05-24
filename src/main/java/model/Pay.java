package model;
import javax.persistence.*;

@Entity
@Table(name = "Pay")
public class Pay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Pay_ID")
    private int payId;

    @Column(name = "PhuongThucThanhToan", nullable = false)
    private String phuongThucThanhToan;

    @Column(name = "TrangThaiThanhToan", nullable = false)
    private String trangThaiThanhToan;

    @ManyToOne
    @JoinColumn(name = "Stay_ID", nullable = false)
    private StayBooking stayBookings;

    @ManyToOne
    @JoinColumn(name = "TourBookings_ID", nullable = false)
    private TourBooking tourBookings;

    @ManyToOne
    @JoinColumn(name = "Bookings_ID", nullable = false)
    private Booking bookings;

    // Getters and Setters
}
