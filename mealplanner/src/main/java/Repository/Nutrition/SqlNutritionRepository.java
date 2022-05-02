/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Nutrition;

import MealPlanner.ConnectDB;
import Model.NutritionModel;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Micah
 */
public class SqlNutritionRepository implements INutritionRepository {

	@Override
	public NutritionModel getNutritionForItemId(int ID) {
		String sqlQuery = "SELECT * FROM NUTRITION WHERE ITEMID = ?";
		NutritionModel[] nutrition = {null};

		ConnectDB.runPreparedStatement(
			sqlQuery,
			statement -> {

				try {
					statement.setInt(1, ID);
				} catch (SQLException ex) {
					Logger.getLogger(SqlNutritionRepository.class.getName()).log(Level.SEVERE, null, ex);
				}

			},
			result -> {
				try {
					while (result.next()) {

						int itemId = result.getInt("ITEMID");
						int calories = result.getInt("CALORIES");
						int fat = result.getInt("FAT");
						int sodium = result.getInt("SODIUM");
						int sugar = result.getInt("SUGAR");
						int protein = result.getInt("PROTEIN");

						nutrition[0] = new NutritionModel.Builder(itemId)
							.calories(calories)
							.fat(fat)
							.sodium(sodium)
							.sugar(sugar)
							.protein(protein)
							.build();
					}
				} catch (SQLException ex) {
					Logger.getLogger(SqlNutritionRepository.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		);

		return nutrition[0];
	}

	@Override
	public NutritionModel createNutrition(NutritionModel nutritionModel) {
		String sqlQuery = "INSERT INTO NUTRITION VALUES (?, ?, ?, ?, ?, ?)";

		try {
			ConnectDB.runPreparedStatement(
				sqlQuery,
				statement -> {
					try {
						statement.setInt(1, nutritionModel.getID());
						statement.setInt(2, nutritionModel.getCalories());
						statement.setInt(3, nutritionModel.getSugar());
						statement.setInt(4, nutritionModel.getProtein());
						statement.setInt(5, nutritionModel.getFat());
						statement.setInt(6, nutritionModel.getSodium());
					} catch (SQLException ex) {
						System.out.println("failed to add");
					}
				},
				result -> {
				});
		} catch (Exception e) {
			return null;
		}
		return nutritionModel;
	}

}
