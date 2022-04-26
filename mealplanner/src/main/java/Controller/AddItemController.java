/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.ItemModel;
import View.AddItemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;
import javax.swing.JFrame;

/**
 *
 * @author micah
 */
public class AddItemController {
	private AddItemView view;
	final private JFrame frame;
	final private Consumer<ItemModel> onSubmit;

	public AddItemController(JFrame frame, Consumer<ItemModel> onSubmit){
		this.onSubmit = onSubmit;
		this.frame = frame;
	}

	public void show(){
		this.view = new AddItemView(frame, true);
		this.view.setCancelButtonListener(new CancelListener());
		this.view.setSubmitButtonListener(new SubmitListener());
		this.view.setLocationRelativeTo(frame);
		this.view.setVisible(true);
	}

	class CancelListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			view.close();
		}
	}

	class SubmitListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			ItemModel newItem = view.getSubmittedItem();

			if (newItem == null) {
				view.displayErrorPopup("There was an error creating the item");
				return;
			}

			view.close();
			onSubmit.accept(newItem);
		}
	}
}
