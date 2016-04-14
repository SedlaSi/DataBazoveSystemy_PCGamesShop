package src.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by root on 14.4.16.
 */
@Entity
@Table(name = "BYL_PROPUJCEN")
public class Pujcka {

    @ManyToOne
    private Exemplar exemplar;

    @ManyToOne
    private Zakaznik zakaznik;

    @ManyToOne
    private Zamestnanec zamestnanec;
}
