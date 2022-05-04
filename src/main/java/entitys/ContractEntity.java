package entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contract")
public class ContractEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "idUser")
    private UsersEntity idUser;
    @ManyToOne
    @JoinColumn(name = "idCustomer")
    private Customer idCustomer;
    @Basic
    @Column(name = "dateCreate")
    private Date dateCreate;
    @Basic
    @Column(name = "vehicle")
    private int vehicle;
    @Basic
    @Column(name = "people")
    private int people;
    @Basic
    @Column(name = "dateEnd")
    private Date dateEnd;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "status")
    private boolean status;
    @ManyToOne
    @JoinColumn(name = "idRoom")
    private RoomEntity idRoom;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private TypecontractEntity idtype;
    @Basic
    @Column(name = "price")
    private Double price;

    @OneToMany(mappedBy = "contract_id")
    private List<ReceiptEntity> receiptList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UsersEntity getIdUser() {
        return idUser;
    }

    public void setIdUser(UsersEntity idUser) {
        this.idUser = idUser;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public RoomEntity getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(RoomEntity idRoom) {
        this.idRoom = idRoom;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypecontractEntity getIdtype() {
        return idtype;
    }

    public void setIdtype(TypecontractEntity idtype) {
        this.idtype = idtype;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Customer getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Customer idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getVehicle() {
        return vehicle;
    }

    public void setVehicle(int vehicle) {
        this.vehicle = vehicle;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public List<ReceiptEntity> getReceiptList() {
        return receiptList;
    }

    public void setReceiptList(List<ReceiptEntity> receiptList) {
        this.receiptList = receiptList;
    }
}
