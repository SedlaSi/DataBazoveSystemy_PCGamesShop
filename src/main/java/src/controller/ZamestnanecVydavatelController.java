package src.controller;

import src.model.Vydavatel;
import src.provider.Provider;

import java.util.List;

/**
 * Created by root on 17.4.16.
 */
public class ZamestnanecVydavatelController extends TemplateController {

    public ZamestnanecVydavatelController(Provider provider) {
        super(provider);
    }

    public List<Vydavatel> getVydavatele(){
        return providerDAO.getVydavatelDAO().getVydavatelList();
    }
}
