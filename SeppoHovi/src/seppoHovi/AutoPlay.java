package seppoHovi;

import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class AutoPlay implements Behavior {

	InputThread in;
	private Fingers fingers;
	private SongBank songBank;
	private volatile boolean suppressed = false;

	public AutoPlay(Fingers fingers, SongBank songBank, InputThread in) {
		this.fingers = fingers;
		this.songBank = songBank;
		this.in = in;
	}

	public boolean takeControl() {


		if (in.getI() == 1) {


			return true;
		}

		return false;
	}

	public void suppress() {
		suppressed = true;
	}

	public void action() {
		suppressed = false;
		in.setI(0);

		fingers.releaseAll();


		fingers.autoPlay(songBank.ukkoNooa);

		while (!suppressed)
			Thread.yield();




	}
}
