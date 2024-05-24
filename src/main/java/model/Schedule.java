package model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "Schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Schedules_ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Transportations_ID", nullable = false)
    private Transportation transportations;

    @Column(name = "NgayKhoiHanh", nullable = false)
    private LocalDate ngayKhoiHanh;

    @Column(name = "GioKhoiHanh", nullable = false)
    private LocalTime gioKhoiHanh;

    @Column(name = "GioDen", nullable = false)
    private LocalTime gioDen;

    @Column(name = "GiaVe", nullable = false, precision = 10, scale = 2)
    private BigDecimal giaVe;

    @Column(name = "SoChoConLai", nullable = false)
    private Integer soChoConLai;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Routes_ID", nullable = false)
    private Route routes;

    @OneToMany(mappedBy = "schedules")
    private Set<TransportBooking> transportBookings = new LinkedHashSet<>();

    public Set<TransportBooking> getBookings() {
        return transportBookings;
    }

    public void setBookings(Set<TransportBooking> transportBookings) {
        this.transportBookings = transportBookings;
    }

    public Route getRoutes() {
        return routes;
    }

    public void setRoutes(Route routes) {
        this.routes = routes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Transportation getTransportations() {
        return transportations;
    }

    public void setTransportations(Transportation transportations) {
        this.transportations = transportations;
    }

    public LocalDate getNgayKhoiHanh() {
        return ngayKhoiHanh;
    }

    public void setNgayKhoiHanh(LocalDate ngayKhoiHanh) {
        this.ngayKhoiHanh = ngayKhoiHanh;
    }

    public LocalTime getGioKhoiHanh() {
        return gioKhoiHanh;
    }

    public void setGioKhoiHanh(LocalTime gioKhoiHanh) {
        this.gioKhoiHanh = gioKhoiHanh;
    }

    public LocalTime getGioDen() {
        return gioDen;
    }

    public void setGioDen(LocalTime gioDen) {
        this.gioDen = gioDen;
    }

    public BigDecimal getGiaVe() {
        return giaVe;
    }

    public void setGiaVe(BigDecimal giaVe) {
        this.giaVe = giaVe;
    }

    public Integer getSoChoConLai() {
        return soChoConLai;
    }

    public void setSoChoConLai(Integer soChoConLai) {
        this.soChoConLai = soChoConLai;
    }

}