package src.data;

import src.model.Pozice;
import src.util.Resources;

import java.util.List;

/**
 * Created by root on 14.4.16.
 */
public class PoziceDAO extends TemplateDAO<Pozice> {

    public PoziceDAO(Resources res) {
        super(res);
    }

    public List<Pozice> getList() {
        return (List<Pozice>)em.createNamedQuery("Pozice.getList").getResultList();
    }

}
