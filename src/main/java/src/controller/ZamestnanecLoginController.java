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
    private ProviderSession providerSession;

    public ZamestnanecLoginController(Provider provider) {
        super(provider);
        zamestnanecDAO = providerDAO.getZamestnanecDAO();
        providerSession = provider.getProviderSession();
    }

    public void setPassWord(String passWord) {
        this.password = passWord;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean performLogin(){
        Zamestnanec z = zamestnanecDAO.getByUserName(userName);
        if(password.equals(z.getPassword())){
            providerSession.initSession(userName, Role.ZAMESTNANEC);
            return true;
        }
        providerSession.endSession();
        return false;
    }
}
