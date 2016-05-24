package src.data;

import src.model.Zanr;
import src.util.Resources;

import java.util.List;

/**
 * Created by root on 14.4.16.
 */
public class ZanrDAO extends TemplateDAO<Zanr> {

    public ZanrDAO(Resources res) {
        super(res);
    }

    public List<Zanr> getList() {
        return (List<Zanr>) em.createNamedQuery("Zanr.getList").getResultList();
    }
}
