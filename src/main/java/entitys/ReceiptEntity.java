package entitys;

import javax.persistence.*;

@Entity
@Table(name = "Receipt")
public class ReceiptEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "service", nullable = false)
    private Double service;

    @Column(name = "parking", nullable = false)
    private Double parking;

    @Column(name = "electricity")
    private Double electricity;

    @Column(name = "water", nullable = false)
    private Double water;

    @Column(name = "internet", nullable = false)
    private Double internet;

    @Column(name = "status", nullable = false)
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "contract_id")
    private ContractEntity contract_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public Double getInternet() {
        return internet;
    }

    public void setInternet(Double internet) {
        this.internet = internet;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Double getService() {
        return service;
    }

    public void setService(Double service) {
        this.service = service;
    }

    public Double getParking() {
        return parking;
    }

    public void setParking(Double parking) {
        this.parking = parking;
    }

    public Double getElectricity() {
        return electricity;
    }

    public void setElectricity(Double electricity) {
        this.electricity = electricity;
    }

    public Double getWater() {
        return water;
    }

    public void setWater(Double water) {
        this.water = water;
    }

    public ContractEntity getContract_id() {
        return contract_id;
    }

    public void setContract_id(ContractEntity contract_id) {
        this.contract_id = contract_id;
    }
}