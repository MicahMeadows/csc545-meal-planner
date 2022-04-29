package MealPlanner;

import Controller.DayPlanController;
import Controller.EditPlannedMealController;
import Model.DayPlanViewModel;
import Model.EditPlannedMealViewModel;
import Model.PlannedMealModel;
import View.DayPlanView;
import View.EditPlannedMealView;
import java.time.LocalDate;
import java.util.function.Consumer;
import javax.swing.JFrame;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author micah
 */
public class ComponentFactory {
	private final DependencyContainer dependencyContainer;

	public ComponentFactory(DependencyContainer dependencyContainer){
		this.dependencyContainer = dependencyContainer;
	}
	
	public DayPlanController createDayPlanController(LocalDate date){
		DayPlanView view = new DayPlanView();
		DayPlanViewModel model = new DayPlanViewModel(dependencyContainer, date);
		DayPlanController controller = new DayPlanController(dependencyContainer, model, view);
		return controller;
	}

	public void showEditPlannedMealDialog(JFrame frame, LocalDate date, Consumer<PlannedMealModel> onSubmit){
		final EditPlannedMealViewModel model = new EditPlannedMealViewModel(date);
		final EditPlannedMealView view = new EditPlannedMealView(frame, true);
		final EditPlannedMealController controller = new EditPlannedMealController(dependencyContainer, frame, date, onSubmit, view, model);
		controller.show();
	}
}
