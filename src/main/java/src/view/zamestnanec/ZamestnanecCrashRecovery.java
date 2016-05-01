package src.view.zamestnanec;

import src.provider.ProviderController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by root on 1.5.16.
 */
public class ZamestnanecCrashRecovery extends JFrame {

    private ProviderController providerController;
    private JTextArea usernameField;
    private JPasswordField passwordField;
    private JLabel hint;

    public static void main(String [] args){

        final ZamestnanecCrashRecovery zkl =  new ZamestnanecCrashRecovery(null);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                zkl.startFrame();
            }
        });

    }

    public ZamestnanecCrashRecovery(ProviderController providerController){
        this.providerController = providerController;
    }

    public void startFrame(){
        this.setSize(400,200);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Crash Recovery Screen");
        this.setLayout(new GridLayout(5,1,3,3));

        JPanel subTitle = new JPanel();
        JPanel userNamePanel = new JPanel();
        JPanel passwordPanel = new JPanel();
        JPanel loginButtonPanel = new JPanel();
        JPanel hintPanel = new JPanel();

        subTitle.setLayout(new FlowLayout());
        userNamePanel.setLayout(new GridLayout(3,1,3,3));
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

        JLabel userNameLabel = new JLabel("Systém detekoval kolizi v přihlašování.");

        JLabel label2 = new JLabel(
                "Zaměstnanec "+ providerController.getZamestnanecLoginController().getUserNameOfLoggedZamestnanec() + " je přihlášen na kase.");
        JLabel label3 = new JLabel("Zadejte heslo k přihlášenému zaměstnanci pro jeho odhlášení:");

        userNamePanel.add(userNameLabel);
        userNamePanel.add(label2);
        userNamePanel.add(label3);

        JLabel passwordLabel = new JLabel("Heslo:");

        passwordField = new JPasswordField();
        passwordField.setColumns(10);

        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        JButton button = new JButton();
        button.setText("Odhlásit zaměstnance");
        button.addActionListener(new ButtonClickedListener());
        loginButtonPanel.add(button);

        hint = new JLabel("");
        hintPanel.add(hint);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //usernameField.requestFocus();
    }

    private class ButtonClickedListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            //System.out.println(usernameField.getText());
            System.out.println(passwordField.getText());
            try{
                if(providerController.getZamestnanecLoginController().performCrashRecoveryLogout(passwordField.getText())){
                    invokeZamestnanecLogin();
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
        hint.setText("Špatné heslo.");
    }

    private void invokeZamestnanecLogin(){
        final ZamestnanecLogin zkl =  new ZamestnanecLogin(providerController);

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                zkl.startFrame();
            }
        });
        this.dispose();
    }

}
