/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author micah
 */
public class PlannedMealModel {
	private final int ID;
	private final LocalDateTime plannedTime;
	private final int mealID;
	private final String mealType;
	private final MealModel meal;
	private final List<RecipeModel> mealRecipes;

	public PlannedMealModel(int ID, LocalDateTime plannedTime, int mealID, String mealType, MealModel meal, List<RecipeModel> mealRecipes){
		this.ID = ID;
		this.plannedTime = plannedTime;
		this.mealID = mealID;
		this.mealType = mealType;
		this.meal = meal;
		this.mealRecipes = mealRecipes;
	}
//
//	public void setMeal(MealModel meal){
//		this.meal = meal;
//	}
//
//	public void setRecipeList(List<RecipeModel> recipes){
//		this.mealRecipes = recipes;
//	}

	public int getID() { return ID; }
	public LocalDateTime getPlannedTime() { return plannedTime; }
	public int getMealID() { return mealID; }
	public String getMealType() { return mealType; }
	public MealModel getMeal() { return meal; }
	public List<RecipeModel> getRecipes() { return this.mealRecipes; }

	@Override
	public String toString() {
		return this.mealType;
	}
}
