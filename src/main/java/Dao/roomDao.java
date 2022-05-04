package Dao;

import JPAUtils.JpaUtil;
import entitys.RoomEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class roomDao {
    private EntityManager em;

    public roomDao() {
        this.em = JpaUtil.getEntityManager();
    }

    public RoomEntity create(RoomEntity entity) throws Exception {
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

    public RoomEntity update(RoomEntity entity) throws Exception {
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

    public List<RoomEntity> all(){
        String jpql="SELECT obj from RoomEntity obj where obj.status=true";
        TypedQuery<RoomEntity> query =this.em.createQuery(jpql,RoomEntity.class);
        List<RoomEntity> list=query.getResultList();
        return list;
    }
    public List<RoomEntity> empty(){
        String jpql="SELECT obj from RoomEntity obj where obj.status=true AND obj.classify=0";
        TypedQuery<RoomEntity> query =this.em.createQuery(jpql,RoomEntity.class);
        List<RoomEntity> list=query.getResultList();
        return list;
    }
    public RoomEntity findByID(int id){
        RoomEntity entity=this.em.find(RoomEntity.class,id);
        return entity;
    }
    public List<RoomEntity> findByIDBuilding(int id){
        String jpql="SELECT obj from RoomEntity obj where obj.idFloor.id= :id AND obj.status=true ";
        TypedQuery<RoomEntity> query =this.em.createQuery(jpql,RoomEntity.class);
        query.setParameter("id",id);
        List<RoomEntity> list=query.getResultList();
        return list;
    }
}
