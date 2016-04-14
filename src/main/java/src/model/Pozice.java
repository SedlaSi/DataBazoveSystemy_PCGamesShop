package src.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by root on 14.4.16.
 */
@Entity
@Table(name = "POZICE")
public class Pozice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_pozice")
    private long id;

    @Column(nullable = false,length = 128)
    private String nazev;

    @ManyToMany(mappedBy = "pozice")
    private List<Zamestnanec> zamestnancy;

    public long getId() {
        return id;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public List<Zamestnanec> getZamestnancy() {
        return zamestnancy;
    }

    public void setZamestnancy(List<Zamestnanec> zamestnancy) {
        this.zamestnancy = zamestnancy;
    }
}
