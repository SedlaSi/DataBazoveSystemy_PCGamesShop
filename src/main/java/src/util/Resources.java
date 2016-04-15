package src.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class Resources {

    @PersistenceContext
    private EntityManager em;

    private void init(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sedlasi_db");
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
