/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.ViewModel;

import MealPlanner.DependencyContainer;
import Model.PlannedMealModel;
import Repository.PlannedMeal.IPlannedMealRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author micah
 */
public class DayPlanViewModel {
	final private IPlannedMealRepository plannedMealRepository;
	final private LocalDate date;

	public DayPlanViewModel(DependencyContainer dependencyContainer, LocalDate date){
		this.plannedMealRepository = dependencyContainer.getRepositoryFactory().getPlannedMealRepository();
		this.date = date;
	}

	public List<PlannedMealModel> getPlannedMeals(){
		return plannedMealRepository.getPlannedMealsForDay(date);
	}

	public String getDayName(){
		return date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US);
	}

	public LocalDate getDate() {
		return this.date; 
	}

	public String getDateString(){
		return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

	public void addPlannedMeal(PlannedMealModel plannedMeal){
		plannedMealRepository.createPlannedMeal(plannedMeal);
	}

}
