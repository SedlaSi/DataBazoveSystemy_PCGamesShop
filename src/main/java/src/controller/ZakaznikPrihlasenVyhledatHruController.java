package src.controller;

import src.model.Exemplar;
import src.model.Vydavatel;
import src.model.Zanr;
import src.provider.Provider;

import java.util.List;

/**
 * Created by root on 21.4.16.
 */
public class ZakaznikPrihlasenVyhledatHruController extends TemplateController {

    public ZakaznikPrihlasenVyhledatHruController(Provider provider) {
        super(provider);
    }

    public List<Vydavatel> getVydavatelList(){
        return providerDAO.getVydavatelDAO().getVydavatelList();
    }

    public List<Zanr> getZanrList(){
        return providerDAO.getZanrDAO().getList();
    }

    public List<Exemplar> getHryDleParametru(String nazev, String vydavatel, String rokVydani, String kodExemplare){
        try{
            return providerDAO.getExemplarDAO().getConcreteList(nazev, vydavatel, rokVydani, kodExemplare);
        } catch (Exception e){
            System.out.println("exeption in ZakaznikPrihlasenVyhledatHruController.getHryDleParametru()");
            return null;
        }
    }

}
