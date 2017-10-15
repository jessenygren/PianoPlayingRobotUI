
package Controller;

public interface SeppoControl_IF {

	public abstract void connect();

	public abstract void sendInt(int data);


	/**
	 * Ottaa näppäinpainalluksen aloitusajan
	 */
	public abstract void timerStart();

	/**
	 * Ottaa näppäinpainalluksen lopetusajan
	 */
	public abstract void timerStop();

	/**
	 * @return Painalluksen kesto
	 */
	public abstract long getTime();

	/**
	 * Lähettää soitettavan kappaleen robotille
	 */
	public abstract void  sendSong();

	/**
	 * @return Poistettavan kappaleen osoite
	 */
	public abstract boolean deleteFile();
}



