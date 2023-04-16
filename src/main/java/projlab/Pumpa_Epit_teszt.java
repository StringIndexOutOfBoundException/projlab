package szkeleton_eles;

public class Pumpa_Epit_teszt {
	/* Ez a fuggveny a "Pumpa Epit" nevu use-case  forgatokonyv bemutatasara keszult
	*Diagram referenciak: Use-case diagram, 5.3.15. szekvenciadiagram, 5.4.10 komm. diagramm
	*5.2.10 a szkeleton leiras	
	*/
	public static void Pumpa_Epit() {

		//inicializalas a komm. diagramm alapjan
		
		Cso cs=new Cso();
		Szerelo sz= new Szerelo();
		Pumpa p=new Pumpa();
		Pumpa p1=new Pumpa();
		Pumpa ujPumpa=new Pumpa();
		p1.Atallit(cs, p1.getBemenet());
		p.Atallit(p.getKimenet(), cs);
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
		System.out.println("p bemenete:" + p.getBemenet()+ ",p1 kimenete:" + p1.getKimenet() + ",sz helyzete:" +  sz.getHelyzet());

		System.out.println("Fuggvenyhivas:sz.PumpatEpit()");
		System.out.println("Fuggvenyhivas:cs.PumpaEpit()");
		sz.PumpatEpit();
	
	
	}	
}