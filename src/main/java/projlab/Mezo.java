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
	private int vizmennyiseg;
	private Boolean mukodik;
	private int maxJatekosok;

	private List <Jatekos> jatekosok;
	private ArrayList <Mezo> szomszedok;

	public Mezo(){szomszedok = new ArrayList<Mezo>();}


	/**
	 * Visszaadja az adott mező szomszédainak a listáját.
	 * @return szomszedok
	 */
	public ArrayList<Mezo> GetSzomszedok() {
		return szomszedok;
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

	
	/**
	 *  Az érintett mezőnek hozzáadja a paraméterként átadott mezőt a szomszédok kollekciójához. 
	 * @param m - Hozzáadandó szomszéd
	 */
	public void SzomszedHozzaad(Mezo m) {
		System.out.println("Függvényhívás: " + this + ".SzomszedHozzaad(" + m + ")");
		szomszedok.add(m);
	}

	/**
	 * Egy mezőt leválasztottunk egy másik mezőről. Az adott mezőnek a szomszédok kollekciójából törli az m mezőt.
	 * @param m Törlendő szomszéd
	 */
	public void SzomszedTorol(Mezo m) {
		szomszedok.remove(m);
		System.out.println("Függvényhívás: " + this + ".SzomszedTorol(" + m + ")");
	}

	/**
	 * A Játékosok ezen a függvényen keresztül csatolhatnak fel elemeket a mezőkhöz.
	 * Minden aktívelemnél ugyanazt csinálja mint a SzomszedHozzaad, viszont a Cső
	 * osztály felüldefiniálja. Default implementációként nem enged felcsatolni.
	 * @param m - Felcsatolandó
	 * @return Sikerült-e a felcsatolás
	 */
	public Boolean SzomszedFelcsatol(Mezo m) {
		System.out.println("Függvényhívás: " + this + ".SzomszedFelcsatol(" + m + ")");
		return false;
	}

	/**
	 * Működése hasonló a GetSzomszedokhoz, viszont csak a játékosok által lecsatlakoztatható 
	 * szomszédokkal tér vissza.
	 * Default implementációként üres listával tér vissza.
	 * @return lecsatlakoztatható szomszédok
	 */
	public ArrayList<Mezo> GetLeszedhetoSzomszedok() {
		System.out.println("Függvényhívás: " + this + ".GetLeszedhetoSzomszedok()");
		return new ArrayList<>();
	}

	public void setMukodik(boolean status){
		mukodik = status;
	}

	public void VizetCsokkent(int meret) throws Exception {};
	public void VizetNovel(int meret) throws Exception {};
	public int getVizmennyiseg(){return vizmennyiseg;}
}
