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
        Query q =  em.createQuery("SELECT z FROM ZAMESTNANEC WHERE z.username = :us");
        q.setParameter("us", userName);
        return (Zamestnanec) q.getSingleResult();
    }

    public List<Zamestnanec> getList() {
        Query q = em.createQuery("SELECT z FROM Zamestnanec z",Zamestnanec.class);
        return (List<Zamestnanec>) q.getResultList();
    }
}
