package Model;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SeppoModel implements SeppoModel_IF {

	DataOutputStream out;

	public SeppoModel(){
		try{
	    	@SuppressWarnings("resource")
			Socket s = new Socket("10.0.1.1", 1111);
			out = new DataOutputStream(s.getOutputStream());
	    	}catch(Exception e){
	    		System.out.println("Yhteyden muodostus epäonnistui");
	    	}
	}


	public void sendInt(int dataObject){

		try {
			out.writeInt(dataObject);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}