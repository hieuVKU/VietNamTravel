package model;

import javax.persistence.*;

@Entity
public class Stay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Stay_ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Accommodations_ID", nullable = false)
    private Accommodation accommodations;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PassengerInformation_ID", nullable = false)
    private PassengerInformation passengerInformation;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SoDienThoai", nullable = false, referencedColumnName = "SoDienThoai")
    private User soDienThoai;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public User getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(User soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

}