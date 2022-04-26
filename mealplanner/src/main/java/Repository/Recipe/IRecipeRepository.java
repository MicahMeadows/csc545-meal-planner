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
interface IRecipeRepository {
	public List<RecipeModel> getAllRecipes();
	public List<RecipeModel> getRecipeWithID(int ID);
	public List<RecipeModel> getRecipeWithName(String name);
	public List<RecipeModel> getRecipeWithGroupName(String groupName);
	public List<RecipeModel> getRecipeWithIngredientNames(List<String> ingredientNames);
}
