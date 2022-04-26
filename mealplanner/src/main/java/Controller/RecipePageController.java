package Controller;

import Model.RecipeModel;
import Model.RecipePageModel;
import View.RecipePageView;
import javax.swing.JList;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;



public class RecipePageController {
	final private RecipePageModel model;
	final private RecipePageView view;

	public RecipePageController(RecipePageModel model, RecipePageView view){
		this.model = model;
		this.view = view;

		view.setFilterListeners(new FilterUpdateListener());
		view.setRecipeList(model.getAllRecipes());
		view.setRecipeListListener(new RecipeSelectedListener());
	}

	class RecipeSelectedListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent lse) {
			JList source = (JList)lse.getSource();
			RecipeModel recipe = (RecipeModel)source.getSelectedValue();
			view.setSelectedRecipe(recipe);
		}
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
