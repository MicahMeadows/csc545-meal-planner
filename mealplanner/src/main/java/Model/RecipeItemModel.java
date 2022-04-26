/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author micah
 */
public class RecipeItemModel {
	final private int recipeID;
	final private int itemID;

	public RecipeItemModel(int recipeID, int itemID) {
		this.recipeID = recipeID;
		this.itemID = itemID;
	}

	public int getRecipeID() {
		return this.recipeID; 
	}

	public int getItemID() {
		return this.itemID;
	}

}
