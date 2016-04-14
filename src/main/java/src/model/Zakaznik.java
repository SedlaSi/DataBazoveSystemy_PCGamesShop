package src.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by root on 14.4.16.
 */
@Entity
public class Zakaznik {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_zakaznik")
    private long id;

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
}
