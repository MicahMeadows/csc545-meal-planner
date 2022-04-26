
package Model;

import Repository.Recipe.IRecipeRepository;
import java.util.List;
import java.util.ArrayList;



public class RecipePageModel {
	final private IRecipeRepository recipeRepository;

	public RecipePageModel(IRecipeRepository recipeRepository){
		this.recipeRepository = recipeRepository;
		this.updateRecipes();
	}

	private List<RecipeModel> recipes = new ArrayList<RecipeModel>();

	public RecipeModel getRecipeWithID(int ID){
		return recipeRepository.getRecipeWithID(ID);
	}

	public List<RecipeModel> getAllRecipes(){
//		return this.recipes;
		return recipeRepository.getAllRecipes();
	}

	public List<RecipeModel> getFilteredRecipes(String nameFilter, String categoryFilter, String ingredientsFilter){
		return recipeRepository.getFilteredRecipes(nameFilter, categoryFilter, ingredientsFilter);
	}

	private boolean recipeMatchesIngredients(List<String> recipeIngredients, List<String> filterIngredients){
		for(String ingredient : filterIngredients){
			if(!checkIngredientExists(recipeIngredients, ingredient))
				return false;
		}
		return true;
	}

	private boolean checkIngredientExists(List<String> recipeIngredients, String ingredientToCheck){
		for(String ingredient : recipeIngredients){
			if(ingredient.toLowerCase().contains(ingredientToCheck.toLowerCase())){
				return true;
			}
		}
		return false;
	}


	public void updateRecipes(){
		recipes = new ArrayList<>();

		NutritionModel nutrition = new NutritionModel.Builder(1).calories(2).fat(5).sodium(9).sugar(23).build();
		ItemModel bun = new ItemModel(0, "Bread", "Bun", nutrition);
		ItemModel tomatoe = new ItemModel(1, "Veggie", "Tomatoe", nutrition);
		ItemModel hamburger = new ItemModel(2, "Meat", "Hamburger", nutrition);

		List<ItemModel> burgerIngredients = new ArrayList<>();
		burgerIngredients.add(bun);
		burgerIngredients.add(tomatoe);
		burgerIngredients.add(hamburger);
		
		recipes.add(new RecipeModel(1, "Burger", "Make burger", "American", burgerIngredients));
		recipes.add(new RecipeModel(2, "Sandwhich", "Make sandwhich", "American", new ArrayList<>()));
		recipes.add(new RecipeModel(3, "Pizza", "Make pizaz", "Italian", new ArrayList<>()));
	}
	
}
