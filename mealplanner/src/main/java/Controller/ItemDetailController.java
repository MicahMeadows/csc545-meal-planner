/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.ItemModel;
import View.ItemDetailsView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Function;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author micah
 */
public class ItemDetailController {
	final private Runnable onClose;
	final private JFrame frame;
	final private ItemModel item;

	public ItemDetailController(JFrame frame, ItemModel item, Runnable onClose){
		this.frame = frame;
		this.item = item;
		this.onClose = onClose;
	}

	public void show(){
		ItemDetailsView itemDetailDialog = new ItemDetailsView(item, frame, true);
		itemDetailDialog.setCloseDetailDialogListener(new CloseDetailDialogListener());
		itemDetailDialog.setLocationRelativeTo(frame);
		itemDetailDialog.setVisible(true);

	}
		
	private class CloseDetailDialogListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			JDialog dialog = (JDialog)SwingUtilities.getRoot((JButton)ae.getSource());
			dialog.dispose();
			dialog.setVisible(false);
			onClose.run();
		}
	}	
	
}
