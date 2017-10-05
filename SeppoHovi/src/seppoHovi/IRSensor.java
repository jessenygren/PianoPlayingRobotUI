package seppoHovi;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;

public class IRSensor {
	Port port;
	SensorModes irSensor;
	SampleProvider irDistance;

	// MUUTTUJAT
	private float distance = 0;

	private boolean wallCheck = false;

	// KONSTRUKTORI
	public IRSensor() {
		try {
			port = LocalEV3.get().getPort("S4");
			irSensor = new EV3IRSensor(port);
			irDistance = ((EV3IRSensor) irSensor).getDistanceMode();
		} catch (Exception e) {
			System.out.println("");
			System.out.println("");
			System.out.println("!!!!!!!!!!!!!!!!!");
			System.out.println("!!!!IR-SENSOR!!!!");
			System.out.println("!!!!!FAILURE!!!!!");
			System.out.println("!!!!!!!!!!!!!!!!!");
			System.out.println("");
		}

	}

	public float getDistance() {
		return distance;
	}


	public void irSensorOn() {
		float[] irSample = new float[irDistance.sampleSize()];
		irDistance.fetchSample(irSample, 0);
		// irDistance.fetchSample(irSample2, 0);

		// System.out.println(irSample[0]);

		distance = irSample[0];
		// dropDistance = irSample2[0];
	}

	public boolean gestureChecker() {

		if (distance <= 50) {
			wallCheck = true;
		}
		return wallCheck;
	}

}
