package projlab;//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : projlab.Forras.java
//  @ Date : 2023. 03. 29.
//  @ Author : 
//
//

import java.util.List;

public class Forras extends AktivElem {
	private static int MAXVIZ = 1;
	public void Frissit() throws Exception {
		System.out.println("Függvényhívás: " + this +": Frissit() ");
		List<Mezo> szomszedok = super.GetLeszedhetoSzomszedok();
		int szomszedszam = szomszedok.size();
		if(szomszedszam > 1){
			throw new IllegalArgumentException();
		} else if (szomszedszam == 1) {
			szomszedok.get(0).VizetNovel(MAXVIZ);
		}
	}
}
