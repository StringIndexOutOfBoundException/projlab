package projlab;//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : projlab.AktivElem.java
//  @ Date : 2023. 03. 29.
//  @ Author : 
//
//

import java.util.ArrayList;

public abstract class AktivElem extends Mezo {
	private int maxCso;

	public abstract void Frissit() throws Exception;

	/**
	 * A Játékosok ezen a függvényen keresztül csatolhatnak fel elemeket.
	 * @Override
	 */
	@Override
	public Boolean SzomszedFelcsatol(Mezo m) {
		System.out.println("Függvényhívás: " + this + ".SzomszedFelcsatol(" + m + ")");

		// Ha nincs hely, akkor visszautasítunk
		if (GetSzomszedok().size() >= maxCso) {
			return false;
		}

		GetSzomszedok().add(m);
		return true;

	}

	/**
	 * A játékosok által lecsatlakoztatható szomszédokkal tér vissza.
	 * @Override
	 */
	@Override
	public ArrayList<Mezo> GetLeszedhetoSzomszedok() {
		System.out.println("Függvényhívás: " + this + ".GetLeszedhetoSzomszedok()");
		return GetSzomszedok();
	}

	/**
	 * Beállítja hogy az adott aktívelemre hány csövet lehet maximum csatlakoztatni.
	 * @param m - Új érték
	 */
	public void setMaxCso(int m) {
		maxCso = m;
	}
}
