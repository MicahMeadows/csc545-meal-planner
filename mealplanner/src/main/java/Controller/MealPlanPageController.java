/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.DayPlanModel;
import Model.MealPlanPageModel;
import Model.PlannedMealModel;
import View.DayPlanView;
import View.MealPlanPageView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author micah
 */
public class MealPlanPageController {
	final private MealPlanPageView view;
	final private MealPlanPageModel model;

	private DayPlanController sundayController;
	private DayPlanController mondayController;
	private DayPlanController tuesdayController;
	private DayPlanController wednesdayController;
	private DayPlanController thursdayController;
	private DayPlanController fridayController;
	private DayPlanController saturdayController;

	public MealPlanPageController(MealPlanPageModel model, MealPlanPageView view){
		this.view = view;
		this.model = model;

		initialize();
	}

	private void initialize(){
		view.setBackListener(new BackListener());
		view.setNextListener(new NextListener());

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

		sundayController = new DayPlanController(new DayPlanModel(day1, model.getPlannedMealsForDay(day1)), view.getSundayPlanView());
		mondayController = new DayPlanController(new DayPlanModel(day2, model.getPlannedMealsForDay(day2)), view.getMondayPlanView());
		tuesdayController = new DayPlanController(new DayPlanModel(day3, model.getPlannedMealsForDay(day3)), view.getTuesdayPlanView());
		wednesdayController = new DayPlanController(new DayPlanModel(day4, model.getPlannedMealsForDay(day4)), view.getWednesdayPlanView());
		thursdayController = new DayPlanController(new DayPlanModel(day5, model.getPlannedMealsForDay(day5)), view.getThursdayPlanView());
		fridayController = new DayPlanController(new DayPlanModel(day6, model.getPlannedMealsForDay(day6)), view.getFridayPlanView());
		saturdayController = new DayPlanController(new DayPlanModel(day7, model.getPlannedMealsForDay(day7)), view.getSaturdayPlanView());

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

	class MealSelectedListener implements ListSelectionListener {
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

	class BackListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			changeSelectedWeek(-1);
		}
	}

	class NextListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			changeSelectedWeek(1);
		}
	}


	
}
