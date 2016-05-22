package src;

import src.controller.*;
import src.model.Pozice;
import src.provider.Provider;
import src.provider.ProviderController;
import src.view.admin.AdminSmazatZamestnance;
import src.view.admin.AdminVytvoritZamestnance;

import java.util.List;

/**
 * Created by root on 15.4.16.
 */
public class ApplicationAdminBuild {


    public static void main(String [] args){

        Provider provider = new Provider();
        AdminSmazatZamestnanceController admSC = new AdminSmazatZamestnanceController(provider);
        AdminVytvoritZamestnanceController admC = new AdminVytvoritZamestnanceController(provider);
        ZamestnanecVytvoritZakaznikaController zvzC = new ZamestnanecVytvoritZakaznikaController(provider);
        ZakaznikLoginController zkC = new ZakaznikLoginController(provider);
        ZamestnanecLoginController zlC = new ZamestnanecLoginController(provider);
        ZamestnanecVydavatelController zvC = new ZamestnanecVydavatelController(provider);
        ZakaznikPrihlasenController zkpC = new ZakaznikPrihlasenController(provider);
        ZakaznikPrihlasenVyhledatHruController zpvC = new ZakaznikPrihlasenVyhledatHruController(provider);
        ZamestnanecPotrvditPrevzetiHryController zpphC = new ZamestnanecPotrvditPrevzetiHryController(provider);
        ProviderController providerController = new ProviderController(zpphC,zpvC,zkpC,admSC,zvC,zvzC,admC,zkC,zlC);


        //startAdminSmazatZamestnance(providerController);
        startAdminVytvoritZamestnance(providerController, provider);


    }

    public static void startAdminSmazatZamestnance(ProviderController providerController){
        final AdminSmazatZamestnance zkl =  new AdminSmazatZamestnance(providerController);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                zkl.startFrame();
            }
        });
    }

    public static void startAdminVytvoritZamestnance(ProviderController providerController, Provider provider){
        List<Pozice> pozice = provider.getProviderDAO().getPoziceDAO().getList();

        if(pozice != null) {
            final AdminVytvoritZamestnance zkl =  new AdminVytvoritZamestnance(providerController, pozice);
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    zkl.createFrame();
                }
            });
        } else {
            System.err.println("Databáze neobsahuje žádné pracovní pozice");
        }

    }

}
