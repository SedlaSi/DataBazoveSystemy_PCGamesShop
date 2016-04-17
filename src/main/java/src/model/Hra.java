package src.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by root on 14.4.16.
 */
@Entity
//@Table(name = "HRA")
public class Hra {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "id_hra")
    private long id;

    @Column(nullable = false,unique = true,length = 128)
    private String nazev;

    @Column(length = 255)
    private String popis;

    @ManyToOne
    private Police police;

    @ManyToOne
    private Vydavatel vydavatel;

    @OneToMany(mappedBy = "hra")
    //@Column(name = "exemplar")
    private List<Exemplar> exemplare;

    @ManyToMany
    /*@JoinTable(name="je_zarnu",
            joinColumns=@JoinColumn(name="id_hra"),
            inverseJoinColumns=@JoinColumn(name="id_zanr"))*/
    private List<Zanr> zanry;

    public long getId(){
        return id;
    }

    public void setNazev(String nazev){
        this.nazev = nazev;
    }

    public String getNazev(){
        return nazev;
    }

    public void setPopis(String popis){
        this.popis = popis;
    }

    public String getPopis(){
        return popis;
    }

    public Police getPolice() {
        return police;
    }

    public void setPolice(Police police) {
        this.police = police;
    }

    public Vydavatel getVydavatel() {
        return vydavatel;
    }

    public void setVydavatel(Vydavatel vydavatel) {
        this.vydavatel = vydavatel;
    }

    public List<Exemplar> getExemplare() {
        return exemplare;
    }

    public void setExemplare(List<Exemplar> exemplare) {
        this.exemplare = exemplare;
    }

    public List<Zanr> getZanry() {
        return zanry;
    }

    public void setZanry(List<Zanr> zanry) {
        this.zanry = zanry;
    }
}
