package src.model;

import javax.persistence.*;

/**
 * Created by root on 14.4.16.
 */
@Entity
@Table(name = "pozice")
public class Pozice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_pozice")
    private long id;

    @Column(nullable = false,length = 128, name = "nazev")
    private String nazev;

    @ManyToOne
    @JoinColumn(name = "id_zamestnanec", nullable = false)
    private Zamestnanec zamestnanec;

    public long getId() {
        return id;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public Zamestnanec getZamestnanec() {
        return zamestnanec;
    }

    public void setZamestnanec(Zamestnanec zamestnanec) {
        this.zamestnanec = zamestnanec;
    }
}
