package model;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "Flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Flights_ID", nullable = false)
    private Integer id;

    @Column(name = "MaChuyenBay", nullable = false, length = 10)
    private String maChuyenBay;

    @Nationalized
    @Column(name = "HangHangKhong", nullable = false)
    private String hangHangKhong;

    @Nationalized
    @Column(name = "SanBayDi", nullable = false)
    private String sanBayDi;

    @Nationalized
    @Column(name = "SanBayDen", nullable = false)
    private String sanBayDen;

    @Column(name = "NgayGioKhoiHanh", nullable = false)
    private Instant ngayGioKhoiHanh;

    @Column(name = "NgayGioDen", nullable = false)
    private Instant ngayGioDen;

    @Column(name = "SoGheConLai", nullable = false)
    private Integer soGheConLai;

    @OneToMany(mappedBy = "plane")
    private Set<Booking> bookings = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMaChuyenBay() {
        return maChuyenBay;
    }

    public void setMaChuyenBay(String maChuyenBay) {
        this.maChuyenBay = maChuyenBay;
    }

    public String getHangHangKhong() {
        return hangHangKhong;
    }

    public void setHangHangKhong(String hangHangKhong) {
        this.hangHangKhong = hangHangKhong;
    }

    public String getSanBayDi() {
        return sanBayDi;
    }

    public void setSanBayDi(String sanBayDi) {
        this.sanBayDi = sanBayDi;
    }

    public String getSanBayDen() {
        return sanBayDen;
    }

    public void setSanBayDen(String sanBayDen) {
        this.sanBayDen = sanBayDen;
    }

    public Instant getNgayGioKhoiHanh() {
        return ngayGioKhoiHanh;
    }

    public void setNgayGioKhoiHanh(Instant ngayGioKhoiHanh) {
        this.ngayGioKhoiHanh = ngayGioKhoiHanh;
    }

    public Instant getNgayGioDen() {
        return ngayGioDen;
    }

    public void setNgayGioDen(Instant ngayGioDen) {
        this.ngayGioDen = ngayGioDen;
    }

    public Integer getSoGheConLai() {
        return soGheConLai;
    }

    public void setSoGheConLai(Integer soGheConLai) {
        this.soGheConLai = soGheConLai;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

}