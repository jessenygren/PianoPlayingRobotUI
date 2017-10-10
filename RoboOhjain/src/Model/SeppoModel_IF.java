package Model;

public interface SeppoModel_IF {

	public abstract void sendInt(int dataObject);

	public abstract void sendSong();

	public abstract void timerStart();
	public abstract void timerStop();
	public abstract long getTime();
}
