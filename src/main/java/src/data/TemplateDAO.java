package src.data;

import src.util.Resources;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Created by root on 31.3.16.
 */
public class TemplateDAO<T> {

    public TemplateDAO(Resources res){
        this.res = res;
        em = res.getEntityManager();
    }

    private Resources res;

    protected EntityManager em;

    public void create(T t) throws Exception{
        em.getTransaction().begin();
        try{
            em.persist(t);
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();

            throw new Exception("rollback invoked");
        }

        em.getTransaction().commit();
    }

    public T update(T t) throws Exception{
        em.getTransaction().begin();
        T t2;
        try{
            t2 = em.merge(t);
        } catch (Exception e){
            em.getTransaction().rollback();
            throw e;
        }
        em.getTransaction().commit();
        return t2;
    }

    public void remove(T t) throws Exception{
        em.getTransaction().begin();
        em.remove(t);
        em.getTransaction().commit();
    }

    public T getById(int id){
        T t;
        em.getTransaction().begin();
        Query q = em.createQuery("SELECT x FROM T x WHERE x.id = :id");
        q.setParameter("id",id);
        t =(T) q.getSingleResult();
        em.getTransaction().commit();
        return t;
    }

}
