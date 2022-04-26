/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author micah
 */
public class FridgeItemModel {
	private final int fridgeID;
	private final int itemID;

	public FridgeItemModel(int fridgeID, int itemID){
		this.fridgeID = fridgeID;
		this.itemID = itemID;
	}

	public int getFridgeID(){
		return this.fridgeID;
	}

	public int getItemID(){
		return this.itemID;
	}
	
}
