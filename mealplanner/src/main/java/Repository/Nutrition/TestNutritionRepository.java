/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Nutrition;

import Model.NutritionModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author micah
 */
public class TestNutritionRepository implements INutritionRepository {
	List<NutritionModel> nutritions = new ArrayList<NutritionModel>();

	public TestNutritionRepository(){
		initNutritions();
	}

	private void initNutritions(){
		nutritions.add(new NutritionModel.Builder(0).fat(1).sodium(20).calories(711).sugar(3).protein(8).build());
		nutritions.add(new NutritionModel.Builder(1).fat(1).sodium(26).calories(121).sugar(99).protein(8).build());
		nutritions.add(new NutritionModel.Builder(2).fat(1).sodium(22).calories(20).sugar(3).protein(8).build());
		nutritions.add(new NutritionModel.Builder(3).fat(1).sodium(29).calories(201).sugar(7).protein(8).build());
		nutritions.add(new NutritionModel.Builder(4).fat(1).sodium(23).calories(1).sugar(9).protein(8).build());
		nutritions.add(new NutritionModel.Builder(5).fat(1).sodium(78).calories(212).sugar(2).protein(8).build());
		nutritions.add(new NutritionModel.Builder(6).fat(1).sodium(88).calories(28).sugar(33).protein(333).build());
		nutritions.add(new NutritionModel.Builder(7).fat(1).sodium(12).calories(221).sugar(32).protein(8).build());
		nutritions.add(new NutritionModel.Builder(8).fat(1).sodium(22).calories(221).sugar(3).protein(7).build());
	}

	@Override
	public NutritionModel getNutritionForItemId(int ID) {
		return nutritions.stream().filter(n -> n.getID() == ID).findFirst().orElse(null);
	}
	
}
