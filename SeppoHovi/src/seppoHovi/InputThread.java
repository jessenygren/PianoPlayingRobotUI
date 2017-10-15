package seppoHovi;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import Model.Note;

import lejos.utility.Delay;

/**
 * @author Ryhmä 5
 * @version 1.0
 *
 */
public class InputThread extends Thread {


	ObjectInputStream in;

	/**
	 * muuttuja int i:hin otetaan vastaan tietokoneelta saatuja komentoja.
	 */
	private int i = 0;
	/**
	 * ArrayList<Note> list:iin tallennetaan tietokoneelta saatuja ArrayList<Note> listoja.
	 */
	private ArrayList<Note> list;
	private Note n;
	/**
	 * boolean luettu:lla varmistetaan, että tietokoneelta on saatu vastaanotettua ArrayList
	 */
	boolean luettu =  false;


	/**
	 * Konstruktorissa ObjectInputStream
	 * @param in, saa parametrikseen ObjectInputStreamin
	 */
	public InputThread(ObjectInputStream in) {
		this.in = in;
	}

	//Säie ottaa vastaan dataa tietokoneelta.
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

	/**
	 * Metodi palauttaa booleanin luettu arvon.
	 * @return
	 */
	public boolean taulukkoLuettu(){
		return luettu;
	}

	public boolean setLuettu(boolean uusi){
		this.luettu = uusi;
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
