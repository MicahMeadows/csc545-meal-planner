/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import MealPlanner.DependencyContainer;
import Model.EditMealViewModel;
import Model.MealModel;
import Model.RecipeModel;
import Repository.Meal.IMealRepository;
import Repository.Recipe.IRecipeRepository;
import View.Dialog.EditMealView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.JFrame;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author micah
 */
public class EditMealController {
	private final Consumer<MealModel> onSubmit;
	private final JFrame frame;
	private final EditMealViewModel model;
	private final EditMealView view;

	public EditMealController(DependencyContainer dependencyContainer, JFrame frame, Consumer<MealModel> onSubmit){
		this.onSubmit = onSubmit;
		this.frame = frame;
		this.view = new EditMealView(frame, true);
		this.model = new EditMealViewModel(dependencyContainer);
		setupListeners();
		initialize();
	}

	private void setupListeners(){
		view.setCloseListener(new CancelListener());
		view.setSubmitListener(new SubmitListener());
		view.setMoveLeftListener(new LeftListener());
		view.setMoveRightListener(new RightListener());
		view.setFilterListener(new FilterChangeListener());
	}

	private void initialize() {
		model.updateRecipes();
		updatePotentialList();
	}

	public void show(){
		view.setLocationRelativeTo(frame);
		view.setVisible(true);
	}

	private void updateModel() {
		model.setMealName(view.getMealNameText());
	}

	private class LeftListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			List<RecipeModel> selectedRecipes = view.getSelectedChosenRecipes();
			if (selectedRecipes != null) {
				model.removeMealRecipes(selectedRecipes);
				updateMealList();
			}
		}

	}

	private class RightListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			List<RecipeModel> selectedRecipes = view.getSelectedPotentialRecipes();
			if (selectedRecipes != null) {
				model.addMealRecipes(selectedRecipes);
				updateMealList();
			}
		}

	}

	private class SubmitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			updateModel();
			MealModel newMeal = model.getNewMealModel();

			

			if (newMeal == null) {
				view.displayError("Invalid meal options.");
				return;
			}

			view.close();
			onSubmit.accept(newMeal);
		}
		
	}

	private class CancelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			view.close();
			onSubmit.accept(null);
		}

	}

	void updatePotentialList() {
		view.setPotentialRecipes(model.getFilteredRecipes(view.getFilterText()));
	}

	void updateMealList() {
		view.setMealRecipes(model.getMealRecipes());
	}

	private class FilterChangeListener implements DocumentListener {

		@Override
		public void insertUpdate(DocumentEvent de) {
			updatePotentialList();
		}

		@Override
		public void removeUpdate(DocumentEvent de) {
			updatePotentialList();
		}

		@Override
		public void changedUpdate(DocumentEvent de) {
			updatePotentialList();
		}

	}
		
	
}
