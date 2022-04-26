package Repository.Recipe;

import Model.ItemModel;
import Model.NutritionModel;
import Model.RecipeModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author micah
 */
public class TestRecipeRepository implements IRecipeRepository {
	List<RecipeModel> recipes = new ArrayList<>();

	public TestRecipeRepository(){
		setupTestData();
	}

	void setupTestData(){
		NutritionModel nutrition = new NutritionModel.Builder(1).calories(2).fat(5).sodium(9).sugar(23).build();

		ItemModel bun = new ItemModel(0, "Bread", "Bun2", nutrition);
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

	@Override
	public List<RecipeModel> getAllRecipes() {
		return recipes;
	}

	@Override
	public RecipeModel getRecipeWithID(int ID) {
		return (RecipeModel) (List<RecipeModel>) recipes.stream().filter(r -> r.getID() == ID).findFirst().orElse(null);
	}

	@Override
	public List<RecipeModel> getFilteredRecipes(String name, String group, String ingredients) {
		return recipes.stream().filter(recipe -> {
			boolean nameMatch = recipe.getName().toLowerCase().contains(name.toLowerCase());

			boolean categoryMatch = recipe.getCategory().toLowerCase().contains(group.toLowerCase());

			String[] splitIngredients = ingredients.replace(" ", "").split(",");
			List<String> filterIngredients = new ArrayList<>(Arrays.asList(splitIngredients));
			List<String> recipeIngredients = recipe.getIngredients().stream().map(ingredient -> ingredient.getName()).collect(Collectors.toList());
			

			boolean ingredientMatch = recipeMatchesIngredients(recipeIngredients, filterIngredients) || ingredients.isEmpty();

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

	

	
}
