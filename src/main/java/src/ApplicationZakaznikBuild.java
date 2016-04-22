package src;

import src.controller.*;
import src.provider.Provider;
import src.provider.ProviderController;
import src.view.zakaznik.ZakaznikLogin;
import src.view.zakaznik.ZakaznikPrihlasen;

/**
 * Created by root on 15.4.16.
 */
public class ApplicationZakaznikBuild {



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
        ProviderController providerController = new ProviderController(zpvC,zkpC,admSC,zvC,zvzC,admC,zkC,zlC);

        startZakaznikLogin(providerController);
        //startZakaznikPrihlasen(providerController);
    }

    public static void startZakaznikLogin(ProviderController providerController){
        final ZakaznikLogin zkl =  new ZakaznikLogin(providerController);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                zkl.startFrame();
            }
        });
    }

    public static void startZakaznikPrihlasen(ProviderController providerController){
        final ZakaznikPrihlasen zkl =  new ZakaznikPrihlasen(providerController);

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                zkl.startFrame();
            }
        });
    }



}
