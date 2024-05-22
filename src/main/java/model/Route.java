package model;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "Routes")
public class Route {
    @Id
    @Column(name = "Routes_ID", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "DiemKhoiHanh", nullable = false)
    private String diemKhoiHanh;

    @Nationalized
    @Column(name = "DiemDen", nullable = false)
    private String diemDen;

    @Nationalized
    @Column(name = "LoaiPhuongTien", nullable = false, length = 50)
    private String loaiPhuongTien;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Images_ID")
    private Images images;

    @OneToMany(mappedBy = "routes")
    private Set<Schedule> schedules = new LinkedHashSet<>();

    public Set<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(Set<Schedule> schedules) {
        this.schedules = schedules;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDiemKhoiHanh() {
        return diemKhoiHanh;
    }

    public void setDiemKhoiHanh(String diemKhoiHanh) {
        this.diemKhoiHanh = diemKhoiHanh;
    }

    public String getDiemDen() {
        return diemDen;
    }

    public void setDiemDen(String diemDen) {
        this.diemDen = diemDen;
    }

    public String getLoaiPhuongTien() {
        return loaiPhuongTien;
    }

    public void setLoaiPhuongTien(String loaiPhuongTien) {
        this.loaiPhuongTien = loaiPhuongTien;
    }

}