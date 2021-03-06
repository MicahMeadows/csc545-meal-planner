package Repository.Recipe;

import Model.MealRecipeModel;
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
	private List<RecipeModel> recipes = new ArrayList<>();
	private List<MealRecipeModel> mealRecipes = new ArrayList<>();

	public TestRecipeRepository(IItemRepository itemRepository){
		this.itemRepository = itemRepository;
		setupTestData();
	}

	void setupTestData(){
		recipes.add(new RecipeModel(1, "Burger", "Make burger", "American", itemRepository.getItemsForRecipeID(1)));
		recipes.add(new RecipeModel(2, "Sandwhich", "Make sandwhich", "American", itemRepository.getItemsForRecipeID(2)));
		recipes.add(new RecipeModel(3, "Fries", "Make fires", "American", itemRepository.getItemsForRecipeID(3)));
		recipes.add(new RecipeModel(4, "Fish", "Make fish", "Seafood", itemRepository.getItemsForRecipeID(4)));
		recipes.add(new RecipeModel(5, "Chips", "Make chips", "British", itemRepository.getItemsForRecipeID(5)));
		recipes.add(new RecipeModel(6, "Rice", "Make rice", "Asian", itemRepository.getItemsForRecipeID(6)));
		recipes.add(new RecipeModel(7, "Lasagna", "Make lasag", "Italian", itemRepository.getItemsForRecipeID(7)));
		
		mealRecipes.add(new MealRecipeModel(0, 1));
		mealRecipes.add(new MealRecipeModel(1, 2));
		mealRecipes.add(new MealRecipeModel(2, 3));
		mealRecipes.add(new MealRecipeModel(3, 4));
		mealRecipes.add(new MealRecipeModel(4, 5));

	}

	@Override
	public List<RecipeModel> getAllRecipes() {
		return recipes;
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

	@Override
	public List<RecipeModel> getRecipesForMealID(int mealID) {
		List<RecipeModel> foundRecipes = new ArrayList<>();

		mealRecipes.stream()
			.filter(recipe -> recipe.getMealID() == mealID)
			.map(MealRecipeModel::getMealID)
			.forEach(id -> {
				RecipeModel recipeWithId = recipes.stream().filter(recipe -> recipe.getID() == id).findFirst().orElse(null);
				if (recipeWithId != null) {
					foundRecipes.add(recipeWithId);
				}
			});
		return foundRecipes;
	}
}
