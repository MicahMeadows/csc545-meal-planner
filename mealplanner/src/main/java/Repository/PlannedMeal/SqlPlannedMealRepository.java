/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.PlannedMeal;

import MealPlanner.ConnectDB;
import Model.MealModel;
import Model.PlannedMealModel;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

	}

	@Override
	public PlannedMealModel createPlannedMeal(PlannedMealModel plannedMeal) {
		String sqlQuery = "INSERT INTO PLANNEDMEAL (plannedTime, mealID, mealType) values (?, ?, ?)";


		PlannedMealModel[] newPlannedMeal = {null};

		ConnectDB.runPreparedStatement(
			sqlQuery,
			statement -> {
				try {
					statement.setTIMESTAMP(1, TIMESTAMP.of(LocalDateTime.now()));
					statement.setInt(2, 1);
					statement.setString(3, "obama time");
				} catch (Exception ex) {
					System.out.println("fail");
				}
			},
			result -> {
				System.out.println("");
			}
		);

		return newPlannedMeal[0];
	}
}
