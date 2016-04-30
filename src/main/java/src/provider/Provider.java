package src.provider;

/**
 * Created by root on 15.4.16.
 */
public class Provider {

    private ProviderDAO providerDAO;

    private ProviderSession zakaznikProviderSession;

    public Provider(){
        providerDAO = new ProviderDAO();
        zakaznikProviderSession = new ProviderSession();
    }

    public ProviderSession getZakaznikProviderSession() {
        return zakaznikProviderSession;
    }

    public ProviderDAO getProviderDAO() {
        return providerDAO;
    }

}
