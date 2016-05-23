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

    public List<Exemplar> getConcreteList(String nazev, String vydavatel, Date rokVydani, long kodExemplare, List<String> zanry, List<String> platformy, boolean showAllResults){
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

        Subquery<Pujcka> subquery = criteriaQuery.subquery(Pujcka.class);
        Root<Pujcka> fromProject = subquery.from(Pujcka.class);
        Join<Pujcka, Exemplar> joinEx = fromProject.join("exemplar");
        subquery.select(joinEx.get("id"));
        subquery.where(criteriaBuilder.and(criteriaBuilder.isNotNull(fromProject.get("pujceno")), criteriaBuilder.isNull(fromProject.get("vraceno"))));
        if(!showAllResults){
            predicates.add(criteriaBuilder.not(criteriaBuilder.in(exemplarRoot.get("id")).value(subquery)));
        }
        predicates.add(criteriaBuilder.equal(exemplarRoot.get("aktivni"), true));

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

        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]))).orderBy(criteriaBuilder.asc(hraJoin.get("nazev")));

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
        Exemplar t = null;
        Query q = em.createNamedQuery("Exemplar.getById");
        q.setParameter("id",id);
        List<Exemplar> list =(List<Exemplar>) q.getResultList();
        if(list != null && list.size() == 1){
            t = list.get(0);
        }
        return t;
    }

    private Exemplar getByIdTransactionFree(long id){
        Exemplar t = null;
        Query q = em.createNamedQuery("Exemplar.getById");
        q.setParameter("id",id);
        List<Exemplar> list =(List<Exemplar>) q.getResultList();
        if( list != null && list.size() == 1){
            t = list.get(0);
        }
        return t;
    }

    public void zapujcitHru(long idExemplar, String zakaznikUserName, String zamestnanecUserName) throws Exception{
        Zakaznik zakaznik;
        Zamestnanec zamestnanec;
        Exemplar exemplar;
        Pujcka pujcka = new Pujcka();
        em.getTransaction().begin();
        try{
            if(providerDAO.getPujckaDAO().getByExemplarId(idExemplar) != null) {
                //em.getTransaction().rollback();
                throw new Exception("rollback invoked, exemplar has been loaned");
            }

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
            pujcka.setCena(exemplar.getCena());
            providerDAO.getPujckaDAO().createTransactionFree(pujcka);

        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new Exception("rollback invoked");
        }
        em.getTransaction().commit();
    }

    public List<Exemplar> getList() {
        return (List<Exemplar>)em.createNamedQuery("Exemplar.getList").getResultList();
    }
}
