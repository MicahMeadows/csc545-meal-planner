/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package View;

import Model.MealModel;
import Model.RecipeModel;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentListener;

/**
 *
 * @author micah
 */
public class EditMealView extends javax.swing.JDialog {

	/**
	 * Creates new form EditMealView
	 */
	public EditMealView(java.awt.Frame parent, boolean modal) {
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

                lblWindowTitle = new javax.swing.JLabel();
                lblMealName = new javax.swing.JLabel();
                tfMealName = new javax.swing.JTextField();
                jScrollPane1 = new javax.swing.JScrollPane();
                lstAllRecipes = new javax.swing.JList<>();
                tfRecipeFilter = new javax.swing.JTextField();
                jScrollPane2 = new javax.swing.JScrollPane();
                lstMealRecipes = new javax.swing.JList<>();
                btnMoveRight = new javax.swing.JButton();
                btnMoveLeft = new javax.swing.JButton();
                lblMealRecipes = new javax.swing.JLabel();
                lblAllRecipes = new javax.swing.JLabel();
                btnSubmit = new javax.swing.JButton();
                btnCancel = new javax.swing.JButton();

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

                lblWindowTitle.setText("Edit Meal");

                lblMealName.setText("Meal Name");

                jScrollPane1.setViewportView(lstAllRecipes);

                jScrollPane2.setViewportView(lstMealRecipes);

                btnMoveRight.setText(">");

                btnMoveLeft.setText("<");

                lblMealRecipes.setText("Meal Recipes");

                lblAllRecipes.setText("All Recipes");

                btnSubmit.setText("Submit");

                btnCancel.setText("Cancel");

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(193, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(btnSubmit)
                                                .addGap(25, 25, 25))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(lblMealName)
                                                .addGap(18, 18, 18)
                                                .addComponent(tfMealName, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(146, 146, 146))))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(32, 32, 32)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblAllRecipes)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                                                        .addComponent(tfRecipeFilter))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(btnMoveRight, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(btnMoveLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addComponent(btnCancel))
                                                .addGap(36, 36, 36)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblMealRecipes)
                                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(248, 248, 248)
                                                .addComponent(lblWindowTitle)))
                                .addGap(0, 70, Short.MAX_VALUE))
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(lblWindowTitle)
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tfMealName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblMealName))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(115, 115, 115)
                                                .addComponent(btnMoveRight)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnMoveLeft))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addComponent(lblAllRecipes)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(tfRecipeFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblMealRecipes))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnSubmit)
                                        .addComponent(btnCancel))
                                .addGap(18, 18, 18))
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents

	public void close(){
		this.dispose();
		this.setVisible(false);
	}

	public String getMealNameText(){
		return tfMealName.getText();
	}

	public String getFilterText(){
		return tfRecipeFilter.getText();
	}

	public void setCloseListener(ActionListener l){
		btnCancel.addActionListener(l);
	}

	public void setSubmitListener(ActionListener l){
		btnSubmit.addActionListener(l);
	}

	public void setMoveLeftListener(ActionListener l){
		btnMoveLeft.addActionListener(l);
	}

	public void setMoveRightListener(ActionListener l){
		btnMoveRight.addActionListener(l);
	}

	public void setFilterListener(DocumentListener l){
		tfRecipeFilter.getDocument().addDocumentListener(l);
	}
	
	public List<RecipeModel> getSelectedPotentialRecipes(){
		return lstAllRecipes.getSelectedValuesList();
	}

	public List<RecipeModel> getSelectedChosenRecipes(){
		return lstMealRecipes.getSelectedValuesList();
	}

	public void setPotentialRecipes(List<RecipeModel> recipes){
		DefaultListModel<RecipeModel> listModel = new DefaultListModel<>();
		recipes.stream().forEach(recipe -> listModel.addElement(recipe));
		lstAllRecipes.setModel(listModel);
	}

	public void setMealRecipes(List<RecipeModel> recipes){
		DefaultListModel<RecipeModel> listModel = new DefaultListModel<>();
		recipes.stream().forEach(recipe -> listModel.addElement(recipe));
		lstMealRecipes.setModel(listModel);
	}

	public void displayError(String message){
		JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
	}

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btnCancel;
        private javax.swing.JButton btnMoveLeft;
        private javax.swing.JButton btnMoveRight;
        private javax.swing.JButton btnSubmit;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JScrollPane jScrollPane2;
        private javax.swing.JLabel lblAllRecipes;
        private javax.swing.JLabel lblMealName;
        private javax.swing.JLabel lblMealRecipes;
        private javax.swing.JLabel lblWindowTitle;
        private javax.swing.JList<RecipeModel> lstAllRecipes;
        private javax.swing.JList<RecipeModel> lstMealRecipes;
        private javax.swing.JTextField tfMealName;
        private javax.swing.JTextField tfRecipeFilter;
        // End of variables declaration//GEN-END:variables
}
