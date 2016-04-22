package src.data;

import src.model.Pujcka;
import src.util.Resources;

import javax.persistence.Query;

/**
 * Created by root on 22.4.16.
 */
public class PujckaDAO extends TemplateDAO<Pujcka> {
    public PujckaDAO(Resources res) {
        super(res);
    }

    public Pujcka getByExemplarId(int idExemplare) throws Exception{
        Query q =  em.createQuery("SELECT p FROM Pujcka p where p.exemplar.id = :id AND p.vraceno = NULL");
        q.setParameter("id",idExemplare);

        return (Pujcka)q.getSingleResult();
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

    public void updateDate(int idExemplar, java.sql.Date date) throws Exception {
        em.getTransaction().begin();
        Pujcka pujcka = this.getByExemplarId(idExemplar);
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
