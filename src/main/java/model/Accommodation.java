package model;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Accommodations")	
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Accommodations_ID", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "Ten", nullable = false)
    private String ten;

    @Nationalized
    @Column(name = "DiaChi")
    private String diaChi;

    @Nationalized
    @Lob
    @Column(name = "MoTa", columnDefinition = "nvarchar(max)")
    private String moTa;

    @Column(name = "GiaPhong", precision = 10, scale = 2)
    private BigDecimal giaPhong;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Images_ID")
    private Images images;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public BigDecimal getGiaPhong() {
        return giaPhong;
    }

    public void setGiaPhong(BigDecimal giaPhong) {
        this.giaPhong = giaPhong;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

}