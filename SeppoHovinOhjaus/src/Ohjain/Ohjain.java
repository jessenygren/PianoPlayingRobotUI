package Ohjain;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

import lejos.utility.Delay;

public class Ohjain {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		int keyPressed;

		try {
			@SuppressWarnings("resource")
			Socket s = new Socket("10.0.1.1", 1111);
			DataOutputStream out = new DataOutputStream(s.getOutputStream());


			char valinta;
			final char  AutoPlay = 'A', PlayNote = 'P', QUIT = 'Q';
			do {
				System.out.println("");
				System.out.println("-------------------------------");
				System.out.println("Valitse toiminto ja paina ENTER");
				System.out.println("-------------------------------");
				System.out.println("");
				System.out.println("Autoplay         - A");
				System.out.println("Play song        - P");
				System.out.println("");
				System.out.println("-------------------------------");
				System.out.println("Valintasi: ");

				valinta = (input.nextLine().toUpperCase()).charAt(0);
				switch (valinta) {

				case AutoPlay:
					out.writeInt(1);
					out.flush();
					break;

				case PlayNote:
					out.writeInt(2);
					out.flush();
					break;

				case QUIT:
					System.exit(0);
					break;
				}
			} while (valinta != QUIT);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}