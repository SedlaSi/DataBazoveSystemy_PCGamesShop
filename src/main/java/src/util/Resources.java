package src.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Resources {

    //@PersistenceContext
    private EntityManager em;

    private void init(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gameshop");
        em = emf.createEntityManager();
    }

    public EntityManager getEntityManager(){
        if(em != null){
            return em;
        } else {
            init();
            return em;
        }
    }

}
