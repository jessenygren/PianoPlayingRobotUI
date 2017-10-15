
package Controller;

import Gui.SeppoView_IF;
import Model.SeppoModel;
import Model.SeppoModel_IF;

public class SeppoControl implements SeppoControl_IF {

	SeppoView_IF gui;
	SeppoModel_IF model;

	public SeppoControl(SeppoView_IF gui, SeppoModel_IF model) {
		this.gui = gui;
		this.model = model;
	}

	@Override
	public void connect() {
		try {
			SeppoModel client = new SeppoModel();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Yhteys epäonnistui");
		}
	}

	@Override
	public void sendInt(int data) {

		model.sendInt(data);
	}

	@Override
	public void sendSong() {

		model.sendSong(model.unpackFile(gui.selectedFilepath()));
	}

	@Override
	public void timerStart() {
		model.timerStart();
	}

	@Override
	public void timerStop() {
		model.timerStop();
	}

	@Override
	public long getTime() {
		return model.getTime();
	}

	@Override
	public boolean deleteFile() {

		try {
			model.deleteFile(gui.selectedFilepath());
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
