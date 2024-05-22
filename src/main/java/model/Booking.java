package model;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "Bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Bookings_ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Users_ID", nullable = false)
    private User users;

    @Nationalized
    @Column(name = "LoaiVe", nullable = false, length = 50)
    private String loaiVe;

    @Column(name = "NgayDat", nullable = false)
    private LocalDate ngayDat;

    @Nationalized
    @Column(name = "TrangThai", nullable = false, length = 50)
    private String trangThai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Schedules_ID")
    private Schedule schedules;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FlightID")
    private Flight plane;

    @OneToMany(mappedBy = "bookings")
    private Set<PassengerInformation> passengerInformations = new LinkedHashSet<>();

    public Set<PassengerInformation> getPassengerInformations() {
        return passengerInformations;
    }

    public void setPassengerInformations(Set<PassengerInformation> passengerInformations) {
        this.passengerInformations = passengerInformations;
    }

    public Flight getPlane() {
        return plane;
    }

    public void setPlane(Flight plane) {
        this.plane = plane;
    }

    public Schedule getSchedules() {
        return schedules;
    }

    public void setSchedules(Schedule schedules) {
        this.schedules = schedules;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public String getLoaiVe() {
        return loaiVe;
    }

    public void setLoaiVe(String loaiVe) {
        this.loaiVe = loaiVe;
    }

    public LocalDate getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(LocalDate ngayDat) {
        this.ngayDat = ngayDat;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

}