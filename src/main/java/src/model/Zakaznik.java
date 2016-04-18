package src.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by root on 14.4.16.
 */
@Entity
@Table(name = "zakaznik")
public class Zakaznik {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_zakaznik")
    private long id;

    @Column(nullable = false, length = 128, unique = true, name = "username")
    private String username;

    @Column(nullable = false, length = 128, name = "password")
    private String password;

    @Column(nullable = false, length = 128, name = "jmeno")
    private String jmeno;

    @Column(nullable = false, length = 128, name = "prijmeni")
    private String prijmeni;

    @Column(nullable = false, length = 128, name = "mesto")
    private String mesto;

    @Column(nullable = false, length = 128, name = "ulice")
    private String ulice;

    @Column(nullable = false,name = "cislo_popisne")
    private int cisloPopisne;

    @Column(nullable = false, length = 16, name = "telefon")
    private String telefon;

    @Column(nullable = false, length = 255, unique = true, name = "email")
    private String email;

    @OneToMany(mappedBy = "zakaznik")
    private List<Pujcka> pujcky;

    public long getId() {
        return id;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public String getUlice() {
        return ulice;
    }

    public void setUlice(String ulice) {
        this.ulice = ulice;
    }

    public int getCisloPopisne() {
        return cisloPopisne;
    }

    public void setCisloPopisne(int cisloPopisne) {
        this.cisloPopisne = cisloPopisne;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Pujcka> getPujcky() {
        return pujcky;
    }

    public void setPujcky(List<Pujcka> pujcky) {
        this.pujcky = pujcky;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
