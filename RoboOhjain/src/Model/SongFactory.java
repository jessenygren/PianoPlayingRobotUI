package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class SongFactory {

	static Scanner lukija = new Scanner(System.in);

	public static void main(String[] args) {

		String name;
		char key;
		int delay;
		int i = 1;

		ArrayList<Note> list = new ArrayList<Note>();

		System.out.println("Sy�t� biisin nimi ja paina ENTER");
		name = lukija.nextLine();

		do {
			System.out.println("-----------------------------------------------------------");
			System.out.println("Sy�t� kosketin, pienell�, ja paina ENTER (c,d,e,f,g,a,h,v)");
			key = lukija.next().trim().charAt(0);
			System.out.println("-----------------------------------------------------------");
			System.out.println("Sy�t� delay (ms) ja paina ENTER");
			System.out.println("------------------------------------------");
			delay = lukija.nextInt();

			Note n = new Note((char) key, delay);

			list.add(n);

			System.out.println("1 + ENTER = Jatka, 0 + ENTER = LOPETA");

			i = lukija.nextInt();

		} while (i != 0);

		System.out.println("Kappale " + name);

		for (Note n : list) {
			System.out.println(n.getKey() + ", " + n.getDelay());
		}
		System.out.println("-----------------------------------------------------------");
		System.out.println("-----------------------------------------------------------");


		try {
			FileOutputStream out = new FileOutputStream("C:\\Users\\jesme\\workspace\\RoboOhjain\\Songs\\" + name);
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(list);
			oos.close();
		} catch (Exception e) {
			System.out.println("Kappaleen tallennus ep�onnistui!!");
			e.printStackTrace();
		}

		System.out.println("Kappaleen tallennus onnistui :)");
		System.out.println("-----------------------------------------------------------");
		System.out.println("-----------------------------------------------------------");
	}

}
