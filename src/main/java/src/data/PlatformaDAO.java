package src.data;

import src.model.Platforma;
import src.util.Resources;

import java.util.List;

/**
 * Created by root on 14.4.16.
 */
public class PlatformaDAO extends TemplateDAO<Platforma> {
    public PlatformaDAO(Resources res) {
        super(res);
    }

    public List<Platforma> getList() {
        return (List<Platforma>)em.createQuery("SELECT p FROM Platforma p").getResultList();
    }

    public Platforma getByNazev(String nazev) {
        em.getTransaction().begin();
        List<Platforma> list = em.createQuery("SELECT p FROM Platforma p WHERE p.nazev = ?1").setParameter(1, nazev).getResultList();
        em.getTransaction().commit();

        if (list.size() == 0) {
            return null;
        }

        return list.get(0);
    }
}
