
import Controller.FridgePageController;
import Controller.MealPlanPageController;
import Controller.RecipePageController;
import Model.FridgeViewModel;
import Model.MealPlanViewModel;
import Model.RecipeViewModel;
import Repository.Item.IItemRepository;
import Repository.Item.TestItemRepository;
import Repository.Meal.IMealRepository;
import Repository.Meal.TestMealRepository;
import Repository.Nutrition.INutritionRepository;
import Repository.Nutrition.TestNutritionRepository;
import Repository.PlannedMeal.IPlannedMealRepository;
import Repository.PlannedMeal.TestPlannedMealRepository;
import Repository.Recipe.IRecipeRepository;
import Repository.Recipe.TestRecipeRepository;
import View.FridgePageView;
import View.MealPlanPageView;
import View.MealPlannerNavigationView;
import View.RecipePageView;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author micah
 */
public class MealPlanner {

	public static void main(String[] args){

		// setup repositories
		final INutritionRepository nutritionRepository = new TestNutritionRepository();
		final IItemRepository itemRepository = new TestItemRepository(nutritionRepository);
		final IRecipeRepository recipeRepository = new TestRecipeRepository(itemRepository);
		final IMealRepository mealRepository = new TestMealRepository();
		final IPlannedMealRepository plannedMealRepository = new TestPlannedMealRepository(recipeRepository, mealRepository);
		
		// Models
		RecipeViewModel recipePageModel = new RecipeViewModel(recipeRepository);
		FridgeViewModel fridgePageModel = new FridgeViewModel(itemRepository, 0); // default 0 fridge value for testing
		MealPlanViewModel mealPlanPageModel = new MealPlanViewModel(plannedMealRepository);

		// Views
		MealPlannerNavigationView navigation = new MealPlannerNavigationView();
		RecipePageView recipePage = new RecipePageView();
		FridgePageView fridgePage = new FridgePageView();
		MealPlanPageView mealPlanPage = new MealPlanPageView();

		// Controllers
		RecipePageController recipePageController = new RecipePageController(recipePageModel, recipePage);
		FridgePageController fridgePageController = new FridgePageController(fridgePageModel, fridgePage);
		MealPlanPageController mealPlanPageController = new MealPlanPageController(mealPlanPageModel, mealPlanPage);

		// Add Tabs to navigation in order
		navigation.setRecipePageView(recipePage); 	// Tab1
		navigation.setFridgePageView(fridgePage); 	// Tab2
		navigation.setMealPlanPageView(mealPlanPage); 	// Tab3

		navigation.setVisible(true);

	}
	
}
