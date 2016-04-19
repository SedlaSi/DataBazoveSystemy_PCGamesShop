package src.controller;

import src.data.ZamestnanecDAO;
import src.model.Zamestnanec;
import src.provider.Provider;

import java.util.List;

/**
 * Created by root on 19.4.16.
 */
public class AdminSmazatZamestnanceController extends TemplateController {
    ZamestnanecDAO zamestnanecDAO;

    public AdminSmazatZamestnanceController(Provider provider) {
        super(provider);
        zamestnanecDAO = provider.getProviderDAO().getZamestnanecDAO();
    }

    public List<Zamestnanec> getZamestnanecList(){
        return zamestnanecDAO.getList();
    }


}
