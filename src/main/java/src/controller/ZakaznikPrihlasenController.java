package src.controller;

import src.login.Session;
import src.model.Exemplar;
import src.model.Zakaznik;
import src.provider.Provider;

import java.util.List;

/**
 * Created by root on 20.4.16.
 */
public class ZakaznikPrihlasenController extends TemplateController {

    private Session session;

    public ZakaznikPrihlasenController(Provider provider) {
        super(provider);
        session = provider.getProviderSession().getSession();
    }


    public List<Exemplar> getVraceneHry(){
        List<Exemplar> ret = null;
        Zakaznik zakaznik = providerDAO.getZakaznikDAO().getByUserName(session.getUserName());
        if(zakaznik == null){
            return ret;
        }
        try{
            ret = providerDAO.getZakaznikDAO().getVraceneHry(zakaznik);
        } catch (Exception e){
            System.out.println("Exeption in ZakaznikPrihlasenController.getVraceneHry");
        }
        return ret;
    }

    public List<Exemplar> getNevraceneHry(){
        List<Exemplar> ret = null;
        Zakaznik zakaznik = providerDAO.getZakaznikDAO().getByUserName(session.getUserName());
        if(zakaznik == null){
            return ret;
        }
        try{
            ret = providerDAO.getZakaznikDAO().getNevraceneHry(zakaznik);
        } catch (Exception e){
            System.out.println("Exeption in ZakaznikPrihlasenController.getNevraceneHry");
        }
        return ret;
    }

}
