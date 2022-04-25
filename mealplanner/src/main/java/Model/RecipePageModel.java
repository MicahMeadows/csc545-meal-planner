
package Model;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;


public class RecipePageModel {

	public RecipePageModel(){
		this.updateRecipes();
	}

	private List<RecipeModel> recipes = new ArrayList<RecipeModel>();


	public List<RecipeModel> getAllRecipes(){
		return this.recipes;
	}

	public List<RecipeModel> getFilteredRecipes(String nameFilter, String categoryFilter, String ingredientsFilter){
		return recipes.stream().filter(recipe -> {
			boolean nameMatch = recipe.getName().toLowerCase().contains(nameFilter.toLowerCase());

			boolean categoryMatch = recipe.getCategory().toLowerCase().contains(categoryFilter.toLowerCase());

			String[] splitIngredients = ingredientsFilter.replace(" ", "").split(",");
			List<String> filterIngredients = new ArrayList<>(Arrays.asList(splitIngredients));
			List<String> recipeIngredients = recipe.getIngredients().stream().map(ingredient -> ingredient.getName()).collect(Collectors.toList());
			

			boolean ingredientMatch = recipeMatchesIngredients(recipeIngredients, filterIngredients) || ingredientsFilter.isEmpty();

			return nameMatch && categoryMatch && ingredientMatch;
		}).collect(Collectors.toList());
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

		ItemModel bun = new ItemModel(0, "Bread", "Bun");
		ItemModel tomatoe = new ItemModel(1, "Veggie", "Tomatoe");
		ItemModel hamburger = new ItemModel(2, "Meat", "Hamburger");

		List<ItemModel> burgerIngredients = new ArrayList<>();
		burgerIngredients.add(bun);
		burgerIngredients.add(tomatoe);
		burgerIngredients.add(hamburger);
		
		recipes.add(new RecipeModel(1, "Burger", "Make burger", "American", burgerIngredients));
		recipes.add(new RecipeModel(2, "Sandwhich", "Make sandwhich", "American", new ArrayList<>()));
		recipes.add(new RecipeModel(3, "Pizza", "Make pizaz", "Italian", new ArrayList<>()));
	}
	
}
