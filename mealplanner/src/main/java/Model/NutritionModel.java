/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author micah
 */
public class NutritionModel {
	final private int itemID;
	final private int calories;
	final private int sugar;
	final private int protein;
	final private int fat;
	final private int sodium;

	NutritionModel(Builder builder){
		this.itemID = builder.ID;
		this.calories = builder.calories;
		this.sugar = builder.sugar;
		this.protein = builder.protein;
		this.fat = builder.fat;
		this.sodium = builder.sodium;
	}

	public int getID(){return itemID;}
	public int getCalories(){return calories;}
	public int getSugar(){return sugar;}
	public int getProtein(){return protein;}
	public int getFat(){return fat;}
	public int getSodium(){return sodium;}

	public static class Builder {
	final private int ID;
	private int calories;
	private int sugar;
	private int protein;
	private int fat;
	private int sodium;	


		public Builder(int ID) {
			this.ID = ID;
		}

		public Builder calories(int calories) {
			this.calories = calories;
			return this;
		}

		public Builder sugar(int sugar) {
			this.sugar = sugar;
			return this;
		}

		public Builder protein(int protein) {
			this.protein = protein;
			return this;
		}

		public Builder fat(int fat) {
			this.fat = fat;
			return this;
		}

		public Builder sodium(int sodium) {
			this.sodium = sodium;
			return this;
		}

		public NutritionModel build() {
			NutritionModel nutrition = new NutritionModel(this);
			return nutrition;
		}
	}

}
