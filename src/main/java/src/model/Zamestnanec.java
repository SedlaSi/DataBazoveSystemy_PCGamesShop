package src.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by root on 14.4.16.
 */
@Entity
@Table(name = "zamestnanec")
@NamedQueries({
        @NamedQuery(name = "Zamestnanec.getList", query = "SELECT z FROM Zamestnanec z"),
        @NamedQuery(name = "Zamestnanec.getByJmenoPrijmeni", query = "SELECT z FROM Zamestnanec z WHERE z.jmeno = :j" +
                " AND z.prijmeni = :p"),
        @NamedQuery(name = "Zamestnanec.getByUserName", query = "SELECT z FROM Zamestnanec z WHERE z.username = :us")
})
@NamedStoredProcedureQuery(
        name = "create_active_prodejce",
        procedureName = "create_active_prodejce",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "username"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = byte [].class, name = "password"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "jmeno"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "prijmeni"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "mesto"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "ulice"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = int.class, name = "cislo_popisne"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "telefon"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "email"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = int.class, name = "plat"),
        }
)
public class Zamestnanec {

    public Zamestnanec() {
    }

    public Zamestnanec(String jmeno, String prijmeni, String mesto, String ulice, int cisloPopisne, String telefon, String email, String username, byte [] password, int plat, Pozice pozice) {
        this.username = username;
        this.password = password;
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.mesto = mesto;
        this.ulice = ulice;
        this.cisloPopisne = cisloPopisne;
        this.telefon = telefon;
        this.email = email;
        this.plat = plat;
        this.pozice = pozice;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_zamestnanec")
    private long id;

    @Column(nullable = false, length = 128, unique = true, name = "username")
    private String username;

    @Column(nullable = false, length = 256, name = "password")
    private byte [] password;

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

    @Column(nullable = false, unique = true, name = "email")
    private String email;

    @Column(nullable = false, name = "plat")
    private int plat;

    @Column(nullable = false, name = "aktivni")
    private boolean aktivni = true;

    @OneToMany(mappedBy = "zamestnanec")
    private List<Pujcka> pujcky;

    @ManyToOne
    @JoinColumn(name = "id_pozice", nullable = false)
    private Pozice pozice;

//    @OneToMany(mappedBy = "zamestnanec")
//    private List<Kasa> kasa;

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

    public Pozice getPozice() {
        return pozice;
    }

    public void setPozice(Pozice pozice) {
        this.pozice = pozice;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    //    public List<Kasa> getKasa() {
//        return kasa;
//    }

//    public void setKasa(List<Kasa> kasa) {
//        this.kasa = kasa;
//    }
}
