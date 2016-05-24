package src.data;

import src.model.Pujcka;
import src.util.Resources;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by root on 22.4.16.
 */
public class PujckaDAO extends TemplateDAO<Pujcka> {
    public PujckaDAO(Resources res) {
        super(res);
    }

    Pujcka getByExemplarId(long idExemplare) {
        Query q = em.createNamedQuery("Pujcka.getByExemplarId");
        q.setParameter("id", idExemplare);

        List<Pujcka> pujckaList = (List<Pujcka>) q.getResultList();

        if (pujckaList == null || pujckaList.size() == 0) {
            return null;
        }

        return pujckaList.get(0);
    }

    @Override
    public Pujcka update(Pujcka p) {
        em.getTransaction().begin();
        p = em.merge(p);
        em.getTransaction().commit();
        return p;
    }

    void createTransactionFree(Pujcka p) {
        em.persist(p);
    }

    public void updateDate(int idExemplar, java.util.Date date) throws Exception {
        em.getTransaction().begin();
        Pujcka pujcka = this.getByExemplarId(idExemplar);

        if (pujcka == null) {
            em.getTransaction().rollback();
            throw new Exception("Invalid exemplar id.");
        }

        if (date.before(pujcka.getPujceno())) {
            System.out.println("date compare fail");
            em.getTransaction().rollback();
            throw new Exception("Inserted date smaller than previous date.");
        }
        pujcka.setVraceno(date);
        em.merge(pujcka);
        em.getTransaction().commit();
    }

}
