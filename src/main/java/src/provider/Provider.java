package src.provider;

/**
 * Created by root on 15.4.16.
 */
public class Provider {

    private ProviderDAO providerDAO;
    private ProviderSession zakaznikProviderSession;
    private ProviderSession zamestnanecProviderSession;

    public Provider(){
        providerDAO = new ProviderDAO();
        zakaznikProviderSession = new ProviderSession();
        zamestnanecProviderSession = new ProviderSession();
    }

    public ProviderSession getZakaznikProviderSession() {
        return zakaznikProviderSession;
    }

    public ProviderSession getZamestnanecProviderSession() {
        return zamestnanecProviderSession;
    }

    public ProviderDAO getProviderDAO() {
        return providerDAO;
    }

}
