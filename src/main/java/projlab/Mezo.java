package projlab;//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : projlab.Mezo.java
//  @ Date : 2023. 03. 29.
//  @ Author : 
//
//

import java.util.ArrayList;
import java.util.List;

public abstract class Mezo {
	private Boolean mukodik;
	private int maxJatekosok;

	private List <Jatekos> jatekosok;
	private List <Mezo> szomszedok;


	public List<Mezo> GetSzomszedok() {
		return null;
	}

	public Boolean JatekosElfogad(Jatekos j) {
		return null;
	}

	public void JatekosEltavolit(Jatekos j) {
	}

	public void Atallit(Mezo kimenet, Mezo bemenet) {
	}

	// cso vagy pumpa megjavitasa
	public void Megjavit() {
		System.out.println("Fuggvenyhivas: Megjavit()");
		mukodik = true;
	}

	public void Kilyukaszt() {
		System.out.println("Fuggvenyhivas: Kilyukaszt()");
		mukodik = true;
	}

	public void PumpaEpit() {
	}

	public void SzomszedHozzaad(Mezo m) {
	}

	public void SzomszedTorol(Mezo m) {
	}

	public Boolean SzomszedFelcsatol(Mezo m) {
		return null;
	}

	public List<Mezo> GetLeszedhetoSzomszedok() {
		return null;
	}

	public void setMukodik(boolean status){
		mukodik = status;
	}

	public void VizetCsokkent(int meret){};
	public void VizetNovel(int meret) {};
}
