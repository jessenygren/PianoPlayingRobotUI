package Model;

import java.io.Serializable;

public class Note implements Serializable {

	char key;
	int i;

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


