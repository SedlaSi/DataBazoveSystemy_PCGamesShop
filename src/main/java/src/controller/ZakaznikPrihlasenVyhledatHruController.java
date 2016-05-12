package src.controller;

import src.model.Exemplar;
import src.model.Platforma;
import src.model.Vydavatel;
import src.model.Zanr;
import src.provider.Provider;
import src.provider.ProviderSession;

import java.util.List;

/**
 * Created by root on 21.4.16.
 */
public class ZakaznikPrihlasenVyhledatHruController extends TemplateController {

    ProviderSession zakaznikProviderSession;

    public ZakaznikPrihlasenVyhledatHruController(Provider provider) {
        super(provider);
        zakaznikProviderSession = provider.getZakaznikProviderSession();
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

    public List<Exemplar> getHryDleParametru(String nazev, String vydavatel, String rokVydani,
                                             String kodExemplare, List<String> zanry, List<String> platformy){

        try{
            java.sql.Date date = null;
            try{
                if(rokVydani != null && !rokVydani.isEmpty()){
                    date = java.sql.Date.valueOf(rokVydani+"-02-03");
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

    public boolean zapujcitHru(int idExemplar){

        try{
            providerDAO.getExemplarDAO().zapujcitHru(idExemplar,zakaznikProviderSession.getSession().getUserName());
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
                    date = java.sql.Date.valueOf(rokVydani+"-02-03");
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
