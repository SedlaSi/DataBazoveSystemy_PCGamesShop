package src.controller;

import src.provider.ProviderDAO;

/**
 * Created by root on 15.4.16.
 */
public class TemplateController {

    ProviderDAO providerDAO;

    public TemplateController(ProviderDAO providerDAO){
        this.providerDAO = providerDAO;
    }
}
