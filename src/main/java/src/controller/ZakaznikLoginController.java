package src.controller;

import src.data.ZakaznikDAO;
import src.model.Zakaznik;
import src.provider.ProviderDAO;

/**
 * Created by root on 15.4.16.
 */
public class ZakaznikLoginController extends TemplateController {

    private String userName;
    private String password;

    private ZakaznikDAO zakaznikDAO;

    public ZakaznikLoginController(ProviderDAO providerDAO) {
        super(providerDAO);
        zakaznikDAO = providerDAO.getZakaznikDAO();
    }

    public void setPassWord(String passWord) {
        this.password = passWord;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean performLogin(){
        Zakaznik z = zakaznikDAO.getByUserName(userName);
        if(password.equals(z.getPassword())){
            return true;
        }
        return false;
    }




}
