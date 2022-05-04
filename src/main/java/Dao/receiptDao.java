package Dao;

import JPAUtils.JpaUtil;
import entitys.FloorEntity;
import entitys.ReceiptEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class receiptDao {
    private EntityManager em;

    public receiptDao() {
        this.em = JpaUtil.getEntityManager();
    }

    public ReceiptEntity create(ReceiptEntity entity) throws Exception {
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

    public ReceiptEntity update(ReceiptEntity entity) throws Exception {
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

    public List<ReceiptEntity> all(){
        String jpql="SELECT obj from ReceiptEntity obj where obj.status=true";
        TypedQuery<ReceiptEntity> query =this.em.createQuery(jpql,ReceiptEntity.class);
        List<ReceiptEntity> list=query.getResultList();
        return list;
    }
    public ReceiptEntity findByID(int id){
        ReceiptEntity entity=this.em.find(ReceiptEntity.class,id);
        return entity;
    }
//    public List<FloorEntity> findByIDBuilding(int id){
//        String jpql="SELECT obj from FloorEntity obj where obj.idBuilding.id= :id AND obj.status=true ";
//        TypedQuery<FloorEntity> query =this.em.createQuery(jpql,FloorEntity.class);
//        query.setParameter("id",id);
//        List<FloorEntity> list=query.getResultList();
//        return list;
//    }
}
