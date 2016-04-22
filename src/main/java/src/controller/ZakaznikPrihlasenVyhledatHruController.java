package src.controller;

import src.model.*;
import src.provider.Provider;
import src.provider.ProviderSession;

import java.sql.Date;
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

    public List<Exemplar> getHryDleParametru(String nazev, String vydavatel, String rokVydani, String kodExemplare){


        try{
            java.sql.Date date;
            try{
                date = new Date(Integer.parseInt(rokVydani),2,3);
            } catch (Exception e){
                date = null;
            }

            int id;
            try{
                id = Integer.parseInt(kodExemplare);
            } catch (Exception e){
                id = -1;
            }
            return providerDAO
                    .getExemplarDAO()
                    .getConcreteList(zakaznikProviderSession.getSession().getUserName(),nazev, vydavatel, date, id);
        } catch (Exception e){
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
