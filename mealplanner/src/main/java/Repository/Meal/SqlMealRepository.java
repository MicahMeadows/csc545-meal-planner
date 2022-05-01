/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Meal;

import MealPlanner.ConnectDB;
import MealPlanner.DependencyContainer;
import Model.MealModel;
import Model.RecipeModel;
import Repository.Recipe.IRecipeRepository;
import java.sql.SQLException;
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
		if (!cachedMeals.isEmpty()) {
			return cachedMeals;
		}
		updateMeals();

		return cachedMeals;
	}
	
	private void updateMeals(){
		String sqlQuery = "SELECT * FROM MEALS";
		List<MealModel> meals = getMealsFromSqlString(sqlQuery, null);
		if (!meals.isEmpty()){
			cachedMeals = meals;
		}
	}

	@Override
	public MealModel getMealForID(int ID) {
		String sqlQuery = "SELECT * FROM MEALS WHERE ID = ?";
		List<MealModel> meals = getMealsFromSqlString(sqlQuery, statement -> {
			try {
				statement.setInt(1, ID);
			} catch (SQLException ex) {
				Logger.getLogger(SqlMealRepository.class.getName()).log(Level.SEVERE, null, ex);
			}
		});
		if (meals.isEmpty()) {
			return null;
		}
		return meals.get(0);
	}

	private List<MealModel> getMealsFromSqlString(String sqlString, Consumer<OraclePreparedStatement> statement) {
		List<MealModel> meals = new ArrayList<>();
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
		return meals;
	}

	@Override
	public MealModel createMeal(MealModel meal) {
		String sqlQuery = "INSERT INTO MEAL VALUES(?, ?)";
		ConnectDB.runPreparedStatement(
			sqlQuery,
			statement -> {
				try {
					statement.setInt(1, meal.getID());
					statement.setString(2, meal.getName());

				} catch (SQLException ex) {
					Logger.getLogger(SqlMealRepository.class.getName()).log(Level.SEVERE, null, ex);
				}
			},
			result -> { }
		);
		return getMealModelLike(meal);
	}
	
	private MealModel getMealModelLike(MealModel mealModel){
		updateMeals();
		return cachedMeals.stream()
			.filter(meal -> meal.equals(mealModel))
			.findFirst()
			.orElse(null);
	}

}
