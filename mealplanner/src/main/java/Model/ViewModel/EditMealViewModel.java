/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.ViewModel;

import MealPlanner.DependencyContainer;
import Model.MealModel;
import Model.RecipeModel;
import Repository.Recipe.IRecipeRepository;
import Repository.Meal.IMealRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author micah
 */
public class EditMealViewModel {
	private final IRecipeRepository recipeRepository;
	private final IMealRepository mealRepository;
	private List<RecipeModel> mealRecipes = new ArrayList<>();
	private List<RecipeModel> allRecipes;
	private String mealName;

	public EditMealViewModel(DependencyContainer dependencyContainer){
		this.recipeRepository = dependencyContainer.getRepositoryFactory().getRecipeRepository();
		this.mealRepository = dependencyContainer.getRepositoryFactory().getMealRepository();
	}

	public MealModel getNewMealModel(){
		try {
			if (mealName.trim().isEmpty()) throw new Exception("Cant have empty name");
			
			MealModel createdMeal = mealRepository.createMeal(new MealModel(-1, mealName, mealRecipes));
			mealRecipes.stream().forEach(recipe -> mealRepository.createMealRecipe(createdMeal.getID(), recipe.getID()));
			return createdMeal; 
		} catch (Exception e){
			return null;
		}
	}

	public void setMealName(String name){
		this.mealName = name;
	}

	public void updateRecipes(){
		this.allRecipes = recipeRepository.getAllRecipes();
	}

	public void addMealRecipes(List<RecipeModel> recipes){
		recipes.stream().forEach(recipe -> {
			if (!mealRecipes.contains(recipe)){
				mealRecipes.add(recipe);
			}
		});
	}

	public void removeMealRecipes(List<RecipeModel> recipes){
		recipes.stream().forEach(recipe -> {
			if (mealRecipes.contains(recipe)){
				mealRecipes.remove(recipe);
			}		
		});
	}

	public List<RecipeModel> getFilteredRecipes(String filter){
		return this.allRecipes.stream()
			.filter(recipe -> recipe.getName().toLowerCase().contains(filter.toLowerCase()))
			.collect(Collectors.toList());
	}

	public List<RecipeModel> getMealRecipes(){
		return this.mealRecipes;
	}
	
}
