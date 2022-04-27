/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Repository.PlannedMeal.IPlannedMealRepository;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author micah
 */
public class MealPlanViewModel {
	private final LocalDate todaysDate;
	private final DayOfWeek firstDayOfWeek;
	private final IPlannedMealRepository plannedMealRepository;

	private int weekOffset = 0;
	private PlannedMealModel selectedPlannedMeal;

	public MealPlanViewModel(IPlannedMealRepository plannedMealRepository){
		this.todaysDate = LocalDate.now();
		this.plannedMealRepository = plannedMealRepository;
		this.firstDayOfWeek = WeekFields.of(Locale.US).getFirstDayOfWeek();
	}

	public List<PlannedMealModel> getPlannedMealsForDay(LocalDate date){
		return plannedMealRepository.getPlannedMealsForDay(date);
	}

	public void setSelectedPlannedMeal(PlannedMealModel meal){
		this.selectedPlannedMeal = meal;
	}

	public PlannedMealModel getSelectedPlannedMeal(){
		return this.selectedPlannedMeal;
	}

	public void addToWeekOffset(int offset){
		this.weekOffset += offset;
	}

	public int getWeekOffset(){
		return this.weekOffset;
	}

	public LocalDate getStartDayDate(){
		return todaysDate.plusWeeks(weekOffset).with(TemporalAdjusters.previousOrSame(this.firstDayOfWeek));
	}
	
}
