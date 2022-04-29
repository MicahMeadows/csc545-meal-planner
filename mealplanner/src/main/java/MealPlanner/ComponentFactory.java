package MealPlanner;

import Controller.DayPlanController;
import Model.DayPlanViewModel;
import View.DayPlanView;
import java.time.LocalDate;

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
	
}
