package src.model;

import javax.persistence.*;

/**
 * Created by root on 14.4.16.
 */
@Entity
//@Table(name = "BYL_PROPUJCEN")
public class Pujcka {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Exemplar exemplar;

    @ManyToOne
    private Zakaznik zakaznik;

    @ManyToOne
    private Zamestnanec zamestnanec;

    public long getId() {
        return id;
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

    public Zakaznik getZakaznik() {
        return zakaznik;
    }

    public void setZakaznik(Zakaznik zakaznik) {
        this.zakaznik = zakaznik;
    }

    public Zamestnanec getZamestnanec() {
        return zamestnanec;
    }

    public void setZamestnanec(Zamestnanec zamestnanec) {
        this.zamestnanec = zamestnanec;
    }
}
