/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Meal;

import Model.MealModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author micah
 */
public class TestMealRepository implements IMealRepository {
	private List<MealModel> meals;

	public TestMealRepository(){
		initialize();
	}

	private void initialize() {
		meals = new ArrayList<>();
		meals.add(new MealModel(0, "Burger and fries"));
		meals.add(new MealModel(1, "Fish and chips"));
		meals.add(new MealModel(2, "Pizza and breadsticks"));
		meals.add(new MealModel(3, "Spaghetti and meatballs"));
		meals.add(new MealModel(4, "Fishsticks and fries"));
		meals.add(new MealModel(5, "Chicken alfredo"));
		meals.add(new MealModel(6, "Sushi and rice"));
	}

	@Override
	public List<MealModel> getAllMeals() {
		return this.meals;
	}

	@Override
	public MealModel getMealForID(int ID) {
		return this.meals.stream()
			.filter(meal -> meal.getID() == ID)
			.findAny()
			.orElse(null);
	}
	
}
