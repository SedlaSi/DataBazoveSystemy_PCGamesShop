package src.view;

import src.controller.*;
import src.klient.demo.Anomalie;
import src.model.Pozice;
import src.provider.Provider;
import src.provider.ProviderController;
import src.view.admin.AdminVytvoritProdejce;
import src.view.zakaznik.ZakaznikLogin;
import src.view.zamestnanec.ZamestnanecLogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class HlavniNabidka extends JFrame implements ActionListener {
    private JButton zakaznik;
    private JButton zamestnanec;
    private JButton sestavy;
    private JButton generovani;
    private JButton anomalie;
    private JButton vytvoritProdejce;
    private ProviderController providerController;
    private Provider provider;

    public static void main(String[] args) {
        Provider provider = new Provider();
        AdminVytvoritZamestnanceController admC = new AdminVytvoritZamestnanceController(provider);
        ZakaznikLoginController zkC = new ZakaznikLoginController(provider);
        ZamestnanecLoginController zlC = new ZamestnanecLoginController(provider);
        ZakaznikPrihlasenController zkpC = new ZakaznikPrihlasenController(provider);
        ZakaznikPrihlasenVyhledatHruController zpvC = new ZakaznikPrihlasenVyhledatHruController(provider);
        ZamestnanecPotrvditPrevzetiHryController zpphC = new ZamestnanecPotrvditPrevzetiHryController(provider);
        HlavniNabidkaController hnC = new HlavniNabidkaController(provider);
        ProviderController providerController = new ProviderController(zpphC, zpvC, zkpC, admC, zkC, zlC, hnC);

        final src.view.HlavniNabidka zkl = new src.view.HlavniNabidka(providerController, provider);
        javax.swing.SwingUtilities.invokeLater(() -> zkl.startFrame());

    }

    public HlavniNabidka(ProviderController providerController, Provider provider) {
        this.providerController = providerController;
        this.provider = provider;
    }

    public void startFrame() {
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Hlavní nabídka");
        setLayout(new GridLayout(6, 1));

        zakaznik = new JButton("Zákazník");
        zamestnanec = new JButton("Zaměstnanec");
        sestavy = new JButton("Sestavy");
        generovani = new JButton("Generování dat");
        anomalie = new JButton("Anomálie");
        vytvoritProdejce = new JButton("Vytvořit prodejce - procedura");

        zakaznik.addActionListener(this);
        zamestnanec.addActionListener(this);
        sestavy.addActionListener(this);
        generovani.addActionListener(this);
        anomalie.addActionListener(this);
        vytvoritProdejce.addActionListener(this);

        add(zakaznik);
        add(zamestnanec);
        add(sestavy);
        add(generovani);
        add(anomalie);
        add(vytvoritProdejce);

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
            providerController.getHlavniNabidkaController().getnerujSestavy();
            JOptionPane.showMessageDialog(this, "Sestavy byly vygenerovány do csv souborů.", "Sestavy", JOptionPane.INFORMATION_MESSAGE);
        } else if (source == generovani) {
            JOptionPane.showMessageDialog(this, "Po odkliknutí tohoto okna započne generování dat. Může to trvat několik sekund.", "Generování dat", JOptionPane.INFORMATION_MESSAGE);
            providerController.getHlavniNabidkaController().getnerujProvozniData();
            JOptionPane.showMessageDialog(this, "Provozní data byla vygenerována.", "Generování dat", JOptionPane.INFORMATION_MESSAGE);
        } else if (source == anomalie) {
            Anomalie anomalie = new Anomalie();
            try {
                Map<String, java.util.List<String>> result = anomalie.otestujAnomalie();
                StringBuilder sb = new StringBuilder();

                for (Map.Entry<String, List<String>> entry : result.entrySet()) {
                    sb.append("Demonstrovaný stupeň izolovanosti: ");
                    sb.append(entry.getKey());
                    sb.append("\n");

                    sb.append("Na Vaší databázi a výše zmíněném stupni izolovanosti se povedlo demonstrovat tyto typy anomálií: \n");

                    for (String string : entry.getValue()) {
                        sb.append("\t- ");
                        sb.append(string);
                        sb.append("\n");
                    }

                    sb.append("\n\n");
                }

                JOptionPane.showMessageDialog(this, sb.toString(), "Anomálie", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(this, "Během demonstrace anomálií nastala chyba.", "Anomálie", JOptionPane.ERROR_MESSAGE);
            }
        } else if (source == vytvoritProdejce) {
            List<Pozice> pozice = provider.getProviderDAO().getPoziceDAO().getList();
            AdminVytvoritProdejce adminVytvoritProdejce = new AdminVytvoritProdejce(providerController, pozice);
            adminVytvoritProdejce.startFrame();
        }
    }
}
