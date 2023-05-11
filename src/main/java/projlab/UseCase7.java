package projlab;
/**
 *Ez a teszt a "Ciszterna{@link Ciszterna} frissül" use-case alapjan keszult
 * <ul>Diagramm referenciak:
 *  <li>5.2.7 szkeleton leiras</li>
 *  <li>5.3.15 szekvenciadiagram</li>
 *  <li>5.3.12  kommunikacios diagramm</li>
 * </ul>
 */
public class UseCase7 {
    public static void use_case_test() {
        Ciszterna c = new Ciszterna();
        Cso cs = new Cso();

        cs.SetVizmennyiseg(1);
        cs.SzomszedFelcsatol(c);
        c.SzomszedFelcsatol(cs);

        try{
            c.Frissit();
        }
        catch (Exception e){}
    }
}
