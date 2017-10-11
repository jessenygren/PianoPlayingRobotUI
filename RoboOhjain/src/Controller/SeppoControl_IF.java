
package Controller;

public interface SeppoControl_IF {

	public abstract void connect();

	public abstract void sendInt(int data);



	public abstract void timerStart();

	public abstract void timerStop();

	public abstract long getTime();

	public abstract void  sendSong();

	public abstract boolean deleteFile();
}



