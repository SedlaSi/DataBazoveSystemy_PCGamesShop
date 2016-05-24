package src.data;

import src.model.Zamestnanec;
import src.util.Resources;

import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

/**
 * Created by root on 14.4.16.
 */
public class ZamestnanecDAO extends TemplateDAO<Zamestnanec> {
    public ZamestnanecDAO(Resources res) {
        super(res);
    }

    public Zamestnanec getByUserName(String userName) {
        Query q = em.createNamedQuery("Zamestnanec.getByUserName");
        q.setParameter("us", userName);
        List<Zamestnanec> list = q.getResultList();
        if (list != null && list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    public List<Zamestnanec> getList() {
        Query q = em.createNamedQuery("Zamestnanec.getList", Zamestnanec.class);
        return (List<Zamestnanec>) q.getResultList();
    }

    public void createProdejce(Zamestnanec zamestnanec) {
        em.getTransaction().begin();
        try {
            StoredProcedureQuery query = em.createNamedStoredProcedureQuery("create_active_prodejce");
            query.setParameter("jmeno", zamestnanec.getJmeno());
            query.setParameter("prijmeni", zamestnanec.getPrijmeni());
            query.setParameter("username", zamestnanec.getUsername());
            query.setParameter("password", zamestnanec.getPassword());
            query.setParameter("mesto", zamestnanec.getMesto());
            query.setParameter("ulice", zamestnanec.getUlice());
            query.setParameter("cislo_popisne", zamestnanec.getCisloPopisne());
            query.setParameter("plat", zamestnanec.getPlat());
            query.setParameter("email", zamestnanec.getEmail());
            query.setParameter("telefon", zamestnanec.getTelefon());
            query.execute();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
        em.getTransaction().commit();
    }
}
