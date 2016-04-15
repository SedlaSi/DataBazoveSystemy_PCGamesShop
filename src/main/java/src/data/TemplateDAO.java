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

    public void create(T t){
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
    }

    public T update(T t){
        em.getTransaction().begin();
        T t2 = em.merge(t);
        em.getTransaction().commit();
        return t2;
    }

    public void remove(T t){
        em.getTransaction().begin();
        em.remove(t);
        em.getTransaction().commit();
    }

    public T getById(int id){
        T t;
        em.getTransaction().begin();
        Query q = em.createQuery("SELECT x FROM T WHERE x.id = :id");
        q.setParameter("id",id);
        t =(T) q.getSingleResult();
        em.getTransaction().commit();
        return t;
    }

}
