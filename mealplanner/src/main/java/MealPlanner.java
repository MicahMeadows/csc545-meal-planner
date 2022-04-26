
import Controller.RecipePageController;
import Model.RecipePageModel;
import Repository.Item.IItemRepository;
import Repository.Item.TestItemRepository;
import Repository.Nutrition.INutritionRepository;
import Repository.Nutrition.TestNutritionRepository;
import Repository.Recipe.IRecipeRepository;
import Repository.Recipe.TestRecipeRepository;
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
		final INutritionRepository nutritionRepository = new TestNutritionRepository();
		final IItemRepository itemRepository = new TestItemRepository(nutritionRepository);
		final IRecipeRepository recipeRepository = new TestRecipeRepository(itemRepository);
		
		// Models
		RecipePageModel recipePageModel = new RecipePageModel(recipeRepository);

		// Views
		MealPlannerNavigationView navigation = new MealPlannerNavigationView();
		RecipePageView recipePage = new RecipePageView();

		// Controllers
		RecipePageController recipePageController = new RecipePageController(recipePageModel, recipePage);

		navigation.setRecipePageView(recipePage);
		navigation.setVisible(true);

	}
	
}
