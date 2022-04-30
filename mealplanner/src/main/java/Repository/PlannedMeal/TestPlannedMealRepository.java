/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.PlannedMeal;

import Model.PlannedMealModel;
import Repository.Item.IItemRepository;
import Repository.Meal.IMealRepository;
import Repository.Recipe.IRecipeRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 *
 * @author micah
 */
public class TestPlannedMealRepository implements IPlannedMealRepository {
	private final IRecipeRepository recipeRepository;
	private final IMealRepository mealRepository;

	private List<PlannedMealModel> plannedMeals;
	
	public TestPlannedMealRepository(
		IRecipeRepository recipeRepository,
		IMealRepository mealRepository
	){
		this.recipeRepository = recipeRepository;
		this.mealRepository = mealRepository;

		initialize();
	}
	
	void initialize(){
		this.plannedMeals = new ArrayList<>();
		plannedMeals.add(new PlannedMealModel(0, LocalDateTime.now().minusDays(1), 0, "Dinner", mealRepository.getMealForID(0), recipeRepository.getRecipesForMealID(0)));
		plannedMeals.add(new PlannedMealModel(1, LocalDateTime.now().minusDays(2), 1, "Lunch", mealRepository.getMealForID(1), recipeRepository.getRecipesForMealID(1)));
		plannedMeals.add(new PlannedMealModel(2, LocalDateTime.now(), 2, "Lunch", mealRepository.getMealForID(2), recipeRepository.getRecipesForMealID(2)));
		plannedMeals.add(new PlannedMealModel(3, LocalDateTime.now(), 3, "Dinner", mealRepository.getMealForID(3), recipeRepository.getRecipesForMealID(3)));
		plannedMeals.add(new PlannedMealModel(4, LocalDateTime.now().plusDays(1), 4, "Lunch", mealRepository.getMealForID(4), recipeRepository.getRecipesForMealID(4)));
	}
	
	@Override
	public List<PlannedMealModel> getPlannedMealsForDay(LocalDate date) {
		return this.plannedMeals.stream().filter(meal -> date.equals(meal.getPlannedTime().toLocalDate())).collect(Collectors.toList());
	}

	@Override
	public void removePlannedMeal(int ID) {
		PlannedMealModel mealToRemove = plannedMeals.stream()
			.filter(meal -> meal.getID() == ID)
			.findFirst().orElse(null);

		if (mealToRemove != null) {
			this.plannedMeals.remove(mealToRemove);
		}
	}

	@Override
	public PlannedMealModel createPlannedMeal(PlannedMealModel plannedMeal) {
		try {
			PlannedMealModel newPlannedMeal = new PlannedMealModel(
				new Random().nextInt(Integer.MAX_VALUE),
				plannedMeal.getPlannedTime(),
				plannedMeal.getMealID(),
				plannedMeal.getMealType(),
				plannedMeal.getMeal(),
				plannedMeal.getRecipes()
			);

			plannedMeals.add(newPlannedMeal);

			return newPlannedMeal;
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public List<PlannedMealModel> getPlannedMealsForRange(LocalDate startDate, LocalDate endDate) {
		List<PlannedMealModel> newPlannedMeals = new ArrayList<>();
		LocalDate movingDate = startDate;
		while (!movingDate.isAfter(endDate)) {
			newPlannedMeals.addAll(getPlannedMealsForDay(movingDate));
			movingDate = movingDate.plusDays(1);
		}
		return newPlannedMeals;
	}
}
