package entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Room")
public class RoomEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nameRoom")
    private String nameRoom;

    @Basic
    @Column(name = "acreage")
    private Double acreage;

    @Basic
    @Column(name = "bedroom")
    private int bedroom;

    @Basic
    @Column(name = "bathroom")
    private int bathroom;
    @Basic
    @Column(name = "classify")
    private int classify;

    @Basic
    @Column(name = "status")
    private boolean status;
    @ManyToOne
    @JoinColumn(name = "idFloor")
    private FloorEntity idFloor;
    @OneToMany(mappedBy = "idRoom")
    private List<ContractEntity> contractList;

    public int getClassify() {
        return classify;
    }

    public void setClassify(int classify) {
        this.classify = classify;
    }

    public int getId() {
        return id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Double getAcreage() {
        return acreage;
    }

    public void setAcreage(Double acreage) {
        this.acreage = acreage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameRoom() {
        return nameRoom;
    }

    public void setNameRoom(String nameRoom) {
        this.nameRoom = nameRoom;
    }

    public FloorEntity getIdFloor() {
        return idFloor;
    }

    public void setIdFloor(FloorEntity idFloor) {
        this.idFloor = idFloor;
    }

    public List<ContractEntity> getContractList() {
        return contractList;
    }

    public void setContractList(List<ContractEntity> contractList) {
        this.contractList = contractList;
    }

    public int getBedroom() {
        return bedroom;
    }

    public void setBedroom(int bedroom) {
        this.bedroom = bedroom;
    }

    public int getBathroom() {
        return bathroom;
    }

    public void setBathroom(int bathroom) {
        this.bathroom = bathroom;
    }
}
