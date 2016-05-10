package src.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by root on 14.4.16.
 */
@Entity
@Table(name = "pozice")
public class Pozice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_pozice")
    private long id;

    @Column(nullable = false,length = 128, name = "nazev")
    private String nazev;

    @OneToMany(mappedBy = "pozice")
    private List<Zamestnanec> zamestnanci;

    public long getId() {
        return id;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public List<Zamestnanec> getZamestnanci() {
        return zamestnanci;
    }

    public void setZamestnanci(List<Zamestnanec> zamestnaneci) {
        this.zamestnanci = zamestnaneci;
    }
}
