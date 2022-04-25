package Controller;

import Model.RecipePageModel;
import View.RecipePageView;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;



public class RecipePageController {
	final private RecipePageModel model;
	final private RecipePageView view;

	public RecipePageController(RecipePageModel model, RecipePageView view){
		this.model = model;
		this.view = view;

		view.setFilterListeners(new FilterUpdateListener());
		view.setRecipeList(model.getAllRecipes());
	}


	class FilterUpdateListener implements DocumentListener {
		@Override
		public void insertUpdate(DocumentEvent de) {
			updateRecipeList();
		}

		@Override
		public void removeUpdate(DocumentEvent de) {
			updateRecipeList();
		}

		@Override
		public void changedUpdate(DocumentEvent de) {
			updateRecipeList();
		}
	}

	private void updateRecipeList(){
		String nameFilter = view.getNameText();
		String categoryFilter = view.getCategoryText();
		String ingredientFilter = view.getIngredientText();

		view.setRecipeList(model.getFilteredRecipes(nameFilter, categoryFilter, ingredientFilter));
	}



}
