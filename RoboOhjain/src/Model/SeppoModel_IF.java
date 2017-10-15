package Model;

import java.io.File;
import java.util.ArrayList;

import Model.Note;

public interface SeppoModel_IF {

	public abstract void sendInt(int dataObject);

	/**
	 * Metodi ottaa talteen sen hetkisen ajan tStart-muuttujaan.
	 * Aika tallennetaan millisekunteina.
	 */
	public abstract void timerStart();
	/**
	 * * Metodi ottaa talteen sen hetkisen ajan tEnd-muuttujaan.
	 * Aika tallennetaan millisekunteina.
	 */
	public abstract void timerStop();
	/**
	 * Metodi vähentää tStart-luvun tEnd-muuttujasta.
	 * Lukujen tulo vastaa sitä kuinka kauan kosketinta pidettiin pohjassa.
	 * @return long-tyyppisen muuttujan time = kuinka pitkään kosketinta painettiin.
	 */
	public abstract long getTime();

	/**
	 * Muuttuja lähettää soitettavan kappaleen robotille.
	 * @param list
	 */
	public abstract void sendSong(ArrayList<Note> list);
	/**
	 * Metodi lukee valitun tiedoston kansiosta ja tallentaa sen Note-listaan.
	 * @param filepath
	 * @return newSong
	 */
	public abstract ArrayList<Note> unpackFile(String filepath);
	/**
	 * Metodi poistaa filepath-muuttujaa vastaavan kohteen
	 * @param filepath
	 */
	public abstract void deleteFile(String filepath);
}




