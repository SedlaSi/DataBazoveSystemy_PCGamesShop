package src.view.zakaznik;

import src.model.Vydavatel;
import src.provider.ProviderController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by root on 20.4.16.
 */
public class ZakaznikPrihlasenVyhledatHru extends JFrame {

    private ProviderController providerController;
    private JTextArea nazev;
    private JTextArea rokVydani;
    private JComboBox<String> vydavatel;
    private JTextArea kodExemplare;
    private JPanel checkBoxPanel;
    private JButton vyhledat;
    private JLabel searchHint;
    private JButton vypujcit;
    private String selectedVydavatel;


    public static void main(String [] args){

        final ZakaznikPrihlasenVyhledatHru zkl =  new ZakaznikPrihlasenVyhledatHru(null);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                zkl.startFrame();
            }
        });

    }

    public ZakaznikPrihlasenVyhledatHru(ProviderController providerController){
        this.providerController = providerController;
    }

    public void startFrame(){
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        //this.setTitle("Přihlášen jako: "+providerController.getZakaznikLoginController().getCurrentSession().getUserName());
        this.setLayout(new GridLayout(2,1,3,3));
        JPanel criteria = new JPanel();
        this.add(criteria);
        JPanel results = new JPanel();
        this.add(results);

        criteria.setLayout(new GridLayout(4,1,3,3));
        results.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1)));
        results.setLayout(new GridLayout(3,1,3,3));
        JPanel nazevNvydavatelPanel = new JPanel();
        JPanel rokVydaniNkodExemplarePanel = new JPanel();
        JPanel zanrPanel = new JPanel();
        JPanel vyhledatButtonPanel = new JPanel();
        criteria.add(nazevNvydavatelPanel);
        criteria.add(rokVydaniNkodExemplarePanel);
        criteria.add(zanrPanel);
        criteria.add(vyhledatButtonPanel);

        nazevNvydavatelPanel.setLayout(new GridLayout(1,4,3,3));
        rokVydaniNkodExemplarePanel.setLayout(new GridLayout(1,4,3,3));
        zanrPanel.setLayout(new FlowLayout());
        vyhledatButtonPanel.setLayout(new GridLayout(1,2,3,3));

        //=============================================
        JLabel nazevLabel = new JLabel("Název:");
        nazev = new JTextArea();
        JLabel vydavatelLabel = new JLabel("Vydavatel:");
        vydavatel = new JComboBox<>();
        vydavatel.addItemListener(new VydavatelSelectedListener());
        fillVydavatel();
        nazevNvydavatelPanel.add(nazevLabel);
        nazevNvydavatelPanel.add(nazev);
        nazevNvydavatelPanel.add(vydavatelLabel);
        nazevNvydavatelPanel.add(vydavatel);

        //=============================================
        JLabel rokVydaniLabel = new JLabel("Rok Vydání:");
        rokVydani = new JTextArea();
        JLabel  kodExemplareLabel = new JLabel("Kód Exempláře:");
        kodExemplare = new JTextArea();

        rokVydaniNkodExemplarePanel.add(rokVydaniLabel);
        rokVydaniNkodExemplarePanel.add(rokVydani);
        rokVydaniNkodExemplarePanel.add(kodExemplareLabel);
        rokVydaniNkodExemplarePanel.add(kodExemplare);
        //==============================================

        JLabel zanrLabel = new JLabel("Žánr:");
        checkBoxPanel = new JPanel();
        fillCheckBoxPanel();
        zanrPanel.add(zanrLabel);
        zanrPanel.add(checkBoxPanel);

        vyhledat = new JButton("Vyhledat");
        JLabel vyhledatLabel = new JLabel("- prázdná pole budou ignorována");
        vyhledatButtonPanel.add(vyhledat);
        vyhledatButtonPanel.add(vyhledatLabel);


        JLabel vysledkyHledaniLabel = new JLabel("Výsledky hledání:");
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1,2,3,3));
        searchHint = new JLabel("");
        vypujcit = new JButton("Vypůjčit");
        bottomPanel.add(searchHint);
        bottomPanel.add(vypujcit);
        results.add(vysledkyHledaniLabel);
        results.add(new JLabel("Žádné výsledky k zobrazení"));
        results.add(bottomPanel);


        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void fillVysledkyHledani() {

    }

    private void fillCheckBoxPanel() {

    }

    private void fillVydavatel() {
        java.util.List<Vydavatel> vyd = providerController.getZakaznikPrihlasenVyhledatHruController().getVydavatelList();
        if(vyd == null || vyd.isEmpty()){
            return;
        } else {
            for(Vydavatel v : vyd){
                vydavatel.add(new JLabel(v.getNazev()));
            }
        }
    }

    private class VydavatelSelectedListener implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent e) {
            selectedVydavatel = e.getItem().toString();
        }
    }

    private class ButtonClickedListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

        }
    }


    private void showHint(){

    }
}
