package src.view.zakaznik;

import src.model.Exemplar;
import src.model.Platforma;
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
import java.util.ArrayList;

/**
 * Created by root on 20.4.16.
 */
public class ZakaznikPrihlasenVyhledatHru extends JFrame {

    private ProviderController providerController;
    private JTextArea nazev;
    private JTextArea rokVydani;
    private JComboBox vydavatel;
    private JTextArea kodExemplare;
    private JPanel zanrCheckBoxPanel;
    private JPanel platformaCheckBoxPanel;
    private JButton vyhledat;
    private JButton pujcit;
    private JLabel hint;
    private String selectedVydavatel;
    private String selectedVyhledanaHra;
    private JPanel vysledkyHledaniPanel;
    private java.util.List<JCheckBox> platformaList;
    private java.util.List<JCheckBox> zanrList;


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

        platformaList = new ArrayList<>();
        zanrList = new ArrayList<>();

        criteria.setLayout(new GridLayout(5,1,3,3));
        results.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1)));
        results.setLayout(new GridLayout(3,1,3,3));
        JPanel nazevNvydavatelPanel = new JPanel();
        JPanel rokVydaniNkodExemplarePanel = new JPanel();
        JPanel zanrPanel = new JPanel();
        JPanel platformaPanel = new JPanel();
        JPanel vyhledatButtonPanel = new JPanel();
        criteria.add(nazevNvydavatelPanel);
        criteria.add(rokVydaniNkodExemplarePanel);
        criteria.add(zanrPanel);
        criteria.add(platformaPanel);
        criteria.add(vyhledatButtonPanel);

        nazevNvydavatelPanel.setLayout(new GridLayout(1,4,3,3));
        rokVydaniNkodExemplarePanel.setLayout(new GridLayout(1,4,3,3));
        zanrPanel.setLayout(new FlowLayout());
        platformaPanel.setLayout(new FlowLayout());
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
        zanrCheckBoxPanel = new JPanel();
        fillZanrCheckBoxPanel();
        zanrPanel.add(zanrLabel);
        zanrPanel.add(zanrCheckBoxPanel);

        JLabel platformaLabel = new JLabel("Platforma:");
        platformaCheckBoxPanel = new JPanel();
        fillPlatformaCheckBoxPanel();
        platformaPanel.add(platformaLabel);
        platformaPanel.add(platformaCheckBoxPanel);

        vyhledat = new JButton("Vyhledat");
        vyhledat.addActionListener(new ButtonClickedListener());
        JLabel vyhledatLabel = new JLabel("- prázdná pole budou ignorována");
        vyhledatButtonPanel.add(vyhledat);
        vyhledatButtonPanel.add(vyhledatLabel);

        JLabel vysledkyHledaniLabel = new JLabel("Výsledky hledání:");
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1,2,3,3));
        results.add(vysledkyHledaniLabel);
        vysledkyHledaniPanel = new JPanel();
        vysledkyHledaniPanel.add(new JLabel("Žádne výsledky k zobrazení"));
        results.add(vysledkyHledaniPanel);
        pujcit = new JButton("Zapůjčit");
        pujcit.addActionListener(new ButtonClickedListener());
        hint = new JLabel("");
        bottomPanel.add(hint);
        bottomPanel.add(pujcit);
        results.add(bottomPanel);

        this.setVisible(true);
    }

    private void fillPlatformaCheckBoxPanel() {
        java.util.List<Platforma> platformy = providerController
                .getZakaznikPrihlasenVyhledatHruController()
                .getPlatformaList();
        try{
            for(Platforma p : platformy){
                JCheckBox pBox = new JCheckBox(p.getNazev(),false);
                platformaList.add(pBox);
                platformaCheckBoxPanel.add(pBox);
           }
         } catch (Exception e){
            System.out.println("Žádné platformy nenalezeny.");
        }
    }

    private void fillVysledkyHledani() {

        java.util.List<String> platformy = new ArrayList<>();
        java.util.List<String> zanry = new ArrayList<>();

        for(JCheckBox c : platformaList){
            if(c.isSelected()){
                platformy.add(c.getText());
            }
        }
        for(JCheckBox c : zanrList){
            if(c.isSelected()){
                zanry.add(c.getText());
            }
        }

        java.util.List<Exemplar> vyhledaneHry = providerController
                .getZakaznikPrihlasenVyhledatHruController()
                .getHryDleParametru(nazev.getText(),selectedVydavatel,rokVydani.getText(),kodExemplare.getText(), zanry, platformy);

        if(vyhledaneHry != null && !vyhledaneHry.isEmpty()){
            vysledkyHledaniPanel.remove(0);
            JList vysledkyHledani;
            String [] list = new String[vyhledaneHry.size()];
            Exemplar e;
            for(int i = 0; i < list.length ; i++){
                e = vyhledaneHry.get(i);
                list[i] = e.getHra().getNazev() + "     " + e.getPlatforma().getNazev() + ", cena:  "+ e.getCena() + " Kč" +",    police: " + e.getHra().getPolice().getNazev() + ",      číslo produktu: " + e.getId();
            }
            vysledkyHledani = new JList(list);
            vysledkyHledani.addListSelectionListener(new HraSelectedListener());
            vysledkyHledaniPanel.add(vysledkyHledani);
        } else {
            vysledkyHledaniPanel.remove(0);
            vysledkyHledaniPanel.add(new JLabel("Žádné výsledky nenalezeny"));
        }
        this.invalidate();
        this.validate();
        this.repaint();
    }

    private void fillZanrCheckBoxPanel() {
        java.util.List<Zanr> zanry = providerController.getZakaznikPrihlasenVyhledatHruController().getZanrList();
        try{
            for(Zanr z : zanry){
                JCheckBox zBox = new JCheckBox(z.getNazev(),false);
                zanrList.add(zBox);
                zanrCheckBoxPanel.add(zBox);
            }
        } catch (Exception e){
            System.out.println("Žádné žárny nenalezeny.");
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
            selectedVydavatel = (String)e.getItemSelectable().getSelectedObjects()[0];
        }
    }

    private class HraSelectedListener implements ListSelectionListener{
        @Override
        public void valueChanged(ListSelectionEvent e) {
            selectedVyhledanaHra = ((JList)e.getSource()).getSelectedValue().toString();
        }
    }

    private class ButtonClickedListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if(((JButton)e.getSource()).getText().equals("Vyhledat")){
                fillVysledkyHledani();
            } else {
                zapujcitHru();
            }
        }
    }

    private void zapujcitHru() {
        if(selectedVyhledanaHra == null){
            showHint();
            return;
        }
        String [] split = selectedVyhledanaHra.split(" ");
        String kod = split[split.length-1];
        int id;
        try{
            id = Integer.parseInt(kod);
            if(!providerController.getZakaznikPrihlasenVyhledatHruController().zapujcitHru(id)){
                throw new Exception();
            }
            showSucces();
            selectedVyhledanaHra = null;
        } catch (Exception e){
            System.out.println("ProviderController.getZakaznikPrihlasenVyhledatHruController().zapujcitHru(id) failure or");
            System.out.println("parse exception occured in ZakaznikPrihlasenVyhledatHru.zapujcitHru()");
            showHint();
            return;
        }

    }

    private void showSucces() {
        hint.setText("Hra zapůjčena!");
    }

    private void showHint() {
        hint.setText("Prosím vyberte hru ze seznamu.");
    }

}
