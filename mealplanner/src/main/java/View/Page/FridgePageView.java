/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.Page;

import Model.ItemModel;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author micah
 */
public class FridgePageView extends javax.swing.JPanel {

	/**
	 * Creates new form FridgePageView
	 */
	public FridgePageView() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the
	 * form. WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                lblItemsInFridge = new javax.swing.JLabel();
                paneFridgeItems = new javax.swing.JScrollPane();
                lstFridgeItems = new javax.swing.JList<>();
                cbGroups = new javax.swing.JComboBox<>();
                lblGroups = new javax.swing.JLabel();
                btnAdd = new javax.swing.JButton();
                btnDelete = new javax.swing.JButton();
                btnViewDetails = new javax.swing.JButton();

                lblItemsInFridge.setText("Items in your fridge");

                paneFridgeItems.setViewportView(lstFridgeItems);

                cbGroups.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Any", "Item 1", "Item 2", "Item 3", "Item 4" }));

                lblGroups.setText("Group");

                btnAdd.setText("Add Item");

                btnDelete.setText("Delete Selected");

                btnViewDetails.setText("View Details");

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnViewDetails)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnDelete)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnAdd))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblItemsInFridge)
                                                .addGap(396, 396, 396)
                                                .addComponent(lblGroups)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cbGroups, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(paneFridgeItems, javax.swing.GroupLayout.Alignment.LEADING))
                                .addContainerGap(20, Short.MAX_VALUE))
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblItemsInFridge)
                                        .addComponent(cbGroups, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblGroups))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(paneFridgeItems, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnAdd)
                                        .addComponent(btnDelete)
                                        .addComponent(btnViewDetails))
                                .addContainerGap(23, Short.MAX_VALUE))
                );
        }// </editor-fold>//GEN-END:initComponents

	public void displayErrorMessage(String message){
		JFrame frame = (JFrame)SwingUtilities.getRoot(this);
		JOptionPane.showMessageDialog(frame, message);
	}

	public void setFridgeItemsList(List<ItemModel> items){
		if (items == null) return;
				
		items = items.stream().distinct().collect(Collectors.toList());
		
		DefaultListModel<ItemModel> listModel = new DefaultListModel<>();
		items.stream().forEach(i -> listModel.addElement(i));
		lstFridgeItems.setModel(listModel);
	}

	public void setGroupOptions(String defaultText, List<String> groups, String selectedGroup){
		DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<>();

		comboModel.addElement(defaultText);
		groups.stream().forEach(g -> comboModel.addElement(g));

		cbGroups.setModel(comboModel);

		String groupWithName = groups.stream()
			.filter(g -> g.equals(selectedGroup))
			.findFirst()
			.orElse(null);

		if (groupWithName != null){
			cbGroups.setSelectedItem(groupWithName);
		}
	}


	public void setGroupChangeListener(ItemListener listener){
		cbGroups.addItemListener(listener);
	}

	public void setRemoveListener(ActionListener listener){
		btnDelete.addActionListener(listener);
	}

	public void setAddListener(ActionListener listener){
		btnAdd.addActionListener(listener);
	}

	public void setViewItemDetailsListener(ActionListener listener){
		btnViewDetails.addActionListener(listener);
	}

	public void setSelectedItemChangeListener(ListSelectionListener listener){
		lstFridgeItems.addListSelectionListener(listener);
	}


        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btnAdd;
        private javax.swing.JButton btnDelete;
        private javax.swing.JButton btnViewDetails;
        private javax.swing.JComboBox<String> cbGroups;
        private javax.swing.JLabel lblGroups;
        private javax.swing.JLabel lblItemsInFridge;
        private javax.swing.JList<ItemModel> lstFridgeItems;
        private javax.swing.JScrollPane paneFridgeItems;
        // End of variables declaration//GEN-END:variables
}
