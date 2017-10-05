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

	RegulatedMotor a;
	RegulatedMotor b;
	RegulatedMotor c;
	RegulatedMotor d;

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

	public void play(String key, int delay) {

		a.setSpeed(1000);
		b.setSpeed(1000);
		c.setSpeed(1000);
		d.setSpeed(1000);

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
		Delay.msDelay(delay);
	}

	public void release() {

		a.rotateTo(0);
		b.rotateTo(0);
		c.rotateTo(0);
		d.rotateTo(0);

	}

	public void airPlay(float distance) {

		if (distance > 5 && distance <= 10) {
			release();
			a.rotateTo(40);
		} else if (distance > 10 && distance <= 15) {
			release();
			a.rotateTo(-40);
		} else if (distance > 15 && distance <= 20) {
			release();
			b.rotateTo(40);
		} else if (distance > 20 && distance <= 25) {
			release();
			b.rotateTo(-40);
		} else if (distance > 25 && distance <= 30) {
			release();
			c.rotateTo(40);
		} else if (distance > 30 && distance <= 35) {
			release();
			c.rotateTo(-40);
		} else if (distance > 35 && distance <= 40) {
			release();
			d.rotateTo(40);
		} else if (distance > 40 && distance <= 45) {
			release();
			d.rotateTo(-40);
		}


	}

	public void autoPlay(Song[] song) {

		for (int i = 0; i < song.length; i++) {

			play(song[i].getKey(), song[i].getDelay());
			release();
		}
	}

	public String interpreter(int i) {
		String key = null;

		if (i == 2) {
			key = "C";
			return key;
		}
		if (i == 3) {
			key = "D";
			return key;
		}
		if (i == 4) {
			key = "E";
			return key;
		}
		if (i == 5) {
			key = "F";
			return key;
		}
		if (i == 6) {
			key = "G";
			return key;
		}
		if (i == 7) {
			key = "A";
			return key;
		}
		if (i == 8) {
			key = "H";
			return key;
		}
		if (i == 9) {
			key = "C";
			return key;
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