package projlab;

public class UseCase7 {
    public static void use_case_test() {
        Ciszterna c = new Ciszterna();
        Cso cs = new Cso();

        cs.setVizmennyiseg(1);
        cs.SzomszedFelcsatol(c);
        c.SzomszedFelcsatol(cs);

        try{
            c.Frissit();
        }
        catch (Exception e){}
    }
}
