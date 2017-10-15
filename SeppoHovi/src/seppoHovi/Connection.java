package seppoHovi;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Ryhmä 5
 * @version 1.0
 *
 */
public class Connection {

	/**
	 * Metodissa luodaan etäyhteyden mahdollistavat asetukset.
	 */
	public void setConnection() {
		try {
			@SuppressWarnings("resource")
			ServerSocket serv = new ServerSocket(1111);
			Socket s = serv.accept();
			DataInputStream in = new DataInputStream(s.getInputStream());
			DataOutputStream out = new DataOutputStream(s.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}