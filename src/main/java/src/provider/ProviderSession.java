package src.provider;

import src.login.Role;
import src.login.Session;

/**
 * Created by root on 15.4.16.
 */
public class ProviderSession {

    private Session session;

    public void initSession(String userName,Role role){
        session = new Session(userName,role);
    }

    public void endSession(){
        session = null;
    }

    public Session getSession(){
        return session;
    }

}
