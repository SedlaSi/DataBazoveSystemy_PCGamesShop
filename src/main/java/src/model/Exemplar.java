package src.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Created by root on 14.4.16.
 */
@Entity
@Table(name = "exemplar")
public class Exemplar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_exemplar")
    private long id;

    private String stav;

    @Column(nullable = false)
    private int cena;

    @Column(nullable = false,name = "rok_vydani")
    private Date rokVydani;

    @Column(nullable = false)
    private boolean aktivni = true;

    @ManyToOne
    private Hra hra;

    @ManyToOne
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
