/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MealPlanner;

import Repository.Item.IItemRepository;
import Repository.Item.SqlItemRepository;
import Repository.Item.TestItemRepository;
import Repository.Meal.IMealRepository;
import Repository.Meal.SqlMealRepository;
import Repository.Meal.TestMealRepository;
import Repository.Nutrition.INutritionRepository;
import Repository.Nutrition.SqlNutritionRepository;
import Repository.Nutrition.TestNutritionRepository;
import Repository.PlannedMeal.IPlannedMealRepository;
import Repository.PlannedMeal.SqlPlannedMealRepository;
import Repository.PlannedMeal.TestPlannedMealRepository;
import Repository.Recipe.IRecipeRepository;
import Repository.Recipe.SqlRecipeRepository;
import Repository.Recipe.TestRecipeRepository;

/**
 *
 * @author micah
 */
public class RepositoryFactory {
	private final DependencyContainer dependencyContainer;

	private IItemRepository itemRepository;
	private IMealRepository mealRepository;
	private INutritionRepository nutritionRepository;
	private IPlannedMealRepository plannedMealRepository;
	private IRecipeRepository recipeRepository;

	public RepositoryFactory(DependencyContainer dependencyContainer){
		this.dependencyContainer = dependencyContainer;
	}
	
	public IItemRepository getItemRepository(){
		if (itemRepository == null){
//			itemRepository = new TestItemRepository(getNutritionRepository());
			itemRepository = new SqlItemRepository(dependencyContainer);
		}
		return itemRepository;
	}

	public IMealRepository getMealRepository(){
		if (mealRepository == null){
//			mealRepository = new TestMealRepository(getRecipeRepository());
			mealRepository = new SqlMealRepository(dependencyContainer);
		}
		return mealRepository;
	}

	public INutritionRepository getNutritionRepository(){
		if (nutritionRepository == null){
			nutritionRepository = new SqlNutritionRepository();
		}
		return nutritionRepository;
	}

	public IPlannedMealRepository getPlannedMealRepository(){
		if (plannedMealRepository == null){
//			plannedMealRepository = new TestPlannedMealRepository(getRecipeRepository(), getMealRepository());
			plannedMealRepository = new SqlPlannedMealRepository(dependencyContainer);
		}
		return plannedMealRepository;
	}

	public IRecipeRepository getRecipeRepository(){
		if (recipeRepository == null){
//			recipeRepository = new TestRecipeRepository(getItemRepository());
			recipeRepository = new SqlRecipeRepository(dependencyContainer);
		}
		return recipeRepository;
	}
}
