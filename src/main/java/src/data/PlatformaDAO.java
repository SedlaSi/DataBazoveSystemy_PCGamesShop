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
}
