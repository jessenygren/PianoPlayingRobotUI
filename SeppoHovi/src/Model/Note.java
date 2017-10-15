package Model;

import java.io.Serializable;

/**
 * @author Ryhm‰ 5
 * @version 1.0
 *
 */
public class Note implements Serializable {
	/**
	 * Muuttuja char key sis‰lt‰‰ halutun soitettavan C-s‰velasteikon soinnun. Korkeampi c on korvattu v:ll‰.
	 */
	char key;
	/**
	 * int i kuvaa viivett‰ kauan robotin pit‰‰ pit‰‰ "sormea" pohjassa. Eli se on soinnun viive.
	 */
	int i;

	public Note(char key, int i) {

		this.key = key;
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
