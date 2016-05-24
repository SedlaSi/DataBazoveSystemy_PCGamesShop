package src.view.admin;

import org.apache.commons.validator.EmailValidator;
import src.controller.AdminVytvoritZamestnanceController;
import src.model.Pozice;
import src.provider.ProviderController;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class AdminVytvoritProdejce extends JDialog implements ActionListener {
    private ProviderController providerController;
    private List<Pozice> poziceList;

    private JTextField jmeno;
    private JTextField prijmeni;
    private JTextField mesto;
    private JTextField ulice;

    private JTextField telefon;
    private JTextField email;
    private JTextField username;
    private JTextField password;
    private JSpinner cisloPopisne;
    private JSpinner plat;

    public AdminVytvoritProdejce(ProviderController providerController, List<Pozice> poziceList) {
        this.providerController = providerController;
        this.poziceList = poziceList;
    }

    public void startFrame() {
        setModalityType(ModalityType.APPLICATION_MODAL);
        setLayout(new FlowLayout());
        setTitle("Přidat Prodejce");

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        JPanel zamestnanecInfo = new JPanel(new GridLayout(9, 2));
        JPanel zamestnanecLogin = new JPanel(new GridLayout(2, 2));
        JPanel savePanel = new JPanel(new BorderLayout());

        zamestnanecInfo.setBorder(new TitledBorder("Osobní údaje"));
        zamestnanecLogin.setBorder(new TitledBorder("Přihlašovací údaje"));


        JLabel jmenoLabel = new JLabel("Jméno:");
        JLabel prijmeniLabel = new JLabel("Příjmení:");
        JLabel mestoLabel = new JLabel("Město:");
        JLabel uliceLabel = new JLabel("Ulice:");
        JLabel cisloPopisneLabel = new JLabel("Číslo popisné:");
        JLabel telefonLabel = new JLabel("Telefon:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel platLabel = new JLabel("Plat:");
        JLabel usernameLabel = new JLabel("Uživatelské jméno:");
        JLabel passwordLabel = new JLabel("Heslo:");

        jmeno = new JTextField();
        jmeno.setColumns(20);

        prijmeni = new JTextField();
        prijmeni.setColumns(20);

        mesto = new JTextField();
        mesto.setColumns(20);

        ulice = new JTextField();
        ulice.setColumns(20);

        telefon = new JTextField();
        telefon.setColumns(20);

        email = new JTextField();
        email.setColumns(20);

        username = new JTextField();
        username.setColumns(20);

        password = new JTextField();
        password.setColumns(20);

        cisloPopisne = new JSpinner(new SpinnerNumberModel(1, 1, 9999, 1));
        plat = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));

        zamestnanecInfo.add(jmenoLabel);
        zamestnanecInfo.add(jmeno);
        zamestnanecInfo.add(prijmeniLabel);
        zamestnanecInfo.add(prijmeni);
        zamestnanecInfo.add(mestoLabel);
        zamestnanecInfo.add(mesto);
        zamestnanecInfo.add(uliceLabel);
        zamestnanecInfo.add(ulice);
        zamestnanecInfo.add(cisloPopisneLabel);
        zamestnanecInfo.add(cisloPopisne);
        zamestnanecInfo.add(telefonLabel);
        zamestnanecInfo.add(telefon);
        zamestnanecInfo.add(emailLabel);
        zamestnanecInfo.add(email);
        zamestnanecInfo.add(platLabel);
        zamestnanecInfo.add(plat);

        zamestnanecLogin.add(usernameLabel);
        zamestnanecLogin.add(username);

        zamestnanecLogin.add(passwordLabel);
        zamestnanecLogin.add(password);

        JButton save = new JButton("Zaregistrovat");
        save.addActionListener(this);

        savePanel.add(save, BorderLayout.EAST);

        jPanel.add(zamestnanecInfo);
        jPanel.add(zamestnanecLogin);
        jPanel.add(savePanel);

        add(jPanel);

        pack();
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (validation()) {
            AdminVytvoritZamestnanceController adminCntrl = providerController.getAdminVytvoritZamestnanceController();
            adminCntrl.setEmail(email.getText());
            adminCntrl.setTelefon(telefon.getText());
            adminCntrl.setCisloPopisne((cisloPopisne.getValue()).toString());
            adminCntrl.setJmeno(jmeno.getText());
            adminCntrl.setPrijmeni(prijmeni.getText());
            adminCntrl.setMesto(mesto.getText());
            adminCntrl.setPassword(password.getText());
            adminCntrl.setUsername(username.getText());
            adminCntrl.setPlat((plat.getValue()).toString());
            adminCntrl.setUlice(ulice.getText());

            try {
                adminCntrl.createProjdejce();
                JOptionPane.showMessageDialog(this, "Prodejce úspěšně vytvořen", "Zaměstnanec", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Prodejce se nepodařilo vytvořit, chyba databáze, nebo už je použité stejné uživatelské jméno nebo email", "Zaměstnanec", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean validation() {
        boolean valid = true;
        EmailValidator emailValidator = EmailValidator.getInstance();

        if (jmeno.getText().equals("") || jmeno.getText().length() > 128) {
            valid = false;
            JOptionPane.showMessageDialog(this, "Jméno nemůže být prázdné nebo delší než 128 znaků", "Jméno", JOptionPane.ERROR_MESSAGE);
        } else if (prijmeni.getText().equals("") || prijmeni.getText().length() > 128) {
            valid = false;
            JOptionPane.showMessageDialog(this, "Příjmení nemůže být prázdné nebo delší než 128 znaků", "Příjmení", JOptionPane.ERROR_MESSAGE);
        } else if (mesto.getText().equals("") || mesto.getText().length() > 128) {
            valid = false;
            JOptionPane.showMessageDialog(this, "Město nemůže být prázdné nebo delší než 128 znaků", "Město", JOptionPane.ERROR_MESSAGE);
        } else if (ulice.getText().equals("") || ulice.getText().length() > 128) {
            valid = false;
            JOptionPane.showMessageDialog(this, "Ulice nemůže být prázdná nebo delší než 128 znaků", "Ulice", JOptionPane.ERROR_MESSAGE);
        } else if (username.getText().equals("") || username.getText().length() > 128) {
            valid = false;
            JOptionPane.showMessageDialog(this, "Uživatelské jméno nemůže být prázdná nebo delší než 128 znaků", "Uživatelské jméno", JOptionPane.ERROR_MESSAGE);
        } else if (password.getText().length() < 6 || password.getText().length() > 128) {
            valid = false;
            JOptionPane.showMessageDialog(this, "Heslo musí obsahovat minimálně 6 znaků a nesmí být delší než 128 znaků", "Heslo", JOptionPane.ERROR_MESSAGE);
        } else if (!emailValidator.isValid(email.getText())) {
            valid = false;
            JOptionPane.showMessageDialog(this, "Nevalidní email", "email", JOptionPane.ERROR_MESSAGE);
        } else if (telefon.getText().length() < 9 || telefon.getText().length() > 16 || !telefon.getText().matches("[0-9+]*")) {
            valid = false;
            JOptionPane.showMessageDialog(this, "Nevalidní telefonní číslo", "Telefon", JOptionPane.ERROR_MESSAGE);
        }

        return valid;
    }
}
