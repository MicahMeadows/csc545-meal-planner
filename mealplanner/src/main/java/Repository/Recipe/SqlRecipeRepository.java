/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Recipe;

import MealPlanner.ConnectDB;
import Model.ItemModel;
import Model.NutritionModel;
import Model.RecipeModel;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;

/**
 *
 * @author Micah
 */
public class SqlRecipeRepository implements IRecipeRepository {

	@Override
	public List<RecipeModel> getAllRecipes() {
		Connection connection = null;
		OraclePreparedStatement preparedStatement = null;
		OracleResultSet resultSet = null;
		
		try {
			connection = ConnectDB.setupConnection();
			String queryString = "SELECT ITEM.ID \n" +
				"    as \"IngredientID\", ITEM.ITEMGROUP \n" +
				"    as \"IngredientGroup\", ITEM.ITEMNAME \n" +
				"    as \"IngredientName\", \n" +
				"    CALORIES, \n" +
				"    SUGAR, \n" +
				"    PROTEIN, \n" +
				"    FAT, \n" +
				"    SODIUM, \n" +
				"    RECIPEID, \n" +
				"    RECIPENAME, \n" +
				"    INSTRUCTIONS, \n" +
				"    RECIPECATEGORY \n" +
				"FROM ITEM INNER JOIN NUTRITION ON ITEM.ID = NUTRITION.ITEMID \n" +
				"INNER JOIN RECIPEITEM ON RECIPEITEM.ITEMID = ITEM.ID \n" +
				"INNER JOIN RECIPE ON RECIPE.ID = RECIPEITEM.RECIPEID";
			
			preparedStatement = (OraclePreparedStatement)connection.prepareStatement(queryString);
			
			resultSet = (OracleResultSet)preparedStatement.executeQuery();
			
			List<RecipeModel> recipes = new ArrayList<>();
			while (resultSet.next()){
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
				NutritionModel ingredientNutrition = new NutritionModel
					.Builder(ingredientID)
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
			return recipes;
		}
		catch (Exception e){
			return new ArrayList<>();
		}
		finally {
			ConnectDB.close(connection);
			ConnectDB.close(preparedStatement);
			ConnectDB.close(resultSet);
		}
	}

	@Override
	public RecipeModel getRecipeWithID(int ID) {
		return null;
	}

	@Override
	public List<RecipeModel> getFilteredRecipes(String name, String group, String ingredients) {
		return null;
	}

	@Override
	public List<RecipeModel> getRecipesForMealID(int mealID) {
		return new ArrayList<RecipeModel>();
	}
	
}
