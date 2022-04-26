
import Controller.RecipePageController;
import Model.RecipePageModel;
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
	final private static IRecipeRepository recipeRepository = new TestRecipeRepository();

	public static void main(String[] args){
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
