package Controller;


import Model.EditPlannedMealViewModel;
import Model.MealModel;
import Model.PlannedMealModel;
import Model.RecipeModel;
import View.EditPlannedMealView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Consumer;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;

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
	private final JFrame frame;
	private final Consumer<PlannedMealModel> onSubmit;
	
	public EditPlannedMealController(JFrame frame, LocalDate planDate, Consumer<PlannedMealModel> onSubmit){
		this.onSubmit = onSubmit;
		this.frame = frame;

		this.model = new EditPlannedMealViewModel(planDate);
		this.view = new EditPlannedMealView(frame, true);

		initialize();
	}

	private void initialize(){
		view.setCancelListener(new CancelButtonListener());
		view.setSubmitListener(new SubmitButtonListener());
		view.setSetMealListener(new SetMealButtonListener());
	}

	public void show() {
		view.setLocationRelativeTo(view);
		view.setVisible(true);
	}

	class SetMealButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			model.setSelectedMeal(new MealModel(0, "SomeMeal", new ArrayList<RecipeModel>()));
			view.setMealNameText(model.getSelectedMeal().getName());

		}

	}

	class CancelButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			view.close();
		}

	}

	class SubmitButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			updateModel();

			PlannedMealModel plannedMeal = model.getSubmittedItem();

			if (plannedMeal == null) {
				view.showError("There was an issue with the planned meal you created.");
				return;
			}

			view.close();
			onSubmit.accept(plannedMeal);
		}

	}

	private void updateModel() {
		String timeString = view.getTimeString();
		model.setPlannedTime(timeString, view.getIsAm());
		
		model.setType(view.getMealType());
	}

	
}
