/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.FridgePageModel;
import Model.ItemModel;
import View.FridgePageView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author micah
 */
public class FridgePageController {
	private final FridgePageView view;
	private final FridgePageModel model;

	public FridgePageController(FridgePageModel model, FridgePageView view){
		this.view = view;
		this.model = model;
		
		this.view.setRemoveListener(new RemoveItemListener());
		this.view.setAddListener(new AddItemListener());
		this.view.setSelectedItemChangeListener(new ItemSelectedListener());
		this.view.setGroupChangeListener(new GroupFilterChangedListener());

		initialize();
	}

	void initialize(){
		model.updateFridgeItems();
		updateFridgeList();
		updateGroupComboBox();
	}

	private void updateFridgeList(){
		view.setFridgeItemsList(model.getFridgeItems());
	}

	private void updateGroupComboBox(){
		view.setGroupOptions(model.getDefaultGroupText(), model.getItemCategories(), model.getSelectedGroup());
	}

	class GroupFilterChangedListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent ie) {
			if (ie.getStateChange() == ItemEvent.SELECTED){
				String groupName = (String)ie.getItem();
				model.setSelectedGroup(groupName);
			}
			if (ie.getStateChange() == ItemEvent.DESELECTED){
				model.clearSelectedGroup();
			}
			updateFridgeList();
		}


	}

	class ItemSelectedListener implements ListSelectionListener{
		@Override
		public void valueChanged(ListSelectionEvent lse) {
			if (lse.getValueIsAdjusting()) return;
			
			JList source = (JList)lse.getSource();
			ItemModel item = (ItemModel)source.getSelectedValue();
			
			if(item == null) return;

			model.setSelectedItem(item);
		}

	}

	class RemoveItemListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			if (model.getSelectedItem() == null) return;
			
			model.deleteItem(model.getSelectedItem().getID());
			updateFridgeList();
			updateGroupComboBox();
		}
	}

	class AddItemListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent ae) {
			System.out.println("do add stuff");
		}
	}

	
}
