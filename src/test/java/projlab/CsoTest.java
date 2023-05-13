package projlab;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CsoTest {

    ParancsErtelmezo pre;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        pre = new ParancsErtelmezo();
        pre.runFromString("letrehoz forras pf1\nletrehoz ciszterna pc1 inletrehoz pumpa pp1\nletrehoz pumpa pp2\nletrehoz pumpa pp3\nletrehoz cso pcs1\nletrehoz cso pcs2\nletrehoz cso pcs3\nletrehoz cso pcs4\nletrehoz cso pcs5\nletrehoz cso pcs6\nletrehoz cso pcs7\nletrehoz szerelo psze1\nletrehoz szerelo psze2\nletrehoz szerelo psza1\nosszekot pcs1 pf1\nosszekot pcs1 pp1\nosszekot pp1 pcs2\nosszekot pcs2 pp2\nosszekot pp2 pcs6\nosszekot pcs6 pc1\nosszekot pc1 pcs7\nosszekot pp1 pcs3\nosszekot pcs3 pp3\nosszekot pp3 pcs4\nosszekot pcs4 pp2\nosszekot pp3 pcs5\nosszekot pcs5 pc1\nlep psza1 pcs3\nlep psze2 pp3\nlep psze1 pcs5   ");
    }

    @Test
    @DisplayName("Cső létrehozása")
    void cso_letrehozasa() {
        pre.runFromString("letrehoz cso cs1");
        pre.runFromString("allapot cs1 *");
        assertEquals("működik: true\nszomszédok:\nmaxJatekosok: 1\nmaxSzomszédok: 2\njátékosok:\nvízmennyiség: 0\nlyukCooldown: 0\ncsuszos: 0\nragados: 0\nragadossatette: null ",
                pre.getAllapotString());
    }

    @Test
    @DisplayName("n Pumpa felcsatolása a csőre")
    void felcsatol() {
        pre.runFromString("letrehoz cs1\nletrehoz p1\nletrehoz p2\nletrehoz p3\nletrehoz p4\nletrehoz p5\nosszekot cs1 p1\nosszekot cs1 p2\nosszekot cs1 p3\nosszekot cs1 p4\nosszekot cs1 p5\nallapot cs1 szomszedok");
        String output = pre.getAllapotString();
        assertEquals("szomszédok:\np1\np2\n", output);
    }

    @Test
    @DisplayName("Két cső összekötése")
    void osszekot_ket_csot() {
        pre.runFromString("letrehoz cs1\nletrehoz cs2\nosszekot cs1 cs2\nallapot cs1 szomszedok\nallapot cs2 szomszedok ");
        assertEquals("", pre.getAllapotString());
    }

    @Test
    @DisplayName("Szomszedos, foglalt csore lépés")
    void csore_lep() {
        pre.runFromString(" allit psze2 pcs3 pcs5\nvizmennyiseg pp3 1\nfrissit\nallapot pcs5 vizmennyiseg\nallapot pp3 vizmennyiseg");
        String out = pre.getAllapotString();
        assertEquals("vízmennyiség: 1\nvízmennyiség: 0 ", out);
    }

    @Test
    @DisplayName("Üres cső túlpumpálása")
    void tulpumpal() {
        pre.runFromString("allit psze2 pcs3 pcs5\nvizmennyiseg pp3 3\nfrissit\nfrissit\nallapot pcs5 vizmennyiseg\nallapot pp3 vizmennyiseg");
        String out = pre.getAllapotString();
        assertEquals("vízmennyiség: 1\nvízmennyiség: 2", out);
    }

    @Test
    @DisplayName("Csőből benne lévő víznél több kiszívása")
    void kisziv() {
        pre.runFromString("allit psze2 pcs3 pcs5\nvizmennyiseg pcs3 1\nfrissit\nallapot pcs3 vizmennyiseg\nallapot pp3 vizmennyiseg");
        String out = pre.getAllapotString();
        assertEquals("vízmennyiség: 0\nvízmennyiség: 1", out);
    }

    @Test
    @DisplayName("Cső kilyukasztása")
    void kilyukaszt(){
        pre.runFromString("allit psze2 pcs3 pcs5\nvizmennyiseg pcs5 1\nvizmennyiseg pp3 3\nallapot pcs5 mukodik\nallapot pcs5 vizmennyiseg\nlyukaszt psze1\nallapot pcs5 mukodik\nallapot pcs5 vizmennyiseg\nfrissit\nallapot pcs5 vizmennyiseg ");
        String out = pre.getAllapotString();
        assertEquals("működik: true\nvízmennyiség: 1\nműködik: false\nvízmennyiség: 0\nvízmennyiség: 0", out);
    }

    @Test
    @DisplayName("Kilyukasztott cső kilyukasztása")
    void lyukaszt(){
        pre.runFromString("allit psze2 pcs3 pcs5\nvizmennyiseg pcs5 1\nvizmennyiseg pp3 3\nallapot pcs5 mukodik\nallapot pcs5 vizmennyiseg\nlyukaszt psze1\nallapot pcs5 mukodik\nallapot pcs5 vizmennyiseg\nfrissit\nallapot pcs5 vizmennyiseg\nlyukaszt psze2\nfrissit\nallapot pcs5 mukodik\nallapot pcs5 vizmennyiseg ");
        String out = pre.getAllapotString();
        assertEquals("működik: true\nvízmennyiség: 1\nműködik: false\nvízmennyiség: 0\nvízmennyiség: 0\nműködik: false\nvízmennyiség: 0\n", out);
    }

    @Test
    @DisplayName("Nemy lyukasztható cső lyukasztása")
    void lyukaszt2(){
        pre.runFromString("allit psze2 pcs3 pcs5\nvizmennyiseg pcs5 1\nvizmennyiseg pp3 3\nlyukaszt psze1\nszerel psze1\nlyukaszt psze1\nallapot pcs5 mukodik\nallapot pcs5 vizmennyiseg\nallapot pcs5 lyukCooldown\nfrissit\nallapot pcs5 vizmennyiseg\nallapot pcs5 lyukCooldown ");
        String out = pre.getAllapotString();
        assertEquals("működik: true\nvízmennyiség: 0\nlyukCooldown: 5\nvízmennyiség: 1\nlyukCooldown: 4\n", out);
    }

    @Test
    @DisplayName("Lyukas cso javitasa")
    void javit(){
        pre.runFromString("allit psze2 pcs3 pcs5\nvizmennyiseg pcs5 1\nvizmennyiseg pp3 3\nlyukaszt psze1\nszerel psze1\nallapot pcs5 mukodik\nallapot pcs5 vizmennyiseg\nallapot pcs5 lyukCooldown\nfrissit\nallapot pcs5 vizmennyiseg\nallapot pcs5 lyukCooldown");
        String out = pre.getAllapotString();
        assertEquals("működik: true\nvízmennyiség: 0\nlyukCooldown: 5\nvízmennyiség: 1\nlyukCooldown: 4", out);
    }

    @Test
    @DisplayName("Nem lyukas, nem lyukasztható csovet javít")
    void javit2(){
        pre.runFromString("allit psze2 pcs3 pcs5\nvizmennyiseg pcs5 1\nvizmennyiseg pp3 3\nlyukaszt psze1\nszerel psze1\nszerel psze1\nallapot pcs5 mukodik\nallapot pcs5 vizmennyiseg\nallapot pcs5 lyukCooldown\nfrissit\nallapot pcs5 vizmennyiseg\nallapot pcs5 lyukCooldown");
        String out = pre.getAllapotString();
        assertEquals("működik: true\nvízmennyiség: 0\nlyukCooldown: 5\nvízmennyiség: 1\nlyukCooldown: 4", out);
    }

    @Test
    @DisplayName("Nem lyukas cső javítása")
    void javit3(){
        pre.runFromString("allit psze2 pcs3 pcs5\nvizmennyiseg pcs5 1\nvizmennyiseg pp3 3\nszerel psze1\nallapot pcs5 mukodik\nallapot pcs5 vizmennyiseg\nallapot pcs5 lyukCooldown\nfrissit\nallapot pcs5 vizmennyiseg ");
        String out = pre.getAllapotString();
        assertEquals("működik: true\nvízmennyiség: 0\nlyukCooldown: 0\nvízmennyiség: 1 ", out);
    }

    @Test
    @DisplayName("Nem csúszós cső csúszóssá tétele")
    void csuszos(){
        pre.runFromString("csuszik psza1\nallapot pcs3 csuszos\nallapot pcs3 jatekosok\nlep psza1 pp1\nlep psze2 pcs3\nallapot pp1 jatekosok\nallapot pcs3 jatekosok\nallapot pp3 jatekosok ");
        String out = pre.getAllapotString();
        assertEquals("csúszós: 5\njátékosok:\npsza1\njátékosok:\npsza1\npsze2\njátékosok:\njátékosok:\n ", out);
    }

    @Test
    @DisplayName("Csúszós cső csúszóssá tétele")
    void csuszos2(){
        pre.runFromString("csuszik psza1\ncsuszik psza1\nallapot pcs3 csuszos\nallapot pcs3 jatekosok\nlep psza1 pp1\nlep psze2 pcs3\nallapot pp1 jatekosok\nallapot pcs3 jatekosok\nallapot pp3 jatekosok ");
        String out = pre.getAllapotString();
        assertEquals("csúszós: 5\njátékosok:\npsza1\njátékosok:\npsza1\npsze2\njátékosok:\njátékosok: ", out);
    }

    @Test
    @DisplayName("Cső ragadóssá tétele")
    void ragados(){
        pre.runFromString("ragad psza1\nallapot pcs3 ragados\nallapot pcs3 ragadossatette\nlep psza1 pp1\nallapot pcs3 jatekosok\nallapot pp1 jatekosok\nallapot pcs3 ragadossatette ");
        String out = pre.getAllapotString();
        assertEquals("ragadós: 5\nragadóssátette: psza1\njátékosok:\njátékosok:\npsza1\nragadóssátette: null ", out);
    }

    @Test
    @DisplayName("Ragadós cső ragadóssá tétele")
    void ragados2(){
        pre.runFromString("ragad psza1\nragad psza1\nallapot pcs3 ragados\nallapot pcs3 ragadossatette\nlep psza1 pp1\nallapot pcs3 jatekosok\nallapot pp1 jatekosok\nallapot pcs3 ragadossatette\nlep psze2 pcs3\nragad psza1\nallapto pcs3 ragadossatette\nlep psze2 pp3\nallapot pcs3 jatekosok\nallapot pp3 jatekosok ");
        String out = pre.getAllapotString();
        assertEquals("ragadós: 5\nragadóssátette: psza1\njátékosok:\njátékosok:\npsza1\nragadóssátette: null\nragadóssátette: null\njátékosok:\npsze2\njátékosok: ", out);
    }

    @Test
    @DisplayName("Ragadós csőről lelépés")
    void ragad_lep(){
        pre.runFromString("ragad psza1\nallapot pcs3 ragados\nallapot pcs3 ragadossatette\nlep psza1 pp1\nallapot pcs3 jatekosok\nallapot pp1 jatekosok\nallapot pcs3 ragadossatette\nlep psze2 pcs3\nlep psze2 pp3\nallapot pcs3 jatekosok\nallapot pp3 jatekosok");
        String out = pre.getAllapotString();
        assertEquals("ragadós: 5\nragadóssátette: psza1\njátékosok:\njátékosok:\npsza1\nragadóssátette: null\njátékosok:\npsze2\njátékosok: ", out);
    }

    @Test
    @DisplayName("Csúszós cső ragadóssá tétele")
    void csuszik_ragad(){
        pre.runFromString("csuszik psza1\nragad psza1\nlep psza1 pp1\nlep psze2 pcs3\nlep psze2 pp3\nallapot pcs3 ragados\nalllapot pcs3 csuszos\nallapot pcs3 jatekosok\nallapot pp3 jatekosok ");
        String out = pre.getAllapotString();
        assertEquals("ragadós: 5\ncsúszós: 5\njátékosok:\npsze2\njátékosok: ", out);
    }



}