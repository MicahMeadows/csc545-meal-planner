package Repository.Item;


import Model.ItemModel;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author micah
 */
public interface IItemRepository {
	List<ItemModel> getAllItems();
	ItemModel getItemWithId(int ID);
	List<ItemModel> getItemsForRecipeID(int recipeID);
	List<ItemModel> getFridgeItems(int fridgeID);
	void deleteFridgeItem(int fridgeID, int itemID);
	void addFridgeItem(int fridgeID, int itemID);
}
