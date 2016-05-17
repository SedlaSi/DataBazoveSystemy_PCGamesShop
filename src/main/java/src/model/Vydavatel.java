package src.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by root on 14.4.16.
 */
@Entity
@Table(name = "vydavatel")
public class Vydavatel {

    public Vydavatel() {
    }

    public Vydavatel(String nazev) {
        this.nazev = nazev;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_vydavatel")
    private long id;

    @Column(nullable = false,unique = true, name = "nazev")
    private String nazev;

    @OneToMany(mappedBy = "vydavatel")
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
