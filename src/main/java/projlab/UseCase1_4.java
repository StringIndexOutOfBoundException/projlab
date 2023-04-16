package projlab;

/**
 * Az 5.2.1, 5.2.2, 5.2.3, 5.2.4 Use Case esetek tesztelésének megvalósításáért felelős osztály
 */
public class UseCase1_4 {

	/**
     * Teszt: 5.2.3 Szerelo pumpat allit
     * a szerelo atallitja egy pumpa be es kimenetet
     */
    public static void szereloPumpatAllitTeszt() {
    	System.out.println("\nSzerelo pumpat allit");
    	//szukseges valtozok letrehozasa
    	Szerelo sz = new Szerelo();
    	Pumpa p = new Pumpa();
        Cso cs1 = new Cso();
        Cso cs2 = new Cso();
        
        //szukseges valtozok inicializalasa
        p.JatekosElfogad(sz);
        sz.setHelyzet(p);
        cs1.SzomszedHozzaad(p);
        p.SzomszedHozzaad(cs1);
        cs2.SzomszedHozzaad(p);
        p.SzomszedHozzaad(cs2);
        
        //szerelo atallitja egy pumpa be es kimenetet
        sz.Allit();
    }
    
    /**
     * Teszt: 5.2.4 Szabotor pumpat allit
     * a szabotor atallitja egy pumpa be es kimenetet
     */
    public static void szabotorPumpatAllitTeszt() {
    	System.out.println("\nSzabotor pumpat allit");
    	//szukseges valtozok letrehozasa
    	Szabotor sz = new Szabotor();
    	Pumpa p = new Pumpa();
        Cso cs1 = new Cso();
        Cso cs2 = new Cso();
        
        //szukseges valtozok inicializalasa
        p.JatekosElfogad(sz);
        sz.setHelyzet(p);
        cs1.SzomszedHozzaad(p);
        p.SzomszedHozzaad(cs1);
        cs2.SzomszedHozzaad(p);
        p.SzomszedHozzaad(cs2);
        
        //szerelo atallitja egy pumpa be es kimenetet
        sz.Allit();
    }
    
    /**
     * Teszt: 5.2.1 Szerelo csovet javit 
     * a szerelo megjavít egy csovet
     */
    public static void szereloCsovetJavitTeszt() {
    	System.out.println("\nSzerelo csovet javit");
    	//szukseges valtozok letrehozasa
    	Cso cs = new Cso();
    	Szerelo sz = new Szerelo();
        
    	//szukseges valtozok inicializalasa
        cs.JatekosElfogad(sz);
        sz.setHelyzet(cs);
        
        //szerelo megjavitja a csovet
        sz.Javit();
    }
    
    /**
     * Teszt: 5.2.2 Szerelo pumpat javit 
     * a szerelo megjavít egy pumpat
     */
    public static void szereloPumpatJavitTeszt() {
    	System.out.println("\nSzerelo pumpat javit");
    	//szukseges valtozok letrehozasa
    	Pumpa p = new Pumpa();
    	Szerelo sz = new Szerelo();
        
    	//szukseges valtozok inicializalasa
        p.JatekosElfogad(sz);
        sz.setHelyzet(p);
        
        //szerelo megjavitja a pumpat
        sz.Javit();
    }

}
