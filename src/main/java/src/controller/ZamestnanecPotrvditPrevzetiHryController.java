package src.controller;

import src.data.PujckaDAO;
import src.provider.Provider;

import java.sql.Date;

/**
 * Created by root on 22.4.16.
 */
public class ZamestnanecPotrvditPrevzetiHryController extends TemplateController{

    private PujckaDAO pujckaDAO;

    public ZamestnanecPotrvditPrevzetiHryController(Provider provider) {
        super(provider);
        pujckaDAO = providerDAO.getPujckaDAO();
    }

    public boolean potvrdit(String kodExemplare, String rok, String mesic, String den) {
        java.sql.Date date;
        try{
            date = new Date(Integer.parseInt(rok),Integer.parseInt(mesic),Integer.parseInt(den));
            pujckaDAO.updateDate(Integer.parseInt(kodExemplare),date);
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
