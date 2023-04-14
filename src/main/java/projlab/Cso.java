package projlab;//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : projlab.Cso.java
//  @ Date : 2023. 03. 29.
//  @ Author : 
//
//

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;

public class Cso extends Mezo {
	private int vizmennyiseg;

	public void Megjavit() {

		setMukodik(true);
	}

	public void PumpaEpit() {
	}

	public void Kilyukaszt() {
		setMukodik(false);
	}

	public void VizetNovel(int meret) {
		System.out.println("Függvényhívás:" + this +": VizetNovel( " + meret + ") ");
		if(meret + vizmennyiseg > 1){	//MAXVIZ
			throw new BufferOverflowException();
		}
		else {
			vizmennyiseg += meret;
		}
	}

	public void VizetCsokkent(int meret) {
		System.out.println("Függvényhívás: " + this +": VizetCsokkent( " + meret + ") ");

		if(meret < vizmennyiseg) {
			vizmennyiseg -= meret;
		}
		else{ throw new BufferUnderflowException();}

	}

	public int getVizmennyiseg(){
		return vizmennyiseg;
	}

}
