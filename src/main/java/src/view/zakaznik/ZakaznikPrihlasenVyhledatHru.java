package src.view.zakaznik;

import src.provider.ProviderController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by root on 20.4.16.
 */
public class ZakaznikPrihlasenVyhledatHru extends JFrame {

    private ProviderController providerController;
    private JTextArea nazev;
    private JTextArea rokVydani;
    private JComboBox<String> vydavatel;
    private JTextArea kodExemplare;


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
        this.setSize(400,400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Přihlášen jako: "+providerController.getZakaznikLoginController().getCurrentSession().getUserName());
        this.setLayout(new GridLayout(2,1,3,3));
        JPanel criteria = new JPanel();
        JPanel results = new JPanel();

        criteria.setLayout(new GridLayout(4,1,3,3));
        JPanel nazevNvydavatelPanel = new JPanel();
        JPanel rokVydaniNkodExemplarePanel = new JPanel();
        JPanel zanrPanel = new JPanel();
        JPanel vyhledatButtonPanel = new JPanel();

        nazevNvydavatelPanel.setLayout(new GridLayout(1,4,3,3));
        rokVydaniNkodExemplarePanel.setLayout(new GridLayout(1,4,3,3));
        zanrPanel.setLayout(new FlowLayout());
        vyhledatButtonPanel.setLayout(new GridLayout(1,2,3,3));

        //=============================================
        JLabel nazevLabel = new JLabel("Název:");
        nazev = new JTextArea();
        JLabel vydavatelLabel = new JLabel("Vydavatel:");
        vydavatel = new JComboBox<>();
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
        JPanel checkBoxPanel = new JPanel();




        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void fillVydavatel() {

    }

    private class ButtonClickedListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

        }
    }


    private void showHint(){

    }
}
