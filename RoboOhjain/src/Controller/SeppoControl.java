package Controller;

import Gui.SeppoView_IF;
import Model.SeppoModel;
import Model.SeppoModel_IF;

public class SeppoControl implements SeppoControl_IF {

	SeppoView_IF gui;
	SeppoModel_IF model;

	public SeppoControl(SeppoView_IF gui, SeppoModel_IF model){
		this.gui = gui;
		this.model = model;
	}

	@Override
	public void connect(){
		try {
			SeppoModel client = new SeppoModel();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Yhteys epäonnistui");
		}
	}

	@Override
	public void send(int data){

		model.sendInt(data);
	}


}
