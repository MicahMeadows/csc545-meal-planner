/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Item;

import MealPlanner.ConnectDB;
import MealPlanner.DependencyContainer;
import Model.FridgeItemModel;
import Model.ItemModel;
import Model.NutritionModel;
import Repository.Nutrition.INutritionRepository;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Micah
 */
public class SqlItemRepository implements IItemRepository {

	private final INutritionRepository nutritionRepository;

	public SqlItemRepository(DependencyContainer dependencyContainer) {
		this.nutritionRepository = dependencyContainer.getRepositoryFactory().getNutritionRepository();
	}

	List<ItemModel> cachedItems = new ArrayList<>();

	@Override
	public List<ItemModel> getAllItems() {
		if (cachedItems.isEmpty()) {
			updateItems();
		}
		return cachedItems;
	}

	private void updateItems() {
		String sqlQuery = "SELECT * FROM ITEM INNER JOIN NUTRITION ON ITEM.ID = NUTRITION.ITEMID";

		List<ItemModel> items = new ArrayList<>();
		ConnectDB.runStatement(sqlQuery, result -> {
			try {
				while (result.next()) {
					try {
						int itemId = result.getInt("ID");
						String group = result.getString("ITEMGROUP");
						String name = result.getString("ITEMNAME");
						int calories = result.getInt("CALORIES");
						int sugar = result.getInt("SUGAR");
						int fat = result.getInt("FAT");
						int sodium = result.getInt("SODIUM");
						int protein = result.getInt("PROTEIN");

						NutritionModel itemNutrition = new NutritionModel.Builder(itemId)
							.calories(calories)
							.fat(fat)
							.sodium(sodium)
							.sugar(sugar)
							.protein(protein)
							.build();

						ItemModel newItem = new ItemModel(itemId, group, name, itemNutrition);
						items.add(newItem);
					} catch (SQLException ex) {
						Logger.getLogger(SqlItemRepository.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			} catch (SQLException ex) {
				Logger.getLogger(SqlItemRepository.class.getName()).log(Level.SEVERE, null, ex);
			}
		});
		if (!items.isEmpty()) {
			cachedItems = items;
		}
	}

	@Override
	public List<ItemModel> getItemsForRecipeID(int recipeID) {
		String sqlQuery = "SELECT ITEMID FROM ITEM INNER JOIN RECIPEITEM ON ITEM.ID = RECIPEITEM.ITEMID WHERE RECIPEID = ?";

		List<Integer> itemIds = new ArrayList<>();
		ConnectDB.runPreparedStatement(sqlQuery, statement -> {
			try {
				statement.setInt(1, recipeID);
			} catch (SQLException ex) {
				Logger.getLogger(SqlItemRepository.class.getName()).log(Level.SEVERE, null, ex);
			}
		}, result -> {
			try {
				while (result.next()) {
					int itemId = Integer.parseInt(result.getString("ITEMID"));
					itemIds.add(itemId);
				}
			} catch (Exception e) {

			}

		});

		return itemIds.stream().map(itemid -> getItemWithId(itemid)).collect(Collectors.toList());
	}

	@Override
	public List<ItemModel> getFridgeItems(int fridgeID) {
		String sqlQuery = "SELECT * FROM FRIDGEITEM";

		List<FridgeItemModel> fridgeItems = new ArrayList<>();
		ConnectDB.runStatement(sqlQuery, result -> {
			try {
				while (result.next()) {
					int fridgeId = result.getInt("FRIDGEID");
					int itemId = result.getInt("ITEMID");
					FridgeItemModel newItem = new FridgeItemModel(fridgeId, itemId);
					fridgeItems.add(newItem);
				}
			} catch (SQLException e) {
				System.out.println("err");
			}
		});
		List<ItemModel> items = new ArrayList<>();
		List<Integer> ids = fridgeItems.stream().map(FridgeItemModel::getItemID).collect(Collectors.toList());
		for (int id : ids) {

			List<ItemModel> itemsWithId = getAllItems().stream().filter(item -> item.getID() == id).collect(Collectors.toList());
			items.addAll(itemsWithId);
		}
		return items;
	}

	@Override
	public void removeFridgeItem(int fridgeID, int itemID) {
		String sqlQuery = "DELETE FROM FRIDGEITEM WHERE FRIDGEID = ? AND ITEMID = ?";
		ConnectDB.runPreparedStatement(
			sqlQuery,
			statement -> {
				try {
					statement.setInt(1, fridgeID);
					statement.setInt(2, itemID);

				} catch (SQLException ex) {
					Logger.getLogger(SqlItemRepository.class.getName()).log(Level.SEVERE, null, ex);
				}

			},
			result -> {

			}
		);
	}

	@Override
	public FridgeItemModel addFridgeItem(int fridgeID, int itemID) {
		String sqlQuery = "INSERT INTO FRIDGEITEM VALUES(?, ?)";
		ConnectDB.runPreparedStatement(
			sqlQuery,
			statement -> {
				try {
					statement.setInt(1, fridgeID);
					statement.setInt(2, itemID);
				} catch (SQLException ex) {
					Logger.getLogger(SqlItemRepository.class.getName()).log(Level.SEVERE, null, ex);
				}
			},
			result -> {

			}
		);
		updateItems();
		return new FridgeItemModel(fridgeID, itemID);
	}

	private int getExistingItemId(String itemName, String itemGroup){
//		String sqlQuery = "SELECT * FROM ITEM WHERE ITEMGROUP = '"+itemGroup+"' AND ITEMNAME = '"+itemName+"'";
		String sqlQuery = "SELECT * FROM ITEM WHERE ITEMNAME = '"+itemName+"'";

		String[] resultId = { null };
		ConnectDB.runStatement(sqlQuery, result -> {
			try {
				while(result.next()){
					String id = result.getString("ID");
					resultId[0] = id;
				}
			} catch (SQLException ex) {
				Logger.getLogger(SqlItemRepository.class.getName()).log(Level.SEVERE, null, ex);
			}
		});
		return Integer.parseInt(resultId[0]);
	}
	
	@Override
	public ItemModel createItem(ItemModel item) {
		try {
			int existingId = getExistingItemId(item.getName(), item.getGroup());
			ItemModel existingItem = getItemWithId(existingId);
			return existingItem;
		} catch (Exception e){
			
		}
		
		String sqlQuery = "INSERT INTO ITEM (ITEMGROUP, ITEMNAME) VALUES(?, ?)";

		List<ItemModel> items = new ArrayList<>();
		try {
			ConnectDB.runPreparedStatement(
				sqlQuery,
				statement -> {
					try {
						statement.setString(1, item.getGroup());
						statement.setString(2, item.getName());
					} catch (SQLException e) {
						System.out.println("failed to insert item");
					}

				},
				result -> {
					ItemModel itemLike = getItemLike(item);
					NutritionModel newNutrition = item.getNutrition().copyWithNewId(itemLike.getID());
					nutritionRepository.createNutrition(newNutrition);
					itemLike.setNutrition(newNutrition);
					items.add(itemLike);
				}
			);
		} catch (Exception e) {
			return null;
		}

		if (items.isEmpty()) {
			return null;
		}
		return items.get(0);
	}

	private ItemModel getItemLike(ItemModel itemModel) {
		String sqlQuery = "SELECT * FROM ITEM WHERE ITEMGROUP = ? AND ITEMNAME = ?";

		List<ItemModel> items = new ArrayList<>();
		ConnectDB.runPreparedStatement(
			sqlQuery,
			statement -> {
				try {
					statement.setString(1, itemModel.getGroup());
					statement.setString(2, itemModel.getName());
				} catch (SQLException e) {
					System.out.println(" error setting up pre for getitemlike itemmodel");
				}

			},
			result -> {
				try {
					while (result.next()) {

						int itemId = result.getInt("ID");
						String groupName = result.getString("ITEMGROUP");
						String itemName = result.getString("ITEMNAME");

						ItemModel newItem = new ItemModel(itemId, groupName, itemName, null);
						items.add(newItem);

					}
				} catch (SQLException ex) {
					Logger.getLogger(SqlItemRepository.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		);
		if (items.isEmpty()) {
			return null;
		}
		return items.get(0);
	}

	@Override
	public ItemModel getItemWithId(int id) {
		if (cachedItems.isEmpty()) {
			updateItems();
		}
		return cachedItems.stream()
			.filter(item -> item.getID() == id)
			.findFirst()
			.orElse(null);
	}

}
