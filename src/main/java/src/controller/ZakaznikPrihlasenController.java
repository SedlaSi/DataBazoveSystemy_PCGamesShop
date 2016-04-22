package src.controller;

import src.login.Session;
import src.model.Exemplar;
import src.model.Zakaznik;
import src.provider.Provider;
import src.provider.ProviderSession;

import java.util.List;

/**
 * Created by root on 20.4.16.
 */
public class ZakaznikPrihlasenController extends TemplateController {

    private Session session;
    private ProviderSession providerSession;

    public ZakaznikPrihlasenController(Provider provider) {
        super(provider);
        providerSession = provider.getZakaznikProviderSession();
    }


    public List<Exemplar> getVraceneHry(){
        if(session == null){
            session = providerSession.getSession();
        }
        List<Exemplar> ret = null;
        Zakaznik zakaznik = providerDAO
                .getZakaznikDAO()
                .getByUserName(session
                        .getUserName());
        if(zakaznik == null){
            return ret;
        }
        try{
            ret = providerDAO.getZakaznikDAO().getVraceneHry(zakaznik);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Exeption in ZakaznikPrihlasenController.getVraceneHry");
        }
        return ret;
    }

    public List<Exemplar> getNevraceneHry(){
        if(session == null){
            session = providerSession.getSession();
        }
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
