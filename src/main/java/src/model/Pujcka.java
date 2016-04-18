package src.model;

import javax.persistence.*;

/**
 * Created by root on 14.4.16.
 */
@Entity
@Table(name = "byl_propujcen")
public class Pujcka {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_byl_propujcen")
    private long id;

    @ManyToOne
    @Column(name = "id_exemplar")
    private Exemplar exemplar;

    @ManyToOne
    @Column(name = "id_zakaznik")
    private Zakaznik zakaznik;

    @ManyToOne
    @Column(name = "id_zamestnanec")
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
