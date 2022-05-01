/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.ViewModel;

import Controller.DayPlanController;
import MealPlanner.DependencyContainer;
import Model.ItemModel;
import Model.PlannedMealModel;
import Model.RecipeModel;
import Repository.Item.IItemRepository;
import Repository.PlannedMeal.IPlannedMealRepository;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author micah
 */
public class MealPlanViewModel {
	private final LocalDate todaysDate;
	private final DayOfWeek firstDayOfWeek;
	private final IPlannedMealRepository plannedMealRepository;
	private final IItemRepository itemRepository;

	private int weekOffset = 0;
	private PlannedMealModel selectedPlannedMeal;

	private	List<DayPlanController> dayPlanControllers;

	public MealPlanViewModel(DependencyContainer dependencyContainer){
		this.plannedMealRepository = dependencyContainer.getRepositoryFactory().getPlannedMealRepository();
		this.itemRepository = dependencyContainer.getRepositoryFactory().getItemRepository();

		this.firstDayOfWeek = WeekFields.of(Locale.US).getFirstDayOfWeek();
		this.todaysDate = LocalDate.now();
	}

	public void clearDayPlanControllers(){
		this.dayPlanControllers = new ArrayList<>();
	}

	public void setDayPlanControllers(List<DayPlanController> dayPlanControllers){
		this.dayPlanControllers = dayPlanControllers;
	}

	public void clearSelected(){
		this.dayPlanControllers.stream().forEach(dayPlan -> dayPlan.clearSelectedItem());
	}

	public List<ItemModel> generateShoppingListItems(LocalDate startDate, LocalDate endDate){
		// get fridge items
		List<ItemModel> fridgeItems = itemRepository.getFridgeItems(1);

		// get plannedMeals in range
		List<PlannedMealModel> plannedMeals = plannedMealRepository.getPlannedMealsForRange(startDate, endDate);

		// get recipes
		List<RecipeModel> recipes = new ArrayList<>();
		plannedMeals.forEach(meal -> recipes.addAll(meal.getRecipes()));

		// get recipe items
		List<ItemModel> itemsForRecipes = new ArrayList<>();
		recipes.forEach(recipe -> itemsForRecipes.addAll(recipe.getIngredients()));

		itemsForRecipes.removeAll(fridgeItems);

		return itemsForRecipes;
	}

	public List<PlannedMealModel> getPlannedMealsForDay(LocalDate date){
		return plannedMealRepository.getPlannedMealsForDay(date);
	}

	public void setSelectedPlannedMeal(PlannedMealModel meal){
		this.selectedPlannedMeal = meal;
	}

	public PlannedMealModel getSelectedPlannedMeal(){
		return this.selectedPlannedMeal;
	}

	public void addToWeekOffset(int offset){
		this.weekOffset += offset;
	}

	public int getWeekOffset(){
		return this.weekOffset;
	}

	public void deletePlannedMeal(PlannedMealModel plannedMeal) {
		plannedMealRepository.removePlannedMeal(plannedMeal.getID());
	}

	public LocalDate getStartDayDate(){
		return todaysDate.plusWeeks(weekOffset).with(TemporalAdjusters.previousOrSame(this.firstDayOfWeek));
	}
	
}
