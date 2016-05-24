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
        try{
            zamestnanec = zamestnanecDAO.getByUserName(userName);
        } catch (Exception e){
            return false;
        }
        //if(password.equals(zamestnanec.getPassword())){

        if(zamestnanec == null) {
            return false;
        }

        if(Decoder.isValid(password,zamestnanec.getPassword())){
            providerSession.initSession(userName, Role.ZAMESTNANEC);
            return true;
        }
        providerSession.endSession();
        return false;
    }

//    public boolean performLogin(){
//        try{
//            providerDAO.getKasaDAO().loginKasa(userName,password);
//        } catch (Exception e){
//            //e.printStackTrace();
//            return false;
//        }
//        return true;
//        /*Zamestnanec z;
//        try{
//            z = zamestnanecDAO.getByUserName(userName);
//        } catch (Exception e){
//            return false;
//        }
//        if(password.equals(z.getPassword())){
//
//            providerZamestnanecSession.initSession(userName, Role.ZAMESTNANEC);
//            return true;
//        }
//        providerZamestnanecSession.endSession();
//        return false;*/
//    }

    public void performLogout() {
        providerSession.endSession();
    }

    /*public void performLogout(){
        try{
            providerDAO.getKasaDAO().logoutKasa();
        } catch (Exception e){
            e.printStackTrace();
        }
        //providerZamestnanecSession.endSession();
    }*/

    public String getUserNameOfLoggedZamestnanec(){
        String username = "";

        if(providerSession != null && providerSession.getSession() != null) {
            username = providerSession.getSession().getUserName();
        }

//        try{
//            username = providerDAO.getKasaDAO().getLoggedZamestnanec().getUsername();
//        } catch (Exception e){
//            e.printStackTrace();
//            username = "";
//        }

        return username;
    }

//    public boolean someoneIsLoggedInKasa(){
//        if(providerDAO.getKasaDAO().getLoggedZamestnanec() != null){
//            return true;
//        }
//        return false;
//    }

    public Session getCurrentSession(){
        return providerSession.getSession();
    }

    public boolean performCrashRecoveryLogout(String password){
//        try {
//            Zamestnanec z = providerDAO.getKasaDAO().getLoggedZamestnanec();
//            if(z.getPassword().equals(password)){
//                this.performLogout();
//            } else {
//                return false;
//            }
//        } catch (Exception e){
//            e.printStackTrace();
//            return false;
//        }
        return true;
    }
}
