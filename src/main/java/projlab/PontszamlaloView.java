package projlab;

import java.awt.*;
import java.util.ArrayList;

public class PontszamlaloView extends ObjectView{
    private static final Font font = new Font("Arial", Font.PLAIN, 14);
    int szePont, szaPont = 0;
    int currRound = 1;
    int szeX, szeY, szaX, szaY, pX, pY = 0;
    public PontszamlaloView(){
        nev = "pontszamlalo";
        szeX = 10;
        szeY = 20;
        szaX = szeX;
        szaY = szeY + 25;
        pX = szeX;
        pY = szaY + 25;
    }

    /**
     * Az eredmény változásakor hívódó függvény
     * @param s kinek a pontja változott
     * @param p mennyi ponttal változott
     */
    public void Notify(String s, int p){
        if(s.compareTo("sze") == 0)
            szePont += p;
        else if(s.compareTo("sza") == 0)
            szaPont += p;
        else if(s.compareTo("k") == 0)
             currRound += p;
    }

    /**
     * Kirajzolásért felelős függvény
     * @param layers - A bufferek amikre rajzolni kell.
     */
    public void Draw(ArrayList<Graphics> layers) {
        Graphics g = layers.get(3);
        g.setColor(Color.black);
        g.drawString("Szerelő pont: " + szePont, szeX, szeY);
        g.drawString("Szabotőr pont: "+ + szaPont, szaX, szaY);
        g.drawString("Kör: " + currRound + "/20", pX, pY);
    }
}
