/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.ItemModel;
import View.Dialog.ItemDetailsView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Function;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author micah
 */
public class ItemDetailController {
	final private Runnable onClose;
	final private JFrame frame;
	final private ItemModel item;
	final private ItemDetailsView view;

	public ItemDetailController(JFrame frame, ItemModel item, Runnable onClose){
		this.frame = frame;
		this.item = item;
		this.onClose = onClose;
		view = new ItemDetailsView(item, frame, true);
	}

	public void show(){
		view.setCloseDetailDialogListener(new CloseDetailDialogListener());
		view.setLocationRelativeTo(frame);
		view.setVisible(true);

	}
		
	private class CloseDetailDialogListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			view.close();
			onClose.run();
		}
	}	
	
}
