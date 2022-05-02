/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Recipe;

import MealPlanner.ConnectDB;
import MealPlanner.DependencyContainer;
import Model.ItemModel;
import Model.MealModel;
import Model.MealRecipeModel;
import Model.NutritionModel;
import Model.RecipeModel;
import Repository.Item.IItemRepository;
import Repository.Meal.IMealRepository;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;

/**
 *
 * @author Micah
 */
public class SqlRecipeRepository implements IRecipeRepository {
//	private final IMealRepository mealRepository;

	private final IItemRepository itemRepository;

	private List<RecipeModel> cachedRecipes = new ArrayList<>();

	public SqlRecipeRepository(DependencyContainer dependencyContainer) {
		this.itemRepository = dependencyContainer.getRepositoryFactory().getItemRepository();
//		this.mealRepository = dependencyContainer.getRepositoryFactory().getMealRepository();
	}

	@Override
	public List<RecipeModel> getAllRecipes() {
		if (!cachedRecipes.isEmpty()) {
			return cachedRecipes;
		}

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

//	private List<Integer> getRecipeIdsForMealId(int mealID) {
//		String sqlQuery = "SELECT * FROM MEALRECIPE WHERE MEALID = ?";
//
//		List<Integer> recipeIds = new ArrayList<>();
//		ConnectDB.runPreparedStatement(sqlQuery,
//			statement -> {
//				try {
//					statement.setInt(1, mealID);
//				} catch (SQLException ex) {
//					Logger.getLogger(SqlRecipeRepository.class.getName()).log(Level.SEVERE, null, ex);
//				}
//			},
//			result -> {
//				try {
//					while (result.next()) {
//						int id = Integer.parseInt(result.getString("RECIPEID"));
//						recipeIds.add(id);
//					}
//				} catch (Exception e) {
//
//				}
//			}
//		);
//		return recipeIds;
//	}
	@Override
	public List<RecipeModel> getRecipesForMealID(int mealID) {
		String sqlQuery = "SELECT MEALID, RECIPE.ID as \"RECIPEID\", RECIPENAME, INSTRUCTIONS, RECIPECATEGORY\n"
			+ "FROM MEALRECIPE INNER JOIN RECIPE ON RECIPEID = RECIPE.ID\n"
			+ "WHERE MEALID = ?";

		List<RecipeModel> recipes = new ArrayList<>();
		ConnectDB.runPreparedStatement(sqlQuery,
			statement -> {
				try {
					statement.setInt(1, mealID);
				} catch (SQLException ex) {
					Logger.getLogger(SqlRecipeRepository.class.getName()).log(Level.SEVERE, null, ex);
				}
			},
			result -> {
				try {
					while (result.next()) {
						int recipeId = Integer.parseInt(result.getString("RECIPEID"));
						String recipeName = result.getString("RECIPENAME");
						String instructions = result.getString("INSTRUCTIONS");
						String category = result.getString("RECIPECATEGORY");
						List<ItemModel> ingredients = itemRepository.getItemsForRecipeID(recipeId);

						recipes.add(new RecipeModel(recipeId, recipeName, instructions, category, ingredients));
					}
				} catch (SQLException e) {
					System.out.println(e.toString());
				}

			}
		);
		return recipes;
	}

}
