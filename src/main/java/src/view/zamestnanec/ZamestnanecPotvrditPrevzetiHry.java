package src.view.zamestnanec;

import src.provider.ProviderController;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by root on 22.4.16.
 */
public class ZamestnanecPotvrditPrevzetiHry extends JDialog implements ActionListener {
    private ProviderController providerController;
    private JTextField kodExemplare;
    private JTextField datum;
    private JButton potvrdit;
    private JButton odhlasit;
    private JButton pujcit;
    private JLabel hint;

    public ZamestnanecPotvrditPrevzetiHry(ProviderController providerController) {
        this.providerController = providerController;
    }

    public void startFrame() {
        setModalityType(ModalityType.APPLICATION_MODAL);

        setLayout(new FlowLayout());
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        JPanel prevzetiPanel = new JPanel();
        prevzetiPanel.setLayout(new BoxLayout(prevzetiPanel, BoxLayout.Y_AXIS));

        JPanel prevzetiInputPanel = new JPanel(new GridLayout(2, 2));
        JPanel prevzetiButtonPanel = new JPanel(new BorderLayout());
        JPanel vypujcitButtonPanel = new JPanel(new BorderLayout());
        JPanel odhlasitButtonPanel = new JPanel(new BorderLayout());

        prevzetiPanel.setBorder(new TitledBorder("Převzít exemplář"));
        vypujcitButtonPanel.setBorder(new TitledBorder("Vypůjčit exemplár"));
        odhlasitButtonPanel.setBorder(new TitledBorder("Odhlásit"));

        setTitle("Přihlášen jako: " + providerController.getZamestnanecLoginController().getCurrentSession().getUserName());

        JLabel kodExemplareLabel = new JLabel("Kód exempláře:");
        JLabel datumLabel = new JLabel("Čas převzetí:");


        kodExemplare = new JTextField();

        datum = new JTextField();
        datum.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH).format(new Date()));

        potvrdit = new JButton("Potvrdit převzetí");
        potvrdit.addActionListener(this);

        hint = new JLabel("");

        odhlasit = new JButton("Odhlásit se");
        odhlasit.addActionListener(this);

        pujcit = new JButton("Vypůjčit");
        pujcit.addActionListener(this);

        prevzetiInputPanel.add(kodExemplareLabel);
        prevzetiInputPanel.add(kodExemplare);

        prevzetiInputPanel.add(datumLabel);
        prevzetiInputPanel.add(datum);

        prevzetiButtonPanel.add(potvrdit, BorderLayout.EAST);
        odhlasitButtonPanel.add(odhlasit, BorderLayout.EAST);
        vypujcitButtonPanel.add(pujcit, BorderLayout.EAST);

        JPanel hintPanel = new JPanel(new FlowLayout());
        hintPanel.add(hint);

        prevzetiPanel.add(prevzetiInputPanel);
        prevzetiPanel.add(prevzetiButtonPanel);

        jPanel.add(prevzetiPanel);
        jPanel.add(vypujcitButtonPanel);
        jPanel.add(odhlasitButtonPanel);
        jPanel.add(hintPanel);

        add(jPanel);

        hint.setText(".");

        pack();

        hint.setText("");

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == odhlasit) {
            dispose();
        } else if (source == potvrdit) {
            if (!providerController.getZamestnanecPotrvditPrevzetiHryController().potvrdit(kodExemplare.getText(), datum.getText())) {
                showHint();
            } else {
                showSuccess();
            }
        } else if (source == pujcit) {
            ZamestnanecPrihlasenVyhledatHru zkl = new ZamestnanecPrihlasenVyhledatHru(providerController);
            zkl.createFrame();
            datum.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH).format(new Date()));
        }
    }

    private void showSuccess() {
        kodExemplare.setText("");
        datum.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH).format(new Date()));
        hint.setText("Produkt byl úspěšně přijat.");
        this.invalidate();
        this.validate();
        this.repaint();
    }

    private void showHint() {
        hint.setText("Chybně zadané informace.");
        this.invalidate();
        this.validate();
        this.repaint();
    }
}
