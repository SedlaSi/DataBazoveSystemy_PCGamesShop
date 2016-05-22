package src.controller;

import src.data.ZakaznikDAO;
import src.login.Decoder;
import src.login.Role;
import src.login.Session;
import src.model.Zakaznik;
import src.provider.Provider;
import src.provider.ProviderSession;

/**
 * Created by root on 15.4.16.
 */
public class ZakaznikLoginController extends TemplateController {

    private String username;
    private String password;

    private ZakaznikDAO zakaznikDAO;
    private ProviderSession providerSession;

    public ZakaznikLoginController(Provider provider) {
        super(provider);
        zakaznikDAO = providerDAO.getZakaznikDAO();
        providerSession = provider.getZakaznikProviderSession();
    }

    public void setPassWord(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean performLogin(){
        Zakaznik z;
        try{
            z = zakaznikDAO.getByUserName(username);
        } catch (Exception e){
            return false;
        }
        if(Decoder.isValid(password,z.getPassword())){
        //if(password.equals(z.getPassword())){
            providerSession.initSession(username, Role.ZAKAZNIK);
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
