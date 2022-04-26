package Model;

/**
 *
 * @author micah
 */
public class ItemModel {
	final private int ID;
	final private String group;
	final private String name;
	final private NutritionModel nutrition;

	public ItemModel(int ID, String group, String name, NutritionModel nutrition){
		this.ID = ID;
		this.group = group;
		this.name = name;
		this.nutrition = nutrition;
	}

	public int getID(){
		return this.ID;
	}

	public NutritionModel getNutrition(){
		return this.nutrition;
	}

	public String getName(){
		return this.name;
	}

	public String getGroup(){
		return this.group;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
