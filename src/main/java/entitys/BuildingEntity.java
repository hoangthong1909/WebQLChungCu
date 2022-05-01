package entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Building")
public class BuildingEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nameBuilding")
    private String nameBuilding;

    @Basic
    @Column(name = "toilet")
    private Double toilet;

    @Basic
    @Column(name = "security")
    private Double security;

    @Basic
    @Column(name = "landscapeCare")
    private Double landscapeCare;

    @Basic
    @Column(name = "work")
    private Double work;

    @Basic
    @Column(name = "garbage")
    private Double garbage;

    @Basic
    @Column(name = "address")
    private String address;

    @Basic
    @Column(name = "dateCreate")
    private Date dateCreate;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private UsersEntity User;

    @Basic
    @Column(name = "status")
    private Integer status;

    @OneToMany(mappedBy = "idBuilding")
    private List<FloorEntity> floorList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameBuilding() {
        return nameBuilding;
    }

    public void setNameBuilding(String nameBuilding) {
        this.nameBuilding = nameBuilding;
    }

    public Double getToilet() {
        return toilet;
    }

    public void setToilet(Double toilet) {
        this.toilet = toilet;
    }

    public Double getSecurity() {
        return security;
    }

    public void setSecurity(Double security) {
        this.security = security;
    }

    public Double getLandscapeCare() {
        return landscapeCare;
    }

    public void setLandscapeCare(Double landscapeCare) {
        this.landscapeCare = landscapeCare;
    }

    public Double getWork() {
        return work;
    }

    public void setWork(Double work) {
        this.work = work;
    }

    public Double getGarbage() {
        return garbage;
    }

    public void setGarbage(Double garbage) {
        this.garbage = garbage;
    }

    public UsersEntity getUser() {
        return User;
    }

    public void setUser(UsersEntity user) {
        User = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<FloorEntity> getFloorList() {
        return floorList;
    }

    public void setFloorList(List<FloorEntity> floorList) {
        this.floorList = floorList;
    }


    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public UsersEntity getIdUser() {
        return User;
    }

    public void setIdUser(UsersEntity idUser) {
        this.User = idUser;
    }
}
