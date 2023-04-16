package szkeleton_eles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UseCase8{

	/*
	 *Ez a teszt a "sikeres pumpa felvetel" use-case alapjan keszult
	 *Diagramm referenciak:
	 *5.2.8 szkeleton leiras
	 *5.3.13 szekvenciadiagram
	 *5.4.9  kommunikacios diagramm
	 */
			public static void sikeres_felvesz_pumpa() {
		
				
				//Kommunikacios diagram 5.4.9 alapjan inicializalas a teszthez
					Szerelo sz=new Szerelo();
					Ciszterna c= new Ciszterna();
					sz.setHelyzet(c);
					c.getJatekosok().add(sz);
					
				//5.4.9 inicializalas vege
						
					System.out.println("A ciszterna hany pumpat hozzon letre?\n A bemenet egy egesz szam 0 es MAXPUMPA kozott");
					//szkeleton leiras szerint 1-et hoz letre, de sikeres a teszt ha nem 0-t irunk be
					int c_pumpaszam=0;
					while(c_pumpaszam<=0) {
						
					BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
					System.out.println("A teszt erdekeben 0-t ne irjon be!");
					try {
					
						c_pumpaszam = Integer.parseInt(reader.readLine());
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}
						
			//c.PumpaKeszit() fuggveny random darabnyi pumpat keszit el, mivel a tesztben a felhasznalo adja meg mennyi pumpa legyen
			//a ciszternanal, ezert nem hivjuk meg a PumpaKeszit fuggvenyt
					
					
			//annyi pumpat hoz letre amennyit beirtak
			//a for ciklusban levo utasitasok ekvivalensek a ciszterna PumpaKeszit fuggveny utasitasaival.
					
					for(int i=0;i<c_pumpaszam;++i) {
						Pumpa pumpa1=new Pumpa();
						 pumpa1.SzomszedHozzaad(c); 
						 c.SzomszedHozzaad(pumpa1); 
						 c.getTermeltPumpak().add(pumpa1);
				}
					System.out.println("A ciszternanal levo pumpak szama:" + c.getTermeltPumpak().size());
					System.out.println("Mennyi pumpa legyen a szerelő (pumpákat tároló) hátizsákjában?"
							 			+ "\n A bemenet egy egesz szam 0 es MAXPUMPA kozott");
					//szkeleton leirasban 0-t irunk be
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
					
					//Ehhez a teszthez meg lehetne adni a szerelo maxHatizsakKapacitas attributumerteket, de mivel a sikeres lefutasra
					//koncentralunk, ezert most nincs kikotve a maximalis kapacitas.
					sz.setmaxHatizsakKapacitas(sz_pumpaszam+1);
					
					//feltoltjuk a pumpaHatizsakot
					for(int i=0;i<sz_pumpaszam;++i) {
						
							 Pumpa pumpa1=new Pumpa();
							 sz.getpumpaHatizsak().add(pumpa1);
					}
					
						System.out.println("A szerelonel levo pumpak szama:" + sz.getpumpaHatizsak().size());
					
					sz.PumpatFelvesz();		
					System.out.println("A szerelonel levo pumpak szama:" + sz.getpumpaHatizsak().size());
					System.out.println("A ciszternanal levo pumpak szama:" + c.getTermeltPumpak().size());		
					
			}
}
