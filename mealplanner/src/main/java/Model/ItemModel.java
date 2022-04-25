package Model;

public class ItemModel {
	final private int ID;
	final private String group;
	final private String name;

	public ItemModel(int ID, String group, String name){
		this.ID = ID;
		this.group = group;
		this.name = name;
	}

	public int getID(){
		return this.ID;
	}

	public String getName(){
		return this.name;
	}

	public String getGroup(){
		return this.group;
	}
}
