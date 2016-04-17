package src.view.zamestnanec;

import src.provider.ProviderController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by root on 17.4.16.
 */
public class ZamestnanecPridatHru extends JFrame {

    private ProviderController providerController;

    public static void main(String [] args){
        final ZamestnanecPridatHru zkl =  new ZamestnanecPridatHru(null);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                zkl.startFrame();
            }
        });
    }

    public ZamestnanecPridatHru(ProviderController providerController){
        this.providerController = providerController;
    }
    public void startFrame(){
        this.setSize(400,400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Login Screen");
        this.setLayout(new GridLayout(8,1,3,3));

        JPanel subTitle = new JPanel();
        JPanel gameNamePanel = new JPanel();
        JPanel notePanel = new JPanel();
        JPanel loginButtonPanel = new JPanel();

        subTitle.setLayout(new GridLayout(2,1,3,3));
        gameNamePanel.setLayout(new GridLayout(1,3,3,3));
        notePanel.setLayout(new GridLayout(1,3,3,3));
        loginButtonPanel.setLayout(new FlowLayout());

        this.add(subTitle);
        this.add(gameNamePanel);
        this.add(notePanel);
        this.add(loginButtonPanel);
        //JPanel mainPanel = new JPanel();


        JLabel subTitleLabel = new JLabel("Přidat novou hru:");
        subTitle.add(subTitleLabel);


        JLabel gameNameLabel = new JLabel("Název hry:");
        JTextArea usernameField = new JTextArea();
        usernameField.setColumns(10);
        usernameField.setLineWrap(true);

        gameNamePanel.add(gameNameLabel);
        gameNamePanel.add(usernameField);

        JLabel noteLabel = new JLabel("Popis:");

        JTextArea noteField = new JTextArea();
        noteField.setColumns(10);

        notePanel.add(noteLabel);
        notePanel.add(noteField);

        JButton button = new JButton();
        button.setText("Přihlásit se");
        button.addActionListener(new ButtonClickedListener());
        loginButtonPanel.add(button);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        usernameField.requestFocus();
    }

    private class ButtonClickedListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            // pridat hru
        }
    }
}
