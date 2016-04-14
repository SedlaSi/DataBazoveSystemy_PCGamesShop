package src.data;

import src.model.Zamestnanec;
import src.util.Resources;

import javax.persistence.Query;

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
}
