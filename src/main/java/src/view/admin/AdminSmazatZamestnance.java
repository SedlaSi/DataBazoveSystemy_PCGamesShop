package src.view.admin;

import src.model.Zamestnanec;
import src.provider.ProviderController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by root on 19.4.16.
 */
public class AdminSmazatZamestnance extends JFrame {

    private java.util.List<Zamestnanec> zamestnanecList;


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
        this.setSize(300,100);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Přidat Zaměstnance");
        this.setLayout(new GridLayout(2,1,3,3));

        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setLayout(new GridLayout(1,2,3,3));
        JLabel gab = new JLabel("Zaměstnanec:");
        JComboBox<String> comboBox = new JComboBox<>();
        fillComboBox(comboBox);
        comboBoxPanel.add(gab);
        comboBoxPanel.add(comboBox);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton button = new JButton("Smazat");
        button.addActionListener(new ButtonClickedListener());
        buttonPanel.add(button);


        this.add(comboBoxPanel);
        this.add(buttonPanel);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void fillComboBox(JComboBox<String> comboBox) {
        zamestnanecList = providerController.getAdminSmazatZamestnanceController().getZamestnanecList();
        for(Zamestnanec z : zamestnanecList){
            comboBox.addItem(z.getJmeno() + " " + z.getPrijmeni());
        }
    }

    private class ButtonClickedListener implements ActionListener {

        public void actionPerformed(ActionEvent e) { //  Zaregistrování uživatele
            System.out.println("clicked");
        }
    }

}
