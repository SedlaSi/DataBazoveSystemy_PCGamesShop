package src.view.admin;

import src.provider.ProviderController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by root on 15.4.16.
 */
public class AdminVytvoritZamestnance extends JFrame {

    private ProviderController providerController;

    public static void main(String [] args){

        final AdminVytvoritZamestnance zkl =  new AdminVytvoritZamestnance(null);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                zkl.startFrame();
            }
        });

    }

    public AdminVytvoritZamestnance(ProviderController providerController){
        this.providerController = providerController;
    }

    public void startFrame(){
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Login Screen");
        this.setLayout(new GridLayout(8,1,3,3));




        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private class ButtonClickedListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if(((JButton)e.getSource()).getText().equals("Vyhledat hru")){ // Vyhledani hry

            } else { // Prihlaseni

            }
        }
    }

}
