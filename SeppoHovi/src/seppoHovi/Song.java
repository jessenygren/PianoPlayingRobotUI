package seppoHovi;

public class Song {

	String string;
	int i;

	public Song(String string, int i) {

		this.string = string;
		this.i = i;
	}

	public String getKey() {
		return string;
	}

	public int getDelay() {
		return i;
	}
}
