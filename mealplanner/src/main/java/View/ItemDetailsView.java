/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package View;

import Model.ItemModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	public void setCloseDetailDialogListener(ActionListener listener){
		btnClose.addActionListener(listener);
	}

	private void setupDialog(ItemModel item) {
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

                lblItemName = new javax.swing.JLabel();
                lblItemCategory = new javax.swing.JLabel();
                panelNutrition = new javax.swing.JPanel();
                lblNutritionFacts = new javax.swing.JLabel();
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

                lblItemName.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
                lblItemName.setText("Test Name");

                lblItemCategory.setFont(new java.awt.Font("sansserif", 2, 14)); // NOI18N
                lblItemCategory.setText("Test Category");

                lblNutritionFacts.setText("Nutrition Facts:");

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
                                .addGroup(panelNutritionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelNutritionLayout.createSequentialGroup()
                                                .addGap(17, 17, 17)
                                                .addGroup(panelNutritionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(lblCalories)
                                                        .addComponent(lblSugar)
                                                        .addComponent(lblProtein)
                                                        .addComponent(lblFat)
                                                        .addComponent(lblSodium))
                                                .addGap(46, 46, 46)
                                                .addGroup(panelNutritionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(lblFatVal)
                                                        .addComponent(lblProteinVal)
                                                        .addComponent(lblSugarVal)
                                                        .addComponent(lblCaloriesVal)
                                                        .addComponent(lblSodiumVal)))
                                        .addGroup(panelNutritionLayout.createSequentialGroup()
                                                .addGap(35, 35, 35)
                                                .addComponent(lblNutritionFacts)))
                                .addContainerGap(27, Short.MAX_VALUE))
                );
                panelNutritionLayout.setVerticalGroup(
                        panelNutritionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelNutritionLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(lblNutritionFacts)
                                .addGap(18, 18, 18)
                                .addGroup(panelNutritionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblCalories)
                                        .addComponent(lblCaloriesVal))
                                .addGap(18, 18, 18)
                                .addGroup(panelNutritionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblSugar)
                                        .addComponent(lblSugarVal))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelNutritionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblProtein)
                                        .addComponent(lblProteinVal))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelNutritionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblFat)
                                        .addComponent(lblFatVal))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelNutritionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblSodium)
                                        .addComponent(lblSodiumVal))
                                .addContainerGap(14, Short.MAX_VALUE))
                );

                btnClose.setText("Close");

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(28, 28, 28)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(panelNutrition, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jSeparator1)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(48, 48, 48)
                                                .addComponent(lblItemName))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(71, 71, 71)
                                                .addComponent(btnClose))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(62, 62, 62)
                                                .addComponent(lblItemCategory)))
                                .addContainerGap(14, Short.MAX_VALUE))
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblItemName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblItemCategory)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelNutrition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnClose)
                                .addGap(32, 32, 32))
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents


        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btnClose;
        private javax.swing.JSeparator jSeparator1;
        private javax.swing.JLabel lblCalories;
        private javax.swing.JLabel lblCaloriesVal;
        private javax.swing.JLabel lblFat;
        private javax.swing.JLabel lblFatVal;
        private javax.swing.JLabel lblItemCategory;
        private javax.swing.JLabel lblItemName;
        private javax.swing.JLabel lblNutritionFacts;
        private javax.swing.JLabel lblProtein;
        private javax.swing.JLabel lblProteinVal;
        private javax.swing.JLabel lblSodium;
        private javax.swing.JLabel lblSodiumVal;
        private javax.swing.JLabel lblSugar;
        private javax.swing.JLabel lblSugarVal;
        private javax.swing.JPanel panelNutrition;
        // End of variables declaration//GEN-END:variables
}