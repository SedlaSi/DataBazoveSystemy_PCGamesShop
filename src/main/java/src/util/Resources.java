package src.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class Resources {

    // use @SuppressWarnings to tell IDE to ignore warnings about field not being referenced directly
    @SuppressWarnings("unused")
    @PersistenceContext
    private EntityManager em;

    private void init(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("java.src");
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
