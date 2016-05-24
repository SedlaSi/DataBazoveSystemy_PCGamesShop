package src.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.Map;

public class Resources {

    private final String createFunction = "CREATE OR REPLACE FUNCTION create_active_prodejce(IN username VARCHAR(128),IN  password BYTEA, IN jmeno VARCHAR(128),IN prijmeni VARCHAR(128),IN mesto VARCHAR(128), IN ulice VARCHAR(128), IN cislo_popisne INTEGER, IN telefon VARCHAR(16), IN email VARCHAR(256), IN plat INTEGER)\n" +
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
            "$$;\n";

    private final String createTrigger = "CREATE OR REPLACE FUNCTION valid_pujcka()\n" +
            "RETURNS trigger LANGUAGE plpgsql SECURITY DEFINER AS $$\n" +
            "BEGIN\n" +
            "\tIF (SELECT COUNT(id_byl_propujcen) FROM byl_propujcen WHERE vraceno IS NULL AND id_exemplar = NEW.id_exemplar) > 0 THEN\n" +
            "\t\tRAISE EXCEPTION 'Exemplar jeste nebyl vracen, nemuze byt pujcen';\n" +
            "\tELSE\n" +
            "\t\tRETURN NEW;\n" +
            "\tEND IF;\n" +
            "END;\n" +
            "$$;\n" +
            "\n" +
            "DROP TRIGGER IF EXISTS pujcka_trigger ON byl_propujcen;\n" +
            "\n" +
            "CREATE TRIGGER pujcka_trigger\n" +
            "        BEFORE INSERT ON byl_propujcen\n" +
            "        FOR EACH ROW\n" +
            "        EXECUTE PROCEDURE valid_pujcka();";

    @PersistenceContext
    private EntityManager em;

    private void init(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sedlasi_db");
        em = emf.createEntityManager();

        boolean postgresql = false;

        for (Map.Entry<String,Object> stringObjectEntry : emf.getProperties().entrySet()) {
                if(stringObjectEntry.getKey().equals("javax.persistence.jdbc.url") && stringObjectEntry.getValue().toString().contains("postgresql")){
                    postgresql = true;
                }
        }

        if(postgresql){
            createProcedures();
        }
    }

    private void createProcedures(){
        em.getTransaction().begin();
        em.createNativeQuery(createFunction + createTrigger).executeUpdate();
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
