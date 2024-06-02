package model;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;

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

    @Column(name = "NgayKhoiHanh", nullable = false)
    private LocalDate ngayKhoiHanh;

    @Column(name = "NgayDen", nullable = false)
    private LocalDate ngayDen;

    @Column(name = "SoGheConLai", nullable = false)
    private Integer soGheConLai;

    @Column(name = "DiemKhoiHanh", nullable = false)
    private String diemKhoiHanh;

    @Column(name = "DiemDen", nullable = false)
    private String diemDen;

    @Column(name = "GioKhoiHanh", nullable = false)
    private Time gioKhoiHanh;

    @Column(name = "GioDen", nullable =false)
    private Time gioDen;

    @Column(name = "GiaVe", nullable = false)
    private Double giaVe;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMaChuyenBay() {
        return maChuyenBay;
    }


    public String getHangHangKhong() {
        return hangHangKhong;
    }

    public String getSanBayDi() {
        return sanBayDi;
    }


    public String getSanBayDen() {
        return sanBayDen;
    }

    public Integer getSoGheConLai() {
        return soGheConLai;
    }

    public LocalDate getNgayKhoiHanh() {
        return ngayKhoiHanh;
    }

    public LocalDate getNgayDen() {
        return ngayDen;
    }

    public Time getGioKhoiHanh() {
        return gioKhoiHanh;
    }

    public Time getGioDen() {
        return gioDen;
    }

    public String getDiemKhoiHanh() {
        return diemKhoiHanh;
    }

    public String getDiemDen() {
        return diemDen;
    }

    public Double getGiaVe() {
        return giaVe;
    }

}