
package Model;

import java.awt.List;
import java.util.ArrayList;


public class RecipePageModel {
	private String nameText;
	private String categoryText;
	private String ingredientText;
	private ArrayList<Recipe> recipes = new ArrayList<Recipe>();


	public void setNameText(String nameText){
		this.nameText = nameText;
	}

	public void setCategoryText(String categoryText){
		this.categoryText = categoryText;
	}

	public void setIngredientText(String ingredientText){
		this.ingredientText = ingredientText;
	}

	public String getNameText() {
		return this.nameText;
	}

	public String getCategoryText(){
		return this.categoryText;
	}

	public String getIngredientText(){
		return this.ingredientText;
	}

	public ArrayList<Recipe> getRecipes(){
		return this.recipes;
	}
	
}
