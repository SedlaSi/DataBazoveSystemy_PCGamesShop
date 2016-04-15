package src.controller;

import src.data.ZamestnanecDAO;
import src.model.Zamestnanec;
import src.provider.Provider;

/**
 * Created by root on 15.4.16.
 */
public class AdminVytvoritZamestnanceController extends TemplateController{

    private ZamestnanecDAO zamestnanecDAO;

    private String username;

    private String password;

    private String jmeno;

    private String prijmeni;

    private String mesto;

    private String ulice;

    private int cisloPopisne;

    private String telefon;

    private String email;

    private int plat;


    public AdminVytvoritZamestnanceController(Provider provider) {
        super(provider);
        zamestnanecDAO = provider.getProviderDAO().getZamestnanecDAO();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public void setUlice(String ulice) {
        this.ulice = ulice;
    }

    public void setCisloPopisne(int cisloPopisne) {
        this.cisloPopisne = cisloPopisne;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPlat(int plat) {
        this.plat = plat;
    }

    public boolean createZamestnanec(){
        if(!correctData()){
            return false;
        }
        Zamestnanec z = new Zamestnanec();
        z.setUsername(username);
        z.setPassword(password);
        z.setJmeno(jmeno);
        z.setMesto(mesto);
        z.setPrijmeni(prijmeni);
        z.setUlice(ulice);
        z.setCisloPopisne(cisloPopisne);
        z.setTelefon(telefon);
        z.setEmail(email);
        z.setPlat(plat);

        zamestnanecDAO.create(z);
        return true;
    }

    private boolean correctData() {
        return true;
    }

}
