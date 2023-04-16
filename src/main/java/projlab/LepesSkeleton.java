package projlab;

import java.util.HashMap;
import java.util.Map;

/**
A lépéssel kapcsolatos teszteket végző Skeleton osztály.
 */
public class LepesSkeleton {
    //A teszteléshez szükséges elemek
    Pumpa p;
    Cso cs1;
    Cso cs2;
    Cso cs3;
    Forras f;
    Ciszterna c;
    Szerelo sz1;
    Szerelo sz2;
    Szerelo sz3;

    /**
     * A 5.3.5-ös szekvenciadiagram alapján teszteli azt, hogy a szerelő üres csőre lép.
     * Az üres cső a cs1, azaz az 1-es opció.
     */
    public void szerelo_ures_csore_lep()
    {
        System.out.println("Függvényhívás: Szerelo: Lep()");
        sz2.Lep();
    }


    /**
     * Az 5.4.5-ös kommunikációs diagram alapján inicializál egy pályát a lépések
     * teszteléséhez.
     * Az 5.3.5-5.3.9-es szekvenciadiagramok alapján készült tesztekhez lesz felhasználva.
     */
    public Map lepes_inicializalas() {
        //Ebben a hashmapben tároljuk a pályaelemeket és a játékosokat, hogy a tesztekben könnyebben hozzájuk tudjunk férni a nevük alapján.
        Map<Object, String> elemek = new HashMap<>();
        //A pályában felhasznált elemek létrehozása
        p = new Pumpa();
        cs1 = new Cso();
        cs2 = new Cso();
        cs3 = new Cso();
        f = new Forras();
        c = new Ciszterna();
        elemek.put(p, "p");
        elemek.put(cs1, "cs1");
        elemek.put(cs2, "cs2");
        elemek.put(cs3, "cs3");
        elemek.put(f, "f");
        elemek.put(c, "c");

        sz1 = new Szerelo();
        sz2 = new Szerelo();
        sz3 = new Szerelo();
        elemek.put(sz1, "sz1");
        elemek.put(sz2, "sz2");
        elemek.put(sz3, "sz3");



        //A pálya felépítése.
        //Minden pályaelemet kölcsönösen hozzá kell csatolni egymáshoz (Pl cs1 a p-hez, majd p a cs1-hez)
        cs1.SzomszedHozzaad(p);
        p.SzomszedHozzaad(cs1);

        p.SzomszedHozzaad(cs2);
        cs2.SzomszedHozzaad(p);

        cs2.SzomszedHozzaad(f);
        f.SzomszedHozzaad(cs2);

        cs3.SzomszedHozzaad(p);
        p.SzomszedHozzaad(cs3);

        c.SzomszedHozzaad(cs3);
        cs3.SzomszedHozzaad(c);

        //A pálya elemekhez hozzárendeljük a szerelőket.
        //Megjegyzés: Bármelyik szerelőt kicserélhetnénk szabotőrre, a működésen semmit se változtatna.
        p.JatekosElfogad(sz2);
        cs2.JatekosElfogad(sz1);
        cs3.JatekosElfogad(sz3);

        //Visszatérés az elemekkel.
        return elemek;

    }

}
