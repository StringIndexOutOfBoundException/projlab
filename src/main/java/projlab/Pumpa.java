package projlab;//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : projlab.Pumpa.java
//  @ Date : 2023. 03. 29.
//  @ Author : 
//
//

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;

public class Pumpa extends AktivElem {
	private  static int MAXVIZ = 1;
	private int vizmennyiseg;
	private Cso bemenet;
	private Cso kimenet;
	

	public void Atallit(Cso kimenet, Cso bemenet) {
		this.bemenet = bemenet;
		this.kimenet = kimenet;
		System.out.println("Függvényhívás: Atallit(cs1, cs2)\n");
	}

	public void Megjavit() {
		setMukodik(true);
	}

	public void Frissit() {
		System.out.println("Függvényhívás: " + this +": Frissit() ");
		int befolyoviz = bemenet.getVizmennyiseg();
		vizmennyiseg += befolyoviz;
		try {
			bemenet.VizetCsokkent(befolyoviz);
		}
		catch (BufferUnderflowException e){
			//System.out.println("Nincs  eleg viz a csoben " + e);
		}


		int kifolyoviz = kimenet.getVizmennyiseg();
		kifolyoviz = MAXVIZ - kifolyoviz;
		if (vizmennyiseg < kifolyoviz){
			kifolyoviz = vizmennyiseg;
		}

		try {
			kimenet.VizetNovel(kifolyoviz);
		}
		catch (BufferOverflowException e){
			//System.out.println("Nem tudsz ennyi vezet a csobe pumpalni: " + kifolyoviz + " " + e );
		}
		vizmennyiseg -= kifolyoviz;


	}
}
