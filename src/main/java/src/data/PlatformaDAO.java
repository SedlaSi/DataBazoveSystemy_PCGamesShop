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
        return (List<Platforma>)em.createNamedQuery("Platforma.getList").getResultList();
    }

    public Platforma getByNazev(String nazev) {
        List<Platforma> list = (List<Platforma>) em.createNamedQuery("Platforma.getByNazev").setParameter(1, nazev).getResultList();

        if (list == null || list.size() == 0) {
            return null;
        }

        return list.get(0);
    }
}
