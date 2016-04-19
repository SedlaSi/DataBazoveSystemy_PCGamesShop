
package src.view.admin;

import src.controller.AdminSmazatZamestnanceController;
import src.model.Zamestnanec;
import src.provider.ProviderController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by root on 19.4.16.
 */
public class AdminSmazatZamestnance extends JFrame {

    private java.util.List<Zamestnanec> zamestnanecList;
    private String jmenoPrijmeni;
    private static final String NOSELECT = "-----NONE-----";
    private JLabel info;
    private JComboBox<String> comboBox;

    private ProviderController providerController;

    public static void main(String [] args){

        final AdminSmazatZamestnance zkl =  new AdminSmazatZamestnance(null);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                zkl.startFrame();
            }
        });

    }

    public AdminSmazatZamestnance(ProviderController providerController){
        this.providerController = providerController;
    }

    public void startFrame(){
        this.setSize(300,150);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Přidat Zaměstnance");
        this.setLayout(new GridLayout(3,1,3,3));

        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setLayout(new GridLayout(1,3,3,3));
        JLabel gab = new JLabel("Zaměstnanec:");
        comboBox = new JComboBox<>();
        fillComboBox();
        comboBoxPanel.add(gab);
        comboBoxPanel.add(comboBox);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton button = new JButton("Smazat");
        button.addActionListener(new ButtonClickedListener());
        buttonPanel.add(button);




        this.add(comboBoxPanel);
        this.add(buttonPanel);
        info = new JLabel("");
        this.add(info);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void fillComboBox() {
        comboBox.removeAllItems();
        zamestnanecList = providerController.getAdminSmazatZamestnanceController().getZamestnanecList();
        JTextField zjp = new JTextField();
        jmenoPrijmeni = NOSELECT;
        comboBox.addItem(NOSELECT);
        for(Zamestnanec z : zamestnanecList){

            comboBox.addItem(z.getJmeno() + " " + z.getPrijmeni());
        }
        comboBox.addItemListener(new BoxSelectedListener());
    }

    private class BoxSelectedListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            try{
                jmenoPrijmeni = (String)e.getItemSelectable().getSelectedObjects()[0];
            } catch (ArrayIndexOutOfBoundsException aioobe){
                System.out.println("array error");
            }
        }
    }

    private class ButtonClickedListener implements ActionListener {

        public void actionPerformed(ActionEvent e) { //  Zaregistrování uživatele
            if(jmenoPrijmeni.equals(NOSELECT)){
                info.setText("Musíte vybrat ze seznamu zaměstnanců.");
            } else {
                AdminSmazatZamestnanceController adminSmazatZamestnanceController = providerController.getAdminSmazatZamestnanceController();
                adminSmazatZamestnanceController.setJmenoPrijmeni(jmenoPrijmeni);
                adminSmazatZamestnanceController.smazat();
                fillComboBox();
                info.setText("Zaměstnanec smazán.");

            }
        }
    }

}
