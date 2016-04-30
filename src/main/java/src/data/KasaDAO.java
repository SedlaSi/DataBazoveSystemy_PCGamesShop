package src.data;

import src.model.Kasa;
import src.model.Zamestnanec;
import src.util.Resources;

import javax.persistence.Query;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

/**
 * Created by root on 30.4.16.
 */
public class KasaDAO extends TemplateDAO<Kasa> {

    private ZamestnanecDAO zamestnanecDAO;

    public KasaDAO(Resources res, ZamestnanecDAO zamestnanecDAO) {
        super(res);
        this.zamestnanecDAO = zamestnanecDAO;
    }

    /***
     *
     * WARNING:
     *          NO TRANSACTION METHOD
     */
    @Override
    public void create(Kasa k){
        em.persist(k);
    }

    /***
     *
     * WARNING:
     *          NO TRANSACTION METHOD
     */
    @Override
    public Kasa update(Kasa k){
        return em.merge(k);
    }

    public void loginKasa(String username, String password) throws Exception{
        boolean failure = false;
        em.getTransaction().begin();
        Zamestnanec z;
        try{
            z = zamestnanecDAO.getByUserName(username);
        } catch (Exception e){
            z = null;
        }
        if(z != null && z.getPassword().equals(password)){
            if(isNotLoged(z)){
                try{
                    Date date = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
                    Kasa k = new Kasa();
                    k.setPrihlaseni(date);
                    k.setZamestnanec(z);
                    this.create(k);
                } catch (Exception e){
                    e.printStackTrace();
                    failure = true;
                }

            } else {
                System.out.println("fail in logging");
                failure = true;
            }
        } else {
            System.out.println("fail in zakaznik");
            failure = true;
        }
        if(failure){
            em.getTransaction().rollback();
            throw new Exception("User is still logged in the system or wrong username / password.");
        }
        em.getTransaction().commit();
    }

    public void logoutKasa() throws Exception {
        Date date = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
        try{
            em.getTransaction().begin();
            Kasa k = getLoggedKasa();
            k.setOdhlaseni(date);
            this.update(k);
        } finally {
            em.getTransaction().commit();
        }

    }

    private Kasa getLoggedKasa(){
        Query q = em.createQuery("SELECT k FROM Kasa k WHERE k.odhlaseni = NULL");
        return (Kasa) q.getSingleResult();
    }

    private boolean isNotLoged(Zamestnanec z){
        Query q = em.createQuery("SELECT k FROM Kasa k WHERE k.zamestnanec = :z  AND k.odhlaseni = NULL");
        q.setParameter("z",z);
        List<Kasa> list = q.getResultList();
        if(list == null || list.isEmpty()){
            return true;
        } else {
            return false;
        }
    }

    public Zamestnanec getLoggedZamestnanec(){
        Zamestnanec z;
        try{
            z = (Zamestnanec)em.createQuery("SELECT z FROM Zamestnanec z WHERE EXISTS (SELECT k FROM z.kasa k WHERE k.odhlaseni = NULL) ").getSingleResult();
        } catch (Exception e){
            z = null;
        }
        return z;
    }
}
