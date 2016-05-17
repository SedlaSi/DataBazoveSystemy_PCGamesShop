package src.data;

import src.model.Hra;
import src.util.Resources;

import java.util.List;

/**
 * Created by root on 14.4.16.
 */
public class HraDAO extends TemplateDAO<Hra> {

    public HraDAO(Resources res) {
        super(res);
    }

    public List<Hra> getList() {
        return (List<Hra>)em.createNamedQuery("Hra.getList").getResultList();
    }

}
