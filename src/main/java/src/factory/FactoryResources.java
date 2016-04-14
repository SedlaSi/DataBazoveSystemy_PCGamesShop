package src.factory;

import src.util.Resources;

/**
 * Created by root on 14.4.16.
 */
public class FactoryResources {

    private Resources res;

    public FactoryResources(){
        res = new Resources();
    }

    public Resources getResources(){
        return res;
    }
}
