package Controller;

import Model.ItemModel;
import Model.NutritionModel;
import Model.RecipeModel;
import Model.RecipePageModel;
import View.ItemDetailsView;
import View.RecipePageView;
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
	final private RecipePageModel model;
	final private RecipePageView view;

	public RecipePageController(RecipePageModel model, RecipePageView view){
		this.model = model;
		this.view = view;

		view.setFilterListeners(new FilterUpdateListener());
		view.setRecipeList(model.getAllRecipes());
		view.setRecipeListListener(new RecipeSelectedListener());
		view.setIngredientListListener(new IngredientSelectedListener());
		view.setSelectedRecipe(null);
	}

	class IngredientSelectedListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent lse) {
			if (lse.getValueIsAdjusting()) return;

			JList source = (JList)lse.getSource();
			ItemModel item = (ItemModel)source.getSelectedValue();

			if (item == null) return;

			JFrame frame = (JFrame)SwingUtilities.getRoot(view);
			ItemDetailsView itemDetailDialog = new ItemDetailsView(item, frame, true);
			itemDetailDialog.setCloseDetailDialogListener(new CloseDetailDialogListener());
			itemDetailDialog.setLocationRelativeTo(frame);
			itemDetailDialog.setVisible(true);

			
		}

	}

	class CloseDetailDialogListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
//			JFrame dialog = (JFrame)ae.getSource();
			JDialog dialog = (JDialog)SwingUtilities.getRoot((JButton)ae.getSource());
			dialog.dispose();
			dialog.setVisible(false);
			
			view.clearSelectedIngredient();
		}
	}

	class RecipeSelectedListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent lse) {
			if (lse.getValueIsAdjusting()) return;

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
