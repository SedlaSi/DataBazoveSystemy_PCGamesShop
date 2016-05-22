package src.view.zamestnanec;

import src.login.Session;
import src.provider.ProviderController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by root on 14.4.16.
 */
public class ZamestnanecLogin extends JDialog{

    private ProviderController providerController;
    private JTextField usernameField;
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
        setModalityType(ModalityType.APPLICATION_MODAL);
//        if(providerController.getZamestnanecLoginController().someoneIsLoggedInKasa()){
//            invokeZamestnanecCrashRecovery();
//            return;
//        }

        this.setSize(400, 200);
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
        usernameField = new JTextField();
        usernameField.setColumns(10);

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

        usernameField.requestFocus();
    }

    public synchronized Session getLogged() {
        Session session = providerController.getZamestnanecLoginController().getCurrentSession();

        return session;
    }

    private class ButtonClickedListener implements ActionListener {

        public synchronized void actionPerformed(ActionEvent e) {

           System.out.println(usernameField.getText());
           System.out.println(new String(passwordField.getPassword()));
           try{
               providerController.getZamestnanecLoginController().setUserName(usernameField.getText());
               providerController.getZamestnanecLoginController().setPassWord(new String(passwordField.getPassword()));
               if(providerController.getZamestnanecLoginController().performLogin()){

                   dispose();
                   ZamestnanecPotvrditPrevzetiHry zkl =  new ZamestnanecPotvrditPrevzetiHry(providerController);
                   zkl.startFrame();
                   usernameField.setText("");
                   passwordField.setText("");
                   setVisible(true);
                   repaint();
               } else {
                   showHint();
               }
           } catch (Exception exc){
               exc.printStackTrace();
               showHint();
           }


        }
    }

    private void showHint() {
        hint.setText("Špatné uživatelské jméno nebo heslo.");
    }

}
