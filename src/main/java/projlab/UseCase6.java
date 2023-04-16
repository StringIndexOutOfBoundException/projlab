package projlab;

/**
 *Ez a teszt a "Pumpa{@link Pumpa} frissül" use-case alapjan keszult
 * <ul>Diagramm referenciak:
 *  <li>5.2.6 szkeleton leiras</li>
 *  <li>5.3.14 szekvenciadiagram</li>
 *  <li>5.3.11  kommunikacios diagramm</li>
 * </ul>
 */
public class UseCase6 {
    public static void use_case_test() {
        Cso cs2 = new Cso();
        cs2.setVizmennyiseg(1);
        Pumpa p = new Pumpa();
        Cso cs1 = new Cso();
        cs1.SzomszedFelcsatol(p);
        cs2.SzomszedFelcsatol(p);

        p.Atallit(cs1, cs2);

        try{
            p.Frissit();
        }
        catch (Exception e) {}
    }
}
