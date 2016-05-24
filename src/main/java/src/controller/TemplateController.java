package src.controller;

import src.provider.Provider;
import src.provider.ProviderDAO;

/**
 * Created by root on 15.4.16.
 */
public class TemplateController {

    protected ProviderDAO providerDAO;

    public TemplateController(Provider provider) {
        this.providerDAO = provider.getProviderDAO();
    }
}
