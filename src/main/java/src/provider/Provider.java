package src.provider;

/**
 * Created by root on 15.4.16.
 */
public class Provider {

    private ProviderDAO providerDAO;

    private ProviderSession providerSession;

    public Provider(){
        providerDAO = new ProviderDAO();
        providerSession = new ProviderSession();
    }

    public ProviderSession getProviderSession() {
        return providerSession;
    }

    public ProviderDAO getProviderDAO() {
        return providerDAO;
    }

}
