package seppoHovi;

import lejos.robotics.subsumption.Behavior;

public class Idle implements Behavior {

	private volatile boolean suppressed = false;

	public boolean takeControl() {
		return true;
	}

	public void suppress() {
		suppressed = true;
	}

	public void action() {
		suppressed = false;
		while (!suppressed)
			Thread.yield();
	}
}
