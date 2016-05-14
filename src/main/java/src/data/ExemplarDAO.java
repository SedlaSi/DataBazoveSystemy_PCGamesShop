package src.data;

import src.model.*;
import src.provider.ProviderDAO;
import src.util.Resources;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by root on 14.4.16.
 */
public class ExemplarDAO extends TemplateDAO<Exemplar> {

    private ProviderDAO providerDAO;

    public ExemplarDAO(Resources res, ProviderDAO providerDAO) {
        super(res);
        this.providerDAO = providerDAO;
    }

    public List<Exemplar> getConcreteList(String nazev, String vydavatel, Date rokVydani, long kodExemplare, List<String> zanry, List<String> platformy){
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Exemplar> criteriaQuery = criteriaBuilder.createQuery(Exemplar.class);
        Root<Exemplar> exemplarRoot = criteriaQuery.from(Exemplar.class);
        Join<Exemplar, Hra> hraJoin = exemplarRoot.join("hra", JoinType.INNER);
        Join<Exemplar, Platforma> platformaJoin = exemplarRoot.join("platforma", JoinType.INNER);
        Join<Hra, Vydavatel> vydavatelJoin = hraJoin.join("vydavatel", JoinType.INNER);
        Join<Hra, Zanr> zanrJoin = hraJoin.join("zanry", JoinType.INNER);
        criteriaQuery.select(exemplarRoot).distinct(true);

        ParameterExpression<String> hraNazev = null;
        ParameterExpression<String> hraVydavatel = null;
        ParameterExpression<Date> exemplarRokVydani = null;
        ParameterExpression<Long> exemplarKod = null;

        List<Predicate> predicates = new ArrayList<>();

        if(nazev != null && !nazev.isEmpty()){
            hraNazev = criteriaBuilder.parameter(String.class);
            predicates.add(criteriaBuilder.equal(hraJoin.get("nazev"), hraNazev));
        }

        if(vydavatel != null && !vydavatel.isEmpty()){
            hraVydavatel = criteriaBuilder.parameter(String.class);
            predicates.add(criteriaBuilder.equal(vydavatelJoin.get("nazev"), hraVydavatel));
        }

        if(rokVydani != null){
            exemplarRokVydani = criteriaBuilder.parameter(Date.class);
            predicates.add(criteriaBuilder.equal(exemplarRoot.get("rokVydani"), exemplarRokVydani));
        }

        if(kodExemplare != -1){
            exemplarKod = criteriaBuilder.parameter(Long.class);
            predicates.add(criteriaBuilder.equal(exemplarRoot.get("id"), exemplarKod));
        }

        if(zanry != null && !zanry.isEmpty()) {
            predicates.add(zanrJoin.get("nazev").in(zanry));
        }

        if(platformy != null && !platformy.isEmpty()) {
            predicates.add(platformaJoin.get("nazev").in(platformy));
        }

        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));

        TypedQuery<Exemplar> query = em.createQuery(criteriaQuery);

        if(nazev != null && !nazev.isEmpty()){
            query.setParameter(hraNazev, nazev);
        }

        if(vydavatel != null && !vydavatel.isEmpty()){
            query.setParameter(hraVydavatel, vydavatel);
        }

        if(rokVydani != null){
           query.setParameter(exemplarRokVydani, rokVydani);
        }

        if(kodExemplare != -1){
            query.setParameter(exemplarKod, kodExemplare);
        }

        return query.getResultList();
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

    public Exemplar getByIdTransactionFree(int id){
        Exemplar t;
        Query q = em.createQuery("SELECT x FROM Exemplar x WHERE x.id = :id");
        q.setParameter("id",id);
        t =(Exemplar) q.getSingleResult();
        return t;
    }

    public void zapujcitHru(int idExemplar, String zakaznikUserName, String zamestnanecUserName) throws Exception{
        Zakaznik zakaznik;
        Zamestnanec zamestnanec;
        Exemplar exemplar;
        Pujcka pujcka = new Pujcka();
        em.getTransaction().begin();
        try{
            exemplar = getByIdTransactionFree(idExemplar);
            zakaznik = providerDAO.getZakaznikDAO().getByUserName(zakaznikUserName);
//            zamestnanec = providerDAO.getKasaDAO().getLoggedZamestnanec();
//            if(zamestnanec == null){
//                throw new Exception("noone logged in");
//            }
            zamestnanec = providerDAO.getZamestnanecDAO().getByUserName(zamestnanecUserName);
            pujcka.setZakaznik(zakaznik);
            pujcka.setZamestnanec(zamestnanec);
            pujcka.setExemplar(exemplar);
            pujcka.setPujceno(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
            providerDAO.getPujckaDAO().createTransactionFree(pujcka);

        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new Exception("rollback invoked");
        }
        em.getTransaction().commit();
    }

    public List<Exemplar> getList() {
        return (List<Exemplar>)em.createQuery("SELECT e FROM Exemplar e").getResultList();
    }
}
