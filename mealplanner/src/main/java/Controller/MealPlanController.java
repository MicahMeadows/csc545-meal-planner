/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import MealPlanner.DependencyContainer;
import Model.DayPlanViewModel;
import Model.ItemModel;
import Model.MealPlanViewModel;
import Model.PlannedMealModel;
import Repository.Meal.IMealRepository;
import Repository.PlannedMeal.IPlannedMealRepository;
import Repository.Recipe.IRecipeRepository;
import View.DayPlanView;
import View.MealPlanView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author micah
 */
public class MealPlanController {
	final private DependencyContainer dependencyContainer;
	final private MealPlanViewModel model;
	final private MealPlanView view;


	public MealPlanController(DependencyContainer dependencyContainer, MealPlanViewModel model, MealPlanView view){
		this.dependencyContainer = dependencyContainer;

		this.view = view;
		this.model = model;

		initialize();
	}

	private void initialize(){
		view.setBackListener(new BackListener());
		view.setNextListener(new NextListener());
		view.setDeleteListener(new DeleteListener());
		view.setGenerateShoppingListListener(new GenerateShoppingListListener());

		setupDayPlans();
	}

	private DayPlanController createDayPlanController(int offset){
		LocalDate date = model.getStartDayDate().plusDays(offset);
		DayPlanController controller = dependencyContainer.getComponentFactory().createDayPlanController(date);
		controller.setMealSelectedListener(new MealSelectedListener());
		return controller;
	}

	private void setupDayPlans(){
		List<DayPlanController> weekPlanControllers = new ArrayList<>();
		for (int i = 0; i < 7; i++){
			weekPlanControllers.add(createDayPlanController(i));
		}
		model.setDayPlanControllers(weekPlanControllers);

		List<DayPlanView> weekPlanViews = weekPlanControllers.stream().map(DayPlanController::getView).collect(Collectors.toList());
		view.setupWeekView(weekPlanViews);
		
		
	}

	private void changeSelectedWeek(int offset){
		model.addToWeekOffset(offset);
		setupDayPlans();
	}



	private class MealSelectedListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent lse) {
			if (lse.getValueIsAdjusting()) return;

			JList source = (JList)lse.getSource();

			PlannedMealModel tappedMeal = (PlannedMealModel)source.getSelectedValue();

			if (tappedMeal == null) return;

			model.clearSelected();

			model.setSelectedPlannedMeal(tappedMeal);
			view.setSelectedPlannedMeal(model.getSelectedPlannedMeal());
		}
	}

	private class GenerateShoppingListListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			List<ItemModel> shoppingListItems = model.generateShoppingListItems(model.getStartDayDate(), model.getStartDayDate().plusDays(6));
			JFrame frame = (JFrame)SwingUtilities.getRoot(view);
			String formattedStartDate = model.getStartDayDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			new ShoppingListController(frame).showList(shoppingListItems, formattedStartDate);
		}

	}

	private class BackListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			changeSelectedWeek(-1);
		}
	}

	private class NextListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			changeSelectedWeek(1);
		}
	}

	private class DeleteListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			PlannedMealModel plannedMeal = model.getSelectedPlannedMeal();
			if (plannedMeal != null) {
				model.deletePlannedMeal(plannedMeal);
				setupDayPlans();
			}
		}

	}
	
}
