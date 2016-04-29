package src.view.zamestnanec;

import src.provider.ProviderController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by root on 14.4.16.
 */
public class ZamestnanecLogin extends JFrame{

    private ProviderController providerController;
    private JTextArea usernameField;
    private JPasswordField passwordField;
    private JLabel hint;

    public static void main(String [] args){

        final ZamestnanecLogin zkl =  new ZamestnanecLogin(null);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                zkl.startFrame();
            }
        });

    }

    public ZamestnanecLogin(ProviderController providerController){
        this.providerController = providerController;
    }

    public void startFrame(){
        this.setSize(400,200);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Login Screen");
        this.setLayout(new GridLayout(5,1,3,3));

        JPanel subTitle = new JPanel();
        JPanel userNamePanel = new JPanel();
        JPanel passwordPanel = new JPanel();
        JPanel loginButtonPanel = new JPanel();
        JPanel hintPanel = new JPanel();

        subTitle.setLayout(new FlowLayout());
        userNamePanel.setLayout(new GridLayout(1,3,3,3));
        passwordPanel.setLayout(new GridLayout(1,3,3,3));
        loginButtonPanel.setLayout(new FlowLayout());
        hintPanel.setLayout(new FlowLayout());

        this.add(subTitle);
        this.add(userNamePanel);
        this.add(passwordPanel);
        this.add(loginButtonPanel);
        this.add(hintPanel);

        JLabel subTitleLabel1 = new JLabel("Přihlašte se:");
        subTitle.add(subTitleLabel1);

        JLabel userNameLabel = new JLabel("Uživatelské jméno:");
        usernameField = new JTextArea();
        usernameField.setColumns(10);
        usernameField.setLineWrap(true);

        userNamePanel.add(userNameLabel);
        userNamePanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Heslo:");

        passwordField = new JPasswordField();
        passwordField.setColumns(10);

        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        JButton button = new JButton();
        button.setText("Přihlásit se");
        button.addActionListener(new ButtonClickedListener());
        loginButtonPanel.add(button);

        hint = new JLabel("");
        hintPanel.add(hint);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        usernameField.requestFocus();
    }

    private class ButtonClickedListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            // prihlaseni zamestnance
            System.out.println(usernameField.getText());
            System.out.println(passwordField.getText());
            providerController.getZamestnanecLoginController().setUserName(usernameField.getText());
            providerController.getZamestnanecLoginController().setPassWord(passwordField.getText());
            if(providerController.getZamestnanecLoginController().performLogin()){
                invokeZamestnanecPotvrditPrevzetiHry();
            } else {
                showHint();
            }
        }
    }

    private void showHint() {
        hint.setText("Špatné uživatelské jméno nebo heslo.");
    }

    private void invokeZamestnanecPotvrditPrevzetiHry(){
        final ZamestnanecPotvrditPrevzetiHry zkl =  new ZamestnanecPotvrditPrevzetiHry(providerController);

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                zkl.startFrame();
            }
        });
        this.setVisible(false);

    }


}
