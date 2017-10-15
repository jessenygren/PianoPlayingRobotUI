package seppoHovi;

import lejos.robotics.subsumption.Behavior;

/**
 * @author Ryhm‰ 5
 * @version 1.0
 *
 */
public class Shutdown implements Behavior {

	/**
	 * Fingers tyyppinen olio fingers, esitell‰‰n jotta voidaan k‰ytt‰‰ luokan metodeja.
	 */
	private Fingers fingers;
	/**
	 * TouchSensor tyyppinen olio touch esitell‰‰n jotta voidaan k‰ytt‰‰ luokan metodeja.
	 */
	private TouchSensor touch;

	/**
	 * Konstruktorissa luodaan k‰ytett‰v‰t oliot.
	 * @param fingers, parametri saa Fingers tyyppisen olion arvokseen
	 * @param touch, saa Touch tyyppisen olion arvokseen.
	 */
	public Shutdown(Fingers fingers, TouchSensor touch) {
		this.fingers = fingers;
		this.touch = touch;
	}

	private volatile boolean suppressed = false;

	// Aktivoituu jos h‰t‰painiketta painetaan .
	public boolean takeControl() {

		return touch.emergency();
	}

	public void suppress() {
		suppressed = true;
	}

	// Pys‰ytt‰‰ liikkeen.
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

