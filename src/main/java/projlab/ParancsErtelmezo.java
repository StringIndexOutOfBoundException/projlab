package projlab;

import java.io.*;
import java.util.*;

public class ParancsErtelmezo {

    private Palya palya;

    //Pályán lévő mezők neveit tároló HashMap
    private HashMap<String, Mezo> mezoMap = new HashMap<String, Mezo>();
    //Pályán lévő játékosok neveit tároló HashMap
    private HashMap<String, Jatekos> jatekosMap = new HashMap<String, Jatekos>();


    //A parancsértelmező képes arra, hogy érzékelje az elgépelt parancsokat, és javaslatot adjon helyettük, vagy akár ki is javítsa az elgépelt parancsot.
    //Ezt a változót át szabad írni, vagy akár a megfelelő függvénnyel lehet módosítani, ez szabályozza, hogy ez be legyen-e kapcsolva.
    //true: be van kapcsolva, false: ki van kapcsolva
    private boolean ENABLE_AUTOCORRECT = true;

    private String allapotString = "";

    //Legutóbb javított parancs (Autocorrect)
    private String acLastCommand = "";
    //Legutóbb javított parancs paraméterei (Autocorrect)
    private String[] acLastParams;

    //A következő string tömbök helyesen beírt szavakat tartalmaznak, amiket a program javasolhat ha valaki elgépel egy parancsot (Autocorrect)
    //A parancsok listája egy tömbben
    private String[] acParancsok = new String[] {"letrehoz", "lep", "osszekot", "szerel", "lyukaszt", "allit", "frissit", "epit", "felvesz", "allapot", "tolt", "csuszik", "ragad", "veletlen", "elront", "termel", "csofelulet", "vizmennyiseg"};
    //Létrehozható elemek listája egy tömbben
    private String[] acElemek = new String[] {"cso", "pumpa", "ciszterna", "forras", "szerelo", "szabotor"};

    public ParancsErtelmezo(Palya palya) {
        this.palya = palya;
    }

    /**
     Adott stringből olvas be egy vagy több parancsot.
     A parancsokat \n karakter választja el egymástól.
     @param parancs A parancs(ok), amit beolvas
     */
    public void runFromString(String parancs)
    {
        //Egy tömb amiben először soronként szétválasztjuk a parancsokat
        String[] parancsokTomb = parancs.split("\n");
        //Egy lista, amibe átmásoljuk a tömböt
        ArrayList<String> parancsok = new ArrayList<String>(Arrays.asList(parancsokTomb));


        parseAll(parancsok);
    }

    /**
    Adott fájlból olvas be parancsokat.
    A fájl kiterjesztése .txt, nem szükséges odaírni (De akár oda is írható).
    @param filename A fájl neve, amiből beolvas
     */
    public void runFromFile(String filename)
    {
        //Megnézzük, hogy a filename végén van-e .txt, ha nincs, hozzáadjuk
        if (!filename.endsWith(".txt")) {
            filename += ".txt";
        }
        ArrayList<String> parancsok = new ArrayList<String>();
        try {
            //Beolvassuk a fájlt soronként
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null) {
                parancsok.add(line);
            }
        } catch (IOException ioe) {
            System.out.println("Hiba a fájl beolvasásakor! ("+ ioe.toString() + ")");
        }

        parseAll(parancsok);
    }

    /**
     * A felhasználótól olvas be parancsokat konzolból. EOF (Windows-on CTRL-D) leállítja.
     */
    public void runFromUser()
    {
        //A konzolról olvasás
        try {
            System.out.print("Kérem a parancsot: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line;
            while ((line = br.readLine()) != null) {
                //Egyetlen parancsot tartalmazó arraylist (mivel a parse függvény arraylistet vár)
                ArrayList<String> parancsok = new ArrayList<String>(List.of(line));
                parseAll(parancsok);
                System.out.print("Kérem a parancsot: ");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * A parancsokat értelmezi és végrehajtatja.
     * Ez csak szétszedi a parancsot és a paramétereket, majd továbbhívja a parseOne függvénynek.
     * @param parancsok A parancsok listája
     */
    private void parseAll(ArrayList<String> parancsok)
    {
        for (String s : parancsok) {
            //s szétválasztása szóközök mentén
            String[] szavak = s.split(" ");
            //A parancs első szava
            String parancs = szavak[0];
            //Paraméterek a parancs többi szava
            String[] param = Arrays.copyOfRange(szavak, 1, szavak.length);

            parseOne(parancs, param);

        }
    }

    /**
     * Egy parancsot értelmez és végrehajtat a megfelelő függvénnyel.
     * @param parancs A parancs neve
     * @param param A parancs paraméterei egy tömbben
     */
    private void parseOne(String parancs, String[] param)
    {
        switch (parancs)
        {
            case "letrehoz":
                cLetrehoz(param);
                break;
            case "lep":
                cLepes(param);
                break;
            case "osszekot":
                cOsszekot(param);
                break;
            case "szerel":
                cSzerel(param);
                break;
            case "lyukaszt":
                cLyukaszt(param);
                break;
            case "allit":
                cAllit(param);
                break;
            case "frissit":
                cFrissit(param);
                break;
            case "epit":
                cEpit(param);
                break;
            case "felvesz":
                cFelvesz(param);
                break;
            case "allapot":
                cAllapot(param);
                break;
            case "tolt":
                cTolt(param);
                break;
            case "csuszik":
                cCsuszik(param);
                break;
            case "ragad":
                cRagad(param);
                break;
            case "veletlen":
                cVeletlen(param);
                break;
            case "elront":
                cElront(param);
                break;
            case "termel":
                cTermel(param);
                break;
            case "csofelulet":
                cCsofelulet(param);
                break;

            //Igazából nem parancs, az autocorrectnél lehet ezt használni hogy kijavítsa a parancsot.
            case "i":
                parseOne(acLastCommand, acLastParams);
                break;
            default:
                System.out.println("Hibás parancsot adtál meg: " + parancs);
                Autocorrect(parancs, param, acParancsok, 0);
                break;

        }
    }

    /**
     * A letrehoz parancsot értelmezi és végrehajtatja.
     * @param param A parancs paraméterei egy tömbben (a létrehozandó dolog típusa és neve)
     */
    private void cLetrehoz(String[] param)
    {
        //Meg kell nézni hogy két paraméter van-e
        if (param.length != 2) {
            System.out.println("A letrehoz parancs 2 paramétert vár. (letrehoz <”cso”/”pumpa”/”ciszterna”/”forras”/”szerelo”/”szabotor”> <nev>)");
            return;
        }
        //Megnézzük hogy milyen típusú elemet kell létrehozni
        switch (param[0])
        {
            case "cso":
                //Megnézzük hogy létezik-e már ilyen nevű elem
                if (mezoMap.containsKey(param[1]) || jatekosMap.containsKey(param[1])) {
                    System.out.println("Már létezik ilyen nevű elem");
                    break;
                }
                //Létrehozzuk a csövet
                Cso cso = new Cso();
                //Hozzáadjuk a hashmaphez a megadott néven
                mezoMap.put(param[1], cso);
                //Hozzáadjuk a pályához
                palya.getElemek().add(cso);
                System.out.println("A " + param[1] + " nevű cső létrehozása sikeres volt!");
                break;

            case "pumpa":
                //Megnézzük hogy létezik-e már ilyen nevű elem
                if (mezoMap.containsKey(param[1]) || jatekosMap.containsKey(param[1])) {
                    System.out.println("Már létezik ilyen nevű elem");
                    break;
                }
                //Létrehozzuk a pumpát
                Pumpa pumpa = new Pumpa();
                //Hozzáadjuk a hashmaphez a megadott néven
                mezoMap.put(param[1], pumpa);
                //Hozzáadjuk a pályához
                palya.getElemek().add(pumpa);
                System.out.println("A " + param[1] + " nevű pumpa létrehozása sikeres volt!");
                break;

            case "ciszterna":
                //Megnézzük hogy létezik-e már ilyen nevű elem
                if (mezoMap.containsKey(param[1]) || jatekosMap.containsKey(param[1])) {
                    System.out.println("Már létezik ilyen nevű elem");
                    break;
                }
                //Létrehozzuk a ciszternát
                Ciszterna ciszterna = new Ciszterna();
                //Hozzáadjuk a hashmaphez a megadott néven
                mezoMap.put(param[1], ciszterna);
                //Hozzáadjuk a pályához
                palya.getElemek().add(ciszterna);
                System.out.println("A " + param[1] + " nevű ciszterna létrehozása sikeres volt!");
                break;

            case "forras":
                //Megnézzük hogy létezik-e már ilyen nevű elem
                if (mezoMap.containsKey(param[1]) || jatekosMap.containsKey(param[1])) {
                    System.out.println("Már létezik ilyen nevű elem");
                    break;
                }
                //Létrehozzuk a forrást
                Forras forras = new Forras();
                //Hozzáadjuk a hashmaphez a megadott néven
                mezoMap.put(param[1], forras);
                //Hozzáadjuk a pályához
                palya.getElemek().add(forras);
                System.out.println("A " + param[1] + " nevű forrás létrehozása sikeres volt!");
                break;

            case "szerelo":
                //Megnézzük hogy létezik-e már ilyen nevű elem
                if (jatekosMap.containsKey(param[1]) || mezoMap.containsKey(param[1])) {
                    System.out.println("Már létezik ilyen nevű elem");
                    break;
                }
                //Létrehozzuk a szerelőt
                Szerelo szerelo = new Szerelo();
                //Hozzáadjuk a hashmaphez a megadott néven
                jatekosMap.put(param[1], szerelo);
                //Hozzáadjuk a pályához
                palya.getJatekosok().add(szerelo);
                System.out.println("A " + param[1] + " nevű szerelő létrehozása sikeres volt!");
                break;

            case "szabotor":
                //Megnézzük hogy létezik-e már ilyen nevű elem
                if (jatekosMap.containsKey(param[1]) || mezoMap.containsKey(param[1])) {
                    System.out.println("Már létezik ilyen nevű elem");
                    break;
                }
                //Létrehozzuk a szabotőrt
                Szabotor szabotor = new Szabotor();
                //Hozzáadjuk a hashmaphez a megadott néven
                jatekosMap.put(param[1], szabotor);
                //Hozzáadjuk a pályához
                palya.getJatekosok().add(szabotor);
                System.out.println("A " + param[1] + " nevű szabotőr létrehozása sikeres volt!");
                break;

            default:
                System.out.println("Hibás paramétert adtál meg! (" + param[0] + ") A lehetőségek a következők: ”cso”/”pumpa”/”ciszterna”/”forras”/”szerelo”/”szabotor”");
                Autocorrect("letrehoz", param, acElemek, 1);
                break;
        }
    }

    /**
     * A játékos léptetését végző függvény
     * @param param A parancs paraméterei (kivel melyik mezőre lépünk)
     */
    private void cLepes(String[] param)
    {
        //Meg kell nézni hogy két paraméter van-e
        if (param.length != 2) {
            System.out.println("A lep parancs 2 paramétert vár. (lep <jatekos> <mezo>)");
            return;
        }
        //Megnézzük hogy létezik-e a megadott játékos
        if (!jatekosMap.containsKey(param[0])) {
            System.out.println("Nincs ilyen nevű játékos: " + param[0]);
            Autocorrect("lep", param, jatekosMap.keySet().toArray(new String[0]), 1);
            return;
        }
        //Megnézzük hogy létezik-e a megadott mező
        if (!mezoMap.containsKey(param[1])) {
            System.out.println("Nincs ilyen nevű mező: " + param[1]);
            Autocorrect("lep", param, mezoMap.keySet().toArray(new String[0]), 2);
            return;
        }
        //Léptetjük a játékost a megadott mezőre
        jatekosMap.get(param[0]).Lep(mezoMap.get(param[1]));
    }

    /**
     * Két mező összekötését végző függvény
     * @param param A parancs paraméterei (A két összekötendő mező neve)
     */
    private void cOsszekot(String[] param)
    {
        //Meg kell nézni hogy két paraméter van-e
        if (param.length != 2) {
            System.out.println("Az osszekot parancs 2 paramétert vár. (osszekot <mezo1> <mezo2>)");
            return;
        }
        //Megnézzük hogy létezik-e a megadott 1. mező
        if (!mezoMap.containsKey(param[0])) {
            System.out.println("Nincs ilyen nevű mező: " + param[0]);
            Autocorrect("osszekot", param, mezoMap.keySet().toArray(new String[0]), 1);
            return;
        }
        //Megnézzük hogy létezik-e a megadott 2. mező
        if (!mezoMap.containsKey(param[1])) {
            System.out.println("Nincs ilyen nevű mező: " + param[1]);
            Autocorrect("osszekot", param, mezoMap.keySet().toArray(new String[0]), 2);
            return;
        }
        //Összekötjük a két mezőt
        mezoMap.get(param[0]).SzomszedHozzaad(mezoMap.get(param[1]));

    }

    /**
     * Adott játékossal javítást végző függvény
     * @param param A parancs paramétere (A játékos akivel szerelni akarunk)
     */
    private void cSzerel(String[] param)
    {
        //Meg kell nézni hogy egy paraméter van-e
        if (param.length != 1) {
            System.out.println("A szerel parancs 1 paramétert vár. (szerel <jatekos>)");
            return;
        }
        //Megnézzük hogy létezik-e a megadott játékos
        if (!jatekosMap.containsKey(param[0])) {
            System.out.println("Nincs ilyen nevű játékos: " + param[0]);
            Autocorrect("szerel", param, jatekosMap.keySet().toArray(new String[0]), 1);
            return;
        }
        //Javítunk az adott szerelővel
        jatekosMap.get(param[0]).Javit();
    }

    /**
     * Adott játékossal lyukasztást végző függvény
     * @param param A parancs paramétere (A játékos akivel lyukasztani akarunk)
     */
    private void cLyukaszt(String[] param)
    {
        //Meg kell nézni hogy egy paraméter van-e
        if (param.length != 1) {
            System.out.println("A lyukaszt parancs 1 paramétert vár. (lyukaszt <jatekos>)");
            return;
        }
        //Megnézzük hogy létezik-e a megadott játékos
        if (!jatekosMap.containsKey(param[0])) {
            System.out.println("Nincs ilyen nevű játékos: " + param[0]);
            Autocorrect("lyukaszt", param, jatekosMap.keySet().toArray(new String[0]), 1);
            return;
        }
        //Lyukasztunk az adott szabotőrrel
        jatekosMap.get(param[0]).Lyukaszt();
    }

    /**
     * Adott játékossal átállítjuk a pumpának amin áll a bemenetét és a kimenetét
     * @param param A parancs paraméterei (A játékos akivel átállítjuk a pumpát, és a bemeneti és kimeneti mező neve)
     */
    private void cAllit(String[] param)
    {
        //Meg kell nézni hogy három paraméter van-e
        if (param.length != 3) {
            System.out.println("Az allit parancs 3 paramétert vár. (allit <jatekos> <bemenet> <kimenet>)");
            return;
        }
        //Megnézzük hogy létezik-e a megadott játékos
        if (!jatekosMap.containsKey(param[0])) {
            System.out.println("Nincs ilyen nevű játékos: " + param[0]);
            Autocorrect("allit", param, jatekosMap.keySet().toArray(new String[0]), 1);
            return;
        }
        //Megnézzük hogy létezik-e a megadott bemeneti mező
        if (!mezoMap.containsKey(param[1])) {
            System.out.println("Nincs ilyen nevű mező: " + param[1]);
            Autocorrect("allit", param, mezoMap.keySet().toArray(new String[0]), 2);
            return;
        }
        //Megnézzük hogy létezik-e a megadott kimeneti mező
        if (!mezoMap.containsKey(param[2])) {
            System.out.println("Nincs ilyen nevű mező: " + param[2]);
            Autocorrect("allit", param, mezoMap.keySet().toArray(new String[0]), 3);
            return;
        }
        //Atállítjuk a pumpát
        jatekosMap.get(param[0]).Allit(mezoMap.get(param[1]), mezoMap.get(param[2]));
    }

    /**
     * Minden mezőt frissítő függvény
     * @param param A parancs paraméterei (Nincs, de az egységesség kedvéért meghagyjuk a paramétert. Ha mégis adnak meg, figyelmeztetjük a felhasználót, de a parancs lefut)
     */
    private void cFrissit(String[] param)
    {
        //Frissítjük a mezőket
        for (Mezo m : mezoMap.values()) {
            try {
                m.Frissit();
            } catch (Exception e) {
                //Ha valami hiba történt, kiírjuk a hibaüzenetet
                System.out.println("Hiba a frissítés során: " + e.getMessage());
            }
        }

        //A függvény nem vár paramétert. Ha véletlen mégis adtak meg, figyelmeztetjük a felhasználót, de attól még a parancs lefut
        if (param.length != 0) {
            System.out.println("Figyelmeztetés: A frissit parancs nem vár paramétert. A parancs ettől függetlenül lefutott.");
        }

    }

    /**
     * Adott szerelővel pumpát vagy csövet építünk a mezőre amin áll
     * @param param A parancs paraméterei (A játékos akivel építünk, és hogy mit építünk ("pumpa"/"cso"))
     */
    private void cEpit(String[] param)
    {
        //Meg kell nézni hogy két paraméter van-e
        if (param.length != 2) {
            System.out.println("Az epit parancs 2 paramétert vár. (epit <szerelo> <\"pumpa\"/\"cso\">)");
            return;
        }
        //Megnézzük hogy létezik-e a megadott játékos
        if (!jatekosMap.containsKey(param[0])) {
            System.out.println("Nincs ilyen nevű játékos: " + param[0]);
            Autocorrect("epit", param, jatekosMap.keySet().toArray(new String[0]), 1);
            return;
        }
        //Megnézzük hogy a második paraméter "pumpa" vagy "cso"
        if (!param[1].equals("pumpa") && !param[1].equals("cso")) {
            System.out.println("A második paraméternek \"pumpa\" vagy \"cso\"-nak kell lennie.");
            Autocorrect("epit", param, new String[]{"pumpa", "cso"}, 2);
            return;
        }
        //Ha "pumpa"-t adtak meg, akkor pumpát építünk
        if (param[1].equals("pumpa")) {
            jatekosMap.get(param[0]).PumpatEpit();
        }
        //Ha "cso"-t adtak meg, akkor csövet építünk
        else{
            jatekosMap.get(param[0]).CsovetFelcsatol();
        }

    }

    /**
     * A szerelő megpróbál felvenni egy csövet, vagy pumpát.
     * Ha csövet vesz fel, akkor meg kell adni a cső nevét is, valamint azt is, hogy az egészet vagy csak a cső felét veszi fel (Alapértelmezetten az egészet veszi fel, ha ezt nem adjuk meg).
     * @param param A parancs paraméterei (A szerelő, akivel felveszünk valamit, és hogy mit veszünk fel ("pumpa"/"cso"), valamint ha csövet veszünk fel, akkor a cső neve és hogy az egészet vagy csak a cső felét vesszük fel)
     */
    private void cFelvesz(String[] param)
    {
        //Meg kell nézni hogy kettő és négy közötti paraméter van-e
        if (param.length < 2 || param.length > 4) {
            System.out.println("A felvesz parancs legalább 2, legfeljebb 4 paramétert vár. (felvesz <szerelo> <\"pumpa\"/\"cso\"> [cso_nev] [\"egesz\"/\"fel\"])");
            return;
        }

        //Megnézzük hogy létezik-e a megadott játékos
        if (!jatekosMap.containsKey(param[0])) {
            System.out.println("Nincs ilyen nevű játékos: " + param[0]);
            Autocorrect("felvesz", param, jatekosMap.keySet().toArray(new String[0]), 1);
            return;
        }
        //Megnézzük hogy a második paraméter "pumpa" vagy "cso"
        if (!param[1].equals("pumpa") && !param[1].equals("cso")) {
            System.out.println("A második paraméternek \"pumpa\" vagy \"cso\"-nak kell lennie.");
            Autocorrect("felvesz", param, new String[]{"pumpa", "cso"}, 2);
            return;
        }
        //Ha "pumpa"-t adtak meg, akkor pumpát veszünk fel
        if (param[1].equals("pumpa")) {
            jatekosMap.get(param[0]).PumpatFelvesz();
            //Ha több paramétert adtak meg, akkor figyelmeztetjük a felhasználót, de a parancs lefut
            if (param.length > 2) {
                System.out.println("Figyelmeztetés: A pumpa felvételéhez nem szükséges kettőnél több paraméter. A parancs ettől függetlenül lefutott.");
            }
            return;
        }

        //Ha "cso"-t adtak meg, akkor azt kell fölvenni. A harmadik parameter ilyenkor muszáj hogy meglegyen
        if (param.length < 3) {
            System.out.println("A cső felvételéhez meg kell adni a cső nevét.");
            return;
        }
        //Megnézzük hogy létezik-e a megadott cső
        if (!mezoMap.containsKey(param[2])) {
            System.out.println("Nincs ilyen nevű cső: " + param[2]);
            Autocorrect("felvesz", param, mezoMap.keySet().toArray(new String[0]), 3);
            return;
        }
        //Ha a cső felét akarjuk felvenni, akkor azt is meg kell adni
        if (param.length == 4 && !param[3].equals("egesz") && !param[3].equals("fel")) {
            System.out.println("A cső felvételéhez az utolsó paraméternek \"egesz\" vagy \"fel\"-nek kell lennie. Az is lehet, hogy nem adunk meg semmit, ekkor az egészet vesszük fel.");
            Autocorrect("felvesz", param, new String[]{"egesz", "fel"}, 4);
            return;
        }
        boolean egeszetVeszunkFel;
        if (param.length == 4) {
            egeszetVeszunkFel = param[3].equals("egesz");
        }
        else{
            egeszetVeszunkFel = true;
        }
        //Felvesszük az egész csövet
        if (egeszetVeszunkFel) {
            jatekosMap.get(param[0]).EgeszCsovetLecsatol(mezoMap.get(param[2]));
        }
        //Felveszzük a cső felét
        else{
            jatekosMap.get(param[0]).CsovetLecsatol(mezoMap.get(param[2]));
        }



    }





    /**
     * Kiírja a pálya, vagy egy objektum, vagy egy objektum változójának jelenlegi állapotát fájlba, vagy a konzolra. A csillag (*) azt jelenti, hogy "minden"<br>
     * Példák:<br>
     * allapot * * (minden objektum állapotát (azaz minden objektum minden attribútumának értékét) kiírja a konzolra) <br>
     * allapot cs1 * (cs1 cső állapotát (azaz minden attribútumának jelenlegi értékét) írja ki a konzolra) <br>
     * allapot cs1 mukodik (cs1 cső működik attribőtumának jelenlegi értékét írja ki a konzolra) <br>
     * FONTOS: String-ként megkapható minden amit az "allapot" parancs kiírt valahova, ha a getAllapotString() függvényt használjuk. <br>
     * @param param A parancs paraméterei (Előszöris hogy minek a mijét írjuk ki, lásd példák, majd egy fájl neve, ha fájlba akarjuk írni)
     */
    private void cAllapot(String[] param)
    {
        //Megnézzük hogy legalább kettő, legfeljebb három paraméter van-e
        if (param.length < 2 || param.length > 3) {
            System.out.println("Az allapot parancs két vagy három paramétert vár. (allapot <objektum> <objektum_attributum> [filenév])");
            return;
        }
        String fileName = null; //Fájl neve (ha van)
        boolean file = false; //Fájlba kell-e írni az állapotot
        //Ha három paraméter van, akkor azt jelenti, hogy fájlba kell írni az állapotot
        if (param.length == 3) {
            fileName = param[2];
            file = true;
        }
        //Ha az első paraméter csillag, akkor az összes objektum állapotát ki kell írni
        if (param[0].equals("*")) {
            //Egész egyszerűen meghívjuk a csillagos parancsokat az összes objektumra

                //Végigmegyünk az összes mezőn
                for (Mezo m : mezoMap.values()) {
                    if (param.length == 3)
                    {
                        runFromString("allapot " + getMezoName(m) + " * " + param[2]);
                    }
                    else
                    {
                        runFromString("allapot " + getMezoName(m) + " *");
                    }
                    PrintOrWrite("", file, fileName);
                }
                //Végigmegyünk az összes játékoson
                for (Jatekos j : jatekosMap.values()) {

                    if (param.length == 3)
                    {
                        runFromString("allapot " + getJatekosName(j) + " * " + param[2]);
                    }
                    else
                    {
                        runFromString("allapot " + getJatekosName(j) + " *");
                    }
                    PrintOrWrite("", file, fileName);
                }
            //A második paraméternek is csillagnak kéne lennie, ha az első az volt. Erre azért figyelmeztetjük a felhasználót, de a parancs lefut
            //Itt amúgy kihasználjuk a rövidzár szabályt.
            if (param.length == 1 || !param[1].equals("*")) {
                System.out.println("Figyelmeztetés: Ha az első paraméter csillag, akkor a második paraméternek is csillagnak kéne lennie. A parancs ettől függetlenül lefutott. (Kiírtuk az egész pályát)");
            }


            return;
            }




        //Pain and suffering
        if(jatekosMap.containsKey(param[0]))
        {
            switch (param[1])
            {
                case "maxHatizsakKapacitas":
                    PrintOrWrite(param[0] + " maxHatizsakKapacitas: " + jatekosMap.get(param[0]).getMaxHatizsakKapacitas(), file, fileName);
                    allapotString += param[0] + " maxHatizsakKapacitas: " + jatekosMap.get(param[0]).getMaxHatizsakKapacitas() + "\n";
                    break;
                case "pumpaHatizsak":
                    PrintOrWrite(param[0] + " pumpaHatizsak: " + getMezoListName(jatekosMap.get(param[0]).getPumpaHatizsak()), file, fileName);
                    allapotString += param[0] + " pumpaHatizsak: " + getMezoListName(jatekosMap.get(param[0]).getPumpaHatizsak()) + "\n";
                    break;
                case "csoHatizsak":
                    PrintOrWrite(param[0] + " csoHatizsak: " + getMezoListName(jatekosMap.get(param[0]).getCsoHatizsak()), file, fileName);
                    allapotString += param[0] + " csoHatizsak: " + getMezoListName(jatekosMap.get(param[0]).getCsoHatizsak()) + "\n";
                    break;
                case "helyzet":
                    if (jatekosMap.get(param[0]).getHelyzet() != null)
                    {
                        PrintOrWrite(param[0] + " helyzet: " + getMezoName(jatekosMap.get(param[0]).getHelyzet()), file, fileName);
                        allapotString += param[0] + " helyzet: " + getMezoName(jatekosMap.get(param[0]).getHelyzet()) + "\n";
                    }
                    break;
                case "*":
                    PrintOrWrite(param[0] + " maxHatizsakKapacitas: " + jatekosMap.get(param[0]).getMaxHatizsakKapacitas(), file, fileName);
                    allapotString += param[0] + " maxHatizsakKapacitas: " + jatekosMap.get(param[0]).getMaxHatizsakKapacitas() + "\n";
                    PrintOrWrite(param[0] + " pumpaHatizsak: " + getMezoListName(jatekosMap.get(param[0]).getPumpaHatizsak()), file, fileName);
                    allapotString += param[0] + " pumpaHatizsak: " + getMezoListName(jatekosMap.get(param[0]).getPumpaHatizsak()) + "\n";
                    PrintOrWrite(param[0] + " csoHatizsak: " + getMezoListName(jatekosMap.get(param[0]).getCsoHatizsak()), file, fileName);
                    allapotString += param[0] + " csoHatizsak: " + getMezoListName(jatekosMap.get(param[0]).getCsoHatizsak()) + "\n";
                    if (jatekosMap.get(param[0]).getHelyzet() != null)
                    {
                        PrintOrWrite(param[0] + " helyzet: " + getMezoName(jatekosMap.get(param[0]).getHelyzet()), file, fileName);
                        allapotString += param[0] + " helyzet: " + getMezoName(jatekosMap.get(param[0]).getHelyzet()) + "\n";
                    }
                    break;
                default:
                    System.out.println("Nincs ilyen attribútum: " + param[1]);
                    Autocorrect("allapot", param, new String[]{"maxHatizsakKapacitas", "pumpaHatizsak", "csoHatizsak", "helyzet"}, 2);
                    return;

            }
        }
        else if(mezoMap.containsKey(param[0]))
        {
            switch(param[1])
            {
                case "mukodik":
                    PrintOrWrite(param[0] + " mukodik: " + mezoMap.get(param[0]).getMukodik(), file, fileName);
                    allapotString += param[0] + " mukodik: " + mezoMap.get(param[0]).getMukodik() + "\n";
                    break;
                case "szomszedok":
                    if (mezoMap.get(param[0]).GetSzomszedok() != null)
                    {
                        PrintOrWrite(param[0] + " szomszedok: " + getMezoListName(mezoMap.get(param[0]).GetSzomszedok()), file, fileName);
                        allapotString += param[0] + " szomszedok: " + getMezoListName(mezoMap.get(param[0]).GetSzomszedok()) + "\n";
                    }
                    break;
                case "maxJatekosok":
                    PrintOrWrite(param[0] + " maxJatekosok: " + mezoMap.get(param[0]).getMaxJatekosok(), file, fileName);
                    allapotString += param[0] + " maxJatekosok: " + mezoMap.get(param[0]).getMaxJatekosok() + "\n";
                    break;
                case "maxSzomszedok":
                    PrintOrWrite(param[0] + " maxSzomszedok: " + mezoMap.get(param[0]).getMaxSzomszedok(), file, fileName);
                    allapotString += param[0] + " maxSzomszedok: " + mezoMap.get(param[0]).getMaxSzomszedok() + "\n";
                    break;
                case "jatekosok":
                    PrintOrWrite(param[0] + " jatekosok: " + getJatekosListName(mezoMap.get(param[0]).getJatekosok()), file, fileName);
                    allapotString += param[0] + " jatekosok: " + getJatekosListName(mezoMap.get(param[0]).getJatekosok()) + "\n";
                    break;
                case "vizmennyiseg":
                    PrintOrWrite(param[0] + " vizmennyiseg: " + mezoMap.get(param[0]).getVizmennyiseg(), file, fileName);
                    allapotString += param[0] + " vizmennyiseg: " + mezoMap.get(param[0]).getVizmennyiseg() + "\n";
                    break;
                case "lyukCooldown":
                    if (mezoMap.get(param[0]).getLyukCooldown() != -1)
                    {
                        PrintOrWrite(param[0] + " lyukCooldown: " + mezoMap.get(param[0]).getLyukCooldown(), file, fileName);
                        allapotString += param[0] + " lyukCooldown: " + mezoMap.get(param[0]).getLyukCooldown() + "\n";
                    }
                    break;
                case "csuszos":
                    if (mezoMap.get(param[0]).getCsuszos() != -1)
                    {
                        PrintOrWrite(param[0] + " csuszos: " + mezoMap.get(param[0]).getCsuszos(), file, fileName);
                        allapotString += param[0] + " csuszos: " + mezoMap.get(param[0]).getCsuszos() + "\n";
                    }
                    break;
                case "ragados":
                    if (mezoMap.get(param[0]).getRagados() != -1)
                    {
                        PrintOrWrite(param[0] + " ragados: " + mezoMap.get(param[0]).getRagados(), file, fileName);
                        allapotString += param[0] + " ragados: " + mezoMap.get(param[0]).getRagados() + "\n";
                    }
                    break;
                case "ragadossaTette":
                    if (mezoMap.get(param[0]).getRagadossaTette() != null)
                    {
                        PrintOrWrite(param[0] + " ragadossaTette: " + getJatekosName(mezoMap.get(param[0]).getRagadossaTette()), file, fileName);
                        allapotString += param[0] + " ragadossaTette: " + getJatekosName(mezoMap.get(param[0]).getRagadossaTette()) + "\n";
                    }
                    break;
                case "termeltPumpak":
                    if (mezoMap.get(param[0]).getTermeltPumpak() != null)
                    {
                        PrintOrWrite(param[0] + " termeltPumpak: " + getMezoListName(mezoMap.get(param[0]).getTermeltPumpak()), file, fileName);
                        allapotString += param[0] + " termeltPumpak: " + getMezoListName(mezoMap.get(param[0]).getTermeltPumpak()) + "\n";
                    }
                    break;
                case "bemenet":
                    if (mezoMap.get(param[0]).getBemenet() != null)
                    {
                        PrintOrWrite(param[0] + " bemenet: " + getMezoName(mezoMap.get(param[0]).getBemenet()), file, fileName);
                        allapotString += param[0] + " bemenet: " + getMezoName(mezoMap.get(param[0]).getBemenet()) + "\n";
                    }
                    break;
                case "kimenet":
                    if (mezoMap.get(param[0]).getKimenet() != null)
                    {
                        PrintOrWrite(param[0] + " kimenet: " + getMezoName(mezoMap.get(param[0]).getKimenet()), file, fileName);
                        allapotString += param[0] + " kimenet: " + getMezoName(mezoMap.get(param[0]).getKimenet()) + "\n";
                    }
                    break;
                case "*":
                    PrintOrWrite(param[0] + " mukodik: " + mezoMap.get(param[0]).getMukodik(), file, fileName);
                    allapotString += param[0] + " mukodik: " + mezoMap.get(param[0]).getMukodik() + "\n";
                    if (mezoMap.get(param[0]).GetSzomszedok() != null)
                    {
                        PrintOrWrite(param[0] + " szomszedok: " + getMezoListName(mezoMap.get(param[0]).GetSzomszedok()), file, fileName);
                        allapotString += param[0] + " szomszedok: " + getMezoListName(mezoMap.get(param[0]).GetSzomszedok()) + "\n";
                    }
                    PrintOrWrite(param[0] + " maxJatekosok: " + mezoMap.get(param[0]).getMaxJatekosok(), file, fileName);
                    allapotString += param[0] + " maxJatekosok: " + mezoMap.get(param[0]).getMaxJatekosok() + "\n";
                    PrintOrWrite(param[0] + " maxSzomszedok: " + mezoMap.get(param[0]).getMaxSzomszedok(), file, fileName);
                    allapotString += param[0] + " maxSzomszedok: " + mezoMap.get(param[0]).getMaxSzomszedok() + "\n";
                    PrintOrWrite(param[0] + " jatekosok: " + getJatekosListName(mezoMap.get(param[0]).getJatekosok()), file, fileName);
                    allapotString += param[0] + " jatekosok: " + getJatekosListName(mezoMap.get(param[0]).getJatekosok()) + "\n";
                    PrintOrWrite(param[0] + " vizmennyiseg: " + mezoMap.get(param[0]).getVizmennyiseg(), file, fileName);
                    allapotString += param[0] + " vizmennyiseg: " + mezoMap.get(param[0]).getVizmennyiseg() + "\n";
                    if (mezoMap.get(param[0]).getLyukCooldown() != -1)
                    {
                        PrintOrWrite(param[0] + " lyukCooldown: " + mezoMap.get(param[0]).getLyukCooldown(), file, fileName);
                        allapotString += param[0] + " lyukCooldown: " + mezoMap.get(param[0]).getLyukCooldown() + "\n";
                    }
                    if (mezoMap.get(param[0]).getCsuszos() != -1)
                    {
                        PrintOrWrite(param[0] + " csuszos: " + mezoMap.get(param[0]).getCsuszos(), file, fileName);
                        allapotString += param[0] + " csuszos: " + mezoMap.get(param[0]).getCsuszos() + "\n";
                    }
                    if (mezoMap.get(param[0]).getRagados() != -1)
                    {
                        PrintOrWrite(param[0] + " ragados: " + mezoMap.get(param[0]).getRagados(), file, fileName);
                        allapotString += param[0] + " ragados: " + mezoMap.get(param[0]).getRagados() + "\n";
                    }
                    if (mezoMap.get(param[0]).getTermeltPumpak() != null)
                    {
                        PrintOrWrite(param[0] + " termeltPumpak: " + getMezoListName(mezoMap.get(param[0]).getTermeltPumpak()), file, fileName);
                        allapotString += param[0] + " termeltPumpak: " + getMezoListName(mezoMap.get(param[0]).getTermeltPumpak()) + "\n";
                    }
                    if (mezoMap.get(param[0]).getBemenet() != null)
                    {
                        PrintOrWrite(param[0] + " bemenet: " + getMezoName(mezoMap.get(param[0]).getBemenet()), file, fileName);
                        allapotString += param[0] + " bemenet: " + getMezoName(mezoMap.get(param[0]).getBemenet()) + "\n";
                    }
                    if (mezoMap.get(param[0]).getKimenet() != null)
                    {
                        PrintOrWrite(param[0] + " kimenet: " + getMezoName(mezoMap.get(param[0]).getKimenet()), file, fileName);
                        allapotString += param[0] + " kimenet: " + getMezoName(mezoMap.get(param[0]).getKimenet()) + "\n";
                    }

                    break;
                default:
                    System.out.println("Nincs ilyen attribútum: " + param[1]);
                    Autocorrect("allapot", param, new String[]{"nev", "maxJatekosok", "maxSzomszedok", "jatekosok", "vizmennyiseg", "lyukCooldown", "csuszos", "ragados", "ragadossaTette", "termeltPumpak", "bemenet", "kimenet"}, 2);
                    return;
            }
        }
        else {
            System.out.println("Nincs ilyen nevű objektum: " + param[0]);
            Autocorrect("allapot", param, jatekosMap.keySet().toArray(new String[0]), 1);
            Autocorrect("allapot", param, mezoMap.keySet().toArray(new String[0]), 1);
            return;
        }







    }



    /**
     * Adott fájlból betöltünk egy játékállást. A fájlban parancsok vannak egymás után, minden sorban egy.
     * @param param A parancs paramétere (A fájl neve, amiből betöltünk)
     */
    private void cTolt(String[] param)
    {
        //Meg kell nézni hogy egy paraméter van-e
        if (param.length != 1) {
            System.out.println("A tolt parancs egy paramétert vár. (tolt <fajlnev>)");
            return;
        }
        //Odaadjuk a fájlt a runFromFile-nak, hogy futtassa a parancsokat
        runFromFile(param[0]);


    }



    /**
     * Adott szabotőr csúszóssá teszi a csövet amin áll
     * @param param A parancs paramétere (A szabotőr, aki csúszóssá tesz egy csövet)
     */
    private void cCsuszik(String[] param)
    {
        //Meg kell nézni hogy egy paraméter van-e
        if (param.length != 1) {
            System.out.println("A csuszik parancs egy paramétert vár. (csuszik <szabotor>)");
            return;
        }
        //Megnézzük hogy létezik-e a megadott játékos
        if (!jatekosMap.containsKey(param[0])) {
            System.out.println("Nincs ilyen nevű játékos: " + param[0]);
            Autocorrect("csuszik", param, jatekosMap.keySet().toArray(new String[0]), 1);
            return;
        }
        //Csúszóssá tesszük a játékos által elfoglalt mezőt
        jatekosMap.get(param[0]).CsuszossaTesz();
    }

    /**
     * Adott játékos ragadóssá teszi a csövet, amin jelenleg áll.
     * @param param A parancs paramétere (A játékos, aki ragadóssá tesz egy csövet)
     */
    private void cRagad(String[] param)
    {
        //Meg kell nézni hogy egy paraméter van-e
        if (param.length != 1) {
            System.out.println("A ragad parancs egy paramétert vár. (ragad <jatekos>)");
            return;
        }
        //Megnézzük hogy létezik-e a megadott játékos
        if (!jatekosMap.containsKey(param[0])) {
            System.out.println("Nincs ilyen nevű játékos: " + param[0]);
            Autocorrect("ragad", param, jatekosMap.keySet().toArray(new String[0]), 1);
            return;
        }
        //Ragadóssá tesszük a játékos által elfoglalt mezőt
        jatekosMap.get(param[0]).RagadossaTesz();
    }

    /**
     * Azt állítja, hogy legyen-e véletlen működés a programban.
     * @param param A parancs paramétere ("be", ha be akarjuk kapcsolni a véletlen működést, "ki", ha ki akarjuk kapcsolni)
     */
    private void cVeletlen(String[] param)
    {
        //Meg kell nézni hogy egy paraméter van-e
        if (param.length != 1) {
            System.out.println("A veletlen parancs egy paramétert vár. (veletlen <\"be\"/\"ki\">)");
            return;
        }
        //Ha be akarjuk kapcsolni a véletlen működést
        if (param[0].equals("be")) {
            Mezo.doRandomThings = true;
        }
        //Ha ki akarjuk kapcsolni a véletlen működést
        else if (param[0].equals("ki")) {
            Mezo.doRandomThings = false;
        }
        else {
            System.out.println("Hibás paraméter. A paraméternek \"be\" vagy \"ki\"-nek kell lennie.");
            Autocorrect("veletlen", param, new String[]{"be", "ki"}, 1);
        }
    }

    /**
     * Akkor hasznos, ha a véletlen események ki vannak kapcsolva, és egy pumpa elromlását szeretnénk szimulálni manuálisan. Ezen kívül cső is kilyukasztható vele játékos használata nélkül.
     * @param param A parancs paramétere (A mezo, amit elrontunk)
     */
    private void cElront(String[] param)
    {
        //Meg kell nézni hogy egy paraméter van-e
        if (param.length != 1) {
            System.out.println("Az elront parancs egy paramétert vár. (elront <mezo>)");
            return;
        }
        //Megnézzük hogy létezik-e a megadott mező
        if (!mezoMap.containsKey(param[0])) {
            System.out.println("Nincs ilyen nevű mező: " + param[0]);
            Autocorrect("elront", param, mezoMap.keySet().toArray(new String[0]), 1);
            return;
        }
        //Elrontjuk a megadott mezőt
        mezoMap.get(param[0]).setMukodik(false);
    }


    /**
     * Akkor hasznos, ha a véletlen események ki vannak kapcsolva, és egy ciszternánál egy pumpa vagy cső termelődését szeretnénk szimulálni manuálisan. A parancs hatására adott ciszternánál létrejön egy "pumpa" vagy "cső" (második paraméterben megadandó)
     * @param param A parancs paraméterei (A ciszterna, ahol termelődik a pumpa/cső, és a termelendő objektum típusa("pumpa" vagy "cso"))
     */
    private void cTermel(String[] param)
    {
        //Meg kell nézni hogy két paraméter van-e
        if (param.length != 2) {
            System.out.println("A termel parancs két paramétert vár. (termel <mezo> <\"pumpa\"/\"cso\">)");
            return;
        }
        //Megnézzük hogy létezik-e a megadott mező
        if (!mezoMap.containsKey(param[0])) {
            System.out.println("Nincs ilyen nevű mező: " + param[0]);
            Autocorrect("termel", param, mezoMap.keySet().toArray(new String[0]), 1);
            return;
        }
        //Megnézzük hogy a második paraméter "pumpa" vagy "cso"
        if (!param[1].equals("pumpa") && !param[1].equals("cso")) {
            System.out.println("Hibás paraméter. A paraméternek \"pumpa\" vagy \"cso\"-nak kell lennie.");
            Autocorrect("termel", param, new String[]{"pumpa", "cso"}, 2);
            return;
        }
        //Letermeljük a megfelelő objektumot
        if (param[1].equals("pumpa")) {
            mezoMap.get(param[0]).PumpatKeszit();
        }
        else {
            mezoMap.get(param[0]).CsovetKeszit();
        }
    }

    /**
     *  Arra való, hogy konkrét csövet csúszóssá vagy ragadóssá tegyünk anélkül, hogy egy játékost kellene használni.
     * @param param A parancs paraméterei (A cső, amit csúszóssá vagy ragadóssá teszünk. Meg kell adni, hogy "csuszos" vagy "ragados" legyen)
     */
    private void cCsofelulet(String[] param)
    {
        //Meg kell nézni hogy két paraméter van-e
        if (param.length != 2) {
            System.out.println("A csofelulet parancs két paramétert vár. (csofelulet <cso> <\"csuszos\"/\"ragados\">)");
            return;
        }

        //Megnézzük hogy létezik-e a megadott cső
        if (!mezoMap.containsKey(param[0])) {
            System.out.println("Nincs ilyen nevű cső: " + param[0]);
            Autocorrect("csofelulet", param, mezoMap.keySet().toArray(new String[0]), 1);
            return;
        }
        //Megnézzük hogy a második paraméter "csuszos" vagy "ragados"
        if (!param[1].equals("csuszos") && !param[1].equals("ragados")) {
            System.out.println("Hibás paraméter. A paraméternek \"csuszos\" vagy \"ragados\"-nak kell lennie.");
            Autocorrect("csofelulet", param, new String[]{"csuszos", "ragados"}, 2);
            return;
        }
        //Beállítjuk a megfelelő cső felületét
        if (param[1].equals("csuszos")) {
            mezoMap.get(param[0]).Csuszik();
        }
        else {
            mezoMap.get(param[0]).Ragad();
        }
    }

    /**
     * A paraméterként megadott csőnél vagy pumpánál megváltoztatja a csőben lévő víz mennyiségét a paraméterben megadott mennyiségre.
     * @param param A parancs paraméterei (A cső vagy pumpa, aminek a víz mennyiségét megváltoztatjuk, és a víz új mennyisége)
     */
    private void cVizmennyiseg(String[] param)
    {
        //Meg kell nézni hogy két paraméter van-e
        if (param.length != 2) {
            System.out.println("A vizmennyiseg parancs két paramétert vár. (vizmennyiseg <cso/pumpa> <mennyiseg>)");
            return;
        }
        //Megnézzük hogy létezik-e a megadott cső vagy pumpa
        if (!mezoMap.containsKey(param[0])) {
            System.out.println("Nincs ilyen nevű cső vagy pumpa: " + param[0]);
            Autocorrect("vizmennyiseg", param, mezoMap.keySet().toArray(new String[0]), 1);
            return;
        }
        //Megnézzük hogy a második paraméter szám-e
        int mennyiseg;
        try {
            mennyiseg = Integer.parseInt(param[1]);
        }
        catch (NumberFormatException e) {
            System.out.println("Hibás a második paraméter. A paraméternek számnak kell lennie.");
            return;
        }
        //Beállítjuk a megfelelő cső vagy pumpa víz mennyiségét
        mezoMap.get(param[0]).SetVizmennyiseg(mennyiseg);
    }




    /**
     * Kiszámolja a két szó közötti <a href="https://en.wikipedia.org/wiki/Levenshtein_distance">Levenshtein-távolságot</a>.
     * Ez annyit jelent igazából hogy visszaad egy számot hogy hány karakterben tér el a két string
     * @param s Egyik bemeneti string
     * @param t Másik bemeneti string
     * @return A két string közötti Levenshtein-távolság
     */
    private int levenshteinDistance(String s, String t) {
        //A két szó hossza
        int m = s.length();
        int n = t.length();

        //2D tömb, itt tároljuk a távolságot
        int[][] dp = new int[m+1][n+1];

        //Ha az egyik szó üres, akkor a távolság a másik szó hossza
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        //Végig megyünk mindkét input string karakterein
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                //Ha a két karakter megegyezik, akkor a távolság ugyanaz, mintha a két megegyező karakter nélkül néznénk.
                if (s.charAt(i-1) == t.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    //Itt három eset van:
                    //1. Egyik stringből levágjuk az elejét
                    //2. Másik stringből levágjuk az elejét
                    //3. Mindkettőből levágjuk az elejét
                    //A háromnak a minimuma + 1 lesz a távolság
                    dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1])) + 1;
                }
            }
        }
        //A tömb végén lévő érték lesz a távolság
        return dp[m][n];
    }

    /**
     * Autocorrecteli a parancsot, vagy a paraméterét ha a felhasználó elírta
     * @param command A parancs
     * @param param A parancs paraméterei
     * @param compareTo Ebben a tömbben vannak a helyesen írt szavak, ehhez hasonlítjuk az elírtat, hogy hátha valamelyikhez hasonlít.
     * @param corretedParamIndex A paraméterek közül melyiket akarjuk autocorrectelni. Ha 0, akkor a parancsot, ha 1, akkor az első paramétert, ha 2, akkor a másoodikat, stb.
     */
    private void Autocorrect(String command, String[] param, String[] compareTo, int corretedParamIndex)
    {
        //Ha az osztály elején kikapcsoltuk az autocorrectet, akkor nem csinálunk semmit
        if(!ENABLE_AUTOCORRECT)
        {
            return;
        }

        //Kiválasztjuk mit is akarunk autocorrectelni
        String misspelledWord = "";
        //Ha 0, akkor a parancsot akarjuk autocorrectelni
        if (corretedParamIndex == 0)
        {
            misspelledWord = command;
        }
        //Ha nem 0, akkor valamelyik paramétert akarjuk autocorrectelni
        else
        {
            misspelledWord = param[corretedParamIndex-1];
        }
        //A compareTo tömbben lévő szavak közül kiválasztjuk azt, amelyik a legközelebb van az elgépelt szóhoz
        int min = Integer.MAX_VALUE;
        String closest = "";
        for (String s : compareTo) {
            //System.out.println("S: "+ s);
            int dist = levenshteinDistance(misspelledWord, s);
            if (dist < min) {
                min = dist;
                closest = s;
            }
        }
        //Ha a legközelebbi szó távolsága kisebb mint 3, akkor figyelmeztetjük a felhasználót
        if (min < 3) {
            System.out.println("* Erre gondotál: \"" + closest+ "\"?");
            System.out.println("* Ha igen, akkor írd be hogy \"i\", és a parancs automatikusan ki lesz javítva és újra lefut!");

            //Beállítjuk a kijavított parancsot vagy paramétert, ha esetleg újra akarjuk futtatni a kijavított parancsot. ("i" parancs)
            acLastCommand = command;
            acLastParams = param;
            if (corretedParamIndex == 0)
            {
                acLastCommand = closest;
            }
            else
            {
                acLastParams[corretedParamIndex-1] = closest;
            }
        }

        //System.out.println("Closest: "+ closest);
        //System.out.println("Min: "+ min);


    }

    /**
     * Visszaadja a legutóbbi állapotstringet
     * @return Legutóbbi állapotstring
     */
    public String getAllapotString()
    {
        return allapotString;
    }

    /**
     * Törli a legutóbbi állapotstringet
     */
    public void clearAllapotString()
    {
        allapotString = "";
    }

    /**
     * Beállítja az autocorrectet
     * @param enable Ha true, akkor bekapcsolja az autocorrectet, ha false, akkor kikapcsolja
     */
    public void EnableAutocorrect(boolean enable)
    {
        ENABLE_AUTOCORRECT = enable;
    }

    /**
     * Visszaadja egy játékos nevét (megszerzi a hashmapből)
     * @param j A játékos
     * @return A játékos neve
     */
    private String getJatekosName(Jatekos j)
    {
        //Megkeressük a játékost a hashmapben
        for (Map.Entry<String, Jatekos> entry : jatekosMap.entrySet()) {
            if (entry.getValue().equals(j)) {
                return entry.getKey();
            }
        }
        //Az baj ha nem találtuk meg... Ennek elvileg sose kéne lefutnia
        return "[Nem található játékos]"; //Majd ezt észreveszi valaki és akkor látja hogy baj történt
    }

    /**
     * Visszaadja egy mező nevét (megszerzi a hashmapből)
     * @param m A mező
     * @return A mező neve
     */
    private String getMezoName(Mezo m)
    {
        //Megkeressük a játékost a hashmapben
        for (Map.Entry<String, Mezo> entry : mezoMap.entrySet()) {
            if (entry.getValue().equals(m)) {
                return entry.getKey();
            }
        }
        //Az baj ha nem találtuk meg... Ennek elvileg sose kéne lefutnia
        return "[Nem található mező]"; //Majd ezt észreveszi valaki és akkor látja hogy baj történt
    }

    /**
     * Visszaadja egy mezők listájából az összes mező nevét (megszerzi a hashmapből)
     * @param mezoList A mezők listája
     * @return Az összes mező neve a listában, soronként (azaz minden mező neve egy új sorban)
     */
    private String getMezoListName(List<Mezo> mezoList)
    {
        //Stringeket először kigyűjtjük egy listába, hogy könnyebb legyen rendezni őket
        ArrayList<String> mezoNameList = new ArrayList<>();
        for (Mezo m : mezoList)
        {
            mezoNameList.add(getMezoName(m));
        }
        //ABC rendbe rendezzük őket
        Collections.sort(mezoNameList);

        StringBuilder s = new StringBuilder();
        s.append("\n");
        for (String m : mezoNameList)
        {
            s.append(m).append("\n");
        }
        //Utolsó newline karaktert töröljük
        s.deleteCharAt(s.length()-1);
        return s.toString();
    }

    /**
     * Visszaadja egy játékosok listájából az összes játékos nevét (megszerzi a hashmapből)
     * @param jatekosList A játékosok listája
     * @return Az összes játékos neve a listában, soronként (azaz minden játékos neve egy új sorban)
     */
    private String getJatekosListName(List<Jatekos> jatekosList)
    {
        //Stringeket először kigyűjtjük egy listába, hogy könnyebb legyen rendezni őket
        ArrayList<String> jatekosNameList = new ArrayList<>();
        for (Jatekos j : jatekosList)
        {
            jatekosNameList.add(getJatekosName(j));
        }
        //ABC rendbe rendezzük őket
        Collections.sort(jatekosNameList);

        StringBuilder s = new StringBuilder();
        s.append("\n");
        for (String j : jatekosNameList)
        {
            s.append(j).append("\n");
        }
        //Utolsó newline karaktert töröljük
        s.deleteCharAt(s.length()-1);
        return s.toString();
    }

    /**
     * Vagy fájlba ír, vagy konzolra
     * @param s Kiírandó string
     * @param fajlba Ha true, akkor fájlba ír, ha false, akkor konzolra
     * @param filename A fájl neve, ha fájlba írunk. Csak txt-be írunk, de ezt nem muszáj megadni, mert ha nincs .txt a végén, akkor hozzáadódik
     */
    private void PrintOrWrite(String s, boolean fajlba, String filename)
    {
        //Ha nincs a fájl nevének a végén .txt, akkor hozzáadjuk
        if (filename != null)
        {
            if (!filename.endsWith(".txt"))
            {
                filename += ".txt";
            }
        }


        if (fajlba)
        {
            try {
                FileWriter myWriter = new FileWriter(filename, true);
                myWriter.write(s);
                myWriter.write("\n");
                myWriter.close();
            } catch (IOException e) {
                System.out.println("Hiba a kimenet kiírásakor a(z) "+filename+" fájlba.");
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println(s);
        }

    }

}
