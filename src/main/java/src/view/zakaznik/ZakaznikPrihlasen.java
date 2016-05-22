package src.view.zakaznik;

import src.login.Session;
import src.model.Exemplar;
import src.provider.ProviderController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by root on 20.4.16.
 */
public class ZakaznikPrihlasen extends JDialog{

    private ProviderController providerController;
    private Session session;
    private JPanel upperForNevraceneHry;
    private JPanel downerForVraceneHry;

    private JList pujceneNevraceneHry;
    private JList pujceneVraceneHry;
    private JButton refresh;

    public static void main(String [] args){

        final ZakaznikPrihlasen zkl =  new ZakaznikPrihlasen(null);
        //zkl.session = new Session("B", Role.ZAKAZNIK);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                zkl.startFrame();
            }
        });

    }

    public ZakaznikPrihlasen(ProviderController providerController){
        this.providerController = providerController;
        //session = new Session("username", Role.ZAKAZNIK);
        session = providerController.getZakaznikLoginController().getCurrentSession();
        if(session == null){
            System.out.println("Uživatel nepřihlášen!");
            return;
        }
    }

    public void startFrame(){
        setModalityType(ModalityType.APPLICATION_MODAL);
        this.setSize(500,450);
        this.setLocationRelativeTo(null);
        //this.setResizable(false);
        this.setTitle("Přihlášen jako: " + session.getUserName());
        //this.setLayout(new GridLayout(2,1,3,3));
        //Box boxLayout = Box.createVerticalBox();
        this.setLayout(new BorderLayout());

        JPanel upper = new JPanel(new BorderLayout());  //new GridLayout(3,1,2,2)
        JPanel downer = new JPanel(new BorderLayout());

        JPanel upperUp = new JPanel(new BorderLayout());
        upperForNevraceneHry = new JPanel();
        JPanel upperDowner = new JPanel(new FlowLayout());
        upper.add(upperUp,BorderLayout.NORTH);
        upper.add(upperForNevraceneHry,BorderLayout.CENTER);
        upper.add(upperDowner,BorderLayout.SOUTH);

        JPanel downerUp = new JPanel(new FlowLayout());
        downerForVraceneHry = new JPanel();
        JPanel downerDowner = new JPanel(new FlowLayout());

        // Plneni seznamu
        fillPujceneHry();

        downer.add(downerUp,BorderLayout.NORTH);
        downer.add(downerForVraceneHry,BorderLayout.CENTER);
        downer.add(downerDowner,BorderLayout.SOUTH);

        JLabel pujceneHryLabel = new JLabel("Půjčené hry:");
        refresh = new JButton();
        try {
            Image img = ImageIO.read(getClass().getResource("/refresh.png"));
            Image newimg = img.getScaledInstance( 30, 30,  java.awt.Image.SCALE_SMOOTH ) ;
            refresh.setIcon(new ImageIcon(newimg));
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Refresh icon cannot be loaded");
        }
        refresh.addActionListener(new RefreshButtonListener());
        JButton vyhledatNovouHru = new JButton("Vyhledat Novou Hru");
        vyhledatNovouHru.addActionListener(new ButtonClickedListener());
        upperUp.add(pujceneHryLabel,BorderLayout.CENTER);
        upperUp.add(refresh, BorderLayout.EAST);

        upperDowner.add(vyhledatNovouHru);

        JLabel historiePujcekLabel = new JLabel("Historie půjček:");

        JButton odhlasitSeButton = new JButton("Odhlásit se");
        odhlasitSeButton.addActionListener(new ButtonClickedListener());
        downerUp.add(historiePujcekLabel);

        downerDowner.add(odhlasitSeButton);


        //this.add(upper);
        //this.add(downer);
        this.add(upper,BorderLayout.NORTH);
        this.add(downer,BorderLayout.SOUTH);
        this.setVisible(true);
    }

    private void fillPujceneHry() {
        java.util.List<Exemplar> pujceneVraceneHryList = null;
        java.util.List<Exemplar> pujceneNevraceneHryList = null;
        pujceneVraceneHryList = providerController
                .getZakaznikPrihlasenController()
                .getVraceneHry();
        pujceneNevraceneHryList = providerController
                .getZakaznikPrihlasenController()
                .getNevraceneHry();
        if(pujceneVraceneHryList == null || pujceneVraceneHryList.isEmpty()){
            // Show empty list message
            try{
                downerForVraceneHry.remove(0);
            } catch (Exception e){

            }
            downerForVraceneHry.add(new JLabel("Neproběhli žádné půjčky."));
        } else {
            try{
                downerForVraceneHry.remove(0);
            } catch (Exception e){

            }
            String [] list = new String[pujceneVraceneHryList.size()];
            for(int i = 0; i < pujceneVraceneHryList.size() ; i++){
                Exemplar e = pujceneVraceneHryList.get(i);
                list[i] = e.getHra().getNazev() + " " + e.getPlatforma().getNazev() + ", produkt číslo: " + e.getId();
            }
            pujceneVraceneHry = new JList(list);
            JScrollPane scrollPane = new JScrollPane(pujceneVraceneHry);
            scrollPane.setSize(150,10);
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            downerForVraceneHry.add(scrollPane,BorderLayout.CENTER);
            //downerForVraceneHry.add(pujceneVraceneHry);
        }

        if(pujceneNevraceneHryList == null || pujceneNevraceneHryList.isEmpty()){
            // Show empty list message
            try{
                upperForNevraceneHry.remove(0);
            } catch (Exception e){

            }
            upperForNevraceneHry.add(new JLabel("Žádné nevrácené hry."));
        } else {
            try{
                upperForNevraceneHry.remove(0);
            } catch (Exception e){

            }

            String [] list = new String[pujceneNevraceneHryList.size()];
            for(int i = 0; i < pujceneNevraceneHryList.size() ; i++){
                Exemplar e = pujceneNevraceneHryList.get(i);
                list[i] = e.getHra().getNazev() + " " + e.getPlatforma().getNazev() + ", produkt číslo: " + e.getId();
            }

            pujceneNevraceneHry = new JList(list);
            JScrollPane scrollPane2 = new JScrollPane(pujceneNevraceneHry);
            scrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            upperForNevraceneHry.add(scrollPane2,BorderLayout.CENTER);
            //upperForNevraceneHry.add(pujceneNevraceneHry);
        }
    }

    private class ButtonClickedListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if(((JButton)e.getSource()).getText().equals("Vyhledat Novou Hru")){ // Vyhledani hry
                ZakaznikVyhledatHru zkl =  new ZakaznikVyhledatHru(providerController);
                zkl.createFrame();
            } else { // Odhlaseni
                dispose();
            }
        }
    }

    private class RefreshButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            refresh();
        }
    }

    public void refresh() {
        fillPujceneHry();
        this.invalidate();
        this.validate();
        this.repaint();
    }
}
