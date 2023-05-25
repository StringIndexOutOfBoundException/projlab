package projlab;

public class ParancsErtelmezoView {

    //A viewhoz tartozó parancsértelmező
    private ParancsErtelmezo pe;

    /**
     * Default konstruktor. Parancsértelmezőt nem állít be. Akkor rendelődik a view egy parancsértelmezőhöz, ha a parancsértelmező beállítja ezt a viewt a magáénak.
     * (Mert ugye ParancsErtelmezo nélkül a view-nak nincs sok értelme.)
     */
    public ParancsErtelmezoView() {
    }

    /**
     * A viewhoz tartozó parancsértelmező beállítása
     * Ezt NE hivogassuk manuálisan, a ParancsErtelmezo beállítja!
     * @param pe a parancsértelmező
     */
    public void SetParancsErtelmezo(ParancsErtelmezo pe) {
        this.pe = pe;
    }

    /**
     * A parancsértlmezőnek küld egy parancsot (Vagy többet, ha azok \n-nel vannak elválasztva)
     *  Ezzel a függvénnyel lehet eljuttatni a parancsértelmezőhöz az input-ért felelős TextBox-ból a parancsokat.
     * @param parancs a parancs(ok)
     */
    public void SendToPE(String parancs) {
        //Ellenőrizzük, hogy van-e parancsértelmező
        if (pe == null) {
            System.out.println("!!! HIBA:\nEz a view nem rendelkezik parancsértelmezővel!\nVálassz egy ParancsErtelmezo-t, és állítsd be ezt a view-jának!");
            return;
        }
        pe.runFromString(parancs);
    }

    /**
     * A parancsértelmezőtől kapott kimenetet megkapja a view
     * @param output a kimenet ami a parancsértelmezőtől jött
     */
    public void ReceiveFromPE(String output) {
        //TODO: Itt kell átadni az output-ért felelős TextBox-nak az outputot ami a parancsértelmezőtől jött.
    }


}
