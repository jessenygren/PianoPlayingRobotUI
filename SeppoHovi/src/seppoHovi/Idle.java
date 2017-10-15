package seppoHovi;

import lejos.robotics.subsumption.Behavior;

/**
 * @author Ryhmä 5
 * @version 1.0
 *
 */
public class Idle implements Behavior {
	InputThread in;
	private volatile boolean suppressed = false;

	public Idle(InputThread in){
		this.in = in;
	}

	// Toimeton tila saavutetaan jos muuta ei tapahdu.
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
