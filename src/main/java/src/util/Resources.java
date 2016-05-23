package src.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
public class Resources {

    public final String createFunction = "CREATE OR REPLACE FUNCTION create_active_prodejce(IN username VARCHAR(128),IN  password BYTEA, IN jmeno VARCHAR(128),IN prijmeni VARCHAR(128),IN mesto VARCHAR(128), IN ulice VARCHAR(128), IN cislo_popisne INTEGER, IN telefon VARCHAR(16), IN email VARCHAR(256), IN plat INTEGER)\n" +
            "RETURNS BOOLEAN LANGUAGE plpgsql SECURITY DEFINER AS $$\n" +
            "BEGIN\n" +
            "\tIF username = '' OR jmeno = '' OR prijmeni = '' OR mesto = '' OR ulice = '' OR email = '' OR telefon = '' OR plat < 0 THEN\n" +
            "\t\tRETURN FALSE;\n" +
            "\tELSE\n" +
            "      \t\tINSERT INTO zamestnanec VALUES ((SELECT nextval('id_zamestnanec_seq')), TRUE, cislo_popisne, email, jmeno, mesto, password, plat,\n" +
            "\t\t\t\t      \t\t     prijmeni, telefon, ulice, username, 30);\n" +
            "      \t\tRETURN TRUE;\n" +
            "\tEND IF;\n" +
            "END;\n" +
            "$$;";

    @PersistenceContext
    private EntityManager em;

    private void init(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sedlasi_db");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createNativeQuery(createFunction).executeUpdate();
        em.getTransaction().commit();
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
