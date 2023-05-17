package projlab;

import java.util.Scanner;

import java.util.ArrayList;

/**
 * Ezt az osztályt a tesztesetek futtatására hoztuk létre
 */
public class Main {
    public static void main(String[] args) {





        //PARANCSÉRTELMEZO PÉLDA:
        ParancsErtelmezo pe = new ParancsErtelmezo();
        //Fájlból parancs futtatás
        pe.runFromFile("commandfiles/test.txt"); //Akárhány parancs lehet a fájlban, minden sorban egy
        //pe.runFromFile("commandfiles/alap"); //Alap pálya. Jó ha van.
        //Stringből parancs futtatás
        pe.runFromString(" letrehoz cso cs2 \n letrehoz szabotor sza1 \n "); //Akárhány parancsot beírhatsz \n-nel elválasztva
        //Konzolról parancs futtatás (Beírsz egy parancsot, majd entert nyomsz. EOF-al ki lehet lépni belőle)
        pe.runFromUser();

        /*
        while (true) {
            System.out.print("\nVálaszd ki a futtatandó tesztesetet (1-20) Vagy írj \"-1\"-et a kilépéshez: ");
            Scanner sc = new Scanner(System.in);
            int valasz;
            try
            {
                valasz = sc.nextInt();

                if (valasz < -1 || valasz > 20) {
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
                    useCase1_4.szereloCsovetJavitTeszt();
                    break;
                case 2:
                    useCase1_4.szereloPumpatJavitTeszt();
                    break;
                case 3:
                    useCase1_4.szereloPumpatAllitTeszt();
                    break;
                case 4:
                    useCase1_4.szabotorPumpatAllitTeszt();
                    break;
                case 5:
                    UseCase5.use_case_test();
                    break;
                case 6:
                    UseCase6.use_case_test();
                    break;
                case 7:
                    UseCase7.use_case_test();
                    break;
                case 8:
                    UseCase8.sikeres_felvesz_pumpa();
                    break;
                case 9:
                    UseCase9.sikertelen_felvesz_pumpa();
                    break;
                case 10:
                    UseCase10.Pumpa_Epit();
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
                    UseCase16.csovetFelcsatolTest();
                    break;
                case 17:
                    UseCase16.csovetLecsatolTest();
                    break;
                case 18:
                    UseCase16.csoreProbalFelcsatolni();
                    break;
                case 19:
                    UseCase16.csoreProbalFelcsatolni();
                    break;
                case 20:
                    UseCaseNull.use_case_test();
                    break;
            }
        } */
    }
}
