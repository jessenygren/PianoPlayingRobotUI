package seppoHovi;

import java.io.IOException;
import Model.Note;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

/**
 * @author Ryhmä 5
 * @version 1.0
 *
 */
public class AutoPlay implements Behavior {


	InputThread in;
	private ArrayList<Note> list = null;
	private Fingers fingers;
	//private Note n;
	private TouchSensor touch;



	private volatile boolean suppressed = false;

	public AutoPlay(Fingers fingers, ArrayList<Note> list, InputThread in, TouchSensor touch) {
		this.fingers = fingers;
		this.list = list;
		this.in = in;
		this.touch = touch;

	}

	// Kontrolli saadaan jos saadaan InputThreadilta tieto luetusta ArrayListista.
	public boolean takeControl() {


		if (in.taulukkoLuettu() == true) {


			return true;
		}

		return false;
	}

	public void suppress() {
		suppressed = true;
	}

	// Action soittaa valitun kappaleen. Kappale saadaan tietokoneohjelmalta.
	public void action() {
		suppressed = false;


		list = in.getList();


		while (in.taulukkoLuettu() == true){
		for (Note n : list){

			fingers.play(fingers.charInterpreter(n.getKey()), n.getDelay());


			// HÄTÄSEIS
			if (touch.emergency() == true){
				System.exit(0);
			}

			/// STOP
			if (in.getI() == 0){
				in.setI(0);
				fingers.releaseAll();
				break;
			}

		}

		in.setLuettu(false);

		}

		fingers.releaseAll();
		in.setI(0);
		System.out.println(in.getI());

		while (!suppressed)
			Thread.yield();




	}
}

