package src.provider;

import src.controller.AdminVytvoritZamestnanceController;
import src.controller.ZakaznikLoginController;
import src.controller.ZamestnanecLoginController;
import src.controller.ZamestnanecVytvoritZakaznikaController;

/**
 * Created by root on 15.4.16.
 */
public class ProviderController {

    private AdminVytvoritZamestnanceController adminVytvoritZamestnanceController;
    private ZakaznikLoginController zakaznikLoginController;
    private ZamestnanecLoginController zamestnanecLoginController;
    private ZamestnanecVytvoritZakaznikaController zamestnanecVytvoritZakaznikaController;

    public ProviderController(ZamestnanecVytvoritZakaznikaController zamestnanecVytvoritZakaznikaController, AdminVytvoritZamestnanceController adminVytvoritZamestnanceController,
                              ZakaznikLoginController zakaznikLoginController,
                              ZamestnanecLoginController zamestnanecLoginController){
        this.adminVytvoritZamestnanceController = adminVytvoritZamestnanceController;
        this.zakaznikLoginController = zakaznikLoginController;
        this.zamestnanecLoginController = zamestnanecLoginController;
        this.zamestnanecVytvoritZakaznikaController = zamestnanecVytvoritZakaznikaController;

    }

    public ZamestnanecLoginController getZamestnanecLoginController() {
        return zamestnanecLoginController;
    }

    public ZakaznikLoginController getZakaznikLoginController() {
        return zakaznikLoginController;
    }

    public AdminVytvoritZamestnanceController getAdminVytvoritZamestnanceController() {
        return adminVytvoritZamestnanceController;
    }

    public ZamestnanecVytvoritZakaznikaController getZamestnanecVytvoritZakaznikaController() {
        return zamestnanecVytvoritZakaznikaController;
    }
}
