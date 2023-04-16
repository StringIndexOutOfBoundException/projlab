package projlab;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

    	UseCase1_4 useCase1_4 = new UseCase1_4();
    	
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
                    break;
                case 12:
                    break;
                case 13:
                    break;
                case 14:
                    break;
                case 15:
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
