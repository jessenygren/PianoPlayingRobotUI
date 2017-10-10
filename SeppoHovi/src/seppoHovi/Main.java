package seppoHovi;

import java.io.DataInputStream;
import Model.Note;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class Main {


	public static void main(String[] args) {

		try {
			@SuppressWarnings("resource")
			ServerSocket serv = new ServerSocket(1111);
			Socket s = serv.accept();
			//DataInputStream in = new DataInputStream(s.getInputStream());
			ObjectInputStream in = new ObjectInputStream(s.getInputStream());
		//	DataOutputStream out = new DataOutputStream(s.getOutputStream());

			ArrayList<Note> list = new ArrayList();
			Fingers fingers = new Fingers();
			IRSensor ir = new IRSensor();
			TouchSensor touch = new TouchSensor();
			InputThread input = new InputThread(in);
			Note n = new Note(null, 0);


			Behavior idle = new Idle();
			Behavior autoPlay = new AutoPlay(fingers, list, input);
			Behavior userPlay = new UserPlay(fingers, input);
			Behavior airplay = new Airplay(fingers, ir, input);
			Behavior emergencyStop = new Shutdown(fingers, touch);
			Behavior[] bArray = { idle,airplay, autoPlay, userPlay, emergencyStop };

			Arbitrator arbitrator = new Arbitrator(bArray);

			input.start();

			arbitrator.go();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
