package seppoHovi;

import lejos.robotics.subsumption.Behavior;

/**
 * @author Ryhmä 5
 * @version 1.0
 *
 */
public class Airplay implements Behavior {

	private Fingers fingers;
	private IRSensor ir;
	private TouchSensor touch;
	InputThread in;

	private volatile boolean suppressed = false;

	public Airplay(Fingers fingers, IRSensor ir, InputThread in, TouchSensor touch) {
		this.fingers = fingers;
		this.ir = ir;
		this.in = in;
		this.touch = touch;
	}

	// Kontrolli saadaan jos käden vie sopivalle etäisyydelle ir-sensorista.
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

	//	Actionissa voidaan soittaa käyttämällä ir-sensoria.
	public void action() {
		suppressed = false;
		boolean held = false;
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

			if (touch.emergency() == true){
				System.exit(0);
			}
		}

		in.setI(0);
		fingers.releaseAll();

	}
}
