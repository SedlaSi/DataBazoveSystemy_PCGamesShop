package src.controller;

import src.data.PujckaDAO;
import src.provider.Provider;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

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
        try{
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
            java.util.Date date = null;

            date = format.parse(datum);

            pujckaDAO.updateDate(Integer.parseInt(kodExemplare),date);
        }  catch (ParseException e) {
            System.err.println("Nepodarilo se naparsovat datum.");
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
