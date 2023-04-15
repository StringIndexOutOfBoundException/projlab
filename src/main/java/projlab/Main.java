package projlab;



public static void sikertelen_felvesz_pumpa() {
// Ez a teszt a "sikertelen pumpa felvetel" nevu use-case 1A es 1B forgatokonyv bemutatasara keszult
//Diagram referenciak: Use-case diagram, 
//5.3.14.1 es 5.3.14.2  szekvenciadiagramok, 5.4.9 komm. diagramm
//5.2.9.1 es 5.2.9.2  szkeleton leiras

	
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
		 c.ciszternaTermeltPumpak().add(pumpa1);
}
	System.out.println("A ciszternanal levo pumpak szama:" + c.ciszternaTermeltPumpak().size());
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
	System.out.println("A ciszternanal levo pumpak szama:" + c.ciszternaTermeltPumpak().size());		
	}

public static void sikeres_felvesz_pumpa() {
	//Ez a teszt a "sikeres pumpa felvetel" use-case alapjan keszult
	//Diagramm referenciak:
	//5.2.8 szkeleton leiras
	//5.3.13 szekvenciadiagram
	//5.4.9  kommunikacios diagramm
	
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
			 c.ciszternaTermeltPumpak().add(pumpa1);
	}
		System.out.println("A ciszternanal levo pumpak szama:" + c.ciszternaTermeltPumpak().size());
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
		System.out.println("A ciszternanal levo pumpak szama:" + c.ciszternaTermeltPumpak().size());		
		
}

public static void Pumpa_Epit() {
	// Ez a fuggveny a "Pumpa Epit" nevu use-case  forgatokonyv bemutatasara keszult
	//Diagram referenciak: Use-case diagram, 5.3.15. szekvenciadiagram, 5.4.10 komm. diagramm
	//5.2.10 a szkeleton leiras	
	
	//inicializalas a komm. diagramm alapjan
	
	Cso cs=new Cso();
	Szerelo sz= new Szerelo();
	Pumpa p=new Pumpa();
	Pumpa p1=new Pumpa();
	Pumpa ujPumpa=new Pumpa();
	p1.setKimenet(cs);
	p.setBemenet(cs);
	cs.SzomszedHozzaad(p1);
	cs.SzomszedHozzaad(p);
	p1.SzomszedHozzaad(cs);
	p.SzomszedHozzaad(cs);
	sz.getpumpaHatizsak().add(ujPumpa);
	sz.setHelyzet(cs);
	cs.getJatekosok().add(sz);
	//inicializalas vege
	
	//most van egy "cs" csovunk, aminek van ket pumpaja "p" es "p1", illetve egy szerelo,
	// aki a "cs" csovon all es van egy pumpa "ujPumpa" a hatizsakjaban
	System.out.println("Letrejott: cs:Cso,p:Pumpa,p1:Pumpa,sz:Szerelo,ujPumpa:Pumpa");
	System.out.println("p bemenete:cs, p1 kimenete:cs, sz helyzete:cs");


	sz.PumpatEpit();	
	System.out.println("p bemenete:ujCso, ujPumpa bemenete:cs kimenete:ujCso, p es cs mar nem szomszedok");
}



public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Dad!");
        Pumpa p = new Pumpa();
        Cso cs1 = new Cso();
        Cso cs2 = new Cso();

        p.Atallit(cs1, cs2);
        p.Frissit();
    }

}
