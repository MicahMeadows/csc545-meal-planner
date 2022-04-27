/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.List;

/**
 *
 * @author micah
 */
public class MealModel {
	private final int ID;
	private final String name;
	private final List<RecipeModel> recipes;

	public MealModel(int ID, String name, List<RecipeModel> recipes){
		this.ID = ID;
		this.name = name;
		this.recipes = recipes;
	}

	public String getName() { return this.name; }
	public int getID() { return this.ID; }
	public List<RecipeModel> getRecipes() { return this.recipes; }

	@Override
	public String toString() {
		return name;
	}

}
