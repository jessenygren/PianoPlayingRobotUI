package seppoHovi;

import java.io.DataInputStream;
import java.io.IOException;

import lejos.utility.Delay;

public class InputThread extends Thread {

	DataInputStream in;

	private int i = 0;

	public InputThread(DataInputStream in) {
		this.in = in;
	}

	@Override
	public void run() {
		while (true) {
			try {
				System.out.println("Säie tulostaa");
				i = in.readInt();
				System.out.println(i);
				System.out.println("Luku onnistunut");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

}
