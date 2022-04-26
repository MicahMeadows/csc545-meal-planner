
import Controller.FridgePageController;
import Controller.RecipePageController;
import Model.FridgePageModel;
import Model.RecipePageModel;
import Repository.Item.IItemRepository;
import Repository.Item.TestItemRepository;
import Repository.Nutrition.INutritionRepository;
import Repository.Nutrition.TestNutritionRepository;
import Repository.Recipe.IRecipeRepository;
import Repository.Recipe.TestRecipeRepository;
import View.FridgePageView;
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
		
		// Models
		RecipePageModel recipePageModel = new RecipePageModel(recipeRepository);
		FridgePageModel fridgePageModel = new FridgePageModel(itemRepository, 0); // default 0 fridge value for testing

		// Views
		MealPlannerNavigationView navigation = new MealPlannerNavigationView();
		RecipePageView recipePage = new RecipePageView();
		FridgePageView fridgePage = new FridgePageView();

		// Controllers
		RecipePageController recipePageController = new RecipePageController(recipePageModel, recipePage);
		FridgePageController fridgePageController = new FridgePageController(fridgePageModel, fridgePage);

		// Add Tabs to navigation in order
		navigation.setRecipePageView(recipePage); // Tab1
		navigation.setFridgePageView(fridgePage); // Tab2

		navigation.setVisible(true);

	}
	
}
