package seppoHovi;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import Model.Note;

import lejos.utility.Delay;

public class InputThread extends Thread {

	ObjectInputStream in;

	private int i = 0;
	private ArrayList<Note> list;
	private Note n;
	boolean luettu =  false;


	public InputThread(ObjectInputStream in) {
		this.in = in;
	}

	@Override
	public void run() {
		while (true) {
			try {
			//	System.out.println("Säie tulostaa");
				i = ((Integer)in.readObject()).intValue();
				System.out.println(i);

				luettu = false;
				if (i == 1){

				//	list = (ArrayList<Note>)in.readObject();
					list = (ArrayList<Note>) in.readObject();

					luettu = true;
				}

			//	System.out.println(i);
			//	System.out.println("Luku onnistunut");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean taulukkoLuettu(){
		return luettu;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public ArrayList<Note> getList(){
		return list;
	}

	public Note getNote(){
		return n;
	}


}
