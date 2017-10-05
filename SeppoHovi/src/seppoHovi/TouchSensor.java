package seppoHovi;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.SampleProvider;

public class TouchSensor {

	Port port;
	SensorMode touch;
	SampleProvider touchSensor;
	private float pressCheck;

	public TouchSensor() {
		try {
			port = LocalEV3.get().getPort("S3");
			touch = new EV3TouchSensor(port);
			touchSensor = ((EV3TouchSensor) touch).getTouchMode();

		} catch (Exception e) {
			System.out.println("");
			System.out.println("");
			System.out.println("!!!!!!!!!!!!!!!!!");
			System.out.println("!!!!TOUCH-SNSR!!!");
			System.out.println("!!!!!FAILURE!!!!!");
			System.out.println("!!!!!!!!!!!!!!!!!");
			System.out.println("");
		}

	}

	public void sensorListener() {
		float[] sample = new float[touchSensor.sampleSize()];
		touchSensor.fetchSample(sample, 0);
		pressCheck = sample[0];

	}

	// Jos "H‰t‰ seis" -nappia painetaan, antaa Shutdown luokkaan tiedon
	// pys‰ytt‰‰ laitteen.

	public boolean emergency() {

		sensorListener();

		if (pressCheck == 1) {

			return true;

		}

		return false;
	}
}


