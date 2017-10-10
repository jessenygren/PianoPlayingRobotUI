package Model;

import java.io.Serializable;

public class Note implements Serializable {

	String string;
	int i;

	public Note(String string, int i) {

		this.string = string;
		this.i = i;
	}

	public Note(){

	}

	public void setString(String string) {
		this.string = string;
	}

	public void setI(int i) {
		this.i = i;
	}



	public String getKey() {
		return string;
	}

	public int getDelay() {
		return i;
	}
}
