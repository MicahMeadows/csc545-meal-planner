package Repository.Recipe;

import Model.ItemModel;
import Model.NutritionModel;
import Model.RecipeModel;
import Repository.Item.IItemRepository;
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
	final private IItemRepository itemRepository;
	List<RecipeModel> recipes = new ArrayList<>();

	public TestRecipeRepository(IItemRepository itemRepository){
		this.itemRepository = itemRepository;
		setupTestData();
	}

	void setupTestData(){
		recipes.add(new RecipeModel(1, "Burger", "Make burger", "American", itemRepository.getItemsForRecipeID(1)));
		recipes.add(new RecipeModel(2, "Sandwhich", "Make sandwhich", "American", itemRepository.getItemsForRecipeID(2)));
		recipes.add(new RecipeModel(0, "Pizza", "Make pizaz", "Italian", itemRepository.getItemsForRecipeID(3)));
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
