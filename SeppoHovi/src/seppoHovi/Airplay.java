package seppoHovi;

import lejos.robotics.subsumption.Behavior;

public class Airplay implements Behavior {

	private Fingers fingers;
	private IRSensor ir;

	private String key;

	private volatile boolean suppressed = false;

	public Airplay(Fingers fingers, IRSensor ir){
		this.fingers = fingers;
		this.ir = ir;
	}


	public boolean takeControl() {
		ir.irSensorOn();

		if (ir.getDistance() <= 50) {
			return true;
		}
		return false;
	}

	public void suppress() {
		suppressed = true;
	}

	public void action() {
		suppressed = false;

		fingers.airPlay(ir.getDistance());


	}

}
