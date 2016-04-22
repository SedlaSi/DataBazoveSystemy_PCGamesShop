package src.view.zakaznik;

import src.model.Exemplar;
import src.model.Vydavatel;
import src.model.Zanr;
import src.provider.ProviderController;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
    private JComboBox vydavatel;
    private JTextArea kodExemplare;
    private JPanel checkBoxPanel;
    private JButton vyhledat;
    private String selectedVydavatel;
    private String selectedVyhledanaHra;
    private JPanel vysledkyHledaniPanel;


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
        this.setTitle("Přihlášen jako: " + providerController.getZakaznikLoginController().getCurrentSession().getUserName());
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
        vydavatel = new JComboBox();
        //vydavatel.addItemListener(new VydavatelSelectedListener());
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
        vyhledat.addActionListener(new ButtonClickedListener());
        JLabel vyhledatLabel = new JLabel("- prázdná pole budou ignorována");
        vyhledatButtonPanel.add(vyhledat);
        vyhledatButtonPanel.add(vyhledatLabel);

        JLabel vysledkyHledaniLabel = new JLabel("Výsledky hledání:");
        JPanel bottomPanel = new JPanel();
        results.add(vysledkyHledaniLabel);
        vysledkyHledaniPanel = new JPanel();
        vysledkyHledaniPanel.add(new JLabel("Žádne výsledky k zobrazení"));
        results.add(vysledkyHledaniPanel);

        this.setVisible(true);

    }

    private void fillVysledkyHledani() {
        /*System.out.println(nazev.getText());
        System.out.println(selectedVydavatel);
        System.out.println(rokVydani.getText());
        System.out.println(kodExemplare.getText());*/
        java.util.List<Exemplar> vyhledaneHry = providerController
                .getZakaznikPrihlasenVyhledatHruController()
                .getHryDleParametru(nazev.getText(),selectedVydavatel,rokVydani.getText(),kodExemplare.getText());

        if(vyhledaneHry != null && !vyhledaneHry.isEmpty()){
            vysledkyHledaniPanel.remove(0);
            JList<String> vysledkyHledani = new JList<>();
            for(Exemplar e : vyhledaneHry){
                vysledkyHledani.add(new JLabel(e.getHra().getNazev() + " " + e.getPlatforma().getNazev() + ", police: +" + e.getHra().getPolice().getNazev() + ", číslo produktu: " + e.getId()));
            }
            vysledkyHledani.addListSelectionListener(new HraSelectedListener());
            vysledkyHledaniPanel.add(vysledkyHledani);
        }
    }

    private void fillCheckBoxPanel() {
        java.util.List<Zanr> zanry = providerController.getZakaznikPrihlasenVyhledatHruController().getZanrList();
        for(Zanr z : zanry){
            checkBoxPanel.add(new JCheckBox(z.getNazev(),false));
        }
    }

    private void fillVydavatel() {
        java.util.List<Vydavatel> vyd = providerController.getZakaznikPrihlasenVyhledatHruController().getVydavatelList();
        if(vyd == null || vyd.isEmpty()){
            System.out.println("vydavatele prazdni");
            return;
        } else {
            String [] vydav = new String[vyd.size()+1];
            vydav[0] = "";
            for(int i = 1; i < vyd.size()+1; i++){
                vydav[i] = vyd.get(i-1).getNazev();
            }
            vydavatel = new JComboBox(vydav);
        }
        vydavatel.addItemListener(new VydavatelSelectedListener());
    }

    private class VydavatelSelectedListener implements ItemListener{
        @Override
        public void itemStateChanged(ItemEvent e) {
            //System.out.println((String)e.getItemSelectable().getSelectedObjects()[0]);
            selectedVydavatel = (String)e.getItemSelectable().getSelectedObjects()[0];
        }
    }

    private class HraSelectedListener implements ListSelectionListener{
        @Override
        public void valueChanged(ListSelectionEvent e) {
            selectedVyhledanaHra = e.getSource().toString();
        }
    }

    private class ButtonClickedListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            fillVysledkyHledani();
        }
    }

}
