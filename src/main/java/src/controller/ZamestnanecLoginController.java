package src.controller;

import src.data.ZamestnanecDAO;
import src.model.Zamestnanec;
import src.provider.Provider;

/**
 * Created by root on 15.4.16.
 */
public class ZamestnanecLoginController extends TemplateController {

    private String userName;
    private String password;

    private ZamestnanecDAO zamestnanecDAO;

    public ZamestnanecLoginController(Provider provider) {
        super(provider);
        zamestnanecDAO = providerDAO.getZamestnanecDAO();
    }

    public void setPassWord(String passWord) {
        this.password = passWord;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean performLogin(){
        try{
            providerDAO.getKasaDAO().loginKasa(userName,password);
        } catch (Exception e){
            //e.printStackTrace();
            return false;
        }
        return true;
        /*Zamestnanec z;
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
        return false;*/
    }

    public void performLogout(){
        try{
            providerDAO.getKasaDAO().logoutKasa();
        } catch (Exception e){
            e.printStackTrace();
        }
        //providerZamestnanecSession.endSession();
    }

    public String getUserNameOfLoggedZamestnanec(){
        String username = "";
        try{
            username = providerDAO.getKasaDAO().getLoggedZamestnanec().getUsername();
        } catch (Exception e){
            e.printStackTrace();
            username = "";
        }

        return username;
    }

    public boolean someoneIsLoggedInKasa(){
        if(providerDAO.getKasaDAO().getLoggedZamestnanec() != null){
            return true;
        }
        return false;
    }

    public boolean performCrashRecoveryLogout(String password){
        try {
            Zamestnanec z = providerDAO.getKasaDAO().getLoggedZamestnanec();
            if(z.getPassword().equals(password)){
                this.performLogout();
            } else {
                return false;
            }
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
