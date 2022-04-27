package Dao;

import JPAUtils.JpaUtil;
import entitys.FloorEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class floorDao {
    private EntityManager em;

    public floorDao() {
        this.em = JpaUtil.getEntityManager();
    }

    public FloorEntity create(FloorEntity entity) throws Exception {
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

    public FloorEntity update(FloorEntity entity) throws Exception {
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

    public List<FloorEntity> all(){
        String jpql="SELECT obj from FloorEntity obj where obj.status=true";
        TypedQuery<FloorEntity> query =this.em.createQuery(jpql,FloorEntity.class);
        List<FloorEntity> list=query.getResultList();
        return list;
    }
    public FloorEntity findByID(int id){
        FloorEntity entity=this.em.find(FloorEntity.class,id);
        return entity;
    }
    public List<FloorEntity> findByIDBuilding(int id){
        String jpql="SELECT obj from FloorEntity obj where obj.idBuilding.id= :id AND obj.status=true ";
        TypedQuery<FloorEntity> query =this.em.createQuery(jpql,FloorEntity.class);
        query.setParameter("id",id);
        List<FloorEntity> list=query.getResultList();
        return list;
    }
}
