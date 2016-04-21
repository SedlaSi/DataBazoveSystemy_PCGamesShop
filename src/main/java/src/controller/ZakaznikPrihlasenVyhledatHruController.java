package src.controller;

import src.model.Vydavatel;
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

}
