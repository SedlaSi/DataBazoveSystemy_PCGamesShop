package src.view.zamestnanec;

import src.provider.ProviderController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

/**
 * Created by root on 22.4.16.
 */
public class ZamestnanecPotvrditPrevzetiHry extends JFrame {
    private ProviderController providerController;
    private JTextArea kodExemplare;
    private JTextArea datum;
    private JButton potvrdit;
    private JLabel hint;

    public static void main(String [] args){

        final ZamestnanecPotvrditPrevzetiHry zkl =  new ZamestnanecPotvrditPrevzetiHry(null);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                zkl.startFrame();
            }
        });

    }


    public ZamestnanecPotvrditPrevzetiHry(ProviderController providerController){
        this.providerController = providerController;
    }

    public void startFrame(){
        this.setSize(400,400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Login Screen");
        this.setLayout(new GridLayout(7,1,3,3));

        JLabel kodExemplareLabel = new JLabel("Kód exempláře:");
        kodExemplare = new JTextArea();
        JLabel datumLabel = new JLabel("Datum převzetí:");
        datum = new JTextArea();
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
        datum.setText(date.toString());
        potvrdit = new JButton("Potvrdit převzetí");
        potvrdit.addActionListener(new ButtonClickedListener());
        hint = new JLabel("");
        JButton odhlasitSeButton = new JButton("Odhlásit se");
        odhlasitSeButton.addActionListener(new ButtonClickedListener());

        JPanel kodExemplarePanel = new JPanel(new GridLayout(1,2,3,3));
        JPanel datumPanel = new JPanel(new GridLayout(1,2,3,3));
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JPanel hintPanel = new JPanel(new FlowLayout());
        JPanel odhlasitSeButtonPanel = new JPanel(new FlowLayout());
        kodExemplarePanel.add(kodExemplareLabel);
        kodExemplarePanel.add(kodExemplare);
        datumPanel.add(datumLabel);
        datumPanel.add(datum);
        buttonPanel.add(potvrdit);
        hintPanel.add(hint);
        odhlasitSeButtonPanel.add(odhlasitSeButton);


        this.add(kodExemplarePanel);
        this.add(datumPanel);
        this.add(buttonPanel);
        this.add(hintPanel);
        this.add(odhlasitSeButtonPanel);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class ButtonClickedListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if(((JButton)e.getSource()).getText().equals("Odhlásit se")){
                invokeLogoutTask();
            } else {
                if(!providerController.getZamestnanecPotrvditPrevzetiHryController().potvrdit(
                        kodExemplare.getText(),datum.getText()
                )){
                    showHint();
                } else {
                    showSuccess();
                }

            }
        }
    }

    private void invokeLogoutTask() {
        providerController.getZamestnanecLoginController().performLogout();
        final ZamestnanecLogin zkl =  new ZamestnanecLogin(providerController);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                zkl.startFrame();
            }
        });
        this.dispose();

    }

    private void showSuccess(){
        kodExemplare.setText("");
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
        datum.setText(date.toString());
        hint.setText("Produkt byl úspěšně přijat.");
        this.invalidate();
        this.validate();
        this.repaint();
    }

    private void showHint(){
        hint.setText("Chybně zadané informace.");
        this.invalidate();
        this.validate();
        this.repaint();
    }
}
