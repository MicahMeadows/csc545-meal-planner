/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import MealPlanner.DependencyContainer;
import Model.DayPlanViewModel;
import Model.ItemModel;
import Model.MealPlanViewModel;
import Model.PlannedMealModel;
import Repository.Meal.IMealRepository;
import Repository.PlannedMeal.IPlannedMealRepository;
import Repository.Recipe.IRecipeRepository;
import View.MealPlanView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author micah
 */
public class MealPlanController {
	final private MealPlanView view;
	final private MealPlanViewModel model;
	final private DependencyContainer dependencyContainer;

	private DayPlanController sundayController;
	private DayPlanController mondayController;
	private DayPlanController tuesdayController;
	private DayPlanController wednesdayController;
	private DayPlanController thursdayController;
	private DayPlanController fridayController;
	private DayPlanController saturdayController;

	public MealPlanController(DependencyContainer dependencyContainer, MealPlanViewModel model, MealPlanView view){
		this.dependencyContainer = dependencyContainer;

		this.view = view;
		this.model = model;

		initialize();
	}

	private void initialize(){
		view.setBackListener(new BackListener());
		view.setNextListener(new NextListener());
		view.setDeleteListener(new DeleteListener());
		view.setGenerateShoppingListListener(new GenerateShoppingListListener());

		setupDayPlans();
	}

	private void setupDayPlans(){
		LocalDate day1 = model.getStartDayDate();
		LocalDate day2 = model.getStartDayDate().plusDays(1);
		LocalDate day3 = model.getStartDayDate().plusDays(2);
		LocalDate day4 = model.getStartDayDate().plusDays(3);
		LocalDate day5 = model.getStartDayDate().plusDays(4);
		LocalDate day6 = model.getStartDayDate().plusDays(5);
		LocalDate day7 = model.getStartDayDate().plusDays(6);

		sundayController = new DayPlanController(dependencyContainer, new DayPlanViewModel(dependencyContainer, day1), view.getSundayPlanView());
		mondayController = new DayPlanController(dependencyContainer, new DayPlanViewModel(dependencyContainer, day2), view.getMondayPlanView());
		tuesdayController = new DayPlanController(dependencyContainer, new DayPlanViewModel(dependencyContainer, day3), view.getTuesdayPlanView());
		wednesdayController = new DayPlanController(dependencyContainer, new DayPlanViewModel(dependencyContainer, day4), view.getWednesdayPlanView());
		thursdayController = new DayPlanController(dependencyContainer, new DayPlanViewModel(dependencyContainer, day5), view.getThursdayPlanView());
		fridayController = new DayPlanController(dependencyContainer, new DayPlanViewModel(dependencyContainer, day6), view.getFridayPlanView());
		saturdayController = new DayPlanController(dependencyContainer, new DayPlanViewModel(dependencyContainer, day7), view.getSaturdayPlanView());

		sundayController.setMealSelectedListener(new MealSelectedListener());
		mondayController.setMealSelectedListener(new MealSelectedListener());
		tuesdayController.setMealSelectedListener(new MealSelectedListener());
		wednesdayController.setMealSelectedListener(new MealSelectedListener());
		thursdayController.setMealSelectedListener(new MealSelectedListener());
		fridayController.setMealSelectedListener(new MealSelectedListener());
		saturdayController.setMealSelectedListener(new MealSelectedListener());
	}




	private void changeSelectedWeek(int offset){
		model.addToWeekOffset(offset);
		setupDayPlans();
	}

	private void clearSelectedItems(){
		sundayController.clearSelectedItem();
		mondayController.clearSelectedItem();
		tuesdayController.clearSelectedItem();
		wednesdayController.clearSelectedItem();
		thursdayController.clearSelectedItem();
		fridayController.clearSelectedItem();
		saturdayController.clearSelectedItem();
	}

	private class MealSelectedListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent lse) {
			if (lse.getValueIsAdjusting()) return;

			JList source = (JList)lse.getSource();

			PlannedMealModel tappedMeal = (PlannedMealModel)source.getSelectedValue();

			if (tappedMeal == null) return;

			clearSelectedItems();

			model.setSelectedPlannedMeal(tappedMeal);
			view.setSelectedPlannedMeal(model.getSelectedPlannedMeal());
		}
	}

	private class GenerateShoppingListListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			List<ItemModel> shoppingListItems = model.generateShoppingListItems();
			JFrame frame = (JFrame)SwingUtilities.getRoot(view);
			String formattedStartDate = model.getStartDayDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			new ShoppingListController(frame).showList(shoppingListItems, formattedStartDate);
		}

	}

	private class BackListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			changeSelectedWeek(-1);
		}
	}

	private class NextListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			changeSelectedWeek(1);
		}
	}

	private class DeleteListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			PlannedMealModel plannedMeal = model.getSelectedPlannedMeal();
			if (plannedMeal != null) {
				model.deletePlannedMeal(plannedMeal);
				setupDayPlans();
			}
		}

	}
	
}
