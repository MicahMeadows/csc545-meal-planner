package Controller;


import Model.EditPlannedMealViewModel;
import Model.MealModel;
import Model.PlannedMealModel;
import Model.RecipeModel;
import Repository.Meal.IMealRepository;
import Repository.Recipe.IRecipeRepository;
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
	private final IMealRepository mealRepository;
	private final IRecipeRepository recipeRepository; 

	private final EditPlannedMealViewModel model;
	private final EditPlannedMealView view;

	private final JFrame frame;
	private final Consumer<PlannedMealModel> onSubmit;
	
	public EditPlannedMealController(IRecipeRepository recipeRepository, IMealRepository mealRepository, JFrame frame, LocalDate planDate, Consumer<PlannedMealModel> onSubmit){
		this.mealRepository = mealRepository;
		this.recipeRepository = recipeRepository;
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
		updateView();
	}

	public void show() {
		view.setLocationRelativeTo(frame);
		view.setVisible(true);
	}

	private class SetMealButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			view.setVisible(false);
			new SelectMealController(recipeRepository, mealRepository, frame, (meal) -> {
				if (meal != null) {
					model.setSelectedMeal(meal);
					updateView();
				}
				view.setVisible(true);
			}).show();
		}

	}

	private class CancelButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			view.close();
		}

	}

	private class SubmitButtonListener implements ActionListener {

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

	private void updateView() {
		MealModel selectedMeal = model.getSelectedMeal();

		String newMealNameText = selectedMeal == null ? "None Selected" : selectedMeal.getName();
		view.setMealNameText(newMealNameText);

		String newSetText = selectedMeal == null ? "Set" : "Edit";
		view.setSetButtonText(newSetText);
	}

	private void updateModel() {
		String timeString = view.getTimeString();
		model.setPlannedTime(timeString, view.getIsAm());
		
		model.setType(view.getMealType());
	}

	
}
