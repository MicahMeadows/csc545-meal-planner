/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Repository.Item.IItemRepository;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author micah
 */
public class FridgePageModel {
	private final IItemRepository itemRepository;
	private final int fridgeID;

	private final String defaultGroupText = "Any";

	private List<ItemModel> fridgeItems;
	private ItemModel selectedItem = null;
	private String selectedGroup = defaultGroupText;

	public FridgePageModel(IItemRepository itemRepository, int fridgeID){
		this.itemRepository = itemRepository;
		this.fridgeID = fridgeID;
	}

	public String getDefaultGroupText(){
		return this.defaultGroupText;
	}

	public void updateFridgeItems(){
		this.fridgeItems = this.itemRepository.getFridgeItems(fridgeID);
	}

	public void updateSelectedGroup(){
		boolean groupExists = getItemCategories().stream()
			.anyMatch(i -> i.equals(this.selectedGroup));

		if (!groupExists){
			clearSelectedGroup();
		}
	}


	public void setSelectedGroup(String newGroup){
		this.selectedGroup = newGroup;
	}

	public void setSelectedItem(ItemModel newItem){
		this.selectedItem = newItem;
	}
	
	public void clearSelectedGroup(){
		setSelectedGroup(this.defaultGroupText);
	}

	public ItemModel getSelectedItem(){
		return this.selectedItem;
	}

	public String getSelectedGroup(){
		return this.selectedGroup;
	}

	public List<ItemModel> getFridgeItems(){
		if (selectedGroup == defaultGroupText)
			return this.fridgeItems;

		return this.fridgeItems.stream()
			.filter(i -> i.getGroup().toLowerCase().equals(selectedGroup.toLowerCase()))
			.collect(Collectors.toList());
	}

	public List<String> getItemCategories(){
		return fridgeItems.stream()
			.map(ItemModel::getGroup)
			.map(String::toLowerCase)
			.map(name -> name.substring(0, 1).toUpperCase() + name.substring(1))
			.distinct()
			.collect(Collectors.toList());
	}

	public void deleteItem(int itemID){
		itemRepository.deleteFridgeItem(fridgeID, itemID);
		updateFridgeItems();
		updateSelectedGroup();

	}

	public void addFridgeItem(ItemModel item){
		try {
			ItemModel createdItem = itemRepository.createItem(item);
			itemRepository.addFridgeItem(this.fridgeID, createdItem.getID());
			updateFridgeItems();
			System.out.println(itemRepository.getAllItems());

		} catch (Exception e){
			throw e;
		}

	}
}
