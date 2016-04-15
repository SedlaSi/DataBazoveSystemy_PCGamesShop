package src.view.zakaznik;

/**
 * Created by root on 14.4.16.
 */

import src.provider.ProviderController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ZakaznikLogin extends JFrame{

    private ProviderController providerController;

    public static void main(String [] args){

       final ZakaznikLogin zkl =  new ZakaznikLogin(null);
       javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                zkl.startFrame();
            }
        });

    }

    public ZakaznikLogin(ProviderController providerController){
        this.providerController = providerController;
    }

    public void startFrame(){
        this.setSize(400,400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Login Screen");
        this.setLayout(new GridLayout(8,1,3,3));

        JPanel title = new JPanel();
        JPanel search = new JPanel();
        JPanel gab1 = new JPanel();
        JPanel subTitle = new JPanel();
        JPanel userNamePanel = new JPanel();
        JPanel passwordPanel = new JPanel();
        JPanel loginButtonPanel = new JPanel();

        title.setLayout(new FlowLayout());
        search.setLayout(new FlowLayout());
        subTitle.setLayout(new GridLayout(2,1,3,3));
        userNamePanel.setLayout(new GridLayout(1,3,3,3));
        passwordPanel.setLayout(new GridLayout(1,3,3,3));
        loginButtonPanel.setLayout(new FlowLayout());

        this.add(title);
        this.add(search);
        this.add(gab1);
        this.add(subTitle);
        this.add(userNamePanel);
        this.add(passwordPanel);
        this.add(loginButtonPanel);
        //JPanel mainPanel = new JPanel();

        JLabel titleLabel = new JLabel("Vítejte v GameShop půjčovně");
        title.add(titleLabel);

        JLabel  searchTitle = new JLabel("Pro vyhledání hry klikněte zde:");
        JButton searchButton = new JButton("Vyhledat hru");
        searchButton.addActionListener(new ButtonClickedListener());
        search.add(searchTitle);
        search.add(searchButton);

        JLabel subTitleLabel1 = new JLabel("Přihlašte se!");
        JLabel subTitleLabel2 = new JLabel("Pokud nemáte účet, registrujte se na pokladně.");

        subTitle.add(subTitleLabel1);
        subTitle.add(subTitleLabel2);


        JLabel userNameLabel = new JLabel("Uživatelské jméno:");
        JTextArea usernameField = new JTextArea();
        usernameField.setColumns(10);
        usernameField.setLineWrap(true);

        userNamePanel.add(userNameLabel);
        userNamePanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Heslo:");

        JPasswordField passwordField = new JPasswordField();
        passwordField.setColumns(10);

        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        JButton button = new JButton();
        button.setText("Přihlásit se");
        button.addActionListener(new ButtonClickedListener());
        loginButtonPanel.add(button);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        usernameField.requestFocus();
    }

    private class ButtonClickedListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            if(((JButton)e.getSource()).getText().equals("Vyhledat hru")){ // Vyhledani hry

            } else { // Prihlaseni

            }
        }
    }

}
