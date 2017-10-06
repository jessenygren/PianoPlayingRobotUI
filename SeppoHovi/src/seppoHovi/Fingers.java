package seppoHovi;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class Fingers {

	final int left = 40;
	final int right = -40;

	RegulatedMotor a;
	RegulatedMotor b;
	RegulatedMotor c;
	RegulatedMotor d;


	private String from = null;
	private String to = null;

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

	private int powerLevel = 0; // moottorin nopeus
	private int travel = 0; // millimetrit
	private int delay = 0; // viive
	private String key;

	private int position = 0;

	public void startSync() {

		a.synchronizeWith(new RegulatedMotor[] { b, c, d });

		a.startSynchronization();

	}

	// päättää synkronoinnin

	public void endSync() {
		a.endSynchronization();
		a.waitComplete();
		b.waitComplete();
		c.waitComplete();
		d.waitComplete();

	}

	public void all(int powerLevel, int travel, int delay) {

		a.setSpeed(powerLevel);

		b.setSpeed(powerLevel);

		c.setSpeed(powerLevel);

		d.setSpeed(powerLevel);
		startSync();

		a.rotate(travel, true);
		b.rotate(travel, true);
		c.rotate(travel, true);
		d.rotate(travel, true);

		endSync();
	}

	// TEsti

	public void playAll() {
		a.rotate(20);
		a.rotate(-40);

		startSync();
		a.rotate(20);
		b.rotate(20);
		endSync();

		b.rotate(-40);

		startSync();
		b.rotate(20);
		c.rotate(20);
		endSync();

		c.rotate(-40);

		startSync();
		c.rotate(20);
		d.rotate(20);
		endSync();

		d.rotate(-40);

		d.rotate(20);

	}

	public void playReverse() {

		d.rotate(-20);
		d.rotate(40);

		startSync();
		d.rotate(-20);
		c.rotate(-20);
		endSync();

		c.rotate(40);

		startSync();
		c.rotate(-20);
		b.rotate(-20);
		endSync();

		b.rotate(40);

		startSync();
		b.rotate(-20);
		a.rotate(-20);
		endSync();

		a.rotate(40);

		a.rotate(-20);

	}


	public void releaseAll() {

		a.rotateTo(0);
		b.rotateTo(0);
		c.rotateTo(0);
		d.rotateTo(0);

	}

	public void playFrom(String from, String to) {

		a.setSpeed(1000);
		b.setSpeed(1000);
		c.setSpeed(1000);
		d.setSpeed(1000);

		if (from == null) {
			if (to == "C") {
				a.rotateTo(left);
			} else if (to == "D") {
				a.rotateTo(right);
			} else if (to == "E") {
				b.rotateTo(left);
			} else if (to == "F") {
				b.rotateTo(right);
			} else if (to == "G") {
				c.rotateTo(left);
			} else if (to == "A") {
				c.rotateTo(right);
			} else if (to == "H") {
				d.rotateTo(left);
			} else if (to == "C2") {
				d.rotateTo(right);
			}
		}

		/// C JA D soitto muihin sointuihin
		if (from == "C" || from == "D") {

			if (from == "C" && to == "D") {
				a.rotateTo(right);
			}

			if (from == "D" && to == "C") {
				a.rotateTo(left);
			}

			if (from == "C" && to == "C"){
				a.rotateTo(0);
				a.rotateTo(left);
			}

			if (from == "D" && to == "D"){
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

			if (from == "E" && to == "E"){
				b.rotateTo(0);
				b.rotateTo(left);
			}

			if (from == "F" && to == "F"){
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

			if (from == "G" && to == "G"){
				c.rotateTo(0);
				c.rotateTo(left);
			}

			if (from == "A" && to == "A"){
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

			if (from == "H" && to == "H"){
				d.rotateTo(0);
				d.rotateTo(left);
			}

			if (from == "C2" && to == "C2"){
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

	public void play(String key, int delay) {

		from = to;
		to = key;

		if (from != to){
			Delay.msDelay(350);
		}

		playFrom(from, to);


		/*
		if ("C".equals(key) || "c".equals(key)) {

			a.rotateTo(40);

		}

		if ("D".equals(key) || "d".equals(key)) {

			a.rotateTo(-40);

		}

		if ("E".equals(key) || "e".equals(key)) {

			b.rotateTo(40);

		}

		if ("F".equals(key) || "f".equals(key)) {

			b.rotateTo(-40);

		}

		if ("G".equals(key) || "g".equals(key)) {

			c.rotateTo(40);

		}

		if ("A".equals(key) || "a".equals(key)) {

			c.rotateTo(-40);

		}

		if ("H".equals(key) || "h".equals(key)) {

			d.rotateTo(40);

		}

		if ("C2".equals(key) || "c2".equals(key)) {

			d.rotateTo(-40);

		}
		*/

		Delay.msDelay(delay);

	}


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

	public void autoPlay(Song[] song) {

		for (int i = 0; i < song.length; i++) {

			play(song[i].getKey(), song[i].getDelay());

		}
	}

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

	public void stop() {
		startSync();

		a.stop();
		b.stop();
		c.stop();
		d.stop();

		endSync();
	}

	public void battle(int delay) {

		a.setAcceleration(10000);

		c.setAcceleration(10000);

		a.setSpeed(1000);
		b.setSpeed(1000);
		c.setSpeed(1000);
		d.setSpeed(1000);

		startSync();

		a.rotate(-6000);
		b.rotate(6000);
		c.rotate(-6000);
		d.rotate(6000);

		endSync();
	}
}