package src.provider;

import src.controller.*;

/**
 * Created by root on 15.4.16.
 */
public class ProviderController {

    private AdminVytvoritZamestnanceController adminVytvoritZamestnanceController;
    private ZakaznikLoginController zakaznikLoginController;
    private ZamestnanecLoginController zamestnanecLoginController;
    private ZakaznikPrihlasenController zakaznikPrihlasenController;
    private ZakaznikPrihlasenVyhledatHruController zakaznikPrihlasenVyhledatHruController;
    private ZamestnanecPotrvditPrevzetiHryController zamestnanecPotrvditPrevzetiHryController;
    private HlavniNabidkaController hlavniNabidkaController;

    public ProviderController(ZamestnanecPotrvditPrevzetiHryController zamestnanecPotrvditPrevzetiHryController, ZakaznikPrihlasenVyhledatHruController zakaznikPrihlasenVyhledatHruController, ZakaznikPrihlasenController zakaznikPrihlasenController, AdminVytvoritZamestnanceController adminVytvoritZamestnanceController,
                              ZakaznikLoginController zakaznikLoginController,
                              ZamestnanecLoginController zamestnanecLoginController, HlavniNabidkaController hlavniNabidkaController) {
        this.adminVytvoritZamestnanceController = adminVytvoritZamestnanceController;
        this.zakaznikLoginController = zakaznikLoginController;
        this.zamestnanecLoginController = zamestnanecLoginController;
        this.zakaznikPrihlasenController = zakaznikPrihlasenController;
        this.zakaznikPrihlasenVyhledatHruController = zakaznikPrihlasenVyhledatHruController;
        this.zamestnanecPotrvditPrevzetiHryController = zamestnanecPotrvditPrevzetiHryController;
        this.hlavniNabidkaController = hlavniNabidkaController;
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

    public ZakaznikPrihlasenController getZakaznikPrihlasenController() {
        return zakaznikPrihlasenController;
    }

    public ZakaznikPrihlasenVyhledatHruController getZakaznikPrihlasenVyhledatHruController() {
        return zakaznikPrihlasenVyhledatHruController;
    }

    public ZamestnanecPotrvditPrevzetiHryController getZamestnanecPotrvditPrevzetiHryController() {
        return zamestnanecPotrvditPrevzetiHryController;
    }

    public HlavniNabidkaController getHlavniNabidkaController() {
        return hlavniNabidkaController;
    }

}
