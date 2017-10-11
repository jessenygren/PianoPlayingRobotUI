package Model;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class SeppoModel implements SeppoModel_IF {

	ObjectOutputStream out;
	private long tStart, tEnd, time;


	Note C = new Note ('c', 0);
	Note D = new Note ('d', 0);



	public SeppoModel() {
		try {
			@SuppressWarnings("resource")
			Socket s = new Socket("10.0.1.1", 1111);
			out = new ObjectOutputStream(s.getOutputStream());

		} catch (Exception e) {
			System.out.println("Yhteyden muodostus epäonnistui");
		}
	}



	public void sendInt(int dataObject) {

		try {
			out.writeObject(new Integer(dataObject));
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public ArrayList<Note> unpackFile(String filepath){

		ArrayList<Note> newSong = null;

		try{
			FileInputStream in = new FileInputStream(filepath);
			ObjectInputStream ois = new ObjectInputStream(in);
			newSong = (ArrayList<Note>) ois.readObject();
			ois.close();

		}catch (Exception e){
			System.out.println("Tiedoston purkaminen epäonnistui.");
			e.printStackTrace();
		}
		return newSong;
	}

	public void deleteFile(String filepath) {
		File targetFile = new File(filepath);
		
		targetFile.delete();
	}


	public void sendSong(ArrayList<Note> list){


		try{
			out.writeObject(list);
			out.flush();
		} catch (Exception e){
			e.printStackTrace();
		}
	}





	public void timerStart(){
		tStart = System.currentTimeMillis();
	}

	public void timerStop(){
		tEnd = System.currentTimeMillis();
	}

	public long getTime(){
		time = tEnd - tStart;
		return time;
	}





}



