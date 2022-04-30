/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.PlannedMeal;

import MealPlanner.ConnectDB;
import Model.MealModel;
import Model.PlannedMealModel;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import oracle.sql.TIMESTAMP;

/**
 *
 * @author Micah
 */
public class SqlPlannedMealRepository implements IPlannedMealRepository {

	@Override
	public List<PlannedMealModel> getPlannedMealsForDay(LocalDate date) {
		// get all planned meals for a day
		final String sqlQuery = "SELECT * FROM PLANNEDMEAL";

		List<PlannedMealModel> plannedMeals = new ArrayList<>();
		ConnectDB.runStatement(sqlQuery, (results) -> {
			try {
				while (results.next()) {
					// get planned meals
					int plannedMealId = results.getInt("ID");
					TIMESTAMP plannedTime = results.getTIMESTAMP("PLANNEDTIME");
					int mealId = results.getInt("MEALID");
					String mealType = results.getString("MEALTYPE");

					MealModel mealForPlan = null; // TODO: Add meal repository interactions

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

			}
		});
		return plannedMeals;
		// then get meal for that planned meal
		// get the recipes for that planned meal
		// build the planned meal and return it
	}

	@Override
	public List<PlannedMealModel> getPlannedMealsForRange(LocalDate startDate, LocalDate endDate) {
		LocalDate movingDate = startDate;
		List<PlannedMealModel> plannedMeals = new ArrayList<>();
		while (!movingDate.isAfter(endDate)) {
			plannedMeals.addAll(getPlannedMealsForDay(movingDate));
			movingDate.plusDays(1);
		}
		return plannedMeals;
	}

	@Override
	public void removePlannedMeal(int ID) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	@Override
	public PlannedMealModel createPlannedMeal(PlannedMealModel plannedMeal) {
		String sqlQuery = "insert into PlannedMeal (plannedTime, mealID, mealType)\n"
			+ "values (TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), 1, 'Brunch w/ GMA')";

		PlannedMealModel[] newPlannedMeal = { null };
		ConnectDB.runStatement(sqlQuery, (result) -> {
			try {
				while (result.next()) {
					System.out.println("asdf");
				}
			} catch (Exception e) {
				newPlannedMeal[0] = null;
			}
		});
		return newPlannedMeal[0];
	}
}
