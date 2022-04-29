/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import MealPlanner.DependencyContainer;
import Model.DayPlanViewModel;
import View.DayPlanView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author micah
 */
public class DayPlanController {
	private final DependencyContainer dependencyContainer;
	private DayPlanView view;
	private DayPlanViewModel model;

	public DayPlanController(DependencyContainer dependencyContainer, DayPlanViewModel model, DayPlanView view){
		this.view = view;
		this.model = model;
		this.dependencyContainer = dependencyContainer;
		initialize();
	}

	public void setView(DayPlanView view){
		this.view = view;
	}

	public void setMealSelectedListener(ListSelectionListener listener){
		view.setMealSelectedListener(listener);
	}

	public void clearSelectedItem(){
		this.view.clearSelected();
	}

	public DayPlanView getView(){
		return this.view;
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

	private class AddMealListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			JFrame viewFrame = (JFrame)SwingUtilities.getRoot(view);
			dependencyContainer.getComponentFactory().showEditPlannedMealDialog(viewFrame, model.getDate(), (newPlan) -> {
				model.addPlannedMeal(newPlan);
				updatePlannedMealsList();
			});
		}
	}


	
}
