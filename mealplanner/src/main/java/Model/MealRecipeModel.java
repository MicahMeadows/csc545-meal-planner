/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author micah
 */
public class MealRecipeModel {
	private final int mealID;
	private final int recipeID;

	public MealRecipeModel(int mealID, int recipeID){
		this.mealID = mealID;
		this.recipeID = recipeID;
	}

	public int getMealID(){
		return this.mealID;
	}

	public int getRecipeID(){
		return this.recipeID;
	}
}
