package projlab;

public class UseCase10 {
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
		sz.getPumpaHatizsak().add(ujPumpa);
		sz.setHelyzet(cs);
		cs.getJatekosok().add(sz);
		//inicializalas vege
		
		//most van egy "cs" csovunk, aminek van ket pumpaja "p" es "p1", illetve egy szerelo,
		// aki a "cs" csovon all es van egy pumpa "ujPumpa" a hatizsakjaban

		System.out.println("\n\nFuggvenyhivas:sz.PumpatEpit()");
		System.out.println("Fuggvenyhivas:cs.PumpaEpit()");
		sz.PumpatEpit();
	
	
	}	
}
