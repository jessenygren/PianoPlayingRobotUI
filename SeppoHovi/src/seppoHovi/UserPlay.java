package seppoHovi;

import lejos.robotics.subsumption.Behavior;

/**
 * @author Ryhmä 5
 * @version 1.0
 *
 */
public class UserPlay implements Behavior {

	InputThread in;
	private Fingers fingers;
	private TouchSensor touch;
	private volatile boolean suppressed = false;

	public UserPlay(Fingers fingers, InputThread in, TouchSensor touch) {
		this.fingers = fingers;
		this.in = in;
		this.touch = touch;
	}

	// Jos saa InputThreadin i saa arvokseen 2-9 aktivoituu.
	public boolean takeControl() {


		if (in.getI() >= 2 && in.getI() <= 9) {
			return true;
		}

		return false;
	}

	public void suppress() {
		suppressed = true;
	}

	// Action toistaa painetun painikkeen.
	public void action() {


		suppressed = false;
		boolean pressed = false;
		String to = fingers.intInterpreter(in.getI());
		String from = null;
		int compare = 0;
		int shut = 10;

		while (in.getI() >= 2 && in.getI() <= 9) {

			if (pressed == false) {
				fingers.userPlay(fingers.intInterpreter(in.getI()), 0);
				compare = in.getI();
				pressed = true;
			}

			while (pressed == true && compare != in.getI()){

				if (compare != in.getI()) {
					fingers.playFrom(fingers.intInterpreter(compare), fingers.intInterpreter(in.getI()));
					compare = in.getI();
				}

				if (in.getI() == shut && compare == shut){
					in.setI(0);
					break;
				}

			}

			if (pressed == true && shut == in.getI()) {
				in.setI(0);
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