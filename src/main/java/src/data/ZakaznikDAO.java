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
        Query q =  em.createNamedQuery("Zakaznik.getByUserName");
        q.setParameter("us", userName);
        return (Zakaznik) q.getSingleResult();
    }

    public List<Exemplar> getNevraceneHry(Zakaznik zakaznik) throws Exception {
        Zakaznik z = this.update(zakaznik);
        Query q = em.createNamedQuery("Zakaznik.getNevraceneHry");
        q.setParameter("z",z);
        return (List<Exemplar>)q.getResultList();

    }

    public List<Exemplar> getVraceneHry(Zakaznik zakaznik) throws Exception {
        Zakaznik z = this.update(zakaznik);
        Query q = em.createNamedQuery("Zakaznik.getVraceneHry");
        q.setParameter("z",z);
        return (List<Exemplar>)q.getResultList();

    }

    public List<Zakaznik> getList() {
        return (List<Zakaznik>)em.createNamedQuery("Zakaznik.getList").getResultList();
    }

}
