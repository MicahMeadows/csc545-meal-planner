/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author micah
 */
public class DayPlanModel {
	final private LocalDate date;
	private List<PlannedMealModel> plannedMeals;

	public DayPlanModel(LocalDate date, List<PlannedMealModel> plannedMeals){
		this.plannedMeals = plannedMeals;
		this.date = date;
	}

	public List<PlannedMealModel> getPlannedMeals(){
		return this.plannedMeals;
	}

	public String getDayName(){
		return date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US);
	}

	public String getDateString(){
		return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

	}

}
