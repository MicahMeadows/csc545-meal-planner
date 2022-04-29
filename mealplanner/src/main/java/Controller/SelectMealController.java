/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import MealPlanner.DependencyContainer;
import Model.MealModel;
import Model.ViewModel.SelectMealViewModel;
import Repository.Meal.IMealRepository;
import Repository.Recipe.IRecipeRepository;
import View.Dialog.SelectMealView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;
import javax.swing.JFrame;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author micah
 */
public class SelectMealController {
	private final DependencyContainer dependencyContainer;
	private final SelectMealViewModel model;
	private final SelectMealView view;
	private final JFrame frame;
	private final Consumer<MealModel> onSubmit;

	public SelectMealController(DependencyContainer dependencyContainer, JFrame frame, Consumer<MealModel> onSubmit){
		this.dependencyContainer = dependencyContainer;
		this.frame = frame;
		this.onSubmit = onSubmit;

		model = new SelectMealViewModel(dependencyContainer);
		view = new SelectMealView(frame, true);

		setupListeners();
		
		updateList();
	}

	void show(){
		view.setLocationRelativeTo(frame);
		view.setVisible(true);
	}

	private void setupListeners(){
		view.setAddMealListener(new AddMealListener());
		view.setCancelListener(new CancelButtonListener());
		view.setSelectListener(new SelectButtonListener());
		view.setFilterListener(new FilterChangeListener());
	}

	private void updateList(){
		model.updateMealList();
		updateListView();
	}

	private void updateListView(){
		view.setMealListData(model.getFilteredList(view.getSearchText()));
	}

	private class AddMealListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			view.setVisible(false);
			new EditMealController(dependencyContainer, frame, (newMeal) -> {
				view.close();
				if (newMeal != null) {
					onSubmit.accept(newMeal);
				} else {
					view.setVisible(true);
				}
			}).show();
		}

	}

	private class CancelButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			view.close();
			onSubmit.accept(null);
		}

	}

	private class SelectButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			MealModel selectedMeal = view.getSelectedMeal();

			if (selectedMeal == null) {
				view.displayErrorPopup("There was no item selected");
				return;
			}

			view.close();
			onSubmit.accept(selectedMeal);
		}

	}

	private class FilterChangeListener implements DocumentListener {

		@Override
		public void insertUpdate(DocumentEvent de) {
			updateList();
		}

		@Override
		public void removeUpdate(DocumentEvent de) {
			updateList();
		}

		@Override
		public void changedUpdate(DocumentEvent de) {
			updateList();
		}

	}
	
}
