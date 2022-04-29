package MealPlanner;

import Controller.FridgePageController;
import Controller.MealPlanController;
import Controller.RecipePageController;
import Model.FridgeViewModel;
import Model.MealPlanViewModel;
import Model.RecipeViewModel;
import View.Page.FridgePageView;
import View.Page.MealPlan.MealPlanView;
import View.MealPlannerNavigationView;
import View.Page.RecipePageView;

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

		DependencyContainer dependencyContainer = new DependencyContainer();
		
		// Models
		RecipeViewModel recipePageModel = new RecipeViewModel(dependencyContainer);
		FridgeViewModel fridgePageModel = new FridgeViewModel(dependencyContainer, 0); // default 0 fridge value for testing
		MealPlanViewModel mealPlanPageModel = new MealPlanViewModel(dependencyContainer);

		// Views
		MealPlannerNavigationView navigation = new MealPlannerNavigationView();
		RecipePageView recipePage = new RecipePageView();
		FridgePageView fridgePage = new FridgePageView();
		MealPlanView mealPlanPage = new MealPlanView();

		// Controllers
		RecipePageController recipePageController = new RecipePageController(recipePageModel, recipePage);
		FridgePageController fridgePageController = new FridgePageController(fridgePageModel, fridgePage);
		MealPlanController mealPlanPageController = new MealPlanController(dependencyContainer, mealPlanPageModel, mealPlanPage);

		// Add Tabs to navigation in order
		navigation.setRecipePageView(recipePage); 	// Tab1
		navigation.setFridgePageView(fridgePage); 	// Tab2
		navigation.setMealPlanPageView(mealPlanPage); 	// Tab3

		navigation.setVisible(true);

	}
	
}
