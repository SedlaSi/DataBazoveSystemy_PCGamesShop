package src.klient.demo;


import src.login.Decoder;
import src.model.*;
import src.provider.ProviderDAO;
import src.util.Util;

import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneratorDat {
    private ProviderDAO providerDAO;

    private String[] zanry = {"Adeventura", "Arkády", "RPG", "Simuátor", "Horor", "Tahová strategie", "Závodní", "Sport", "FPS"};
    private String[] pozice = {"Prodejce"};
    private String[] jmena = {"Adam", "Antonín", "Petr", "Pavel", "Jan", "Evžen", "Denis", "Kamil"};
    private String[] prijemeni = {"Novák", "Svoboda", "Novotný", "Dvořák", "Procházka", "Kučera", "Veselý", "Smutný", "Horák"};
    private String[] mesta = {"Praha", "Brno", "Příbram", "Davle", "České Budějovice", "Český Krumlov", "Tábor", "Písek", "Ostrava"};
    private String[] ulice = {"Malá", "Široká", "Krátká", "Dlouhá", "Pražská", "Pařížská", "Karlovo Náměstí", "Dejvická", "Ostrovní"};
    private String[] stavExemplare = {"Nepoškozená", "Lehké škrábance"};

    private MessageDigest md;
    private List<String> gameList;
    private int idCounter = 1;
    private Random random = new Random(2291252016L);

    void initGameList() {
        gameList = new ArrayList<>();
        gameList.add("Assassin's Creed II (2009, Ubisoft) (PlayStation 3, Windows, Xbox 360, Nintendo DS, Macintosh)");
        gameList.add("Assassin's Creed III (2012, Ubisoft) (PlayStation 3, Wii U, Windows, Xbox 360)");
        gameList.add("Assassin's Creed IV: Black Flag (2013, Ubisoft) (Windows, Xbox 360, Xbox One, PlayStation 3, PlayStation 4, Wii U)");
        gameList.add("Assassin's Creed: Bloodlines (2009, Ubisoft) (PSP)");
        gameList.add("Assassin's Creed: Brotherhood (2010, Ubisoft) (Macintosh, PlayStation 3, Windows, Xbox 360)");
        gameList.add("Assassin's Creed: Relevations (2011, Ubisoft) (PlayStation 3, Windows, Xbox 360)");
        gameList.add("Assassin's Creed: Revelations (2011, Ubisoft) (PlayStation 3)");
        gameList.add("Assassin's Creed: Unity (2014, Ubisoft) (PlayStation 4, Xbox One, Windows)");
        gameList.add("Grand Theft Auto 2 (1999, Rockstar Games) (Dreamcast, Game Boy Color, PlayStation, Windows)");
        gameList.add("Grand Theft Auto III (2001, Rockstar Games) (Macintosh, PlayStation 2, PlayStation 3, Windows)");
        gameList.add("Grand Theft Auto IV (2010, Rockstar Games) (PlayStation 3, Windows, Xbox 360)");
        gameList.add("Grand Theft Auto: San Andreas (2004, Rockstar Games)  (Macintosh, PlayStation 2, PlayStation 3, Windows, Xbox, Xbox 360)");
        gameList.add("Grand Theft Auto V (2013, Rockstar Games) (PlayStation 3, Xbox 360, PlayStation 4, Xbox One, Windows)");
        gameList.add("Grand Theft Auto: Vice City (2002, Rockstar Games) (Macintosh, PlayStation 2, PlayStation 3, Windows)");
        gameList.add("The Last of Us (2013, Sony Computer Entertainment) (PlayStation 3)");
        gameList.add("The Last of Us Remastered (2014, Sony Computer Entertainment) (PlayStation 4)");
        gameList.add("Uncharted 2: Among Thieves (2009, Sony Computer Entertainment) (PlayStation 3)");
        gameList.add("Uncharted 3: Drake's Deception (2011, Sony Computer Entertainment) (PlayStation 3)");
        gameList.add("Uncharted: Drake's Fortune (2007, Sony Computer Entertainment) (PlayStation 3)");
        gameList.add("Uncharted: Golden Abyss (2011, Sony Computer Entertainment) (PS Vita)");
    }

    void generujVypujcky() {
        List<Zamestnanec> zamestnanciList = providerDAO.getZamestnanecDAO().getList();
        List<Zakaznik> zakazniciList = providerDAO.getZakaznikDAO().getList();
        List<Exemplar> exemplareList = providerDAO.getExemplarDAO().getList();

        if (zamestnanciList.size() == 0 || zakazniciList.size() == 0 || exemplareList.size() == 0) {
            System.err.println("V databazi nejsou zadni zamestnanci nebo zakaznici nabo exemplare.");
            return;
        }


        long dayInMs = 86400000L;
        long startDate = 1420110000000L;

        for (Exemplar exemplar : exemplareList) {
            Date lastData = new Date(startDate);

            for (int i = 0; i < 45; i++) {
                int dobaVypujcky = 1 + random.nextInt(7);
                Date vraceno = new java.sql.Date(lastData.getTime() + dobaVypujcky * dayInMs);

                Pujcka pujcka = new Pujcka(lastData, vraceno, exemplar.getCena(), exemplar, zakazniciList.get(random.nextInt(zakazniciList.size())), zamestnanciList.get(random.nextInt(zamestnanciList.size())));

                pujcka.setPujceno(lastData);
                pujcka.setZakaznik(zakazniciList.get(random.nextInt(zakazniciList.size())));
                pujcka.setZamestnanec(zamestnanciList.get(random.nextInt(zamestnanciList.size())));
                pujcka.setExemplar(exemplar);

                lastData = new Date(vraceno.getTime() + dayInMs);

                try {
                    providerDAO.getPujckaDAO().create(pujcka);
                } catch (Exception e) {
                    System.out.println("Nepodarilo se vytvorit vypujcku.");
                    e.printStackTrace();
                }
            }
        }
    }

    void generujPlatformy() {
        Set<String> platformy = new HashSet<>();

        Pattern gamePattern = Pattern.compile("([^\\(]+)\\s*\\((\\d+),\\s*([^\\)]+)\\)\\s*\\(([^\\)]+)");

        for (String game : gameList) {
            Matcher gameMatcher = gamePattern.matcher(game);

            while (gameMatcher.find()) {
                platformy.addAll(Arrays.asList(Arrays.stream(gameMatcher.group(4).split(",")).map(String::trim).toArray(String[]::new)));
            }
        }

        for (String platformaNazev : platformy) {
            Platforma platforma = new Platforma(platformaNazev);

            try {
                providerDAO.getPlatformaDAO().create(platforma);
            } catch (Exception e) {
                System.err.println("Nepodarilo se vytvořit platformu.");
                e.printStackTrace();
            }
        }
    }

    void generujVydavatele() {
        Set<String> vydavatele = new HashSet<>();

        Pattern gamePattern = Pattern.compile("([^\\(]+)\\s*\\((\\d+),\\s*([^\\)]+)\\)\\s*\\(([^\\)]+)");

        for (String game : gameList) {
            Matcher gameMatcher = gamePattern.matcher(game);

            while (gameMatcher.find()) {
                vydavatele.addAll(Arrays.asList(Arrays.stream(gameMatcher.group(3).split(",")).map(String::trim).toArray(String[]::new)));
            }
        }

        for (String vydavateleNazev : vydavatele) {
            Vydavatel vydavatel = new Vydavatel(vydavateleNazev);

            try {
                providerDAO.getVydavatelDAO().create(vydavatel);
            } catch (Exception e) {
                System.err.println("Nepodarilo se vytvořit vydavatele.");
                e.printStackTrace();
            }
        }
    }

    void generujExemplare(int pocetExemplaruOdDanePlatformy) {
        Pattern gamePattern = Pattern.compile("([^\\(]+)\\s*\\((\\d+),\\s*([^\\)]+)\\)\\s*\\(([^\\)]+)");

        List<Police> policeList = providerDAO.getPoliceDAO().getList();
        List<Zanr> zanryList = providerDAO.getZanrDAO().getList();

        if (policeList.size() == 0 || zanryList.size() == 0) {
            System.err.println("V databazi nejsou zadne polize nebo zanry.");
            return;
        }

        for (String game : gameList) {
            Matcher gameMatcher = gamePattern.matcher(game);

            while (gameMatcher.find()) {
                Vydavatel vydavatel = providerDAO.getVydavatelDAO().getByNazev(gameMatcher.group(3).trim());

                if (vydavatel == null) {
                    System.err.println("Neexistujici vydavatel.");
                    break;
                }

                Hra hra = new Hra(gameMatcher.group(1).trim(), "Hra", policeList.get(random.nextInt(policeList.size())), vydavatel);

                hra.setZanry(Util.getSubList(zanryList, random.nextInt(2) + 1, random));

                try {
                    providerDAO.getHraDAO().create(hra);
                } catch (Exception e) {
                    System.err.println("Nepodarilo se vytvorit hru.");
                    e.printStackTrace();
                    continue;
                }

                for (String platformaNazev : Arrays.stream(gameMatcher.group(4).split(",")).map(String::trim).toArray(String[]::new)) {
                    for (int i = 0; i <= random.nextInt(pocetExemplaruOdDanePlatformy); i++) {
                        Exemplar exemplar = new Exemplar();
                        exemplar.setStav(stavExemplare[random.nextInt(stavExemplare.length)]);

                        if (exemplar.getStav().equals(stavExemplare[0])) {
                            exemplar.setCena((random.nextInt(6) + 5) * 10);
                        } else {
                            exemplar.setCena((random.nextInt(5) + 1) * 10);
                        }

                        exemplar.setHra(hra);

                        DateFormat format = new SimpleDateFormat("yyyy", Locale.ENGLISH);
                        java.util.Date date = null;

                        try {
                            date = format.parse(gameMatcher.group(2).trim());
                        } catch (ParseException e) {
                            System.err.println("Nepodarilo se naparsovat datum.");
                            e.printStackTrace();
                            continue;
                        }

                        exemplar.setRokVydani(new Date(date.getTime()));

                        Platforma platforma = providerDAO.getPlatformaDAO().getByNazev(platformaNazev);

                        if (platforma == null) {
                            System.err.println("Neexistujici platforma.");
                        }

                        exemplar.setPlatforma(platforma);

                        try {
                            providerDAO.getExemplarDAO().create(exemplar);
                        } catch (Exception e) {
                            System.err.println("Nepodarilo se vytvorit exemplar.");
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    void generujZanry() {
        for (int i = 0; i < zanry.length; i++) {
            Zanr zanr = new Zanr(zanry[i]);

            try {
                providerDAO.getZanrDAO().create(zanr);
            } catch (Exception e) {
                System.err.println("Nepodarilo se vytvořit zanr.");
                e.printStackTrace();
            }
        }
    }

    void generujPolice(int count) {
        for (int i = 1; i <= count; i++) {
            Police police = new Police("P" + i, "Police číslo: " + i);

            try {
                providerDAO.getPoliceDAO().create(police);
            } catch (Exception e) {
                System.err.println("Nepodarilo se vytvořit polici.");
                e.printStackTrace();
            }
        }
    }

    void generujPozice() {
        for (int i = 0; i < pozice.length; i++) {
            Pozice poz = new Pozice(pozice[i]);

            try {
                providerDAO.getPoziceDAO().create(poz);
            } catch (Exception e) {
                System.err.println("Nepodarilo se vytvořit pozici.");
                e.printStackTrace();
            }
        }
    }

    void generujZakazniky(int count) {
        for (int i = 0; i < count; i++) {
            Osoba osoba = generujOsobu();
            Zakaznik zakaznik = new Zakaznik(osoba.getJmeno(), osoba.getPrijmeni(), osoba.getMesto(), osoba.getUlice(), osoba.getCisloPopisne(), Integer.toString(osoba.getTelefon()), osoba.getEmail(), osoba.getUsername(), osoba.getPassword());
            try {
                providerDAO.getZakaznikDAO().create(zakaznik);
            } catch (Exception e) {
                System.err.println("Nepodarilo se vytvořit zakaznika.");
                e.printStackTrace();
            }
        }
    }

    void generujZamestnance(int count) {
        List<Pozice> poziceList = providerDAO.getPoziceDAO().getList();

        if (poziceList.size() == 0) {
            System.err.println("V databazi nejsou zadne pozice.");
            return;
        }

        for (int i = 0; i < count; i++) {
            Osoba osoba = generujOsobu();
            Zamestnanec zamestnanec = new Zamestnanec(osoba.getJmeno(), osoba.getPrijmeni(), osoba.getMesto(), osoba.getUlice(), osoba.getCisloPopisne(), Integer.toString(osoba.getTelefon()), osoba.getEmail(), osoba.getUsername(), osoba.getPassword(), (random.nextInt(10) + 8) * 10000, poziceList.get(random.nextInt(poziceList.size())));
            try {
                providerDAO.getZamestnanecDAO().create(zamestnanec);
            } catch (Exception e) {
                System.err.println("Nepodarilo se vytvořit zamestnance.");
                e.printStackTrace();
            }
        }
    }

    Osoba generujOsobu() {
        Osoba osoba = new Osoba(jmena[random.nextInt(jmena.length)], prijemeni[random.nextInt(prijemeni.length)], mesta[random.nextInt(mesta.length)], ulice[random.nextInt(ulice.length)], idCounter, new Integer(random.nextInt(99999999) + 700000000));
        osoba.setUsername(Util.stripAccents(osoba.getPrijmeni()).toLowerCase() + Util.stripAccents(osoba.getJmeno()).toLowerCase() + idCounter);
        osoba.setEmail(osoba.getUsername() + "@gmail.com");
        osoba.setPassword(Decoder.hashPassword("Heslo"));
        idCounter++;

        return osoba;
    }

    public GeneratorDat(ProviderDAO providerDAO) {
        this.providerDAO = providerDAO;
    }

    public void start() {
        initGameList();

        System.out.println("Generuji provozni data.");

        generujZanry();
        generujPolice(20);
        generujPozice();
        generujZakazniky(25);
        generujZamestnance(5);
        generujPlatformy();
        generujVydavatele();
        generujExemplare(2);
        generujVypujcky();

        System.out.println("Generovani dat dokonceno.");
    }

    private class Osoba {
        private String jmeno;
        private String prijmeni;
        private String mesto;
        private String ulice;
        private int telefon;
        private String username;
        private char[] password;
        private String email;
        private int cisloPopisne;

        public Osoba(String jmeno, String prijmeni, String mesto, String ulice, int cisloPopisne, int telefon) {
            this.jmeno = jmeno;
            this.prijmeni = prijmeni;
            this.mesto = mesto;
            this.ulice = ulice;
            this.telefon = telefon;
            this.cisloPopisne = cisloPopisne;
        }

        public String getJmeno() {
            return jmeno;
        }

        public String getPrijmeni() {
            return prijmeni;
        }

        public String getMesto() {
            return mesto;
        }

        public String getUlice() {
            return ulice;
        }

        public int getTelefon() {
            return telefon;
        }

        public String getUsername() {
            return username;
        }

        public char[] getPassword() {
            return password;
        }

        public String getEmail() {
            return email;
        }

        public int getCisloPopisne() {
            return cisloPopisne;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(char[] password) {
            this.password = password;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

}
