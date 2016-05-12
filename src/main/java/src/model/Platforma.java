package src.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by root on 14.4.16.
 */
@Entity
@Table(name = "platforma")
public class Platforma {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_platforma")
    private long id;

    @Column(nullable = false,length = 128, name = "nazev", unique = true)
    private String nazev;

    @OneToMany(mappedBy = "platforma")
    private List<Exemplar> exemplare;

    public long getId() {
        return id;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public List<Exemplar> getExemplare() {
        return exemplare;
    }

    public void setExemplare(List<Exemplar> exemplare) {
        this.exemplare = exemplare;
    }
}
