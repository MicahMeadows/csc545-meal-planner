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
	public List<RecipeModel> getAllRecipes();
	public RecipeModel getRecipeWithID(int ID);
	public List<RecipeModel> getFilteredRecipes(String name, String group, String ingredients);
	public List<RecipeModel> getRecipesForMealID(int mealID);

}
