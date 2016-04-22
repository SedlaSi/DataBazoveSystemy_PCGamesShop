package src.data;

import src.model.Exemplar;
import src.util.Resources;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by root on 14.4.16.
 */
public class ExemplarDAO extends TemplateDAO<Exemplar> {
    public ExemplarDAO(Resources res) {
        super(res);
    }

    public List<Exemplar> getConcreteList(String nazev, String vydavatel, String rokVydani, String kodExemplare){
        if(nazev.isEmpty()){
            nazev = "NOT_SET";
        }
        if(vydavatel.isEmpty()){
            vydavatel = "NOT_SET";
        }
        if(rokVydani.isEmpty()){
            rokVydani = "NOT_SET";
        }
        if(kodExemplare.isEmpty()){
            kodExemplare = "NOT_SET";
        }

        Query q = em.createQuery("SELECT e FROM Exemplar e WHERE (e.hra.nazev = :nazev OR :nazev = 'NOT_SET') " +
                "AND (e.hra.vydavatel.nazev = :vydavatel OR :vydavatel = 'NOT_SET') " +
                "AND (e.rokVydani = :rokVydani OR :rokVydani = 'NOT_SET') " +
                "AND (e.id = :kodExemplare OR :id = 'NOT_SET')");
        q.setParameter("nazev",nazev);
        q.setParameter("vydavatel",vydavatel);
        q.setParameter("rokVydani",rokVydani);
        q.setParameter("kodExemplare",kodExemplare);

        return (List<Exemplar>)q.getResultList();
    }
}
