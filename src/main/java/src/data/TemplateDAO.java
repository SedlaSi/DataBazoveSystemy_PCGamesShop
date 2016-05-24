package src.data;

import src.util.Resources;

import javax.persistence.EntityManager;

/**
 * Created by root on 31.3.16.
 */
public class TemplateDAO<T> {

    public TemplateDAO(Resources res) {
        this.res = res;
        em = res.getEntityManager();
    }

    private Resources res;

    protected EntityManager em;

    public void create(T t) throws Exception {
        em.getTransaction().begin();
        try {
            em.persist(t);
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();

            throw new Exception("rollback invoked");
        }

        em.getTransaction().commit();
    }

    public T update(T t) throws Exception {
        em.getTransaction().begin();
        T t2;
        try {
            t2 = em.merge(t);
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
        em.getTransaction().commit();
        return t2;
    }

}
