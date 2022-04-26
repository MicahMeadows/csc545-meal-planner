/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repository.Nutrition;

import Model.NutritionModel;
import java.util.List;

/**
 *
 * @author micah
 */
public interface INutritionRepository {
	NutritionModel getNutritionForItemId(int ID);
}
