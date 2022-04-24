
package Model;


public class Recipe {
	private int ID;
	private String name;
	private String instructions;
	private String category;

	public Recipe(int ID, String name, String instructions, String category){
		this.ID = ID;
		this.name = name;
		this.instructions = instructions;
		this.category = category;
	}

	public void setID(int ID){
		this.ID = ID;
	}

	public void setName(String name){
		this.name = name;
	}

	public void setInstructions(String instructions){
		this.instructions = instructions;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public int getID(){ return ID; }
	public String getName(){ return name; }
	public String getInstructions(){ return instructions; }
	public String getCategory(){ return category; }
}
