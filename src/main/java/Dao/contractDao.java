package Dao;

import JPAUtils.JpaUtil;
import entitys.ContractEntity;
import entitys.RoomEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class contractDao {
    private EntityManager em;

    public contractDao() {
        this.em = JpaUtil.getEntityManager();
    }

    public ContractEntity create(ContractEntity entity) throws Exception {
        try {
            this.em.getTransaction().begin();
            this.em.persist(entity);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            this.em.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        }
        return entity;
    }

    public ContractEntity update(ContractEntity entity) throws Exception {
        try {
            this.em.getTransaction().begin();
            this.em.merge(entity);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            this.em.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        }
        return entity;
    }

    public List<ContractEntity> all(){
        String jpql="SELECT obj from ContractEntity obj where obj.status=true";
        TypedQuery<ContractEntity> query =this.em.createQuery(jpql,ContractEntity.class);
        List<ContractEntity> list=query.getResultList();
        return list;
    }
    public ContractEntity findByID(int id){
        ContractEntity entity=this.em.find(ContractEntity.class,id);
        return entity;
    }
//    public List<ContractEntity> findByIDBuilding(int id){
//        String jpql="SELECT obj from ContractEntity obj where obj.idFloor.id= :id AND obj.status=true ";
//        TypedQuery<RoomEntity> query =this.em.createQuery(jpql,RoomEntity.class);
//        query.setParameter("id",id);
//        List<RoomEntity> list=query.getResultList();
//        return list;
//    }
}
