package src.controller;

import src.model.*;
import src.provider.Provider;
import src.provider.ProviderSession;

import java.util.List;

/**
 * Created by root on 21.4.16.
 */
public class ZakaznikPrihlasenVyhledatHruController extends TemplateController {

    ProviderSession zakaznikProviderSession;
    ProviderSession zamestnanecProviderSession;

    public ZakaznikPrihlasenVyhledatHruController(Provider provider) {
        super(provider);
        zakaznikProviderSession = provider.getZakaznikProviderSession();
        zamestnanecProviderSession = provider.getZamestnanecProviderSession();
    }

    public List<Vydavatel> getVydavatelList(){
        return providerDAO.getVydavatelDAO().getVydavatelList();
    }

    public List<Zanr> getZanrList(){
        return providerDAO.getZanrDAO().getList();
    }

    public List<Platforma> getPlatformaList(){
        return providerDAO.getPlatformaDAO().getList();
    }

    public List<Zakaznik> getZakaznikList(){
        return providerDAO.getZakaznikDAO().getList();
    }

    public List<Exemplar> getHryDleParametru(String nazev, String vydavatel, String rokVydani,
                                             String kodExemplare, List<String> zanry, List<String> platformy){

        try{
            java.sql.Date date = null;
            try{
                if(rokVydani != null && !rokVydani.isEmpty()){
                    date = java.sql.Date.valueOf(rokVydani+"-01-01");
                }
            } catch (Exception e){
                date = java.sql.Date.valueOf("1215-02-03");
            }

            int id;
            try{
                id = Integer.parseInt(kodExemplare);
            } catch (Exception e){
                id = -1;
            }
            return providerDAO
                    .getExemplarDAO()
                    .getConcreteList(nazev, vydavatel, date, id, zanry, platformy);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("exeption in ZakaznikPrihlasenVyhledatHruController.getHryDleParametru()");
            return null;
        }
    }

    public boolean zapujcitHru(long idExemplar, Zakaznik zakaznik){
        String zamestnanecUsername = null;

        if(zamestnanecProviderSession != null && zamestnanecProviderSession.getSession() != null) {
            zamestnanecUsername = zamestnanecProviderSession.getSession().getUserName();
        }

        if(zakaznik == null || zakaznik == null) {
            return false;
        }

        try{
            providerDAO.getExemplarDAO().zapujcitHru(idExemplar, zakaznik.getUsername(), zamestnanecUsername);
        } catch (Exception e){
            //e.printStackTrace();
            return false;
        }

        return true;
    }

    public List<Exemplar> getHryDleParametruNoUser(String nazev, String vydavatel, String rokVydani,
                                                   String kodExemplare, List<String> zanry, List<String> platformy) {
        try{
            java.sql.Date date = null;
            try{
                if(rokVydani != null && !rokVydani.isEmpty()){
                    date = java.sql.Date.valueOf(rokVydani+"-01-01");
                }
            } catch (Exception e){
                date = java.sql.Date.valueOf("1215-02-03");
            }

            int id;
            try{
                id = Integer.parseInt(kodExemplare);
            } catch (Exception e){
                id = -1;
            }
            return providerDAO
                    .getExemplarDAO()
                    .getConcreteList(nazev, vydavatel, date, id, zanry, platformy);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("exeption in ZakaznikPrihlasenVyhledatHruController.getHryDleParametru()");
            return null;
        }

    }
}
