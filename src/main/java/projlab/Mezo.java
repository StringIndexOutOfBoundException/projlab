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
	private Jatekos jatekosok;
	private ArrayList<Mezo> szomszedok;

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

	public void Megjavit() {
	}

	public void Kilyukaszt() {
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
