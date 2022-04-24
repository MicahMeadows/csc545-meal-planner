package Controller;

import Model.RecipePageModel;
import View.RecipePageView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class RecipePageController {
	final private RecipePageModel model;
	final private RecipePageView view;

	public RecipePageController(RecipePageModel model, RecipePageView view){
		this.model = model;
		this.view = view;

		this.view.addNameSearchListener(new FilterDocumentListener());
		this.view.addCategorySearchListener(new FilterDocumentListener());
		this.view.addIngredientSearchListener(new FilterDocumentListener());
	}

	void updateRecipeList(){
		String nameFilter = view.getNameText();
		String categoryFilter = view.getCategoryText();
		String ingredientFilter = view.getIngredientText();
		model.updateRecipes(nameFilter, categoryFilter, ingredientFilter);
		view.setRecipeList(model.getRecipes());
		view.repaint();
	}


	class FilterDocumentListener implements DocumentListener {

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
}
