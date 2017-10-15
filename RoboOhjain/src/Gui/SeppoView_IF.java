package Gui;

import java.io.File;
import java.util.ArrayList;

import Model.Note;

public interface SeppoView_IF {

	/**
	 * Metodi luo stringin ListViewissa valitun tiedoston sijainnille.
	 *
	 * @return filepath
	 */
	public abstract String selectedFilepath();

	/**
	 * Metodi lisää kohdekansiossa olevien tiedostojen nimet
	 * GUI:n ListViewiin.
	 */
	public abstract void listList();


	/*
	public abstract Note createNote(char c, long delay);
	public abstract ArrayList<Note> createSong(Note note);
	public abstract void clearSong();
*/

}
