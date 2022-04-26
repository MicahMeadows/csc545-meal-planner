/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Item;

import Model.FridgeItemModel;
import Model.ItemModel;
import Model.RecipeItemModel;
import Repository.Nutrition.INutritionRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author micah
 */
public class TestItemRepository implements IItemRepository {
	final private INutritionRepository nutritionRepo;
	List<ItemModel> items = new ArrayList<>();
	List<RecipeItemModel> recipeItems = new ArrayList<>();
	List<FridgeItemModel> fridgeItems = new ArrayList<>();

	public TestItemRepository(INutritionRepository nutritionRepo){
		this.nutritionRepo = nutritionRepo;
		initItems();
	}

	private void initItems(){
		items.add(new ItemModel(0, "Bread", "Bun", nutritionRepo.getNutritionForItemId(0)));
		items.add(new ItemModel(1, "Veggie", "Tomatoe", nutritionRepo.getNutritionForItemId(1)));
		items.add(new ItemModel(2, "Meat", "Hamburger", nutritionRepo.getNutritionForItemId(2)));
		items.add(new ItemModel(3, "Veggie", "Onion", nutritionRepo.getNutritionForItemId(3)));
		items.add(new ItemModel(4, "Fruit", "Apple", nutritionRepo.getNutritionForItemId(4)));
		items.add(new ItemModel(5, "Seasoning", "Salt", nutritionRepo.getNutritionForItemId(5)));

		recipeItems.add(new RecipeItemModel(1, 0));
		recipeItems.add(new RecipeItemModel(1, 1));
		recipeItems.add(new RecipeItemModel(3, 2));
		recipeItems.add(new RecipeItemModel(3, 4));
		recipeItems.add(new RecipeItemModel(2, 3));
		recipeItems.add(new RecipeItemModel(2, 5));

		fridgeItems.add(new FridgeItemModel(0, 1));
		fridgeItems.add(new FridgeItemModel(0, 2));
		fridgeItems.add(new FridgeItemModel(0, 3));
		fridgeItems.add(new FridgeItemModel(0, 4));
		fridgeItems.add(new FridgeItemModel(1, 2));
		fridgeItems.add(new FridgeItemModel(1, 2));
	}

	@Override
	public List<ItemModel> getAllItems() {
		return items;
	}

	@Override
	public ItemModel getItemWithId(int ID) {
		return items.stream().filter(i -> ID == ID).findFirst().orElse(null);
	}

	@Override
	public List<ItemModel> getItemsForRecipeID(int recipeID) {
		List<RecipeItemModel> recipeItemsForRecipe =  recipeItems.stream().filter(i -> i.getRecipeID() == recipeID).collect(Collectors.toList());

		List<ItemModel> recipeIngredients = new ArrayList<>();
		for (RecipeItemModel recipeItem :recipeItemsForRecipe){
			ItemModel item = items.stream().filter(i -> i.getID() == recipeItem.getItemID()).findFirst().orElse(null);
			if (item != null) {
				recipeIngredients.add(item);
			}
		}
		return recipeIngredients;
	}

	@Override
	public List<ItemModel> getFridgeItems(int fridgeID) {
		List<FridgeItemModel> fridgeItemsForFridge = this.fridgeItems.stream()
			.filter(i -> i.getFridgeID() == fridgeID)
			.collect(Collectors.toList());

		List<Integer> fridgeItemIds = fridgeItemsForFridge.stream()
			.map(FridgeItemModel::getItemID)
			.collect(Collectors.toList());

		List<ItemModel> itemsInFridge = new ArrayList<>();
		for (int id :fridgeItemIds){
			ItemModel itemWithId = items.stream()
				.filter(i -> i.getID() == id)
				.findFirst()
				.orElse(null);
					
			if(itemWithId != null){
				itemsInFridge.add(itemWithId);
			}
		}
		return itemsInFridge;
	}

	@Override
	public void deleteFridgeItem(int fridgeID, int itemID) {
		FridgeItemModel item = this.fridgeItems.stream()
			.filter(i -> i.getFridgeID() == fridgeID && i.getItemID() == itemID)
			.findFirst()
			.orElse(null);

		if(item == null) return;

		fridgeItems.remove(item);
	}

	@Override
	public void addFridgeItem(int fridgeID, int itemID) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}
	
}
