/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.ViewModel;

import MealPlanner.DependencyContainer;
import Model.MealModel;
import Repository.Meal.IMealRepository;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author micah
 */
public class SelectMealViewModel {
	private final IMealRepository mealRepository;
	private List<MealModel> meals;

	public SelectMealViewModel(DependencyContainer dependencyContainer){
		this.mealRepository = dependencyContainer.getRepositoryFactory().getMealRepository();
	}

	public void updateMealList(){
		this.meals = mealRepository.getAllMeals();
	}

	public List<MealModel> getFilteredList(String nameFilter){
		return meals.stream()
			.filter(meal -> meal.getName().toLowerCase().contains(nameFilter.toLowerCase()))
			.collect(Collectors.toList());
	}
	
}
