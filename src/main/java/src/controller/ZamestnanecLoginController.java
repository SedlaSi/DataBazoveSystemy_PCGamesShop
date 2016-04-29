package src.controller;

import src.data.ZamestnanecDAO;
import src.login.Role;
import src.model.Zamestnanec;
import src.provider.Provider;
import src.provider.ProviderSession;

/**
 * Created by root on 15.4.16.
 */
public class ZamestnanecLoginController extends TemplateController {

    private String userName;
    private String password;

    private ZamestnanecDAO zamestnanecDAO;
    private ProviderSession providerZamestnanecSession;

    public ZamestnanecLoginController(Provider provider) {
        super(provider);
        zamestnanecDAO = providerDAO.getZamestnanecDAO();
        providerZamestnanecSession = provider.getZamestnanecProviderSession();
    }

    public void setPassWord(String passWord) {
        this.password = passWord;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean performLogin(){
        Zamestnanec z;
        try{
            z = zamestnanecDAO.getByUserName(userName);
        } catch (Exception e){
            return false;
        }
        if(password.equals(z.getPassword())){

            providerZamestnanecSession.initSession(userName, Role.ZAMESTNANEC);
            return true;
        }
        providerZamestnanecSession.endSession();
        return false;
    }

    public void performLogout(){
        providerZamestnanecSession.endSession();
    }
}
