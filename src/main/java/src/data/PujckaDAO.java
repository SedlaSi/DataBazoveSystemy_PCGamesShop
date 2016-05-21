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

    Pujcka getByExemplarId(int idExemplare) throws Exception{
        Query q =  em.createNamedQuery("Pujcka.getByExemplarId");
        q.setParameter("id",idExemplare);

        List<Pujcka> pujckaList =(List<Pujcka>) q.getResultList();

        if(pujckaList.size() == 0) {
            return null;
        }

        return pujckaList.get(0);
    }

    public String getUserNameOfPujckaByExemplarId(int idExemplare){
        Query q =  em.createNamedQuery("Pujcka.getByExemplarId");
        q.setParameter("id",idExemplare);
        List<Pujcka> pujckaList =(List<Pujcka>) q.getResultList();
        if(pujckaList == null || pujckaList.size() == 0) {
            return null;
        }
        Pujcka pujcka = pujckaList.get(0);
        return pujcka.getZakaznik().getUsername();
    }

    /**
    *    WARNING:
    *          update without transaction
    */
    @Override
    public Pujcka update(Pujcka p){
        Pujcka p2 = em.merge(p);
        return p2;
    }

    void createTransactionFree(Pujcka p){
        em.persist(p);
    }

    public void updateDate(int idExemplar, java.sql.Date date) throws Exception {
        em.getTransaction().begin();
        Pujcka pujcka = this.getByExemplarId(idExemplar);

        if(pujcka == null) {
            em.getTransaction().rollback();
            throw new Exception("Invalid exemplar id.");
        }

        if(date.compareTo(pujcka.getPujceno()) == -1){
            System.out.println("date compare fail");
            em.getTransaction().rollback();
            throw new Exception("Inserted date smaller than previous date.");
        }
        pujcka.setVraceno(date);
        this.update(pujcka);
        em.getTransaction().commit();
    }

}
