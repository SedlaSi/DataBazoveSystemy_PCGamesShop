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

    public List<Exemplar> getConcreteList(String zakaznikUserName,String nazev, String vydavatel, java.sql.Date rokVydani, int kodExemplare){

        String nazevQuery = "e.hra.nazev = :nazev ";
        String vydavatelQuery = "AND e.hra.vydavatel.nazev = :vydavatel ";
        String rokVydaniQuery = "AND e.rokVydani = :rokVydani ";
        String kodExemplareQuery = "AND e.id = :kodExemplare";

        if(nazev.isEmpty()){
            nazevQuery = " 1 = 1 ";
        }
        if(vydavatel == null || vydavatel.isEmpty()){
            vydavatelQuery = "";
        }
        if(rokVydani == null){
            rokVydaniQuery = "";
        }
        if(kodExemplare == -1){
            kodExemplareQuery = "";
        }
        Query q = em.createQuery("SELECT e FROM Exemplar e WHERE "
                + nazevQuery
                + vydavatelQuery
                + rokVydaniQuery
                + kodExemplareQuery
                );
        if(!nazev.isEmpty()){
            q.setParameter("nazev",nazev);
        }
        if(vydavatel != null && !vydavatel.isEmpty()){
            q.setParameter("vydavatel",vydavatel);
        }
        if(rokVydani != null){
            q.setParameter("rokVydani",rokVydani);
        }
        if(kodExemplare != -1){
            q.setParameter("kodExemplare",kodExemplare);
        }
        return (List<Exemplar>)q.getResultList();
    }

    @Override
    public Exemplar getById(int id){
        Exemplar t;
        em.getTransaction().begin();
        Query q = em.createQuery("SELECT x FROM Exemplar x WHERE x.id = :id");
        q.setParameter("id",id);
        t =(Exemplar) q.getSingleResult();
        em.getTransaction().commit();
        return t;
    }
}
