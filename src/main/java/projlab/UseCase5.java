package projlab;

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
