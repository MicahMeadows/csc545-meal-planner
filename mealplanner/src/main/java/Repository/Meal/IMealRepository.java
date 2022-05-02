/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repository.Meal;

import Model.MealModel;
import Model.MealRecipeModel;
import Model.RecipeModel;
import java.util.List;

/**
 *
 * @author micah
 */
public interface IMealRepository {
	List<MealModel> getAllMeals();
	MealModel getMealForID(int ID);
	MealModel createMeal(MealModel meal);
	MealRecipeModel createMealRecipe(int mealId, int recipeId);
}
