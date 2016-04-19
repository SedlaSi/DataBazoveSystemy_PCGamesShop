package src.controller;

import org.apache.commons.validator.EmailValidator;
import src.data.ZamestnanecDAO;
import src.model.Zamestnanec;
import src.provider.Provider;

/**
 * Created by root on 15.4.16.
 */
public class AdminVytvoritZamestnanceController extends TemplateController{

    private ZamestnanecDAO zamestnanecDAO;

    private String username;

    private String password;

    private String jmeno;

    private String prijmeni;

    private String mesto;

    private String ulice;

    private String cisloPopisne;

    private String telefon;

    private String email;

    private String plat;


    public AdminVytvoritZamestnanceController(Provider provider) {
        super(provider);
        zamestnanecDAO = provider.getProviderDAO().getZamestnanecDAO();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public void setUlice(String ulice) {
        this.ulice = ulice;
    }

    public void setCisloPopisne(String cisloPopisne) {
        this.cisloPopisne = cisloPopisne;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPlat(String plat) {
        this.plat = plat;
    }

    public void createZamestnanec(){
        int cp = Integer.parseInt(cisloPopisne);
        int pt = Integer.parseInt(plat);

        Zamestnanec z = new Zamestnanec();
        z.setUsername(username);
        z.setPassword(password);
        z.setJmeno(jmeno);
        z.setMesto(mesto);
        z.setPrijmeni(prijmeni);
        z.setUlice(ulice);
        z.setCisloPopisne(cp);
        z.setTelefon(telefon);
        z.setEmail(email);
        z.setPlat(pt);

        zamestnanecDAO.create(z);
    }

    public boolean validData() {
        boolean valid = true;
        int ascii;
        // Jmeno
        if(jmeno.isEmpty()){
            return false;
        }
        for(char c : jmeno.toCharArray()){
            ascii = (int)c;
            if(ascii < 65 || (ascii > 90 && ascii < 97) || ascii > 122){
                return false;
            }
        }
        // Prijmeni
        if(prijmeni.isEmpty()){
            return false;
        }
        for(char c : prijmeni.toCharArray()){
            ascii = (int)c;
            if(ascii < 65 || (ascii > 90 && ascii < 97) || ascii > 122){
                return false;
            }
        }
        // Username
        if(username.isEmpty()){
            return false;
        }
        for(char c : username.toCharArray()){
            if(c == ' '){
                return false;
            }
        }
        // Password
        if(password.isEmpty()){
            return false;
        }
        for(char c : password.toCharArray()){
            if(c == ' '){
                return false;
            }
        }
        // Mesto
        if(mesto.isEmpty()){
            return false;
        }
        for(char c : mesto.toCharArray()){
            if(c == ' '){
                return false;
            }
        }
        // Ulice
        if(ulice.isEmpty()){
            return false;
        }
        for(char c : ulice.toCharArray()){
            if(c == ' '){
                return false;
            }
        }
        // Telefon
        if(telefon.isEmpty()){
            return false;
        }
        for(char c : telefon.toCharArray()){
            ascii = (int)c;
            if(!(ascii > 47 && ascii < 58) && c != ' ' && c != '+'){
                System.out.println("Telefon validation failed");
                return false;
            }
        }
        // Email
        if(email.isEmpty()){
            return false;
        }
        EmailValidator emailValidator = EmailValidator.getInstance();
        if(!emailValidator.isValid(email)){
            System.out.println("Email validation failed");
         return false;
        }
        // Plat
        if(plat.isEmpty()){
            return false;
        }
        try{
            Double.parseDouble(plat);
        } catch (Exception e){
            return false;
        }

        // Cislo Popisne
        if(cisloPopisne.isEmpty()){
            return false;
        }
        try{
            if(Integer.parseInt(cisloPopisne) < 1){
                return false;
            }
        } catch (Exception e){
            return false;
        }

        return true;
    }

}
