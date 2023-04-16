package szkeleton_eles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sikertelen_Pumpat_Felvesz_teszt {
	/*
	 *Ez a teszt a "sikertelen pumpa felvetel" nevu use-case 1A es 1B forgatokonyv bemutatasara keszult
	 *Diagram referenciak: Use-case diagram, 
	 *5.3.14.1 es 5.3.14.2  szekvenciadiagramok, 5.4.9 komm. diagramm
	 *5.2.9.1 es 5.2.9.2  szkeleton leiras
	 */
		public static void sikertelen_felvesz_pumpa() {
			
				
				//Kommunikacios diagram 5.4.9 alapjan inicializalas a teszthez
				Szerelo sz=new Szerelo();
				Ciszterna c= new Ciszterna();
				sz.setHelyzet(c);
				c.getJatekosok().add(sz);
				
			//5.4.9 inicializalas vege
				
				
				System.out.println("A ciszterna hany pumpat hozzon letre?\n A bemenet egy egesz szam 0 es MAXPUMPA kozott");
				
				//a szkeleton leiras alapjan, ha 1-et hoz letre, akkor az 1A use-caset kovetjuk
				//ha 0-t akkor az 1B-t
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				int c_pumpaszam=0;
				try {
					c_pumpaszam = Integer.parseInt(reader.readLine());
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
				
				
				//c.PumpaKeszit() fuggveny random darabnyi pumpat keszit el, mivel a tesztben a felhasznalo adja meg mennyi pumpa legyen
				//a ciszternanal, ezert nem hivjuk meg a PumpaKeszit fuggvenyt
				
				//PumpatKeszit metodus a for cikluson beluli utasitasokat hajtja vegbe (csak véletlenszámszor)
				for(int i=0;i<c_pumpaszam;++i) {
					Pumpa pumpa1=new Pumpa();
					 pumpa1.SzomszedHozzaad(c); 
					 c.SzomszedHozzaad(pumpa1); 
					 c.getTermeltPumpak().add(pumpa1);
			}
				System.out.println("A ciszternanal levo pumpak szama:" + c.getTermeltPumpak().size());
				System.out.println("Mennyi pumpa legyen a szerelő (pumpákat tároló) hátizsákjában?"
						 			+ "\n A bemenet egy egesz szam 0 es MAXPUMPA kozott");
				
				//A szkeleton leiras alapjan, ha tele van a hatizsak, akkor az 1A use-caset kovetjuk
				//ha nincs benne egy pumpa sem akkor az 1B-t
				BufferedReader reader2 = new BufferedReader(new InputStreamReader(System.in));
				int sz_pumpaszam=0;
				try {
					sz_pumpaszam = Integer.parseInt(reader2.readLine()); //MaxHatizsakKapacitas
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//itt  beallitjuk mennyi a maximalis kapacitas, majd feltoltjuk a pumpaHatizsakot.
				sz.setmaxHatizsakKapacitas(10);
				for(int i=0;i<sz_pumpaszam;++i) {
					if(i<=9) {
						 Pumpa pumpa1=new Pumpa();
						 sz.getpumpaHatizsak().add(pumpa1);
					}
			    
				}
				
					System.out.println("A szerelonel levo pumpak szama:" + sz.getpumpaHatizsak().size());
				
				
				sz.PumpatFelvesz();
				
				System.out.println("A szerelonel levo pumpak szama:" + sz.getpumpaHatizsak().size());
				System.out.println("A ciszternanal levo pumpak szama:" + c.getTermeltPumpak().size());		
				}

}
