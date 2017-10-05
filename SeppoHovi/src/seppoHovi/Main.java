package seppoHovi;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class Main {


	public static void main(String[] args) {

		try {
			@SuppressWarnings("resource")
			ServerSocket serv = new ServerSocket(1111);
			Socket s = serv.accept();
			DataInputStream in = new DataInputStream(s.getInputStream());
		//	DataOutputStream out = new DataOutputStream(s.getOutputStream());


			SongBank songBank = new SongBank();
			Fingers fingers = new Fingers();
			IRSensor ir = new IRSensor();
			TouchSensor touch = new TouchSensor();
			InputThread input = new InputThread(in);

			Behavior idle = new Idle();
			Behavior autoPlay = new AutoPlay(fingers, songBank, input);
			Behavior userPlay = new UserPlay(fingers, input);
			Behavior airplay = new Airplay(fingers, ir);
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
