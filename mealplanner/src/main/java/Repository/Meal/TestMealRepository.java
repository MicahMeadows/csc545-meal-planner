/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Meal;

import Model.MealModel;
import Model.RecipeModel;
import Repository.Recipe.IRecipeRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author micah
 */
public class TestMealRepository implements IMealRepository {
	private final IRecipeRepository recipeRepository;
	private List<MealModel> meals;

	public TestMealRepository(IRecipeRepository recipeRepository){
		this.recipeRepository = recipeRepository;
		initialize();
	}

	private void initialize() {
		meals = new ArrayList<>();
		meals.add(new MealModel(0, "Burger and fries", recipeRepository.getRecipesForMealID(0)));
		meals.add(new MealModel(1, "Fish and chips", recipeRepository.getRecipesForMealID(1)));
		meals.add(new MealModel(2, "Pizza and breadsticks", recipeRepository.getRecipesForMealID(2)));
		meals.add(new MealModel(3, "Spaghetti and meatballs", recipeRepository.getRecipesForMealID(3)));
		meals.add(new MealModel(4, "Fishsticks and fries", recipeRepository.getRecipesForMealID(4)));
		meals.add(new MealModel(5, "Chicken alfredo", recipeRepository.getRecipesForMealID(5)));
		meals.add(new MealModel(6, "Sushi and rice", recipeRepository.getRecipesForMealID(6)));
	}

	@Override
	public List<MealModel> getAllMeals() {
		return this.meals;
	}

	@Override
	public MealModel getMealForID(int ID) {
		return this.meals.stream()
			.filter(meal -> meal.getID() == ID)
			.findAny()
			.orElse(null);
	}

	@Override
	public MealModel createMeal(MealModel meal) {
		try {
			MealModel newMeal = new MealModel(new Random().nextInt(Integer.MAX_VALUE), meal.getName(), meal.getRecipes());
			meals.add(newMeal);
			return newMeal;
		} catch (Exception e) {
			return null;
		}
	}
	
}
