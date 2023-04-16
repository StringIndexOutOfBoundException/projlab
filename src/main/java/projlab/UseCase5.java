package projlab;

/**
 *Ez a teszt a "Forras{@link Forras} frissül" use-case alapjan keszult
 * <ul>Diagramm referenciak:
 *  <li>5.2.5 szkeleton leiras</li>
 *  <li>5.3.13 szekvenciadiagram</li>
 *  <li>5.3.10  kommunikacios diagramm</li>
 * </ul>
 */
public class UseCase5 {
    public static void use_case_test(){
        Cso cs0 = new Cso();
        Forras f = new Forras();

        cs0.SzomszedFelcsatol(f);
        f.SzomszedFelcsatol(cs0);

        try{
            f.Frissit();
        }
        catch (Exception e){}
    }
}
