package src.controller;

import src.klient.demo.GeneratorDat;
import src.klient.demo.Sestavy;
import src.provider.Provider;

public class HlavniNabidkaController extends TemplateController {

    public HlavniNabidkaController(Provider provider) {
        super(provider);
    }

    public void getnerujSestavy(){
        Sestavy sestavy = new Sestavy(providerDAO.getRes().getEntityManager());
        sestavy.start();
    }

    public void getnerujProvozniData(){
        GeneratorDat generatorDat = new GeneratorDat(providerDAO);
        generatorDat.start();
    }
}
