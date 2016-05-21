package src.controller;

import src.data.PujckaDAO;
import src.provider.Provider;

/**
 * Created by root on 22.4.16.
 */
public class ZamestnanecPotrvditPrevzetiHryController extends TemplateController{

    private PujckaDAO pujckaDAO;

    public ZamestnanecPotrvditPrevzetiHryController(Provider provider) {
        super(provider);
        pujckaDAO = providerDAO.getPujckaDAO();
    }

    public boolean potvrdit(String kodExemplare, String datum) {
        java.sql.Date date;
        try{
            date = java.sql.Date.valueOf(datum);
            pujckaDAO.updateDate(Integer.parseInt(kodExemplare),date);
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String usernameZakaznikaPujckyExemplare(String idExemplare){
        try {
            int id = Integer.parseInt(idExemplare);
            return pujckaDAO.getUserNameOfPujckaByExemplarId(id);
        } catch (Exception e){
            return null;
        }
    }
}
