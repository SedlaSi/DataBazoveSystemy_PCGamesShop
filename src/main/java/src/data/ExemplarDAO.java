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

    public List<Exemplar> getConcreteList(String zakaznikUserName,String nazev, String vydavatel,
                                          java.sql.Date rokVydani, int kodExemplare,
                                          List<String> zanry, List<String> platformy){
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
        String zanryQuery = getZanryQuery(zanry);
        String platformyQuery = getPlatformyQuery(platformy);

        Query q = em.createQuery("SELECT e FROM Exemplar e WHERE "
                + nazevQuery
                + vydavatelQuery
                + rokVydaniQuery
                + kodExemplareQuery
                + zanryQuery
                + platformyQuery
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
        if(zanry != null){
            for(int i = 0; i < zanry.size(); i++){
                System.out.println(zanry.get(i));
                q.setParameter("z"+i,zanry.get(i));
            }
        }
        if(platformy != null){
            for(int i = 0; i < platformy.size(); i++){
                System.out.println(platformy.get(i));
                q.setParameter("p"+i,platformy.get(i));
            }
        }

        return (List<Exemplar>)q.getResultList();
    }

    private String getPlatformyQuery(List<String> platformy) {
        StringBuilder query = new StringBuilder("");
        if(!platformy.isEmpty()){
            query.append(" AND (");
            query.append("e.platforma.nazev = :p"+0+" ");
            for(int i = 1; i < platformy.size() ; i++){
                query.append("OR e.platforma.nazev = :p"+i+" ");
            }
            query.append(")");
        }
        return query.toString();
    }

    private String getZanryQuery(List<String> zanry) {
        StringBuilder query = new StringBuilder("");
        if(!zanry.isEmpty()){
            query.append(" AND (");
            query.append("e.zanr.nazev = :z"+0+" ");
            for(int i = 1; i < zanry.size() ; i++){
                query.append("OR e.zanr.nazev = :z"+i+" ");
            }
            query.append(")");
        }
        return query.toString();
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
