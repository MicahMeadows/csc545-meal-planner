package Repository.Recipe;


import Model.RecipeModel;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author micah
 */
public interface IRecipeRepository {
	List<RecipeModel> getAllRecipes();
	List<RecipeModel> getFilteredRecipes(String name, String group, String ingredients);
	List<RecipeModel> getRecipesForMealID(int mealID);
}
