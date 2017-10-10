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

	//	System.out.println(in.getI());

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
		String to = fingers.intInterpreter(in.getI());
		String from = null;
		int compare = 0;
		int shut = 10;

		while (in.getI() >= 2 && in.getI() <= 9) {

			if (pressed == false) {
				fingers.play(fingers.intInterpreter(in.getI()), 0);
				compare = in.getI();
				pressed = true;
			}

			while (pressed == true && compare != in.getI()){

				if (compare != in.getI()) {
					fingers.playFrom(fingers.intInterpreter(compare), fingers.intInterpreter(in.getI()));
					compare = in.getI();
				}

				if (in.getI() == shut && compare == shut){
					break;
				}

			}

			if (pressed == true && shut == in.getI()) {
				break;
			}

		}

		in.setI(0);
		fingers.releaseAll();

	}
}