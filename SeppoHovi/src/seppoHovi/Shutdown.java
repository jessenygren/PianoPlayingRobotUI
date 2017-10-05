package seppoHovi;

import lejos.robotics.subsumption.Behavior;

public class Shutdown implements Behavior {

	private Fingers fingers;
	private TouchSensor touch;

	public Shutdown(Fingers fingers, TouchSensor touch) {
		this.fingers = fingers;
		this.touch = touch;
	}

	private volatile boolean suppressed = false;

	public boolean takeControl() {

		return touch.emergency();
	}

	public void suppress() {
		suppressed = true;
	}

	public void action() {
		suppressed = false;
		System.out.println("");
		System.out.println("");
		System.out.println("!!!!!!!!!!!!!!!!!");
		System.out.println("!!!!EMERGENCY!!!!");
		System.out.println("!!!!SHUTDOWN!!!!!");
		System.out.println("!!!!!!!!!!!!!!!!!");
		System.out.println("");
		while (true) {
			fingers.stop();
		}
	}
}

