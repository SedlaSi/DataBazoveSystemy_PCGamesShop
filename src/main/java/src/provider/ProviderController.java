package src.provider;

import src.controller.ZakaznikLoginController;
import src.controller.ZamestnanecLoginController;

/**
 * Created by root on 15.4.16.
 */
public class ProviderController {

    private ZakaznikLoginController zakaznikLoginController;

    private ZamestnanecLoginController zamestnanecLoginController;

    public ProviderController(ZakaznikLoginController zakaznikLoginController, ZamestnanecLoginController zamestnanecLoginController){
        this.zakaznikLoginController = zakaznikLoginController;
        this.zamestnanecLoginController = zamestnanecLoginController;

    }

    public ZamestnanecLoginController getZamestnanecLoginController() {
        return zamestnanecLoginController;
    }

    public ZakaznikLoginController getZakaznikLoginController() {
        return zakaznikLoginController;
    }
}
