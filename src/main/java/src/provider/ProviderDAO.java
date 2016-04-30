package src.provider;

import src.data.*;
import src.util.Resources;

/**
 * Created by root on 14.4.16.
 */
public class ProviderDAO {

    private ExemplarDAO exemplarDAO;
    private HraDAO hraDAO;
    private PlatformaDAO platformaDAO;
    private PoliceDAO policeDAO;
    private VydavatelDAO vydavatelDAO;
    private ZakaznikDAO zakaznikDAO;
    private ZamestnanecDAO zamestnanecDAO;
    private ZanrDAO zanrDAO;
    private PoziceDAO poziceDAO;
    private PujckaDAO pujckaDAO;
    private KasaDAO kasaDAO;

    private Resources res;

    public ProviderDAO(){
        res = new Resources();
        hraDAO = new HraDAO(res);
        platformaDAO = new PlatformaDAO(res);
        policeDAO = new PoliceDAO(res);
        vydavatelDAO = new VydavatelDAO(res);
        zakaznikDAO = new ZakaznikDAO(res);
        zamestnanecDAO = new ZamestnanecDAO(res);
        zanrDAO = new ZanrDAO(res);
        poziceDAO = new PoziceDAO(res);
        pujckaDAO = new PujckaDAO(res);
        kasaDAO = new KasaDAO(res,zamestnanecDAO);
        exemplarDAO = new ExemplarDAO(res,this);
    }

    public PoziceDAO getPoziceDAO() {
        return poziceDAO;
    }

    public ExemplarDAO getExemplarDAO() {
        return exemplarDAO;
    }

    public HraDAO getHraDAO() {
        return hraDAO;
    }

    public PlatformaDAO getPlatformaDAO() {
        return platformaDAO;
    }

    public PoliceDAO getPoliceDAO() {
        return policeDAO;
    }

    public VydavatelDAO getVydavatelDAO() {
        return vydavatelDAO;
    }

    public ZakaznikDAO getZakaznikDAO() {
        return zakaznikDAO;
    }

    public ZamestnanecDAO getZamestnanecDAO() {
        return zamestnanecDAO;
    }

    public ZanrDAO getZanrDAO() {
        return zanrDAO;
    }

    public PujckaDAO getPujckaDAO() {
        return pujckaDAO;
    }

    public KasaDAO getKasaDAO() {
        return kasaDAO;
    }
}
