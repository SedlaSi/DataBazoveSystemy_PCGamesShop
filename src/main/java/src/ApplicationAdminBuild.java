package src;

import src.controller.AdminVytvoritZamestnanceController;
import src.controller.ZakaznikLoginController;
import src.controller.ZamestnanecLoginController;
import src.controller.ZamestnanecVytvoritZakaznikaController;
import src.provider.Provider;
import src.provider.ProviderController;
import src.view.admin.AdminVytvoritZamestnance;

/**
 * Created by root on 15.4.16.
 */
public class ApplicationAdminBuild {


    public static void main(String [] args){


        Provider provider = new Provider();
        AdminVytvoritZamestnanceController admC = new AdminVytvoritZamestnanceController(provider);
        ZamestnanecVytvoritZakaznikaController zvzC = new ZamestnanecVytvoritZakaznikaController(provider);
        ZakaznikLoginController zkC = new ZakaznikLoginController(provider);
        ZamestnanecLoginController zlC = new ZamestnanecLoginController(provider);
        ProviderController providerController = new ProviderController(zvzC,admC,zkC,zlC);

        final AdminVytvoritZamestnance zkl =  new AdminVytvoritZamestnance(providerController);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                zkl.startFrame();
            }
        });

    }

}
