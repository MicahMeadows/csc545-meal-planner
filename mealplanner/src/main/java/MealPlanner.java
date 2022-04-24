
import Controller.RecipePageController;
import Model.RecipePageModel;
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
		MealPlannerNavigationView navigation = new MealPlannerNavigationView();
		RecipePageView recipePageView = navigation.getRecipePageView();
		RecipePageModel recipePageModel = new RecipePageModel();

		RecipePageController recipePageController = new RecipePageController(recipePageModel, recipePageView);

		navigation.main(args);
	}
	
}
