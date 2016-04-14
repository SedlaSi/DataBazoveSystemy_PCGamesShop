package src.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by root on 14.4.16.
 */
@Entity
public class Police {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_police")
    private long id;

    @Column(nullable = false,unique = true,length = 128)
    private String nazev;

    @Column(length = 255)
    private String popis;

    @OneToMany(mappedBy = "police")
    private List<Hra> hry;

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public long getId() {
        return id;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public void setHry(List<Hra> hry){
        this.hry = hry;
    }

    public List<Hra> getHry(){
        return hry;
    }

}
