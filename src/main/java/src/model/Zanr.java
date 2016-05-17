package src.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by root on 14.4.16.
 */
@Entity
@Table(name = "zanr")
public class Zanr {
    public Zanr() {
    }

    public Zanr(String nazev) {
        this.nazev = nazev;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_zanr")
    private long id;

    @Column(nullable = false,unique = true,length = 128)
    private String nazev;

    @ManyToMany(mappedBy = "zanry")
    private List<Hra> hry;

    public long getId() {
        return id;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public List<Hra> getHry() {
        return hry;
    }

    public void setHry(List<Hra> hry) {
        this.hry = hry;
    }
}
