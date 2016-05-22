package src.view;

import src.controller.*;
import src.provider.Provider;
import src.provider.ProviderController;
import src.view.zakaznik.ZakaznikLogin;
import src.view.zamestnanec.ZamestnanecLogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HlavniNabidka extends JFrame implements ActionListener {
    private JButton zakaznik;
    private JButton zamestnanec;
    private JButton sestavy;
    private JButton generovani;
    private JButton anomalie;
    private ProviderController providerController;

    public static void main(String[] args) {
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
        ProviderController providerController = new ProviderController(zpphC, zpvC, zkpC, admSC, zvC, zvzC, admC, zkC, zlC);

        final src.view.HlavniNabidka zkl = new src.view.HlavniNabidka(providerController);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                zkl.startFrame();
            }
        });

    }

    public HlavniNabidka(ProviderController providerController) {
        this.providerController = providerController;
    }

    public void startFrame() {
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Hlavní nabídka");
        setLayout(new GridLayout(5, 1));

        zakaznik = new JButton("Zákazník");
        zamestnanec = new JButton("Zaměstnanec");
        sestavy = new JButton("Sestavy");
        generovani = new JButton("Generování dat");
        anomalie = new JButton("Anomálie");

        zakaznik.addActionListener(this);
        zamestnanec.addActionListener(this);
        sestavy.addActionListener(this);
        generovani.addActionListener(this);
        anomalie.addActionListener(this);

        add(zakaznik);
        add(zamestnanec);
        add(sestavy);
        add(generovani);
        add(anomalie);

        pack();

        setSize(new Dimension(300, getHeight()));

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == zakaznik) {
            ZakaznikLogin zakaznikLogin = new ZakaznikLogin(providerController);
            zakaznikLogin.startFrame();
        } else if (source == zamestnanec) {
            ZamestnanecLogin zamestnanecLogin = new ZamestnanecLogin(providerController);
            zamestnanecLogin.startFrame();
        } else if (source == sestavy) {

        } else if (source == generovani) {

        } else if (source == anomalie) {

        }
    }
}
