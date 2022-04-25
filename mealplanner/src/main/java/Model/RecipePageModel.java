
package Model;

import java.awt.List;
import java.util.ArrayList;


public class RecipePageModel {

	private ArrayList<RecipeModel> recipes = new ArrayList<RecipeModel>();


	public ArrayList<RecipeModel> getRecipes(){
		return this.recipes;
	}

	public void updateRecipes(String nameFilter, String categoryFilter, String ingredientsFilter){
		recipes = new ArrayList<RecipeModel>();
		recipes.add(new RecipeModel(1, "Burger", "Make burger", "American"));
		recipes.add(new RecipeModel(2, "Sandwhich", "Make sandwhich", "American"));
		recipes.add(new RecipeModel(3, "Pizza", "Make pizaz", "Italian"));
		System.out.println(this.recipes);
	}
	
}
