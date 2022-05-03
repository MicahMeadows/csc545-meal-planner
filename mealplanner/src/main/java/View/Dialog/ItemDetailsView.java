/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package View.Dialog;

import Model.ItemModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author micah
 */
public class ItemDetailsView extends javax.swing.JDialog {


	/**
	 * Creates new form ItemDetailsView
	 */
	public ItemDetailsView(ItemModel item, java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
//		btnClose.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent ae) {
//				dispose();
//				setVisible(false);
//			}
//		});
		setupDialog(item);
	}
	
	public void close(){
		this.dispose();
		this.setVisible(false);
	}

	public void setCloseDetailDialogListener(ActionListener listener){
		btnClose.addActionListener(listener);
	}

	public void displayErrorPopup(String message){
		JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	private void setupDialog(ItemModel item) {
		if (item == null){
			displayErrorPopup("No nutrition data loaded.");
			this.close();
			return;
		}
		int calories = item.getNutrition().getCalories();
		int fat = item.getNutrition().getFat();
		int sugar = item.getNutrition().getSugar();
		int sodium = item.getNutrition().getSodium();
		int protein = item.getNutrition().getProtein();

		lblCaloriesVal.setText(Integer.toString(calories));
		lblProteinVal.setText(Integer.toString(protein));
		lblFatVal.setText(Integer.toString(fat));
		lblSodiumVal.setText(Integer.toString(sodium));
		lblSugarVal.setText(Integer.toString(sugar));
		lblItemName.setText(item.getName());
		lblItemCategory.setText(item.getGroup());
	}

	/**
	 * This method is called from within the constructor to initialize the
	 * form. WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jPanel1 = new javax.swing.JPanel();
                lblItemName = new javax.swing.JLabel();
                lblItemCategory = new javax.swing.JLabel();
                panelNutrition = new javax.swing.JPanel();
                lblCalories = new javax.swing.JLabel();
                lblSugar = new javax.swing.JLabel();
                lblProtein = new javax.swing.JLabel();
                lblFat = new javax.swing.JLabel();
                lblSodium = new javax.swing.JLabel();
                lblCaloriesVal = new javax.swing.JLabel();
                lblSugarVal = new javax.swing.JLabel();
                lblProteinVal = new javax.swing.JLabel();
                lblFatVal = new javax.swing.JLabel();
                lblSodiumVal = new javax.swing.JLabel();
                jSeparator1 = new javax.swing.JSeparator();
                btnClose = new javax.swing.JButton();

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                setUndecorated(true);
                setResizable(false);

                jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

                lblItemName.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
                lblItemName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lblItemName.setText("Test Name");

                lblItemCategory.setFont(new java.awt.Font("sansserif", 2, 14)); // NOI18N
                lblItemCategory.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lblItemCategory.setText("Test Category");

                lblCalories.setText("Calories");

                lblSugar.setText("Sugar");

                lblProtein.setText("Protein");

                lblFat.setText("Fat");

                lblSodium.setText("Sodium");

                lblCaloriesVal.setText("jLabel1");

                lblSugarVal.setText("jLabel2");

                lblProteinVal.setText("jLabel3");

                lblFatVal.setText("jLabel4");

                lblSodiumVal.setText("jLabel5");

                javax.swing.GroupLayout panelNutritionLayout = new javax.swing.GroupLayout(panelNutrition);
                panelNutrition.setLayout(panelNutritionLayout);
                panelNutritionLayout.setHorizontalGroup(
                        panelNutritionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelNutritionLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(panelNutritionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblCalories)
                                        .addComponent(lblSugar)
                                        .addComponent(lblProtein)
                                        .addComponent(lblFat)
                                        .addComponent(lblSodium))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelNutritionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblSugarVal)
                                        .addComponent(lblCaloriesVal)
                                        .addComponent(lblProteinVal)
                                        .addComponent(lblFatVal)
                                        .addComponent(lblSodiumVal))
                                .addContainerGap(19, Short.MAX_VALUE))
                );
                panelNutritionLayout.setVerticalGroup(
                        panelNutritionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelNutritionLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelNutritionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblCalories)
                                        .addComponent(lblCaloriesVal))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelNutritionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblSugar)
                                        .addComponent(lblSugarVal))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelNutritionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblProtein)
                                        .addComponent(lblProteinVal))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelNutritionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblFat)
                                        .addComponent(lblFatVal))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelNutritionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblSodium)
                                        .addComponent(lblSodiumVal))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                btnClose.setText("Close");

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGap(0, 29, Short.MAX_VALUE)
                                                .addComponent(panelNutrition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(33, 33, 33))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblItemName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(lblItemCategory, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jSeparator1))
                                                .addContainerGap())))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnClose)
                                .addGap(60, 60, 60))
                );
                jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblItemName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblItemCategory)
                                .addGap(12, 12, 12)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelNutrition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnClose)
                                .addContainerGap(24, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents


        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btnClose;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JSeparator jSeparator1;
        private javax.swing.JLabel lblCalories;
        private javax.swing.JLabel lblCaloriesVal;
        private javax.swing.JLabel lblFat;
        private javax.swing.JLabel lblFatVal;
        private javax.swing.JLabel lblItemCategory;
        private javax.swing.JLabel lblItemName;
        private javax.swing.JLabel lblProtein;
        private javax.swing.JLabel lblProteinVal;
        private javax.swing.JLabel lblSodium;
        private javax.swing.JLabel lblSodiumVal;
        private javax.swing.JLabel lblSugar;
        private javax.swing.JLabel lblSugarVal;
        private javax.swing.JPanel panelNutrition;
        // End of variables declaration//GEN-END:variables
}
