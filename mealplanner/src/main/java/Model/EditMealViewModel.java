/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

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

	public EditMealViewModel(IMealRepository mealRepository, IRecipeRepository recipeRepository){
		this.recipeRepository = recipeRepository;
		this.mealRepository = mealRepository;
	}

	public MealModel getNewMealModel(){
		try {
			if (mealName.trim().isEmpty()) throw new Exception("Cant have empty name");
			return mealRepository.createMeal(new MealModel(-1, mealName, mealRecipes));
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
