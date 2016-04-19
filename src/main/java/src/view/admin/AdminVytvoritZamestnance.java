package src.view.admin;

import src.controller.AdminVytvoritZamestnanceController;
import src.provider.ProviderController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by root on 15.4.16.
 */
public class AdminVytvoritZamestnance extends JFrame {

    private JTextArea jmeno;
    private JTextArea prijmeni;
    private JTextArea mesto;
    private JTextArea ulice;
    private JTextArea cisloPopisne;
    private JTextArea telefon;
    private JTextArea email;
    private JTextArea plat;
    private JTextArea username;
    private JTextArea password;

    private JLabel infoJmeno;
    private JLabel infoPrijmeni;
    private JLabel infoMesto;
    private JLabel infoUlice;
    private JLabel infoCisloPopisne;
    private JLabel infoTelefon;
    private JLabel infoEmail;
    private JLabel infoPlat;
    private JLabel infoUsername;
    private JLabel infoPassword;


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
        this.setSize(700,500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Přidat Zaměstnance");
        this.setLayout(new GridLayout(14,1,3,3));

        JPanel titlePanel  =  new JPanel();
        JPanel jmenoPanel = new JPanel();
        JPanel prijmeniPanel = new JPanel();
        JPanel mestoPanel = new JPanel();
        JPanel ulicePanel = new JPanel();
        JPanel cisloPopisnePanel = new JPanel();
        JPanel telefonPanel = new JPanel();
        JPanel emailPanel = new JPanel();
        JPanel platPanel = new JPanel();
        JPanel gab = new JPanel();
        JPanel usernamePanel = new JPanel();
        JPanel passwordPanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        titlePanel.setLayout(new FlowLayout());
        jmenoPanel.setLayout(new GridLayout(1,3,3,3));
        prijmeniPanel.setLayout(new GridLayout(1,3,3,3));
        mestoPanel.setLayout(new GridLayout(1,3,3,3));
        ulicePanel.setLayout(new GridLayout(1,3,3,3));
        cisloPopisnePanel.setLayout(new GridLayout(1,3,3,3));
        telefonPanel.setLayout(new GridLayout(1,3,3,3));
        emailPanel.setLayout(new GridLayout(1,3,3,3));
        platPanel.setLayout(new GridLayout(1,3,3,3));
        usernamePanel.setLayout(new GridLayout(1,3,3,3));
        passwordPanel.setLayout(new GridLayout(1,3,3,3));
        buttonPanel.setLayout(new FlowLayout());

        JLabel title = new JLabel("Vytvořit nového zaměstnance:");
        titlePanel.add(title);

        infoJmeno = new JLabel("");
        infoPrijmeni = new JLabel("");
        infoMesto = new JLabel("");
        infoUlice = new JLabel("");
        infoCisloPopisne = new JLabel("");
        infoTelefon = new JLabel("");
        infoEmail = new JLabel("");
        infoPlat = new JLabel("");
        infoUsername = new JLabel("");
        infoPassword = new JLabel("");


        JLabel jmenoLabel = new JLabel("Jméno:");
        jmeno = new JTextArea();
        jmeno.setColumns(20);
        jmenoPanel.add(jmenoLabel);
        jmenoPanel.add(jmeno);
        jmenoPanel.add(infoJmeno);

        JLabel prijmeniLabel = new JLabel("Příjmení:");
        prijmeni = new JTextArea();
        prijmeni.setColumns(20);
        prijmeniPanel.add(prijmeniLabel);
        prijmeniPanel.add(prijmeni);
        prijmeniPanel.add(infoPrijmeni);

        JLabel mestoLabel = new JLabel("Město:");
        mesto = new JTextArea();
        mesto.setColumns(20);
        mestoPanel.add(mestoLabel);
        mestoPanel.add(mesto);
        mestoPanel.add(infoMesto);

        JLabel uliceLabel = new JLabel("Ulice:");
        ulice = new JTextArea();
        ulice.setColumns(20);
        ulicePanel.add(uliceLabel);
        ulicePanel.add(ulice);
        ulicePanel.add(infoUlice);

        JLabel cisloPopisneLabel = new JLabel("Číslo popisné:");
        cisloPopisne = new JTextArea();
        cisloPopisne.setColumns(20);
        cisloPopisnePanel.add(cisloPopisneLabel);
        cisloPopisnePanel.add(cisloPopisne);
        cisloPopisnePanel.add(infoCisloPopisne);

        JLabel telefonLabel = new JLabel("Telefon:");
        telefon = new JTextArea();
        telefon.setColumns(20);
        telefonPanel.add(telefonLabel);
        telefonPanel.add(telefon);
        telefonPanel.add(infoTelefon);

        JLabel emailLabel = new JLabel("Email:");
        email = new JTextArea();
        email.setColumns(20);
        emailPanel.add(emailLabel);
        emailPanel.add(email);
        emailPanel.add(infoEmail);

        JLabel platLabel = new JLabel("Plat:");
        plat = new JTextArea();
        plat.setColumns(20);
        platPanel.add(platLabel);
        platPanel.add(plat);
        platPanel.add(infoPlat);

        JLabel usernameLabel = new JLabel("Uživatelské jméno:");
        username = new JTextArea();
        username.setColumns(20);
        usernamePanel.add(usernameLabel);
        usernamePanel.add(username);
        usernamePanel.add(infoUsername);

        JLabel passwordLabel = new JLabel("Heslo:");
        password = new JTextArea();
        password.setColumns(20);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(password);
        passwordPanel.add(infoPassword);

        JLabel buttonGabLabel = new JLabel("");
        JButton button = new JButton("Zaregistrovat");
        button.addActionListener(new ButtonClickedListener());
        buttonPanel.add(buttonGabLabel);
        buttonPanel.add(button);



        this.add(titlePanel); this.add(jmenoPanel); this.add(prijmeniPanel); this.add(mestoPanel); this.add(ulicePanel);
        this.add(cisloPopisnePanel); this.add(telefonPanel); this.add(emailPanel); this.add(platPanel); this.add(gab);
        this.add(usernamePanel); this.add(passwordPanel); this.add(buttonPanel);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private class ButtonClickedListener implements ActionListener {

        public void actionPerformed(ActionEvent e) { //  Zaregistrování uživatele
            AdminVytvoritZamestnanceController adminCntrl = providerController.getAdminVytvoritZamestnanceController();
            adminCntrl.setEmail(email.getText());
            adminCntrl.setTelefon(telefon.getText());
            adminCntrl.setCisloPopisne(cisloPopisne.getText());
            adminCntrl.setJmeno(jmeno.getText());
            adminCntrl.setPrijmeni(prijmeni.getText());
            adminCntrl.setMesto(mesto.getText());
            adminCntrl.setPassword(password.getText());
            adminCntrl.setUsername(username.getText());
            adminCntrl.setPlat(plat.getText());
            adminCntrl.setUlice(ulice.getText());
            if(adminCntrl.validData()){
                adminCntrl.createZamestnanec();
            } else {
                showHint();
            }
        }
    }

    private void showHint(){
        infoJmeno.setText("zadejte jméno ve formátu 'Abcdef'");
        infoPrijmeni.setText("zadejte příjmení ve formátu 'Abcdef'");
        infoMesto.setText("zadejte město ve formátu 'abcdef'");
        infoUlice.setText("zadejte ulici ve formátu 'abcdef'");
        infoCisloPopisne.setText("zadejte číslo od 1 do 999");
        infoTelefon.setText("zadejte telefoní číslo");
        infoEmail.setText("zadejte email ve formátu 'nazev@adresa.pripona'");
        infoPlat.setText("zadejte číslo větší rovno nule");
        infoUsername.setText("zadejte libovolné neprázdné uživatelské jméno");
        infoPassword.setText("zadejte libovolné neprázdné heslo");
    }

}
