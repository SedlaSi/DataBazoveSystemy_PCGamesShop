package src.data;

import src.model.Vydavatel;
import src.util.Resources;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by root on 14.4.16.
 */
public class VydavatelDAO extends TemplateDAO<Vydavatel> {
    public VydavatelDAO(Resources res) {
        super(res);
    }

    public List<Vydavatel> getVydavatelList(){
        Query q = em.createNamedQuery("Vydavatel.getVydavatelList");
        return (List<Vydavatel>)q.getResultList();
    }

    public Vydavatel getByNazev(String nazev) {
        em.getTransaction().begin();
        List<Vydavatel> list = em.createNamedQuery("Vydavatel.getByNazev").setParameter(1, nazev).getResultList();
        em.getTransaction().commit();

        if (list.size() == 0) {
            return null;
        }

        return list.get(0);
    }
}
