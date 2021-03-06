package src.view.zamestnanec;

import src.model.*;
import src.provider.ProviderController;
import src.renderer.ExemplarRenderer;
import src.renderer.ZakaznikRenderer;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ZamestnanecPrihlasenVyhledatHru extends JDialog implements ActionListener {
    private ProviderController providerController;

    private JTextField nazev;
    private JTextField rokVydani;
    private JComboBox vydavatel;
    private JTextField kodExemplare;
    private JComboBox zakaznik;

    private JButton vyhledat;
    private JButton pujcit;

    private JCheckBox all;

    private JPanel zanrPanel;
    private JPanel platformaPanel;
    private JList vysledkyHledani;

    private java.util.List<JCheckBox> platformaListCheckBox;
    private java.util.List<JCheckBox> zanrListCheckBox;

    public ZamestnanecPrihlasenVyhledatHru(ProviderController providerController) {
        this.providerController = providerController;
    }

    public void createFrame() {
        setModalityType(ModalityType.APPLICATION_MODAL);
        setLayout(new FlowLayout());

        setTitle("Přihlášen jako: " + providerController.getZamestnanecLoginController().getCurrentSession().getUserName() + " - Půjčit hru");

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        JPanel hraInfo = new JPanel();
        hraInfo.setLayout(new BoxLayout(hraInfo, BoxLayout.Y_AXIS));

        JPanel hraInfoFirst = new JPanel(new BorderLayout());
        JPanel hraInfoFirstLeft = new JPanel(new GridLayout(2, 2));
        JPanel hraInfoFirstRight = new JPanel(new GridLayout(2, 2));
        JPanel hraInfoSecond = new JPanel(new GridLayout(1, 2));
        JPanel vyhledavaniPanel = new JPanel(new BorderLayout());
        JPanel vyhledatPanel = new JPanel();
        JPanel vyhledatButtonPanel = new JPanel(new BorderLayout());
        JPanel zapujcitButtonPanel = new JPanel(new BorderLayout());
        JPanel zakaznikInfoPanel = new JPanel(new GridLayout(1, 2));

        JPanel zakaznikPanel = new JPanel();

        zakaznikPanel.setLayout(new BoxLayout(zakaznikPanel, BoxLayout.Y_AXIS));
        vyhledatPanel.setLayout(new BoxLayout(vyhledatPanel, BoxLayout.Y_AXIS));

        zanrPanel = new JPanel();
        zanrPanel.setLayout(new BoxLayout(zanrPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollZanrPane = new JScrollPane(zanrPanel);
        scrollZanrPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollZanrPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollZanrPane.setPreferredSize(new Dimension(160, 160));

        platformaPanel = new JPanel();
        platformaPanel.setLayout(new BoxLayout(platformaPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPlatformaPane = new JScrollPane(platformaPanel);
        scrollPlatformaPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPlatformaPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPlatformaPane.setPreferredSize(new Dimension(160, 160));

        JScrollPane scrollvyhledavaniPane = new JScrollPane(vyhledavaniPanel);
        scrollvyhledavaniPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollvyhledavaniPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollvyhledavaniPane.setPreferredSize(new Dimension(160, 160));

        hraInfo.setBorder(new TitledBorder("Informace o hře"));
        vyhledatPanel.setBorder(new TitledBorder("Výsledky vyhledávání"));
        zakaznikPanel.setBorder(new TitledBorder("Zapůjčení"));
        scrollPlatformaPane.setBorder(new TitledBorder("Platformy"));
        scrollZanrPane.setBorder(new TitledBorder("Žánry"));

        JLabel nazevHryLabel = new JLabel("Název hry:");
        JLabel vydavatelLabel = new JLabel("Vydavatel:");
        JLabel rokVydaniLabel = new JLabel("Rok vydání:");
        JLabel kodExemplareLabel = new JLabel("Kód exempláře:");
        JLabel zakaznikLabel = new JLabel("Zákazník:");

        nazev = new JTextField();
        rokVydani = new JTextField();
        vydavatel = new JComboBox();
        kodExemplare = new JTextField();
        zakaznik = new JComboBox();
        zakaznik.setRenderer(new ZakaznikRenderer());
        all = new JCheckBox("Zobrazit i půjčené hry");

        vysledkyHledani = new JList(new String[]{null});
        vysledkyHledani.setCellRenderer(new ExemplarRenderer());

        vyhledat = new JButton("Vyhledat");
        pujcit = new JButton("Půjčit");

        nazev.setColumns(15);

        vyhledavaniPanel.add(vysledkyHledani, BorderLayout.CENTER);

        hraInfoFirstLeft.add(nazevHryLabel);
        hraInfoFirstLeft.add(nazev);

        hraInfoFirstLeft.add(vydavatelLabel);
        hraInfoFirstLeft.add(vydavatel);

        hraInfoFirstRight.add(rokVydaniLabel);
        hraInfoFirstRight.add(rokVydani);

        hraInfoFirstRight.add(kodExemplareLabel);
        hraInfoFirstRight.add(kodExemplare);

        hraInfoFirst.add(hraInfoFirstLeft, BorderLayout.WEST);
        hraInfoFirst.add(hraInfoFirstRight, BorderLayout.EAST);

        hraInfoSecond.add(scrollZanrPane, BorderLayout.WEST);
        hraInfoSecond.add(scrollPlatformaPane, BorderLayout.EAST);

        vyhledatPanel.add(scrollvyhledavaniPane);

        zapujcitButtonPanel.add(pujcit, BorderLayout.EAST);
        vyhledatButtonPanel.add(all, BorderLayout.WEST);
        vyhledatButtonPanel.add(vyhledat, BorderLayout.EAST);

        zakaznikInfoPanel.add(zakaznikLabel);
        zakaznikInfoPanel.add(zakaznik);

        vyhledatPanel.add(vyhledatButtonPanel);

        zakaznikPanel.add(zakaznikInfoPanel);
        zakaznikPanel.add(zapujcitButtonPanel);

        hraInfo.add(hraInfoFirst);
        hraInfo.add(hraInfoSecond);
        jPanel.add(hraInfo);
        jPanel.add(vyhledatPanel);
        jPanel.add(zakaznikPanel);


        add(jPanel);

        zanrListCheckBox = new ArrayList<>();
        platformaListCheckBox = new ArrayList<>();

        pujcit.addActionListener(this);
        vyhledat.addActionListener(this);

        fillPlatformaCheckBoxPanel();
        fillVydavatel();
        fillZanrCheckBoxPanel();
        fillZakaznik();
        fillVysledkyHledani();

        pack();
        setResizable(false);
        setVisible(true);
    }

    private void fillZakaznik() {
        java.util.List<Zakaznik> zakaznikList = providerController.getZakaznikPrihlasenVyhledatHruController().getZakaznikList();
        if (zakaznikList == null || zakaznikList.isEmpty()) {
            System.err.println("Nenalezeni žádní zákazníci.");
            return;
        }

        try {
            zakaznik.removeAllItems();

            for (Zakaznik z : zakaznikList) {
                zakaznik.addItem(z);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Nenalezeni žádní zákazníci.");
        }
    }

    private void fillPlatformaCheckBoxPanel() {
        java.util.List<Platforma> platformy = providerController.getZakaznikPrihlasenVyhledatHruController().getPlatformaList();
        if (platformy == null || platformy.isEmpty()) {
            System.err.println("Nenalezeny žádné platformy.");
            return;
        }

        try {
            for (Platforma p : platformy) {
                JCheckBox pBox = new JCheckBox(p.getNazev(), false);
                platformaPanel.add(pBox);
                platformaListCheckBox.add(pBox);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Nenalezeny žádné platformy.");
        }
    }

    private void fillVysledkyHledani() {
        java.util.List<String> platformy = new ArrayList<>();
        java.util.List<String> zanry = new ArrayList<>();

        for (JCheckBox c : platformaListCheckBox) {
            if (c.isSelected()) {
                platformy.add(c.getText());
            }
        }

        for (JCheckBox c : zanrListCheckBox) {
            if (c.isSelected()) {
                zanry.add(c.getText());
            }
        }

        String selectedVydavatel = (String) vydavatel.getSelectedItem();

        java.util.List<Exemplar> vyhledaneHry = providerController.getZakaznikPrihlasenVyhledatHruController().getHryDleParametru(nazev.getText(), selectedVydavatel, rokVydani.getText(), kodExemplare.getText(), zanry, platformy, all.isSelected());
        if (vyhledaneHry != null && !vyhledaneHry.isEmpty()) {
            vysledkyHledani.setListData(vyhledaneHry.toArray());
        } else {
            vysledkyHledani.setListData(new Exemplar[]{null});
        }

        this.invalidate();
        this.validate();
        this.repaint();
    }

    private void fillZanrCheckBoxPanel() {
        java.util.List<Zanr> zanry = providerController.getZakaznikPrihlasenVyhledatHruController().getZanrList();

        if (zanry == null || zanry.isEmpty()) {
            System.err.println("Nenalezeny žádné žánry.");
            return;
        }

        try {
            for (Zanr z : zanry) {
                JCheckBox zBox = new JCheckBox(z.getNazev(), false);
                zanrPanel.add(zBox);
                zanrListCheckBox.add(zBox);
            }
        } catch (Exception e) {
            System.out.println("Nenalezeny žádné žánry.");
        }
    }

    private void fillVydavatel() {
        java.util.List<Vydavatel> vyd = providerController.getZakaznikPrihlasenVyhledatHruController().getVydavatelList();
        if (vyd == null || vyd.isEmpty()) {
            System.err.println("Nenalezeni žádní vydavatelé.");
            return;
        } else {
            vydavatel.removeAllItems();
            vydavatel.addItem("");
            for (Vydavatel vydav : vyd) {
                vydavatel.addItem(vydav.getNazev());
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == vyhledat) {
            fillVysledkyHledani();
        } else if (source == pujcit) {
            if (providerController.getZamestnanecLoginController().getCurrentSession() == null) {
                quitFrame();
            }
            zapujcitHru();
        }
    }

    private void quitFrame() {
        this.dispose();
    }

    private void zapujcitHru() {
        Exemplar ex = (Exemplar) vysledkyHledani.getSelectedValue();

        if (ex == null) {
            JOptionPane.showMessageDialog(this, "Nevybral jste si hru, nebo vámi vybraná hra již není dostupná.", "Hra", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            if (!providerController.getZakaznikPrihlasenVyhledatHruController().zapujcitHru(ex.getId(), (Zakaznik) zakaznik.getSelectedItem())) {
                throw new Exception();
            }
            JOptionPane.showMessageDialog(this, "Hra zapůjčena!", "Hra", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Hru se nepovedlo zapůjčit.", "Hra", JOptionPane.ERROR_MESSAGE);
            return;
        }

        fillVysledkyHledani();
    }

}
