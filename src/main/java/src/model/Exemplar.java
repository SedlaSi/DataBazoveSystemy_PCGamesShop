package src.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by root on 14.4.16.
 */
@Entity
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
}
