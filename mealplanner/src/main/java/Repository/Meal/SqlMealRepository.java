/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Meal;

import MealPlanner.ConnectDB;
import MealPlanner.DependencyContainer;
import Model.FridgeItemModel;
import Model.MealModel;
import Model.MealRecipeModel;
import Model.RecipeModel;
import Repository.Item.SqlItemRepository;
import Repository.Recipe.IRecipeRepository;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OraclePreparedStatement;

/**
 *
 * @author Micah
 */
public class SqlMealRepository implements IMealRepository {

	private final IRecipeRepository recipeRepository;

	public SqlMealRepository(DependencyContainer dependencyContainer) {
		this.recipeRepository = dependencyContainer.getRepositoryFactory().getRecipeRepository();
	}

	List<MealModel> cachedMeals = new ArrayList<>();

	@Override
	public List<MealModel> getAllMeals() {
//		if (!cachedMeals.isEmpty()) {
//			return cachedMeals;
//		}
		updateMeals();

		return cachedMeals;
	}

	private void updateMeals() {
		String sqlQuery = "SELECT * FROM MEAL";
		List<MealModel> meals = getMealsFromSqlString(sqlQuery, statement -> {

		});
		if (!meals.isEmpty()) {
			cachedMeals = meals;
		}
	}

	@Override
	public MealModel getMealForID(int ID) {
		String sqlQuery = "SELECT * FROM MEAL WHERE ID = ?";
		List<MealModel> meals = getMealsFromSqlString(sqlQuery, statement -> {
			try {
				statement.setInt(1, ID);
			} catch (SQLException ex) {
				System.out.println("error with get meal id");
			}
		});
		if (meals.isEmpty()) {
			return null;
		}
		MealModel mealWithId = meals.get(0);

		List<RecipeModel> recipesForMeal = recipeRepository.getRecipesForMealID(mealWithId.getID());

		mealWithId.setRecipes(recipesForMeal);
		return mealWithId;
	}

	private List<MealModel> getMealsFromSqlString(String sqlString, Consumer<OraclePreparedStatement> statement) {
		List<MealModel> meals = new ArrayList<>();
		try {
			ConnectDB.runPreparedStatement(sqlString, statement, result -> {
				try {
					while (result.next()) {
						int mealId = result.getInt("ID");
						String mealName = result.getString("MEALNAME");
						List<RecipeModel> mealRecipes = recipeRepository.getRecipesForMealID(mealId);
						meals.add(new MealModel(mealId, mealName, mealRecipes));
					}
				} catch (SQLException ex) {
					Logger.getLogger(SqlMealRepository.class.getName()).log(Level.SEVERE, null, ex);
				}
			});
		} catch (Exception e) {
			System.out.println("fail get meal from string");
		}

		return meals;
	}

	private int getNextId() {
		String sqlQuery = "SELECT ISEQ$$_124897.NEXTVAL from dual";

		String[] val = {null};
		ConnectDB.runStatement(sqlQuery, result -> {
			try {
				while (result.next()) {
					val[0] = result.getString("NEXTVAL");
				}
			} catch (SQLException ex) {
				Logger.getLogger(SqlMealRepository.class.getName()).log(Level.SEVERE, null, ex);
			}
		});
		try {
			return Integer.parseInt(val[0]);
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public MealModel createMeal(MealModel meal) {
		int newId = getNextId();
		System.out.println(newId);

		String sqlQuery = "INSERT INTO MEAL VALUES (?, ?)";

		try {
			ConnectDB.runPreparedStatement(
				sqlQuery,
				statement -> {
					try {
						statement.setInt(1, newId);
						statement.setString(2, meal.getName());

					} catch (SQLException ex) {
						System.out.println(ex.toString());
					}
				},
				result -> {

				}
			);
		} catch (Exception e) {
			return null;
		}

		
		meal.setID(newId);
		return meal;
	}

	@Override
	public MealRecipeModel createMealRecipe(int mealId, int recipeId) {
		String sqlQuery = "INSERT INTO MEALRECIPE VALUES(?, ?)";
		ConnectDB.runPreparedStatement(
			sqlQuery,
			statement -> {
				try {
					statement.setInt(1, mealId);
					statement.setInt(2, recipeId);
				} catch (SQLException ex) {
					Logger.getLogger(SqlItemRepository.class.getName()).log(Level.SEVERE, null, ex);
				}
			},
			result -> {

			}
		);
		return new MealRecipeModel(mealId, recipeId);
	}
}
