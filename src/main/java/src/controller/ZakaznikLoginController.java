package src.controller;

import src.data.ZakaznikDAO;
import src.login.Role;
import src.login.Session;
import src.model.Zakaznik;
import src.provider.Provider;
import src.provider.ProviderSession;

/**
 * Created by root on 15.4.16.
 */
public class ZakaznikLoginController extends TemplateController {

    private String userName;
    private String password;

    private ZakaznikDAO zakaznikDAO;
    private ProviderSession providerSession;

    public ZakaznikLoginController(Provider provider) {
        super(provider);
        zakaznikDAO = providerDAO.getZakaznikDAO();
        providerSession = provider.getProviderSession();
    }

    public void setPassWord(String passWord) {
        this.password = passWord;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean performLogin(){
        Zakaznik z;
        try{
            z = zakaznikDAO.getByUserName(userName);
        } catch (Exception e){
            return false;
        }
        if(password.equals(z.getPassword())){
            providerSession.initSession(userName, Role.ZAKAZNIK);
            return true;
        }
        providerSession.endSession();
        return false;
    }

    public Session getCurrentSession(){
        return providerSession.getSession();
    }

    public void performLogout() {
        providerSession.endSession();
    }
}
