package seppoHovi;

import java.io.IOException;
import Model.Note;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class AutoPlay implements Behavior {


	InputThread in;
	private ArrayList<Note> list = null;
	private Fingers fingers;
	//private Note n;



	private volatile boolean suppressed = false;

	public AutoPlay(Fingers fingers, ArrayList<Note> list, InputThread in) {
		this.fingers = fingers;
		this.list = list;
		this.in = in;

	}

	public boolean takeControl() {


		if (in.taulukkoLuettu() == true) {


			return true;
		}

		return false;
	}

	public void suppress() {
		suppressed = true;
	}

	public void action() {
		suppressed = false;

		Note uusiNuotti = new Note("A", 0);



		int delay;

		list = in.getList();

	/*	for (Note n : list){
			System.out.println(n.getKey());
			System.out.println(n.getDelay());
		}*/

		ArrayList<Note> uusiArray = new ArrayList<Note>();

		for (int i = 0; i < list.size(); i++){
			Note n = list.get(i);
			Note uusiNote = new Note(n.getKey(), n.getDelay());
			uusiArray.add(uusiNote);
		}

		Note[] taulukko = new Note[uusiArray.size()];

		fingers.autoPlay(uusiArray.toArray(taulukko));

		/*
		for (int i = 0; i < list.size(); i++){
			Note n = list.get(i);
			String to = (String)n.getKey();
			String from;
			if (i == 0){
				fingers.playFrom(null, to);
				from = to;
			}

			if (i > 0){
			System.out.println(to);
			delay = n.getDelay();
			fingers.playFrom(from, to);
			from = to;

		}
*/
		//Note[] returnArray = new Note [list.size()];
		//System.out.println(n.getKey() + " " + n.getDelay());
		//fingers.autoPlay(list.toArray(returnArray));

		//fingers.playFrom(null, uusiNuotti.getKey());

		in.setI(0);
		System.out.println(in.getI());


		while (!suppressed)
			Thread.yield();




	}
}

