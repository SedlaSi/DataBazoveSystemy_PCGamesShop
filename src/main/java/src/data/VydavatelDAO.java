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
        Query q = em.createQuery("SELECT Vydavatel FROM Vydavatel");

        return (List<Vydavatel>)q.getResultList();
    }
}
