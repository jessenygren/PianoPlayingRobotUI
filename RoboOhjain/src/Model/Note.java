package Model;

import java.io.Serializable;

/**
 * Luokka käsittelee nuottien tietoja
 * @author tiimi 5
 *
 */
public class Note implements Serializable {

	/**
	 * Muuttujaan tallennetaan painettu nuotti (C, D jne.)
	 */
	char key;
	/**
	 * Muuttujaan tallennetaan painalluksen kesto.
	 */
	int i;

	/**
	 * Yksi nuotti koostuu kirjaimesta ja painalluksen kestosta.
	 * @param kirjain
	 * @param i
	 */
	public Note(char kirjain, int i) {

		this.key = kirjain;
		this.i = i;
	}

	public Note(){

	}

	public void setI(int i) {
		this.i = i;
	}

	public char getKey() {
		return key;
	}

	public void setKey(char kirjain) {
		this.key = kirjain;
	}

	public int getDelay() {
		return i;
	}
}


