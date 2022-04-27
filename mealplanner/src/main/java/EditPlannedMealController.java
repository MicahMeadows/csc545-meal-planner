
import Model.EditPlannedMealViewModel;
import View.EditPlannedMealView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author micah
 */
public class EditPlannedMealController {
	private final EditPlannedMealViewModel model;
	private final EditPlannedMealView view;
	
	public EditPlannedMealController(EditPlannedMealViewModel model, EditPlannedMealView view){
		this.view = view;
		this.model = model;

		initialize();

	}

	private void initialize(){
		view.setCancelListener(new CancelButtonListener());
		view.setAmListener(new AmSelectedListener());
		view.setPmListener(new PmSelectedListener());
		view.setSubmitListener(new SubmitButtonListener());
		view.setSetMealListener(new SetMealButtonListener());
		view.setTimeChangeListener(new TimeChangedListener());
	}

	class SetMealButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
		}

	}

	class CancelButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
		}

	}

	class SubmitButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
		}

	}

	class TimeChangedListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent ie) {
			throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
		}

	}


	class AmSelectedListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
		}

}

	class PmSelectedListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
		}

	}

	

	
}
