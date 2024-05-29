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

    public int getStayId() {
        return stayId;
    }

    public void setStayId(int stayId) {
        this.stayId = stayId;
    }

    public Accommodation getAccommodations() {
        return accommodations;
    }

    public void setAccommodations(Accommodation accommodations) {
        this.accommodations = accommodations;
    }

    public PassengerInformation getPassengerInformation() {
        return passengerInformation;
    }

    public void setPassengerInformation(PassengerInformation passengerInformation) {
        this.passengerInformation = passengerInformation;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public int getSoNguoi() {
        return soNguoi;
    }

    public void setSoNguoi(int soNguoi) {
        this.soNguoi = soNguoi;
    }

    public int getSoPhong() {
        return soPhong;
    }

    public void setSoPhong(int soPhong) {
        this.soPhong = soPhong;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }
}
