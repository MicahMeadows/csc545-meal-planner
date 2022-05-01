package Repository.Item;


import Model.FridgeItemModel;
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
	List<ItemModel> getItemsForRecipeID(int recipeID);
	List<ItemModel> getFridgeItems(int fridgeID);
	void removeFridgeItem(int fridgeID, int itemID);
	FridgeItemModel addFridgeItem(int fridgeID, int itemID);
	ItemModel createItem(ItemModel item);
	ItemModel getItemWithId(int id);
}
