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

		/// TÄMÄN LOGIIKKA EI TOIMI, EI NOSTA


		suppressed = false;
		boolean pressed = false;
		String to = fingers.interpreter(in.getI());
		String from = null;

		while (in.getI() != 10 && in.getI() != 0){

			while (in.getI() >= 2 && in.getI() <= 9){

				if (in.getI() == 10){
					to = null;
					break;
				}

				fingers.playFrom(from, to);
				from = to;


			}

			pressed = false;
			break;
		}

	}
}