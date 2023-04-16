package projlab;

import java.util.Scanner;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        UseCase11_15 ls = new UseCase11_15();

        while (true) {
            System.out.print("\nVálaszd ki a futtatandó tesztesetet (1-19) Vagy írj \"-1\"-et a kilépéshez: ");
            Scanner sc = new Scanner(System.in);
            int valasz;
            try
            {
                valasz = sc.nextInt();

                if (valasz < -1 || valasz > 19) {
                    throw new Exception();
                }
            }
            //Ha nem számot adott meg, vagy nem létező tesztesetet választott, akkor újra kell próbálkozni
            catch (Exception e)
            {
                System.out.println("Nem megfelelő bemenet!");
                continue;
            }
            switch (valasz)
            {
                case -1:
                    return;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 11:
                    ls.lepes_inicializalas();
                    ls.szerelo_ures_csore_lep();
                    break;
                case 12:
                    ls.lepes_inicializalas();
                    ls.szerelo_foglalt_csore_akar_lepni();
                    break;
                case 13:
                    ls.lepes_inicializalas();
                    ls.szerelo_pumpara_lep();
                    break;
                case 14:
                    ls.lepes_inicializalas();
                    ls.szerelo_ciszternara_lep();
                    break;
                case 15:
                    ls.lepes_inicializalas();
                    ls.szerelo_forrasra_lep();
                    break;
                case 16:
                    break;
                case 17:
                    break;
                case 18:
                    break;
                case 19:
                    break;
            }
        }
    }
}
