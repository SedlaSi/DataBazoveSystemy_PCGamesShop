package src.controller;

import src.data.ZamestnanecDAO;
import src.model.Zamestnanec;
import src.provider.Provider;

import java.util.List;

/**
 * Created by root on 19.4.16.
 */
public class AdminSmazatZamestnanceController extends TemplateController {

    private ZamestnanecDAO zamestnanecDAO;
    private String jmeno;
    private String prijmeni;

    public AdminSmazatZamestnanceController(Provider provider) {
        super(provider);
        zamestnanecDAO = provider.getProviderDAO().getZamestnanecDAO();
    }

    public List<Zamestnanec> getZamestnanecList(){
        return zamestnanecDAO.getList();
    }

    public void setJmenoPrijmeni(String jmenoPrijmeni){
        String [] s = jmenoPrijmeni.split(" ");
        jmeno = s[0];
        prijmeni = s[1];
    }

    public void smazat(){
        Zamestnanec zamestnanec = zamestnanecDAO.getByJmenoPrijmeni(jmeno,prijmeni);
        zamestnanecDAO.remove(zamestnanec);
    }


}
