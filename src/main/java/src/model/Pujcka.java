package src.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by root on 14.4.16.
 */
@Entity
@Table(name = "byl_propujcen")
@NamedQueries({
        @NamedQuery(name = "Pujcka.getByExemplarId", query = "SELECT p FROM Pujcka p where p.exemplar.id = :id AND p.vraceno = NULL")
})
public class Pujcka {

    public Pujcka() {
    }

    public Pujcka(Date pujceno, Date vraceno, int cena, Exemplar exemplar, Zakaznik zakaznik, Zamestnanec zamestnanec) {
        this.pujceno = pujceno;
        this.vraceno = vraceno;
        this.cena = cena;
        this.exemplar = exemplar;
        this.zakaznik = zakaznik;
        this.zamestnanec = zamestnanec;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_byl_propujcen")
    private long id;

    @Column(name = "pujceno", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date pujceno;

    @Column(name = "vraceno")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vraceno = null;

    @Column(nullable = false, name = "cena")
    private int cena;

    @ManyToOne
    @JoinColumn(name = "id_exemplar",nullable = false)
    private Exemplar exemplar;

    @ManyToOne
    @JoinColumn(name = "id_zakaznik",nullable = false)
    private Zakaznik zakaznik;

    @ManyToOne
    @JoinColumn(name = "id_zamestnanec",nullable = false)
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

    public Date getPujceno() {
        return pujceno;
    }

    public void setPujceno(Date pujceno) {
        this.pujceno = pujceno;
    }

    public Date getVraceno() {
        return vraceno;
    }

    public void setVraceno(Date vraceno) {
        this.vraceno = vraceno;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }
}
