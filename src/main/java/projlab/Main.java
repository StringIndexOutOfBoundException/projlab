package projlab;


import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Dad!");
        Pumpa p = new Pumpa();
        Cso cs1 = new Cso();
        Cso cs2 = new Cso();

        p.Atallit(cs1, cs2);
        p.Frissit();

        //lepes_inicializalas();
        LepesSkeleton ls = new LepesSkeleton();
        ls.lepes_inicializalas();
        ls.szerelo_ures_csore_lep();

    }

}
