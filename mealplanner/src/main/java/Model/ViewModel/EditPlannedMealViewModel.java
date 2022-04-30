/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.ViewModel;

import Model.MealModel;
import Model.PlannedMealModel;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *
 * @author micah
 */
public class EditPlannedMealViewModel {
	private String type;
	private LocalTime plannedTime;
	private MealModel selectedMeal;
	private LocalDate plannedDate;

	public EditPlannedMealViewModel(LocalDate plannedDate){
		this.plannedDate = plannedDate;
	}

	public String getPlannedMealType() { return this.type; }
	public LocalTime getPlannedTime() { return this.plannedTime; }
	public MealModel getSelectedMeal() { return this.selectedMeal; }

	public void setType(String newType) {
		this.type = newType;
	}

	public void setPlannedTime(String timeString, boolean isAm){
		LocalTime parsedTime = LocalTime.parse(timeString + ":00");

		if (!isAm) parsedTime.plusHours(12);

		this.plannedTime = parsedTime;
	}

	public void setSelectedMeal(MealModel selectedMeal) {
		this.selectedMeal = selectedMeal;
	}
	
	public PlannedMealModel getSubmittedItem(){
		try {
			if (type.trim().isEmpty()) throw new Exception();

			LocalDateTime planTime = LocalDateTime.of(plannedDate, plannedTime);
			PlannedMealModel newPlannedMeal = new PlannedMealModel(-1, planTime, selectedMeal.getID(), type, selectedMeal);

			return newPlannedMeal;

		} catch (Exception e){
			return null;
		}
	}
	
}
