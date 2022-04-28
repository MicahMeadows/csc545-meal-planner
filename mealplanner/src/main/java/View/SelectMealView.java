/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package View;

import Model.MealModel;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.event.DocumentListener;

/**
 *
 * @author micah
 */
public class SelectMealView extends javax.swing.JDialog {

	/**
	 * Creates new form SelectMealView
	 */
	public SelectMealView(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
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

                lblTitle = new javax.swing.JLabel();
                jScrollPane1 = new javax.swing.JScrollPane();
                lstMeals = new javax.swing.JList<>();
                tfSearch = new javax.swing.JTextField();
                lblSearch = new javax.swing.JLabel();
                btnSelect = new javax.swing.JButton();
                btnCancel = new javax.swing.JButton();
                btnAddMeal = new javax.swing.JButton();

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

                lblTitle.setText("Select meal");

                jScrollPane1.setViewportView(lstMeals);

                lblSearch.setText("Search");

                btnSelect.setText("Select");

                btnCancel.setText("Cancel");

                btnAddMeal.setText("Add");

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(265, 265, 265)
                                                .addComponent(lblTitle))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(32, 32, 32)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(lblSearch)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(btnAddMeal))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(btnCancel)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(btnSelect))
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(52, Short.MAX_VALUE))
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(lblTitle)
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblSearch)
                                        .addComponent(btnAddMeal))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnSelect)
                                        .addComponent(btnCancel))
                                .addGap(15, 15, 15))
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents

	/**
	 * @param args the command line arguments
	 */

	public void setCancelListener(ActionListener l) {
		btnCancel.addActionListener(l);
	}

	public void setSelectListener(ActionListener l) {
		btnSelect.addActionListener(l);
	}

	public void setAddMealListener(ActionListener l) {
		btnAddMeal.addActionListener(l);
	}

	public void setFilterListener(DocumentListener l){
		tfSearch.getDocument().addDocumentListener(l);
	}

	public String getSearchText(){
		return tfSearch.getText();
	}

	public void close(){
		this.dispose();
		this.setVisible(false);
	}

	public void setMealListData(List<MealModel> meals){
		MealModel lastSelected = lstMeals.getSelectedValue();

		DefaultListModel<MealModel> listModel = new DefaultListModel<>();
		meals.stream().forEach(meal -> listModel.addElement(meal));
		lstMeals.setModel(listModel);

		if (meals.contains(lastSelected)) {
			lstMeals.setSelectedValue(lastSelected, true);
		}

	}

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btnAddMeal;
        private javax.swing.JButton btnCancel;
        private javax.swing.JButton btnSelect;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JLabel lblSearch;
        private javax.swing.JLabel lblTitle;
        private javax.swing.JList<MealModel> lstMeals;
        private javax.swing.JTextField tfSearch;
        // End of variables declaration//GEN-END:variables
}
