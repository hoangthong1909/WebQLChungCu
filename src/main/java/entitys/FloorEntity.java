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
@Table(name = "Floor")
public class FloorEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nameFloor")
    private String nameFloor;
    @Basic
    @Column(name = "dateCreate")
    private Date dateCreate;
    @Basic
    @Column(name = "status")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "idBuilding")
    private BuildingEntity idBuilding;
    @OneToMany(mappedBy = "idFloor")
    private List<RoomEntity> roomList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameFloor() {
        return nameFloor;
    }

    public void setNameFloor(String nameFloor) {
        this.nameFloor = nameFloor;
    }

    public BuildingEntity getIdBuilding() {
        return idBuilding;
    }

    public void setIdBuilding(BuildingEntity idBuilding) {
        this.idBuilding = idBuilding;
    }

    public List<RoomEntity> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<RoomEntity> roomList) {
        this.roomList = roomList;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
