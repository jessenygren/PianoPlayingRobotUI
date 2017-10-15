package seppoHovi;

import Model.Note;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

import lejos.robotics.RegulatedMotor;

import lejos.utility.Delay;

/**
 * @author Ryhm‰ 5
 * @version 1.0
 *
 */
public class Fingers {

	/**
	 * Integer muuttujat left ja right ovat Final tyyppisia ja kuvaavat moottorien k‰‰nnˆksen astelukua.
	 */
	private final int left = 40;
	private final int right = -40;

	RegulatedMotor a;
	RegulatedMotor b;
	RegulatedMotor c;
	RegulatedMotor d;

	/**
	 * String tyyppiset muuttujat from ja to ovat aloitusarvoltaan null. Muuttuja from:lla kuvataan mist‰ koskettimesta soitetaan muuttujan to koskettimeen.
	 */
	private String from = null;
	private String to = null;

	/**
	 *	Konstruktorissa luodaan oliot nelj‰lle moottorille. Moottorit nimetty a, b, c ja d nimisiksi.
	 */
	public Fingers() {
		try {
			a = new EV3LargeRegulatedMotor(MotorPort.A);
			b = new EV3LargeRegulatedMotor(MotorPort.B);
			c = new EV3LargeRegulatedMotor(MotorPort.C);
			d = new EV3LargeRegulatedMotor(MotorPort.D);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Meotodilla aloitetaan moottorien synkronointi.
	 */
	public void startSync() {

		a.synchronizeWith(new RegulatedMotor[] { b, c, d });

		a.startSynchronization();

	}





	/**
	 * Metodi p‰‰tt‰‰ moottorien synkronoinnin.
	 */
	public void endSync() {
		a.endSynchronization();
		a.waitComplete();
		b.waitComplete();
		c.waitComplete();
		d.waitComplete();

	}

	/**
	 * Metodi palauttaa kaikki sormet vakio aloitusasentoonsa.
	 */
	public void releaseAll() {

		a.rotateTo(0);
		b.rotateTo(0);
		c.rotateTo(0);
		d.rotateTo(0);

	}


	/**
	 * Metodilla voidaan liikuttaa robotin sormia mahdollisimman tehokkaasti. Metodi on tarkoitettu k‰ytett‰v‰ksi automaatisoidun soiton tueksi.
	 * @param from, parametri kuvaa mist‰ sormesta (koskettimesta) liikutaan.
	 * @param to, parametri kuvaa mihin sormeen (koskettimeen) liikutaan.
	 */
	public void autoplayFrom(String from, String to){
		a.setSpeed(1000);
		b.setSpeed(1000);
		c.setSpeed(1000);
		d.setSpeed(1000);

		if (from == null) {
			if (to == "C") {
				startSync();
				a.rotateTo(left);
				endSync();
			} else if (to == "D") {
				startSync();
				a.rotateTo(right);
				endSync();
			} else if (to == "E") {
				startSync();
				b.rotateTo(left);
				endSync();
			} else if (to == "F") {
				startSync();
				b.rotateTo(right);
				endSync();
			} else if (to == "G") {
				startSync();
				c.rotateTo(left);
				endSync();
			} else if (to == "A") {
				startSync();
				c.rotateTo(right);
				endSync();
			} else if (to == "H") {
				startSync();
				d.rotateTo(left);
				endSync();
			} else if (to == "C2") {
				startSync();
				d.rotateTo(right);
				endSync();
			}
		}

		/// C JA D soitto muihin sointuihin
		if (from == "C" || from == "D") {

			if (from == "C" && to == "D") {
				startSync();
				a.rotateTo(right);
				endSync();
			}

			if (from == "D" && to == "C") {
				startSync();
				a.rotateTo(left);
				endSync();
			}

			if (from == "C" && to == "C") {
				a.rotateTo(0);
				a.rotateTo(left);

			}

			if (from == "D" && to == "D") {
				a.rotateTo(0);
				a.rotateTo(right);

			}

			if (to == "E") {
				startSync();
				a.rotateTo(0);
				b.rotateTo(left);
				endSync();
			}
			if (to == "F") {
				startSync();
				a.rotateTo(0);
				b.rotateTo(right);
				endSync();
			}
			if (to == "G") {
				startSync();
				a.rotateTo(0);
				c.rotateTo(left);
				endSync();
			}
			if (to == "A") {
				startSync();
				a.rotateTo(0);
				c.rotateTo(right);
				endSync();
			}
			if (to == "H") {
				startSync();
				a.rotateTo(0);
				d.rotateTo(left);
				endSync();
			}
			if (to == "C2") {
				startSync();
				a.rotateTo(0);
				d.rotateTo(right);
				endSync();
			}
			if (to == null) {
				a.rotateTo(0);
			}

		}

		if (from == "E" || from == "F") {

			if (from == "E" && to == "F") {
				b.rotateTo(right);
			}

			if (from == "F" && to == "E") {
				b.rotateTo(left);
			}

			if (from == "E" && to == "E") {
				b.rotateTo(0);
				b.rotateTo(left);
			}

			if (from == "F" && to == "F") {
				b.rotateTo(0);
				b.rotateTo(right);
			}

			if (to == "C") {
				startSync();
				b.rotateTo(0);
				a.rotateTo(left);
				endSync();
			}
			if (to == "D") {
				startSync();
				b.rotateTo(0);
				a.rotateTo(right);
				endSync();
			}
			if (to == "G") {
				startSync();
				b.rotateTo(0);
				c.rotateTo(left);
				endSync();
			}
			if (to == "A") {
				startSync();
				b.rotateTo(0);
				c.rotateTo(right);
				endSync();
			}
			if (to == "H") {
				startSync();
				b.rotateTo(0);
				d.rotateTo(left);
				endSync();
			}
			if (to == "C2") {
				startSync();
				b.rotateTo(0);
				d.rotateTo(right);
				endSync();
			}

		}

		if (from == "G" || from == "A") {

			if (from == "G" && to == "A") {
				c.rotateTo(right);
			}

			if (from == "A" && to == "G") {
				c.rotateTo(left);
			}

			if (from == "G" && to == "G") {
				c.rotateTo(0);
				c.rotateTo(left);
			}

			if (from == "A" && to == "A") {
				c.rotateTo(0);
				c.rotateTo(right);
			}

			if (to == "E") {
				startSync();
				c.rotateTo(0);
				b.rotateTo(left);
				endSync();
			}
			if (to == "F") {
				startSync();
				c.rotateTo(0);
				b.rotateTo(right);
				endSync();
			}
			if (to == "C") {
				startSync();
				c.rotateTo(0);
				a.rotateTo(left);
				endSync();
			}
			if (to == "D") {
				startSync();
				c.rotateTo(0);
				a.rotateTo(right);
				endSync();
			}
			if (to == "H") {
				startSync();
				c.rotateTo(0);
				d.rotateTo(left);
				endSync();
			}
			if (to == "C2") {
				startSync();
				c.rotateTo(0);
				d.rotateTo(right);
				endSync();
			}

		}

		if (from == "H" || from == "C2") {

			if (from == "H" && to == "C2") {
				d.rotateTo(right);
			}

			if (from == "C2" && to == "H") {
				d.rotateTo(left);
			}

			if (from == "H" && to == "H") {
				d.rotateTo(0);
				d.rotateTo(left);
			}

			if (from == "C2" && to == "C2") {
				d.rotateTo(0);
				d.rotateTo(right);
			}

			if (to == "E") {
				startSync();
				d.rotateTo(0);
				b.rotateTo(left);
				endSync();
			}
			if (to == "F") {
				startSync();
				d.rotateTo(0);
				b.rotateTo(right);
				endSync();
			}
			if (to == "G") {
				startSync();
				d.rotateTo(0);
				c.rotateTo(left);
				endSync();
			}
			if (to == "A") {
				startSync();
				d.rotateTo(0);
				c.rotateTo(right);
				endSync();
			}
			if (to == "C") {
				startSync();
				d.rotateTo(0);
				a.rotateTo(left);
				endSync();
			}
			if (to == "D") {
				startSync();
				d.rotateTo(0);
				a.rotateTo(right);
				endSync();
			}

		}

	}

	/**
	 * Metodilla voidaan liikuttaa robotin sormia mahdollisimman tehokkaasti. Metodi on tarkoitettu k‰ytett‰v‰ksi manuaalisen soiton ja Airplayn tueksi.
	 * @param from, parametri kuvaa mist‰ sormesta (koskettimesta) liikutaan.
	 * @param to, parametri kuvaa mihin sormeen (koskettimeen) liikutaan.
	 */
	public void playFrom(String from, String to) {

		a.setSpeed(1000);
		b.setSpeed(1000);
		c.setSpeed(1000);
		d.setSpeed(1000);

		if (from == null) {
			if (to == "C") {
				startSync();
				a.rotateTo(left);
				endSync();
			} else if (to == "D") {
				startSync();
				a.rotateTo(right);
				endSync();
			} else if (to == "E") {
				startSync();
				b.rotateTo(left);
				endSync();
			} else if (to == "F") {
				startSync();
				b.rotateTo(right);
				endSync();
			} else if (to == "G") {
				startSync();
				c.rotateTo(left);
				endSync();
			} else if (to == "A") {
				startSync();
				c.rotateTo(right);
				endSync();
			} else if (to == "H") {
				startSync();
				d.rotateTo(left);
				endSync();
			} else if (to == "C2") {
				startSync();
				d.rotateTo(right);
				endSync();
			}
		}

		/// C JA D soitto muihin sointuihin
		if (from == "C" || from == "D") {

			if (from == "C" && to == "D") {
				startSync();
				a.rotateTo(right);
				endSync();
			}

			if (from == "D" && to == "C") {
				startSync();
				a.rotateTo(left);
				endSync();
			}

			if (from == "C" && to == "C") {

				a.rotateTo(left);

			}

			if (from == "D" && to == "D") {

				a.rotateTo(right);

			}

			if (to == "E") {
				startSync();
				a.rotateTo(0);
				b.rotateTo(left);
				endSync();
			}
			if (to == "F") {
				startSync();
				a.rotateTo(0);
				b.rotateTo(right);
				endSync();
			}
			if (to == "G") {
				startSync();
				a.rotateTo(0);
				c.rotateTo(left);
				endSync();
			}
			if (to == "A") {
				startSync();
				a.rotateTo(0);
				c.rotateTo(right);
				endSync();
			}
			if (to == "H") {
				startSync();
				a.rotateTo(0);
				d.rotateTo(left);
				endSync();
			}
			if (to == "C2") {
				startSync();
				a.rotateTo(0);
				d.rotateTo(right);
				endSync();
			}
			if (to == null) {
				a.rotateTo(0);
			}

		}

		if (from == "E" || from == "F") {

			if (from == "E" && to == "F") {
				b.rotateTo(right);
			}

			if (from == "F" && to == "E") {
				b.rotateTo(left);
			}

			if (from == "E" && to == "E") {

				b.rotateTo(left);
			}

			if (from == "F" && to == "F") {

				b.rotateTo(right);
			}

			if (to == "C") {
				startSync();
				b.rotateTo(0);
				a.rotateTo(left);
				endSync();
			}
			if (to == "D") {
				startSync();
				b.rotateTo(0);
				a.rotateTo(right);
				endSync();
			}
			if (to == "G") {
				startSync();
				b.rotateTo(0);
				c.rotateTo(left);
				endSync();
			}
			if (to == "A") {
				startSync();
				b.rotateTo(0);
				c.rotateTo(right);
				endSync();
			}
			if (to == "H") {
				startSync();
				b.rotateTo(0);
				d.rotateTo(left);
				endSync();
			}
			if (to == "C2") {
				startSync();
				b.rotateTo(0);
				d.rotateTo(right);
				endSync();
			}

		}

		if (from == "G" || from == "A") {

			if (from == "G" && to == "A") {
				c.rotateTo(right);
			}

			if (from == "A" && to == "G") {
				c.rotateTo(left);
			}

			if (from == "G" && to == "G") {

				c.rotateTo(left);
			}

			if (from == "A" && to == "A") {

				c.rotateTo(right);
			}

			if (to == "E") {
				startSync();
				c.rotateTo(0);
				b.rotateTo(left);
				endSync();
			}
			if (to == "F") {
				startSync();
				c.rotateTo(0);
				b.rotateTo(right);
				endSync();
			}
			if (to == "C") {
				startSync();
				c.rotateTo(0);
				a.rotateTo(left);
				endSync();
			}
			if (to == "D") {
				startSync();
				c.rotateTo(0);
				a.rotateTo(right);
				endSync();
			}
			if (to == "H") {
				startSync();
				c.rotateTo(0);
				d.rotateTo(left);
				endSync();
			}
			if (to == "C2") {
				startSync();
				c.rotateTo(0);
				d.rotateTo(right);
				endSync();
			}

		}

		if (from == "H" || from == "C2") {

			if (from == "H" && to == "C2") {
				d.rotateTo(right);
			}

			if (from == "C2" && to == "H") {
				d.rotateTo(left);
			}

			if (from == "H" && to == "H") {

				d.rotateTo(left);
			}

			if (from == "C2" && to == "C2") {

				d.rotateTo(right);
			}

			if (to == "E") {
				startSync();
				d.rotateTo(0);
				b.rotateTo(left);
				endSync();
			}
			if (to == "F") {
				startSync();
				d.rotateTo(0);
				b.rotateTo(right);
				endSync();
			}
			if (to == "G") {
				startSync();
				d.rotateTo(0);
				c.rotateTo(left);
				endSync();
			}
			if (to == "A") {
				startSync();
				d.rotateTo(0);
				c.rotateTo(right);
				endSync();
			}
			if (to == "C") {
				startSync();
				d.rotateTo(0);
				a.rotateTo(left);
				endSync();
			}
			if (to == "D") {
				startSync();
				d.rotateTo(0);
				a.rotateTo(right);
				endSync();
			}

		}

	}

	/**
	 * Automaattisen soiton mahdollistava metodi.
	 * @param key, parametriksi oliosta saatu arvo joka vastaa haluttua kosketinta.
	 * @param delay, parametriksi oliosta saatu viivett‰ kuvaava arvo.
	 */
	public void play(String key, int delay) {

		from = to;
		to = key;

		if (from != to) {
			Delay.msDelay(350);
		}

		autoplayFrom(from, to);

		Delay.msDelay(delay);

	}

	/**
	 *
	 * Manuaalisen soiton mahdollistava metodi.
	 * @param key, arvoksi soitettava kosketin.
	 * @param delay, arvoksi viive kuinka kauan kosketinta pidet‰‰n pohjassa.
	 */
	public void userPlay(String key, int delay) {

		from = to;
		to = key;

		if (from != to) {
			// ennen delay 350
			Delay.msDelay(0);
		}

		playFrom(from, to);

		Delay.msDelay(delay);

	}

	/**
	 * Metodi joka muuttaa IR-sensorilta saadun datan vastaamaan sopivaa kosketinta.
	 * @param distance, parametriksi sensorilta saatu float tyyppinen et‰isyystieto.
	 */
	public void airPlay(float distance) {

		if (distance > 5 && distance <= 11.25) {

			a.rotateTo(40);
		} else if (distance > 11.25 && distance <= 17.50) {

			a.rotateTo(-40);
		} else if (distance > 17.50 && distance <= 23.75) {

			b.rotateTo(40);
		} else if (distance > 23.75 && distance <= 29.00) {

			b.rotateTo(-40);
		} else if (distance > 29.00 && distance <= 35.25) {

			c.rotateTo(40);
		} else if (distance > 35.25 && distance <= 41.50) {

			c.rotateTo(-40);
		} else if (distance > 41.50 && distance <= 47.75) {

			d.rotateTo(40);
		} else if (distance > 47.75 && distance <= 55) {

			d.rotateTo(-40);
		} else {
			releaseAll();
		}

	}

	/**
	 * Metodi k‰‰nt‰‰ char tyyppisen muuttujan vastaamaan String-tyyppist‰ muuttujaa.
	 * @param c, parametriksi Note-olion char tyyppinen key muuttuja.
	 * @return Palauttaa sopivan String-muuttujan.
	 */
	public String charInterpreter(char c) {
		String key = null;
		if (c == 'c') {
			key = "C";
		}
		if (c == 'd') {
			key = "D";
		}
		if (c == 'e') {
			key = "E";
		}
		if (c == 'f') {
			key = "F";
		}
		if (c == 'g') {
			key = "G";
		}
		if (c == 'a') {
			key = "A";
		}
		if (c == 'h') {
			key = "H";
		}
		if (c == 'v') {
			key = "C2";
		}
		return key;
	}


	/**
	 * Metodi k‰‰nt‰‰ integer-muuttujan arvon vastaamaan jotain tietty‰ String-muuttujaa. String-muuttujat kuvaavat soitettavia koskettimia.
	 * @param i, parametriksi arvo v‰lilt‰ 2-9, vastaa soitettavaa kosketinta.
	 * @return Palauttaa sopivan String-muuttujan, joka vastaa pianon C-s‰velasteikon kirjainta.
	 */
	public String intInterpreter(int i) {
		String key = null;

		if (i == 2) {
			key = "C";
		}
		if (i == 3) {
			key = "D";
		}
		if (i == 4) {
			key = "E";
		}
		if (i == 5) {
			key = "F";
		}
		if (i == 6) {
			key = "G";
		}
		if (i == 7) {
			key = "A";
		}
		if (i == 8) {
			key = "H";
		}
		if (i == 9) {
			key = "C2";
		}
		return key;
	}

	/**
	 * Metodi k‰‰nt‰‰ float tyyppisen tiedon integer tyyppiseksi. Muuttaa IR-sensorin dataa vastaavaan muotoon, kuin tietokoneohjelmalta l‰hetetty data, joka saadaan n‰pp‰imistˆlt‰.
	 * @param distance, parametri sis‰lt‰‰ IR-sensorilta saadun datan.
	 * @return Palautuu integer tyyppisen‰ datana arvona 0, 2-10;
	 */
	public int irInterpreter(float distance) {
		int key = 0;

		if (distance >= 5 && distance <= 11.25) {
			key = 2;
		}
		if (distance > 11.25 && distance <= 17.50) {
			key = 3;
		}
		if (distance > 17.50 && distance <= 23.75) {
			key = 4;
		}
		if (distance > 23.75 && distance <= 29.00) {
			key = 5;
		}
		if (distance > 29.00 && distance <= 35.25) {
			key = 6;
		}
		if (distance > 35.25 && distance <= 41.50) {
			key = 7;
		}
		if (distance > 41.50 && distance <= 47.75) {
			key = 8;
		}
		if (distance > 47.75 && distance <= 55) {
			key = 9;
		}
		if (distance < 5 || distance > 55) {
			key = 10;
		}
		return key;
	}

	/**
	 * Metodi pys‰ytt‰‰ moottorit
	 */
	public void stop() {
		startSync();

		a.stop();
		b.stop();
		c.stop();
		d.stop();

		endSync();
	}

}