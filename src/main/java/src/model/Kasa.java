package src.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by root on 30.4.16.
 */
@Entity
@Table(name = "kasa")
public class Kasa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_kasa")
    private int id;

    @ManyToOne
    @JoinColumn(name = "zamestnanec", nullable = false)
    private Zamestnanec zamestnanec;

    @Column(name = "prihlaseni", nullable = false)
    private Date prihlaseni;

    @Column(name = "odhlaseni")
    private Date odhlaseni = null;

    public Zamestnanec getZamestnanec() {
        return zamestnanec;
    }

    public void setZamestnanec(Zamestnanec zamestnanec) {
        this.zamestnanec = zamestnanec;
    }

    public Date getPrihlaseni() {
        return prihlaseni;
    }

    public void setPrihlaseni(Date prihlaseni) {
        this.prihlaseni = prihlaseni;
    }

    public Date getOdhlaseni() {
        return odhlaseni;
    }

    public void setOdhlaseni(Date odhlaseni) {
        this.odhlaseni = odhlaseni;
    }
}
