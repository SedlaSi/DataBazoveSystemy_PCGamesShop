package src;

import src.controller.*;
import src.provider.Provider;
import src.provider.ProviderController;
import src.view.zamestnanec.ZamestnanecPotvrditPrevzetiHry;

import javax.swing.*;
import java.awt.*;

/**
 * Created by root on 15.4.16.
 */
public class ApplicationZamestnanecBuild {

    public static void main(String [] args){

        Provider provider = new Provider();
        AdminSmazatZamestnanceController admSC = new AdminSmazatZamestnanceController(provider);
        AdminVytvoritZamestnanceController admC = new AdminVytvoritZamestnanceController(provider);
        ZamestnanecVytvoritZakaznikaController zvzC = new ZamestnanecVytvoritZakaznikaController(provider);
        ZakaznikLoginController zkC = new ZakaznikLoginController(provider);
        ZamestnanecLoginController zlC = new ZamestnanecLoginController(provider);
        ZamestnanecVydavatelController zvC = new ZamestnanecVydavatelController(provider);
        ZakaznikPrihlasenController zkpC = new ZakaznikPrihlasenController(provider);
        ZakaznikPrihlasenVyhledatHruController zpvC = new ZakaznikPrihlasenVyhledatHruController(provider);
        ZamestnanecPotrvditPrevzetiHryController zpphC = new ZamestnanecPotrvditPrevzetiHryController(provider);
        HlavniNabidkaController hnC = new HlavniNabidkaController(provider);
        ProviderController providerController = new ProviderController(zpphC,zpvC,zkpC,admSC,zvC,zvzC,admC,zkC,zlC,hnC);

        //provider.getZamestnanecProviderSession().initSession("A", Role.ZAMESTNANEC);

        startZamestnanecPotvrditPrevzetiHry(providerController);

    }

    public static void startZamestnanecPotvrditPrevzetiHry(ProviderController providerController){
        final ZamestnanecPotvrditPrevzetiHry zkl =  new ZamestnanecPotvrditPrevzetiHry(providerController);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                zkl.startFrame();
            }
        });
    }

    public static void mainTest(String args[]) {
        String labels[] = { "A", "B", "C", "D","E", "F", "G", "H","I", "J","A", "B", "C", "D","E", "F", "G", "H","I", "J" };

        String title = "JList Sample";
        JFrame f = new JFrame(title);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JList list = new JList(labels);
        JScrollPane scrollPane = new JScrollPane(list);

        Container contentPane = f.getContentPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        f.setSize(200, 200);
        f.setVisible(true);
    }
}
