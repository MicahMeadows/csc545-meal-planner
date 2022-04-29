package MealPlanner;

import Repository.Item.IItemRepository;
import Repository.Item.TestItemRepository;
import Repository.Meal.IMealRepository;
import Repository.Meal.TestMealRepository;
import Repository.Nutrition.INutritionRepository;
import Repository.Nutrition.TestNutritionRepository;
import Repository.PlannedMeal.IPlannedMealRepository;
import Repository.PlannedMeal.TestPlannedMealRepository;
import Repository.Recipe.IRecipeRepository;
import Repository.Recipe.TestRecipeRepository;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author micah
 */
public class DependencyContainer {
	private ComponentFactory componentFactory;
	private RepositoryFactory repositoryFactory;

	public ComponentFactory getComponentFactory(){
		if (componentFactory == null){
			componentFactory = new ComponentFactory(this);
		}
		return this.componentFactory;
	}

	public RepositoryFactory getRepositoryFactory(){
		if (repositoryFactory == null){
			repositoryFactory = new RepositoryFactory(this);
		}
		return repositoryFactory;
	}
}
