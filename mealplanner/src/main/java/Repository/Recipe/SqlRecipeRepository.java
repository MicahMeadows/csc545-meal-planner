/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Recipe;

import MealPlanner.ConnectDB;
import Model.ItemModel;
import Model.MealRecipeModel;
import Model.NutritionModel;
import Model.RecipeModel;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;

/**
 *
 * @author Micah
 */
public class SqlRecipeRepository implements IRecipeRepository {

//	void main() {
//		String sq1String = "SELECT * FORM SKDFKSDFLJSDF";
//		ConnectDB.runStatement(sqlString, (resultSet) {
//			while(resultSet.next()){
//				
//			}
//		});
//	}
	List<RecipeModel> cachedRecipes = new ArrayList<>();

	@Override
	public List<RecipeModel> getAllRecipes() {

		String queryString = "SELECT ITEM.ID \n"
			+ "    as \"IngredientID\", ITEM.ITEMGROUP \n"
			+ "    as \"IngredientGroup\", ITEM.ITEMNAME \n"
			+ "    as \"IngredientName\", \n"
			+ "    CALORIES, \n"
			+ "    SUGAR, \n"
			+ "    PROTEIN, \n"
			+ "    FAT, \n"
			+ "    SODIUM, \n"
			+ "    RECIPEID, \n"
			+ "    RECIPENAME, \n"
			+ "    INSTRUCTIONS, \n"
			+ "    RECIPECATEGORY \n"
			+ "FROM ITEM INNER JOIN NUTRITION ON ITEM.ID = NUTRITION.ITEMID \n"
			+ "INNER JOIN RECIPEITEM ON RECIPEITEM.ITEMID = ITEM.ID \n"
			+ "INNER JOIN RECIPE ON RECIPE.ID = RECIPEITEM.RECIPEID";

		List<RecipeModel> recipes = new ArrayList<>();
		ConnectDB.runStatement(queryString, (resultSet) -> {
			try {
				while (resultSet.next()) {

					int recipeId = resultSet.getInt("RECIPEID");
					String recipeName = resultSet.getString("RECIPENAME");
					String recipeInstructions = resultSet.getString("INSTRUCTIONS");
					String recipeCategory = resultSet.getString("RECIPECATEGORY");
					int ingredientID = resultSet.getInt("IngredientID");
					String ingredientGroup = resultSet.getString("IngredientGroup");
					String ingredientName = resultSet.getString("IngredientName");
					int calories = resultSet.getInt("CALORIES");
					int fat = resultSet.getInt("FAT");
					int sodium = resultSet.getInt("SODIUM");
					int protein = resultSet.getInt("PROTEIN");
					int sugar = resultSet.getInt("SUGAR");
					NutritionModel ingredientNutrition = new NutritionModel.Builder(ingredientID)
						.calories(calories)
						.fat(fat)
						.sodium(sodium)
						.sugar(sugar)
						.protein(protein)
						.build();

					ItemModel newIngredient = new ItemModel(ingredientID, ingredientGroup, ingredientName, ingredientNutrition);

					RecipeModel currentRecipe = recipes.stream().filter(recipe -> recipe.getID() == recipeId).findFirst().orElse(null);

					if (currentRecipe == null) {
						List<ItemModel> newIngredients = new ArrayList<>();
						newIngredients.add(newIngredient);
						recipes.add(new RecipeModel(recipeId, recipeName, recipeInstructions, recipeCategory, newIngredients));
					} else {
						currentRecipe.getIngredients().add(newIngredient);
					}

				}
			} catch (Exception e) {

			}
			cachedRecipes = new ArrayList<>(recipes);

		});
		return recipes;
	}

	@Override
	public List<RecipeModel> getFilteredRecipes(String name, String group, String ingredients) {
		if (cachedRecipes == null) {
			getAllRecipes();
		}
		return cachedRecipes.stream().filter(recipe -> {
			boolean nameMatches = recipe.getName().toLowerCase().contains(name.toLowerCase()) || name.isBlank();
			boolean groupMatches = recipe.getCategory().toLowerCase().contains(group.toLowerCase()) || group.isBlank();

			String[] splitIngredients = ingredients.replace(" ", "").split(",");
			List<String> filterIngredients = new ArrayList<>(Arrays.asList(splitIngredients));
			List<String> recipeIngredients = recipe.getIngredients().stream().map(ingredient -> ingredient.getName()).collect(Collectors.toList());

			boolean ingredientMatch = recipeMatchesIngredients(recipeIngredients, filterIngredients) || ingredients.isEmpty();

			return nameMatches && groupMatches && ingredientMatch;
		}).collect(Collectors.toList());
	}

	private boolean recipeMatchesIngredients(List<String> recipeIngredients, List<String> filterIngredients) {
		for (String ingredient : filterIngredients) {
			if (!checkIngredientExists(recipeIngredients, ingredient)) {
				return false;
			}
		}
		return true;
	}

	private boolean checkIngredientExists(List<String> recipeIngredients, String ingredientToCheck) {
		for (String ingredient : recipeIngredients) {
			if (ingredient.toLowerCase().contains(ingredientToCheck.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<RecipeModel> getRecipesForMealID(int mealID) {

		// TODO: come back to this and see if we need it
		List<MealRecipeModel> mealRecipes = new ArrayList<>();
		mealRecipes.add(new MealRecipeModel(1, 1));

		if (cachedRecipes == null) {
			getAllRecipes();
		}

		List<Integer> mealIds = mealRecipes.stream()
			.map(MealRecipeModel::getMealID)
			.collect(Collectors.toList());

		List<RecipeModel> recipesForMeal = new ArrayList<>();
		for (int mealId : mealIds) {
			cachedRecipes.stream().forEach(recipe -> {
				if (recipe.getID() == mealId) {
					recipesForMeal.add(recipe);
				}
			});
		}

		return recipesForMeal;
	}

}
