package src.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by root on 14.4.16.
 */
@Entity
@Table(name = "ZAMESTNANEC")
public class Zamestnanec {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_zamestnanec")
    private long id;

    @Column(nullable = false, length = 128, unique = true)
    private String username;

    @Column(nullable = false, length = 128)
    private String password;

    @Column(nullable = false, length = 128)
    private String jmeno;

    @Column(nullable = false, length = 128)
    private String prijmeni;

    @Column(nullable = false, length = 128)
    private String mesto;

    @Column(nullable = false, length = 128)
    private String ulice;

    @Column(nullable = false,name = "cislo_popisne")
    private int cisloPopisne;

    @Column(nullable = false, length = 16)
    private String telefon;

    @Column(nullable = false, length = 255, unique = true)
    private String email;

    @Column(nullable = false)
    private int plat;

    @Column(nullable = false)
    private boolean aktivni = true;

    @OneToMany(mappedBy = "zamestnanec")
    private List<Pujcka> pujcky;

    @ManyToMany
    @JoinTable(name="ma_pozice",
            joinColumns=@JoinColumn(name="id_zamestnanec"),
            inverseJoinColumns=@JoinColumn(name="id_pozice"))
    private List<Pozice> pozice;

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

    public int getPlat() {
        return plat;
    }

    public void setPlat(int plat) {
        this.plat = plat;
    }

    public boolean isAktivni() {
        return aktivni;
    }

    public void setAktivni(boolean aktivni) {
        this.aktivni = aktivni;
    }

    public List<Pujcka> getPujcky() {
        return pujcky;
    }

    public void setPujcky(List<Pujcka> pujcky) {
        this.pujcky = pujcky;
    }

    public List<Pozice> getPozice() {
        return pozice;
    }

    public void setPozice(List<Pozice> pozice) {
        this.pozice = pozice;
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
