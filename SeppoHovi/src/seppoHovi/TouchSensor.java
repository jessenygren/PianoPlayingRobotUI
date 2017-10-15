package seppoHovi;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.SampleProvider;

/**
 * @author Ryhm‰ 5
 * @version 1.0
 *
 */
public class TouchSensor {

	Port port;
	SensorMode touch;
	SampleProvider touchSensor;
	private float pressCheck;

	/**
	 * Konstruktorissa luodaan kosketussensori.
	 */
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

	/**
	 * Metodilla "k‰ynnistet‰‰n" sensori, eli "kuunnellaan" sensorin antamaa dataa.
	 */
	public void sensorListener() {
		float[] sample = new float[touchSensor.sampleSize()];
		touchSensor.fetchSample(sample, 0);
		pressCheck = sample[0];

	}

	// Jos "H‰t‰ seis" -nappia painetaan, antaa Shutdown luokkaan tiedon
	// pys‰ytt‰‰ laitteen.

	/**
	 * Metodi havaitsee jos kosketussensori aktivoituu.
	 * @return palauttaa joko true tai false tilanteen mukaan.
	 */
	public boolean emergency() {

		sensorListener();

		if (pressCheck == 1) {

			return true;

		}

		return false;
	}
}


