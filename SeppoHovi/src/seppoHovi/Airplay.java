package seppoHovi;

import lejos.robotics.subsumption.Behavior;

public class Airplay implements Behavior {

	private Fingers fingers;
	private IRSensor ir;
	InputThread in;

	private volatile boolean suppressed = false;

	public Airplay(Fingers fingers, IRSensor ir, InputThread in) {
		this.fingers = fingers;
		this.ir = ir;
		this.in = in;
	}

	public boolean takeControl() {
		ir.irSensorOn();

		if (ir.getDistance() <= 55) {
			return true;
		}
		return false;
	}

	public void suppress() {
		suppressed = true;
	}

	public void action() {
		suppressed = false;
		boolean held = false;
		String to = fingers.intInterpreter(in.getI());
		String from = null;
		int compare = 0;
		int shut = 10;

		while (fingers.irInterpreter(ir.getDistance()) >= 2 && fingers.irInterpreter(ir.getDistance()) <= 9) {

			ir.irSensorOn();

			if (held == false) {
				fingers.play(fingers.intInterpreter(fingers.irInterpreter(ir.getDistance())), 0);
				compare = fingers.irInterpreter(ir.getDistance());
				held = true;
			}

			while (held == true && compare != fingers.irInterpreter(ir.getDistance())) {

				ir.irSensorOn();

				if (compare != fingers.irInterpreter(ir.getDistance())) {
					fingers.playFrom(fingers.intInterpreter(compare),
							fingers.intInterpreter(fingers.irInterpreter(ir.getDistance())));
					compare = fingers.irInterpreter(ir.getDistance());
				}

				if (fingers.irInterpreter(ir.getDistance()) == shut && compare == shut) {
					break;
				}

			}

			if (held == true && shut == fingers.irInterpreter(ir.getDistance())) {
				break;
			}

		}

		in.setI(0);
		fingers.releaseAll();

	}
}
