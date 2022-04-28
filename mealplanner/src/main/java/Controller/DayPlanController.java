/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.DayPlanModel;
import Repository.Meal.IMealRepository;
import View.DayPlanView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author micah
 */
public class DayPlanController {
	private final DayPlanView view;
	private final DayPlanModel model;
	private final IMealRepository mealRepository;

	public DayPlanController(IMealRepository mealRepository, DayPlanModel model, DayPlanView view){
		this.mealRepository = mealRepository;
		this.view = view;
		this.model = model;
		
		initialize();
	}

	public void setMealSelectedListener(ListSelectionListener listener){
		view.setMealSelectedListener(listener);
	}

	public void clearSelectedItem(){
		this.view.clearSelected();
	}

	private void initialize(){
		view.setAddMealListener(new AddMealListener());
		updatePlannedMealsList();
		updateDayPlanContent();
	}

	private void updateDayPlanContent(){
		view.setWeekdayText(model.getDayName());
		view.setDateText(model.getDateString());
		updatePlannedMealsList();
	}

	private void updatePlannedMealsList(){
		view.setPlannedMealsList(model.getPlannedMeals());
	}

	class AddMealListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			new EditPlannedMealController(mealRepository, (JFrame) SwingUtilities.getRoot(view), model.getDate(), (newPlan) -> {
				model.addPlannedMeal(newPlan);
				updatePlannedMealsList();
			}).show();
		}
	}


	
}
