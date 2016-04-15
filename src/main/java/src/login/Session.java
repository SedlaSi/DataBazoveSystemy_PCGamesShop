package src.login;

/**
 * Created by root on 15.4.16.
 */
public class Session {

    private String userName;
    private Role role;

    public Session(String userName, Role role){
        this.userName = userName;
        this.role = role;
    }

    public String getUserName(){
        return userName;
    }

    public Role getRole(){
        return role;
    }
}
