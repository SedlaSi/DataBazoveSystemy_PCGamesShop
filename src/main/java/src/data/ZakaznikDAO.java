package src.data;

import src.model.Zakaznik;
import src.util.Resources;

import javax.persistence.Query;

/**
 * Created by root on 14.4.16.
 */
public class ZakaznikDAO extends TemplateDAO<Zakaznik> {
    public ZakaznikDAO(Resources res) {
        super(res);
    }

    public Zakaznik getByUserName(String userName){
        Query q =  em.createQuery("SELECT z FROM ZAKAZNIK WHERE z.username = :us");
        q.setParameter("us", userName);
        return (Zakaznik) q.getSingleResult();
    }
}
