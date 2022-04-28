/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repository.PlannedMeal;

import Model.PlannedMealModel;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author micah
 */
public interface IPlannedMealRepository {
	List<PlannedMealModel> getPlannedMealsForDay(LocalDate date);
	void removePlannedMeal(int ID);
	PlannedMealModel createPlannedMeal(PlannedMealModel plannedMeal);
}
