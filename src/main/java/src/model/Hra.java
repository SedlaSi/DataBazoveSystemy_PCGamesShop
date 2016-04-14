package src.model;

import javax.persistence.*;

/**
 * Created by root on 14.4.16.
 */
@Entity
@Table(name = "HRA")
public class Hra {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_hra")
    private long id;

    @Column(nullable = false,unique = true,length = 128)
    private String nazev;

    @Column(length = 255)
    private String popis;

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

}
