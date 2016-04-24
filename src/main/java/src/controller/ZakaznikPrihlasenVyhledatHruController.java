package src.controller;

import src.model.*;
import src.provider.Provider;
import src.provider.ProviderSession;

import java.util.Calendar;
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
                    .getConcreteList(zakaznikProviderSession.getSession().getUserName(),nazev, vydavatel, date, id, zanry, platformy);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("exeption in ZakaznikPrihlasenVyhledatHruController.getHryDleParametru()");
            return null;
        }
    }

    public boolean zapujcitHru(int idExemplar){
        String zamestnanecUserName = zamestnanecProviderSession.getSession().getUserName();
        String zakaznikUserName = zakaznikProviderSession.getSession().getUserName();
        Zakaznik zakaznik;
        Zamestnanec zamestnanec;
        Exemplar exemplar;
        Pujcka pujcka = new Pujcka();
        try{
            exemplar = providerDAO.getExemplarDAO().getById(idExemplar);
            zakaznik = providerDAO.getZakaznikDAO().getByUserName(zakaznikUserName);
            zamestnanec = providerDAO.getZamestnanecDAO().getByUserName(zamestnanecUserName);
            pujcka.setZakaznik(zakaznik);
            pujcka.setZamestnanec(zamestnanec);
            pujcka.setExemplar(exemplar);
            pujcka.setPujceno(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
            providerDAO.getPujckaDAO().create(pujcka);
        } catch (Exception e){
            return false;
        }
        return true;
    }
}
