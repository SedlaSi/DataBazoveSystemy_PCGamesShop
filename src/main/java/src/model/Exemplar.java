package src.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 14.4.16.
 */
@Entity
@Table(name = "exemplar")
@NamedQueries(
        {
                @NamedQuery(name = "Exemplar.getList", query = "SELECT e FROM Exemplar e"),
                @NamedQuery(name = "Exemplar.getById", query = "SELECT x FROM Exemplar x WHERE x.id = :id")
        }
)
public class Exemplar {

    public Exemplar() {
    }

    public Exemplar(String stav, int cena, Date rokVydani, Hra hra, Platforma platforma) {
        this.stav = stav;
        this.cena = cena;
        this.rokVydani = rokVydani;
        this.hra = hra;
        this.platforma = platforma;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_exemplar")
    private long id;

    @Column(name = "stav")
    private String stav;

    @Column(nullable = false,name = "cena")
    private int cena;

    @Column(nullable = false,name = "rok_vydani", updatable = false)
    private Date rokVydani;

    @Column(nullable = false, name = "aktivni")
    private boolean aktivni = true;

    @ManyToOne
    @JoinColumn(name = "id_hra", nullable = false, updatable = false)
    private Hra hra;

    @ManyToOne
    @JoinColumn(name = "id_platforma", nullable = false, updatable = false)
    private Platforma platforma;

    @OneToMany(mappedBy = "exemplar")
    private List<Pujcka> pujcka;

    public long getId() {
        return id;
    }

    public String getStav() {
        return stav;
    }

    public void setStav(String stav) {
        this.stav = stav;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public Date getRokVydani() {
        return rokVydani;
    }

    public void setRokVydani(Date rokVydani) {
        this.rokVydani = rokVydani;
    }

    public boolean isAktivni() {
        return aktivni;
    }

    public void setAktivni(boolean aktivni) {
        this.aktivni = aktivni;
    }

    public Hra getHra() {
        return hra;
    }

    public void setHra(Hra hra) {
        this.hra = hra;
    }

    public Platforma getPlatforma() {
        return platforma;
    }

    public void setPlatforma(Platforma platforma) {
        this.platforma = platforma;
    }

    public List<Pujcka> getPujcka() {
        return pujcka;
    }

    public void setPujcka(List<Pujcka> pujcka) {
        this.pujcka = pujcka;
    }
}
