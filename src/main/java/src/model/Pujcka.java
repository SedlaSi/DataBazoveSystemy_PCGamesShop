package src.model;

import javax.persistence.*;
import java.sql.Date;

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

    @Column(name = "pujceno", nullable = false)
    private Date pujceno;

    @Column(name = "vraceno")
    private Date vraceno;

    @ManyToOne
    @JoinColumn(name = "id_exemplar")
    private Exemplar exemplar;

    @ManyToOne
    @JoinColumn(name = "id_zakaznik")
    private Zakaznik zakaznik;

    @ManyToOne
    @JoinColumn(name = "id_zamestnanec")
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
}
