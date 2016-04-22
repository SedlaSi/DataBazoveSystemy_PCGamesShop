package src.view.zamestnanec;

import src.provider.ProviderController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by root on 22.4.16.
 */
public class ZamestnanecPotvrditPrevzetiHry extends JFrame {
    private ProviderController providerController;
    private JTextArea kodExemplare;
    private JTextArea rok;
    private JTextArea mesic;
    private JTextArea den;
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
        this.setLayout(new GridLayout(6,1,3,3));

        JLabel kodExemplareLabel = new JLabel("Kód exempláře:");
        kodExemplare = new JTextArea();
        JLabel rokLabel = new JLabel("Rok:");
        rok = new JTextArea();
        JLabel mesicLabel = new JLabel("Měsíc:");
        mesic = new JTextArea();
        JLabel denLabel = new JLabel("Den:");
        den = new JTextArea();
        potvrdit = new JButton("Potvrdit převzetí");
        potvrdit.addActionListener(new ButtonClickedListener());
        hint = new JLabel("");

        JPanel kodExemplarePanel = new JPanel(new GridLayout(1,2,3,3));
        JPanel rokPanel = new JPanel(new GridLayout(1,2,3,3));
        JPanel mesicPanel = new JPanel(new GridLayout(1,2,3,3));
        JPanel denPanel = new JPanel(new GridLayout(1,2,3,3));
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JPanel hintPanel = new JPanel(new FlowLayout());
        kodExemplarePanel.add(kodExemplareLabel);
        kodExemplarePanel.add(kodExemplare);
        rokPanel.add(rokLabel);
        rokPanel.add(rok);
        mesicPanel.add(mesicLabel);
        mesicPanel.add(mesic);
        denPanel.add(denLabel);
        denPanel.add(den);
        buttonPanel.add(potvrdit);
        hintPanel.add(hint);

        this.add(kodExemplarePanel);
        this.add(rokPanel);
        this.add(mesicPanel);
        this.add(denPanel);
        this.add(buttonPanel);
        this.add(hintPanel);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class ButtonClickedListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if(!providerController.getZamestnanecPotrvditPrevzetiHryController().potvrdit(
                    kodExemplare.getText(),rok.getText(),mesic.getText(),den.getText()
            )){
                showHint();
            } else {
                showSuccess();
            }

        }
    }

    private void showSuccess(){
        kodExemplare.setText("");
        rok.setText("");
        mesic.setText("");
        den.setText("");
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
