package src.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by root on 14.4.16.
 */
@Entity
@Table(name = "vydavatel")
@NamedQueries({
        @NamedQuery(name = "Vydavatel.getVydavatelList", query = "SELECT v FROM Vydavatel v"),
        @NamedQuery(name = "Vydavatel.getByNazev", query = "SELECT v FROM Vydavatel v WHERE v.nazev = ?1")
})
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
