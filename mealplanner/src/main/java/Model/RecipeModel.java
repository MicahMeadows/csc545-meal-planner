
package Model;

import java.util.List;

public class RecipeModel {
	final private int ID;
	final private String name;
	final private String instructions;
	final private String category;
	final private List<ItemModel> ingredients;

	public RecipeModel(int ID, String name, String instructions, String category, List<ItemModel> ingredients){
		this.ID = ID;
		this.name = name;
		this.instructions = instructions;
		this.category = category;
		this.ingredients = ingredients;
	}

	public int getID(){ return ID; }
	public String getName(){ return name; }
	public String getInstructions(){ return instructions; }
	public String getCategory(){ return category; }
	public List<ItemModel> getIngredients(){ return this.ingredients; }
}
