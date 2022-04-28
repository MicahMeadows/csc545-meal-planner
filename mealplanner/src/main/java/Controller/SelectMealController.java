/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.MealModel;
import Model.SelectMealViewModel;
import Repository.Meal.IMealRepository;
import View.SelectMealView;
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
	private final SelectMealViewModel model;
	private final SelectMealView view;
	private final JFrame frame;
	private final Consumer<MealModel> onSubmit;

	public SelectMealController(IMealRepository mealRepository, JFrame frame, Consumer<MealModel> onSubmit){
		this.frame = frame;
		this.onSubmit = onSubmit;

		model = new SelectMealViewModel(mealRepository);
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
			System.out.println("add");
			// on submit accept new model
		}

	}

	private class CancelButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			view.close();
		}

	}

	private class SelectButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			MealModel selectedMeal = view.getSelectedMeal();

			if (selectedMeal == null) {
				view.displayErrorPopup("There was no item selected");
			}

			onSubmit.accept(selectedMeal);
			view.close();
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
