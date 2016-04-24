package src.view.zakaznik;

import src.login.Session;
import src.model.Exemplar;
import src.provider.ProviderController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by root on 20.4.16.
 */
public class ZakaznikPrihlasen extends JFrame {

    private ProviderController providerController;
    private Session session;
    private JPanel upperForNevraceneHry;
    private JPanel downerForVraceneHry;

    private JList pujceneNevraceneHry;
    private JList pujceneVraceneHry;

    public static void main(String [] args){

        final ZakaznikPrihlasen zkl =  new ZakaznikPrihlasen(null);
        //zkl.session = new Session("user", Role.ZAKAZNIK);
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
        this.setSize(400,400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Přihlášen jako: " + session.getUserName());
        this.setLayout(new GridLayout(2,1,3,3));

        JPanel upper = new JPanel(new GridLayout(3,1,3,3));
        JPanel downer = new JPanel(new GridLayout(3,1,3,3));

        JPanel upperUp = new JPanel(new FlowLayout());
        upperForNevraceneHry = new JPanel();
        JPanel upperDowner = new JPanel(new FlowLayout());
        upper.add(upperUp);
        upper.add(upperForNevraceneHry);
        upper.add(upperDowner);

        JPanel downerUp = new JPanel(new FlowLayout());
        downerForVraceneHry = new JPanel();
        JPanel downerDowner = new JPanel(new FlowLayout());

        // Plneni seznamu
        fillPujceneHry();

        downer.add(downerUp);
        downer.add(downerForVraceneHry);
        downer.add(downerDowner);

        JLabel pujceneHryLabel = new JLabel("Půjčené hry:");

        JButton vyhledatNovouHru = new JButton("Vyhledat Novou Hru");
        vyhledatNovouHru.addActionListener(new ButtonClickedListener());
        upperUp.add(pujceneHryLabel);

        upperDowner.add(vyhledatNovouHru);

        JLabel historiePujcekLabel = new JLabel("Historie půjček:");

        JButton odhlasitSeButton = new JButton("Odhlásit se");
        odhlasitSeButton.addActionListener(new ButtonClickedListener());
        downerUp.add(historiePujcekLabel);

        downerDowner.add(odhlasitSeButton);


        this.add(upper);
        this.add(downer);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
                //System.out.println("Vyhledat");
                invokeZakaznikPrihlasenVyhledatHru();
            } else { // Odhlaseni
                invokeZakaznikLogin();
                //System.out.println("Logout");
            }
        }
    }

    private void invokeZakaznikLogin(){
        providerController.getZakaznikLoginController().performLogout();
        // invoke frame
        final ZakaznikLogin zkl =  new ZakaznikLogin(providerController);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                zkl.startFrame();
            }
        });
        this.setState(JFrame.ABORT);
    }

    private void invokeZakaznikPrihlasenVyhledatHru(){
        // invoke frame
        final ZakaznikPrihlasenVyhledatHru zkl =  new ZakaznikPrihlasenVyhledatHru(providerController);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                zkl.startFrame();
            }
        });
    }
}
