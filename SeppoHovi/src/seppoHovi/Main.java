package seppoHovi;


import Model.Note;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

/**
 * @author Ryhmä 5
 * @version 1.0
 *
 */
public class Main {


	/**
	 * Robotin main-metodi.
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			@SuppressWarnings("resource")

			// Serverisoketin luonti
			ServerSocket serv = new ServerSocket(1111);
			// Soketin hyväksyminen
			Socket s = serv.accept();
			//ObjectInputStreamin luonti.
			ObjectInputStream in = new ObjectInputStream(s.getInputStream());

			// Luodaan uusi ArrayList<Note>
			ArrayList<Note> list = new ArrayList();
			// Luodaan Fingers tyyppinen olio
			Fingers fingers = new Fingers();
			// Luodaan IRSensor olio
			IRSensor ir = new IRSensor();
			// Luodaan TouchSensor olio
			TouchSensor touch = new TouchSensor();
			// Luodaan InputThread olio
			InputThread input = new InputThread(in);


			// Luodaan käyttäytymisluokat ja priorisoidaan ne taulukossa.
			Behavior idle = new Idle(input);
			Behavior autoPlay = new AutoPlay(fingers, list, input, touch);
			Behavior userPlay = new UserPlay(fingers, input, touch);
			Behavior airplay = new Airplay(fingers, ir, input, touch);
			Behavior emergencyStop = new Shutdown(fingers, touch);
			Behavior[] bArray = { idle,  autoPlay,userPlay, airplay,  emergencyStop };

			// Arbitraattorin luonti
			Arbitrator arbitrator = new Arbitrator(bArray);

			// käynnuistetään säie, jolla vastaanotetaan dataa
			input.start();

			// Käynnistetään arbitraattori.
			arbitrator.go();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
