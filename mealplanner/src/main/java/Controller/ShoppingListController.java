/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.ItemModel;
import View.ShoppingListView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author micah
 */
public class ShoppingListController {
	private final ShoppingListView view;

	public ShoppingListController(JFrame frame){
		view = new ShoppingListView(frame, true);
		view.setLocationRelativeTo(frame);
		view.setCloseListener(new CloseListener());
	}

	public void showList(List<ItemModel> items, String dateString) {
		view.setItemList(items);
		view.setDateString(dateString);
		view.setVisible(true);
	}

	private class CloseListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			view.close();
		}
	}
		
	
}
