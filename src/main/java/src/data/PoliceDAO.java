package src.data;

import src.model.Platforma;
import src.model.Police;
import src.util.Resources;

import java.util.List;

/**
 * Created by root on 14.4.16.
 */
public class PoliceDAO extends TemplateDAO<Police> {
    public PoliceDAO(Resources res) {
        super(res);
    }

    public List<Police> getList() {
        return (List<Police>)em.createQuery("SELECT p FROM Police p").getResultList();
    }
}
