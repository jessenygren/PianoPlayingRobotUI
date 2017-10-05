package seppoHovi;

import lejos.robotics.subsumption.Behavior;

public class UserPlay implements Behavior {

	InputThread in;
	private Fingers fingers;
	private volatile boolean suppressed = false;

	public UserPlay(Fingers fingers, InputThread in) {
		this.fingers = fingers;
		this.in = in;
	}

	public boolean takeControl() {

		System.out.println(in.getI());


		if (in.getI() >= 2 && in.getI() <= 9) {

			return true;
		}

		return false;
	}

	public void suppress() {
		suppressed = true;
	}

	public void action() {
		suppressed = false;

		String key;
		key = fingers.interpreter(in.getI());

		in.setI(0);

		fingers.play(key, 0);
		fingers.release(key);

	}
}