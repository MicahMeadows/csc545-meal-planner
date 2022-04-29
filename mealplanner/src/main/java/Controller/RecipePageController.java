package Controller;

import Model.ItemModel;
import Model.NutritionModel;
import Model.RecipeModel;
import Model.ViewModel.RecipeViewModel;
import View.Dialog.ItemDetailsView;
import View.Page.RecipePageView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;



public class RecipePageController {
	final private RecipeViewModel model;
	final private RecipePageView view;

	public RecipePageController(RecipeViewModel model, RecipePageView view){
		this.model = model;
		this.view = view;

		view.setFilterListeners(new FilterUpdateListener());
		view.setRecipeList(model.getAllRecipes());
		view.setRecipeListListener(new RecipeSelectedListener());
		view.setIngredientListListener(new IngredientSelectedListener());
		view.setSelectedRecipe(null);
	}

	private class IngredientSelectedListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent lse) {
			if (lse.getValueIsAdjusting()) return;

			JList source = (JList)lse.getSource();
			ItemModel item = (ItemModel)source.getSelectedValue();

			if (item == null) return;

			JFrame frame = (JFrame)SwingUtilities.getRoot(view);
			new ItemDetailController(frame, item, () -> {
				view.clearSelectedIngredient();
			}).show();
		}

	}


	private class RecipeSelectedListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent lse) {
			if (lse.getValueIsAdjusting()) return;

			JList source = (JList)lse.getSource();
			RecipeModel recipe = (RecipeModel)source.getSelectedValue();
			view.setSelectedRecipe(recipe);
		}
	}

	private class FilterUpdateListener implements DocumentListener {
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
