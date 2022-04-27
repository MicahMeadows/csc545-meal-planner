/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repository.Meal;

import Model.MealModel;
import java.util.List;

/**
 *
 * @author micah
 */
public interface IMealRepository {
	public List<MealModel> getAllMeals();
	public MealModel getMealForID(int ID);
	
}
