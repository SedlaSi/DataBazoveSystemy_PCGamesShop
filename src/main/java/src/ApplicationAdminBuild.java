package src;

import src.controller.*;
import src.provider.Provider;
import src.provider.ProviderController;
import src.view.admin.AdminSmazatZamestnance;
import src.view.admin.AdminVytvoritZamestnance;

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
        ProviderController providerController = new ProviderController(admSC,zvC,zvzC,admC,zkC,zlC);


       // startAdminSmazatZamestnance(providerController);
        startAdminVytvoritZamestnance(providerController);


    }

    public static void startAdminSmazatZamestnance(ProviderController providerController){
        final AdminSmazatZamestnance zkl =  new AdminSmazatZamestnance(providerController);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                zkl.startFrame();
            }
        });
    }

    public static void startAdminVytvoritZamestnance(ProviderController providerController){
        final AdminVytvoritZamestnance zkl =  new AdminVytvoritZamestnance(providerController);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                zkl.startFrame();
            }
        });
    }

}
