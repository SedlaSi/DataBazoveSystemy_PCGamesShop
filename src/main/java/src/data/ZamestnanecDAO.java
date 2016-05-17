package src.data;

import src.model.Zamestnanec;
import src.util.Resources;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by root on 14.4.16.
 */
public class ZamestnanecDAO extends TemplateDAO<Zamestnanec> {
    public ZamestnanecDAO(Resources res) {
        super(res);
    }

    public Zamestnanec getByUserName(String userName){
        Query q =  em.createNamedQuery("Zamestnanec.getByUserName");
        q.setParameter("us", userName);
        return (Zamestnanec) q.getSingleResult();
    }

    public Zamestnanec getByJmenoPrijmeni(String jmeno, String prijmeni){
        Query q = em.createNamedQuery("Zamestnanec.getByJmenoPrijmeni", Zamestnanec.class);
        q.setParameter("j",jmeno);
        q.setParameter("p",prijmeni);
        return (Zamestnanec) q.getSingleResult();
    }

    public List<Zamestnanec> getList() {
        Query q = em.createNamedQuery("Zamestnanec.getList",Zamestnanec.class);
        return (List<Zamestnanec>) q.getResultList();
    }
}
