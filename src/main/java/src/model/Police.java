package src.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by root on 14.4.16.
 */
@Entity
@Table(name = "police")
@NamedQueries({
        @NamedQuery(name = "Police.getList", query = "SELECT p FROM Police p")
})
public class Police {

    public Police() {
    }

    public Police(String nazev, String popis) {
        this.nazev = nazev;
        this.popis = popis;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_police")
    private long id;

    @Column(nullable = false,unique = true,length = 128, name = "nazev")
    private String nazev;

    @Column(length = 255, name = "popis")
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
