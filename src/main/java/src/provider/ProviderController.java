package src.provider;

import src.controller.AdminVytvoritZamestnanceController;
import src.controller.ZakaznikLoginController;
import src.controller.ZamestnanecLoginController;

/**
 * Created by root on 15.4.16.
 */
public class ProviderController {

    private AdminVytvoritZamestnanceController adminVytvoritZamestnanceController;
    private ZakaznikLoginController zakaznikLoginController;
    private ZamestnanecLoginController zamestnanecLoginController;

    public ProviderController(AdminVytvoritZamestnanceController adminVytvoritZamestnanceController,
                              ZakaznikLoginController zakaznikLoginController,
                              ZamestnanecLoginController zamestnanecLoginController){
        this.adminVytvoritZamestnanceController = adminVytvoritZamestnanceController;
        this.zakaznikLoginController = zakaznikLoginController;
        this.zamestnanecLoginController = zamestnanecLoginController;

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
}
