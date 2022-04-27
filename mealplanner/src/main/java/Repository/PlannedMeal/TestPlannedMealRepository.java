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

//		dinner = new PlannedMealModel(0, LocalDateTime.now().minusDays(1), 0, "Dinner");
//
//		PlannedMealModel dinner = new PlannedMealModel(0, LocalDateTime.now(), 1, "Dinner"); 
//		dinner.setMeal(new MealModel(1, "Burger Meal"));
//		List<RecipeModel> burgerMealRecipes = new ArrayList<>();
//		burgerMealRecipes.add(new RecipeModel(0, "Burger", "make burger", "American", new ArrayList<>()));
//		burgerMealRecipes.add(new RecipeModel(1, "Fries", "make fries", "American", new ArrayList<>()));
//		dinner.setRecipeList(burgerMealRecipes);
//		
//		PlannedMealModel lunch = new PlannedMealModel(1, LocalDateTime.now(), 1, "Lunch"); 
//		lunch.setMeal(new MealModel(1, "Asian Meal"));
//		List<RecipeModel> asianMealRecipes = new ArrayList<>();
//		asianMealRecipes.add(new RecipeModel(0, "Burger", "make burger", "American", new ArrayList<>()));
//		asianMealRecipes.add(new RecipeModel(1, "Fries", "make fries", "American", new ArrayList<>()));
//		lunch.setRecipeList(asianMealRecipes);
//
//		plannedMeals.add(lunch);
//		plannedMeals.add(dinner);

	}
	
	@Override
	public List<PlannedMealModel> getPlannedMealsForDay(LocalDate date) {
		return this.plannedMeals.stream().filter(meal -> date.equals(meal.getPlannedTime().toLocalDate())).collect(Collectors.toList());
	}
	
}
