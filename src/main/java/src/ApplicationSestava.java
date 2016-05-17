package src;

import src.util.Resources;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class ApplicationSestava {
    private EntityManager entityManager;

    public static void main(String[] args) {
        new ApplicationSestava().start();
    }

    public void start() {
        entityManager = new Resources().getEntityManager();
        zakazniciPodleUtraty();
        zakazniciCoSiNejvicePujcuji();
        nejpujcovanejsiHry();
    }

    private void nejpujcovanejsiHry() {
        Sestava sestava = new Sestava(new String[]{"id_hra", "hra_nazev", "vydavatel_nazev", "police_nazev", "pujcena_kolikrat"});

        Query q = entityManager.createNativeQuery("SELECT hra.id_hra, hra.nazev, vydavatel.nazev as vydavatel_nazev, police.nazev as police_nazev, COUNT(byl_propujcen) as pujcena FROM hra INNER JOIN exemplar ON (exemplar.id_hra = hra.id_hra) INNER JOIN byl_propujcen ON (exemplar.id_exemplar = byl_propujcen.id_exemplar) INNER JOIN police ON (hra.id_police = police.id_police) INNER JOIN vydavatel ON (vydavatel.id_vydavatel = hra.id_vydavatel) WHERE byl_propujcen.vraceno IS NOT NULL GROUP BY hra.id_hra, police.id_police, vydavatel.id_vydavatel ORDER BY pujcena DESC");
        List<Object[]> hry = q.getResultList();

        sestava.addAll(hry);
        sestava.prinToFile("nejpujcovanejsi_hry.csv");
    }

    private void zakazniciPodleUtraty() {
        Sestava sestava = new Sestava(new String[]{"id_zakaznik", "username", "email", "jmeno", "prijmeni", "mesto", "email", "mesto", "ulice", "cislo_popisne", "telefon", "utrata"});

        Query q = entityManager.createNativeQuery("SELECT zakaznik.id_zakaznik, username, email, jmeno, prijmeni, mesto, email, mesto, ulice, cislo_popisne, telefon, CONCAT(SUM(cena), ' Kč') as utrata FROM zakaznik INNER JOIN byl_propujcen ON (zakaznik.id_zakaznik = byl_propujcen.id_zakaznik) WHERE byl_propujcen.vraceno IS NOT NULL  GROUP BY zakaznik.id_zakaznik ORDER BY SUM(cena) DESC");
        List<Object[]> zakaznici = q.getResultList();

        sestava.addAll(zakaznici);
        sestava.prinToFile("zakaznici_podle_utraty.csv");
    }

    private void zakazniciCoSiNejvicePujcuji() {
        Sestava sestava = new Sestava(new String[]{"id_zakaznik", "username", "email", "jmeno", "prijmeni", "mesto", "email", "mesto", "ulice", "cislo_popisne", "telefon", "utrata"});

        Query q = entityManager.createNativeQuery("SELECT zakaznik.id_zakaznik, username, email, jmeno, prijmeni, mesto, email, mesto, ulice, cislo_popisne, telefon, COUNT(byl_propujcen) as pujcil_si_celkem FROM zakaznik INNER JOIN byl_propujcen ON (zakaznik.id_zakaznik = byl_propujcen.id_zakaznik) GROUP BY zakaznik.id_zakaznik ORDER BY pujcil_si_celkem DESC");
        List<Object[]> zakaznici = q.getResultList();

        sestava.addAll(zakaznici);
        sestava.prinToFile("zakaznici_co_si_nejvice_pujcuji.csv");
    }

    public class Sestava {
        private String[] headers;
        private List<Object[]> data = new ArrayList<>();

        public Sestava(String[] headers) {
            this.headers = headers;
        }

        public void prinToFile(String path) {
            try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "Windows-1250"))) {
                if(headers != null) {
                    for (String header : headers) {
                        bw.write(header);
                        bw.write(";");
                    }
                    bw.newLine();
                }

                for (Object[] o : data) {
                    for (Object o1 : o) {
                        bw.write(o1.toString());
                        bw.write(";");
                    }
                    bw.newLine();
                }
            }
            catch (IOException ex) {
                System.err.println("Soubor se nepodarilo ulozit.");
                ex.printStackTrace();
            }
        }

        public void addAll(List<Object[]> list){
            data.addAll(list);
        }
    }
}
