package src.controller;

import src.data.ZamestnanecDAO;
import src.model.Zamestnanec;
import src.provider.ProviderDAO;

/**
 * Created by root on 15.4.16.
 */
public class ZamestnanecLoginController extends TemplateController {

    private String userName;
    private String password;

    private ZamestnanecDAO zamestnanecDAO;

    public ZamestnanecLoginController(ProviderDAO providerDAO) {
        super(providerDAO);
        zamestnanecDAO = providerDAO.getZamestnanecDAO();
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
            return true;
        }
        return false;
    }
}
