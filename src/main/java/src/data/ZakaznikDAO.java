package src.data;

import src.model.Exemplar;
import src.model.Zakaznik;
import src.util.Resources;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by root on 14.4.16.
 */
public class ZakaznikDAO extends TemplateDAO<Zakaznik> {
    public ZakaznikDAO(Resources res) {
        super(res);
    }

    public Zakaznik getByUserName(String userName){
        Query q =  em.createQuery("SELECT z FROM Zakaznik z WHERE z.username = :us");
        q.setParameter("us", userName);
        return (Zakaznik) q.getSingleResult();
    }

    public List<Exemplar> getNevraceneHry(Zakaznik zakaznik) throws Exception {
        Zakaznik z = this.update(zakaznik);
        //Query k = em.createQuery("SELECT e From Exemplar e where Exists (SELECT p FROM e.pujcka p WHERE p.zakaznik = :z AND p.vraceno = NULL) ");
        Query q = em.createQuery("SELECT e From Exemplar e where Exists (SELECT p FROM e.pujcka p WHERE p.zakaznik = :z AND p.vraceno = NULL) ");
        //Query k= em.createQuery("SELECT Pujcka FROM Pujcka p WHERE p.zakaznik = :z AND p.vraceno = NULL");
        q.setParameter("z",z);
        return (List<Exemplar>)q.getResultList();

    }

    public List<Exemplar> getVraceneHry(Zakaznik zakaznik) throws Exception {
        Zakaznik z = this.update(zakaznik);
        Query q = em.createQuery("SELECT e FROM Exemplar e WHERE EXISTS (SELECT p FROM e.pujcka p WHERE p.zakaznik = :z AND p.vraceno != NULL)");
        //Query k= em.createQuery("SELECT Pujcka FROM Pujcka p WHERE p.zakaznik = :z AND p.vraceno = NULL");
        q.setParameter("z",z);
        return (List<Exemplar>)q.getResultList();

    }
}
