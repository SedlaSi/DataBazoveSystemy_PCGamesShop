package src.controller;

import src.data.ZamestnanecDAO;
import src.login.Decoder;
import src.login.Role;
import src.login.Session;
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
        providerSession = provider.getZamestnanecProviderSession();
    }

    public void setPassWord(String passWord) {
        this.password = passWord;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean performLogin() {
        Zamestnanec zamestnanec;
        try {
            zamestnanec = zamestnanecDAO.getByUserName(userName);
        } catch (Exception e) {
            return false;
        }
        if (zamestnanec == null || !zamestnanec.isAktivni()) {
            return false;
        }

        if (Decoder.isValid(password, zamestnanec.getPassword())) {
            providerSession.initSession(userName, Role.ZAMESTNANEC);
            return true;
        }
        providerSession.endSession();
        return false;
    }

    public Session getCurrentSession() {
        return providerSession.getSession();
    }
}
