package src;

import src.controller.*;
import src.provider.Provider;
import src.provider.ProviderController;
import src.view.HlavniNabidka;

public class ApplicationHlavniNabidka {
    public static void main(String[] args) {

        Provider provider = new Provider();
        AdminVytvoritZamestnanceController admC = new AdminVytvoritZamestnanceController(provider);
        ZakaznikLoginController zkC = new ZakaznikLoginController(provider);
        ZamestnanecLoginController zlC = new ZamestnanecLoginController(provider);
        ZakaznikPrihlasenController zkpC = new ZakaznikPrihlasenController(provider);
        ZakaznikPrihlasenVyhledatHruController zpvC = new ZakaznikPrihlasenVyhledatHruController(provider);
        ZamestnanecPotrvditPrevzetiHryController zpphC = new ZamestnanecPotrvditPrevzetiHryController(provider);
        HlavniNabidkaController hnC = new HlavniNabidkaController(provider);
        ProviderController providerController = new ProviderController(zpphC, zpvC, zkpC, admC, zkC, zlC, hnC);

        HlavniNabidka hlavniNabidka = new HlavniNabidka(providerController, provider);
        hlavniNabidka.startFrame();
    }
}
