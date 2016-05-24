package src.klient.demo;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Sestavy {
    private EntityManager entityManager;

    public Sestavy(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void start() {
        zakazniciPodleUtraty();
        zakazniciCoSiNejvicePujcuji();
        nejpujcovanejsiHry();
        nejdelePujceneHry();
        hryNejvicekratPujceneOpakovane();
    }

    private void hryNejvicekratPujceneOpakovane() {
        Sestava sestava = new Sestava(new String[]{"id_hra", "hra_nazev", "vydavatel_nazev", "maximalni_pocet_opakovanych_pujceni"});

        Query q = entityManager.createNativeQuery("SELECT tabulka.id_hra, tabulka.hra_nazev, tabulka.vydavatel_nazev, MAX(pocet) as max_pujceno FROM (SELECT hra.id_hra, hra.nazev as hra_nazev, vydavatel.nazev as vydavatel_nazev, COUNT(*) as pocet FROM byl_propujcen INNER JOIN exemplar ON ( byl_propujcen.id_exemplar = exemplar.id_exemplar) INNER JOIN hra ON (exemplar.id_hra = hra.id_hra) INNER JOIN vydavatel ON (hra.id_vydavatel = vydavatel.id_vydavatel) GROUP BY hra.id_hra, byl_propujcen.id_zakaznik, vydavatel.id_vydavatel) as tabulka group by tabulka.id_hra, tabulka.hra_nazev, tabulka.vydavatel_nazev ORDER BY max_pujceno DESC");
        List<Object[]> hry = q.getResultList();

        sestava.addAll(hry);
        sestava.prinToFile("hry_nejvicekrat_pujcene_opakovane.csv");
    }

    private void nejdelePujceneHry() {
        Sestava sestava = new Sestava(new String[]{"id_hra", "hra_nazev", "vydavatel_nazev", "pujceno_celkem_hodin"});

        Query q = entityManager.createNativeQuery("SELECT hra.id_hra, hra.nazev, vydavatel.nazev, EXTRACT(EPOCH FROM SUM(vraceno-pujceno))/86400 as pujceno_celkem FROM byl_propujcen INNER JOIN exemplar ON ( byl_propujcen.id_exemplar = exemplar.id_exemplar) INNER JOIN hra ON (exemplar.id_hra = hra.id_hra) INNER JOIN vydavatel ON (hra.id_vydavatel = vydavatel.id_vydavatel) group by hra.id_hra, vydavatel.id_vydavatel order by pujceno_celkem DESC");
        List<Object[]> hry = q.getResultList();

        sestava.addAll(hry);
        sestava.prinToFile("nejdele_pujcene_hry.csv");
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
