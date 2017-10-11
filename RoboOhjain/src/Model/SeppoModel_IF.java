package Model;

import java.io.File;
import java.util.ArrayList;

import Model.Note;

public interface SeppoModel_IF {

	public abstract void sendInt(int dataObject);

	public abstract void timerStart();
	public abstract void timerStop();
	public abstract long getTime();

	public abstract void sendSong(ArrayList<Note> list);
	public abstract ArrayList<Note> unpackFile(String filepath);
	public abstract void deleteFile(String filepath);
}




