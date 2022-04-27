package Dao;

import JPAUtils.JpaUtil;
import entitys.BuildingEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class buildingDao {
    private EntityManager em;

    public buildingDao() {
        this.em = JpaUtil.getEntityManager();
    }

    public BuildingEntity create(BuildingEntity entity) throws Exception {
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

    public BuildingEntity update(BuildingEntity entity) throws Exception {
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

    public List<BuildingEntity> all() {
        String jpql = "SELECT obj from BuildingEntity obj where obj.status=1";
        TypedQuery<BuildingEntity> query = this.em.createQuery(jpql, BuildingEntity.class);
        List<BuildingEntity> list = query.getResultList();
        return list;
    }
//    public int quantity() {
//        String jpql = "SELECT count (obj.id) from BuildingEntity obj where obj.status=1";
//        TypedQuery<BuildingEntity> query = this.em.createQuery(jpql, BuildingEntity.class);
//        List<BuildingEntity> list = query.getResultList();
//        return ;
//    }

    public BuildingEntity findByID(int id) {
        BuildingEntity entity = this.em.find(BuildingEntity.class, id);
        return entity;
    }
}
