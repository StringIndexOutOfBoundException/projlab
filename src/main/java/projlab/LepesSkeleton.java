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
        System.out.println("---\nTeszt: Szerelő üres csőre lép. Az 1-es cső lesz az üres.\n---");
        System.out.println("Függvényhívás: Szerelo: Lep()");
        sz2.Lep();
    }

    /**
     * A 5.3.6-os szekvenciadiagram alapján teszteli azt, hogy a szerelő foglalt csőre lép.
     * A foglalt cső a cs2, azaz a 2-es opció. (vagy a cs3, azaz a 3-as opció)
     */
    public void szerelo_foglalt_csore_akar_lepni()
    {
        System.out.println("---\nTeszt: Szerelő foglalt csőre akar lépni. A 2-es (és 3-as) cső lesz a foglalt.\n---");
        System.out.println("Függvényhívás: Szerelo: Lep()");
        sz2.Lep();
    }

    /**
     * A 5.3.7-es szekvenciadiagram alapján teszteli azt, hogy a szerelő pumpára lép.
     *
     */
    public void szerelo_pumpara_lep()
    {
        System.out.println("---\nTeszt: Szerelő pumpára lép. Az 1-es lesz a pumpa\n---");
        System.out.println("Függvényhívás: Szerelo: Lep()");
        sz1.Lep();
    }

    /**
     * A 5.3.8-as szekvenciadiagram alapján teszteli azt, hogy a szerelő ciszternára lép.
     */
    public void szerelo_ciszternara_lep()
    {
        System.out.println("---\nTeszt: Szerelő ciszternára lép. A 2-es lesz a ciszterna\n---");
        System.out.println("Függvényhívás: Szerelo: Lep()");
        sz3.Lep();
    }

    /**
     * A 5.3.9-es szekvenciadiagram alapján teszteli azt, hogy a szerelő forráshoz lép.
     */
    public void szerelo_forrasra_lep()
    {
        System.out.println("---\nTeszt: Szerelő forráshoz lép. A 2-es lesz a forrás\n---");
        System.out.println("Függvényhívás: Szerelo: Lep()");
        sz1.Lep();
    }

    /**
     * Az 5.4.5-ös kommunikációs diagram alapján inicializál egy pályát a lépések
     * teszteléséhez.
     * Az 5.3.5-5.3.9-es szekvenciadiagramok alapján készült tesztekhez lesz felhasználva.
     */
    public void lepes_inicializalas() {
        //A pályában felhasznált elemek létrehozása
        p = new Pumpa();
        cs1 = new Cso();
        cs2 = new Cso();
        cs3 = new Cso();
        f = new Forras();
        c = new Ciszterna();

        sz1 = new Szerelo();
        sz2 = new Szerelo();
        sz3 = new Szerelo();



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
        //tmp
        p.JatekosElfogad(new Szerelo());
        cs2.JatekosElfogad(new Szerelo());
        //Visszatérés az elemekkel.

    }

}
