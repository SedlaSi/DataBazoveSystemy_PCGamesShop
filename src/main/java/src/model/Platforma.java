package src.model;

import javax.persistence.*;

/**
 * Created by root on 14.4.16.
 */
@Entity
public class Platforma {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_platforma")
    private long id;

    @Column(nullable = false,unique = true,length = 128)
    private String nazev;

    public long getId() {
        return id;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }
}
