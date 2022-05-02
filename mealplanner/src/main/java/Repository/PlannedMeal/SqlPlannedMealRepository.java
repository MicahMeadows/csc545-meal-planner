/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.PlannedMeal;

import MealPlanner.ConnectDB;
import MealPlanner.DependencyContainer;
import Model.MealModel;
import Model.PlannedMealModel;
import Model.RecipeModel;
import Repository.Meal.IMealRepository;
import Repository.Recipe.IRecipeRepository;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import oracle.sql.TIMESTAMP;

/**
 *
 * @author Micah
 */
public class SqlPlannedMealRepository implements IPlannedMealRepository {

	private final IMealRepository mealRepository;
	private final IRecipeRepository recipeRepository;

	public SqlPlannedMealRepository(DependencyContainer dependencyContainer) {
		this.recipeRepository = dependencyContainer.getRepositoryFactory().getRecipeRepository();
		this.mealRepository = dependencyContainer.getRepositoryFactory().getMealRepository();
	}

	@Override
	public List<PlannedMealModel> getPlannedMealsForDay(LocalDate date) {
		return getPlannedMealsForRange(date, date);
	}
	

	@Override
	public List<PlannedMealModel> getPlannedMealsForRange(LocalDate startDate, LocalDate endDate) {
		// get all planned meals for a day
		System.out.println(startDate.toString());
		System.out.println(endDate.toString());
		System.out.println("-------");
		final String sqlQuery = "SELECT * FROM PLANNEDMEAL "
			+ "WHERE PLANNEDTIME "
			+ "BETWEEN TO_DATE('" + startDate.toString() + "', 'YYYY-MM-DD') AND TO_DATE('" + endDate.plusDays(1).toString() + "', 'YYYY-MM-DD')";

		
		List<PlannedMealModel> plannedMeals = new ArrayList<>();
		try {
			ConnectDB.runPreparedStatement(
				sqlQuery,
				statement -> {
//					try {
//						statement.setDate(1, Date.valueOf(date));
//					} catch (SQLException ex) {
//						Logger.getLogger(SqlPlannedMealRepository.class.getName()).log(Level.SEVERE, null, ex);
//					}
				},
				results -> {
					try {
						while (results.next()) {
							// get planned meals
							int plannedMealId = Integer.parseInt(results.getString("ID")) ;
							TIMESTAMP plannedTime = results.getTIMESTAMP("PLANNEDTIME");
							int mealId = Integer.parseInt(results.getString("MEALID")) ;
							String mealType = results.getString("MEALTYPE");

							MealModel mealForPlan = mealRepository.getMealForID(mealId);

							PlannedMealModel newPlannedMeal = new PlannedMealModel(
								plannedMealId,
								plannedTime.toLocalDateTime(), // convert from timestamp
								mealId,
								mealType,
								mealForPlan // get from meal repo
							);

							plannedMeals.add(newPlannedMeal);
						}
					} catch (Exception e) {
						System.out.println(e.toString());
					}
				}
			);
		} catch (Exception e) {
			System.out.println(e);
		}

		return plannedMeals;
	}


	@Override
	public void removePlannedMeal(int ID) {
		String sqlQuery = "DELETE FROM PLANNEDMEAL WHERE ID = ?";

		ConnectDB.runPreparedStatement(
			sqlQuery,
			(statement) -> {
				try {
					statement.setInt(1, ID);
				} catch (Exception ex) {
					System.out.println("fail to remove");
				}
			},
			result -> {
			}
		);
	}

	@Override
	public boolean createPlannedMeal(PlannedMealModel plannedMeal) {
		String sqlQuery = "INSERT INTO PLANNEDMEAL (plannedTime, mealID, mealType) values (?, ?, ?)";

		try {
			ConnectDB.runPreparedStatement(
				sqlQuery,
				statement -> {
					try {
						statement.setTIMESTAMP(1, TIMESTAMP.of(plannedMeal.getPlannedTime()));
						statement.setInt(2, plannedMeal.getMeal().getID());
						statement.setString(3, plannedMeal.getMealType());
					} catch (Exception ex) {
						System.out.println("fail");
					}
				},
				result -> {
				}
			);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
