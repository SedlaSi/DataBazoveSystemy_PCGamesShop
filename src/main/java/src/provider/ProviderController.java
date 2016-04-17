package src.provider;

import src.controller.*;

/**
 * Created by root on 15.4.16.
 */
public class ProviderController {

    private AdminVytvoritZamestnanceController adminVytvoritZamestnanceController;
    private ZakaznikLoginController zakaznikLoginController;
    private ZamestnanecLoginController zamestnanecLoginController;
    private ZamestnanecVytvoritZakaznikaController zamestnanecVytvoritZakaznikaController;
    private ZamestnanecVydavatelController zamestnanecVydavatelController;

    public ProviderController(ZamestnanecVydavatelController zamestnanecVydavatelController,ZamestnanecVytvoritZakaznikaController zamestnanecVytvoritZakaznikaController, AdminVytvoritZamestnanceController adminVytvoritZamestnanceController,
                              ZakaznikLoginController zakaznikLoginController,
                              ZamestnanecLoginController zamestnanecLoginController){
        this.adminVytvoritZamestnanceController = adminVytvoritZamestnanceController;
        this.zakaznikLoginController = zakaznikLoginController;
        this.zamestnanecLoginController = zamestnanecLoginController;
        this.zamestnanecVytvoritZakaznikaController = zamestnanecVytvoritZakaznikaController;
        this.zamestnanecVydavatelController = zamestnanecVydavatelController;

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

    public ZamestnanecVydavatelController getZamestnanecVydavatelController() {
        return zamestnanecVydavatelController;
    }
}
