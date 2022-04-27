/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author micah
 */
public class MealModel {
	private final int ID;
	private final String name;

	public MealModel(int ID, String name){
		this.ID = ID;
		this.name = name;
	}

	public String getName() { return this.name; }
	public int getID() { return this.ID; }

	@Override
	public String toString() {
		return name;
	}

}
